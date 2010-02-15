package limone.service.spreedly;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import limone.model.AbstractResponse;
import limone.model.CreateSubscriberResponse;
import limone.model.SubscriptionResponse;
import limone.model.spreedly.ResponseSubscriber;
import limone.model.spreedly.Subscriber;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreedlyPaymentProcessor {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String BASE_URL;
	private final String SPREEDLY_API_KEY;
	private SpreedlyIntegrationService spreedly;
	private WebClient client;

	/**
	 * Generic constructor - tries to load the base URL and API key from system
	 * properties. Pass these in via -DBASE_URL and -DSPREEDLY_API_KEY. Not
	 * secure in any particular way, but sure is lazy.
	 */
	protected SpreedlyPaymentProcessor() {
		BASE_URL = System.getProperty("BASE_URL");
		SPREEDLY_API_KEY = System.getProperty("SPREEDLY_API_KEY");

		if (BASE_URL == null || BASE_URL.length() == 0) { throw new IllegalArgumentException("Base URL was not specified."); }

		if (SPREEDLY_API_KEY == null || SPREEDLY_API_KEY.length() == 0) { throw new IllegalArgumentException("Spreedly API key was not specified."); }

		log.info("Creating Spreedly integration client.");
		init(BASE_URL, SPREEDLY_API_KEY);
	}

	/**
	 * A more Spring-like constructor - pass in the parameters for the base URL
	 * and API key.
	 * 
	 * @param BASE_URL
	 * @param SPREEDLY_API_KEY
	 */
	protected SpreedlyPaymentProcessor(String BASE_URL, String SPREEDLY_API_KEY) {
		if (BASE_URL == null || BASE_URL.length() == 0) { throw new IllegalArgumentException("Base URL was not specified."); }

		if (SPREEDLY_API_KEY == null || SPREEDLY_API_KEY.length() == 0) { throw new IllegalArgumentException("Spreedly API key was not specified."); }

		this.BASE_URL = BASE_URL;
		this.SPREEDLY_API_KEY = SPREEDLY_API_KEY;

		log.info("Creating Spreedly integration client.");
		init(BASE_URL, SPREEDLY_API_KEY);
	}

	/**
	 * Retrieve a list of subscriptions.
	 * 
	 * @return SubscriptionResponse Contains the list of the subscriptions, or an
	 *         error code if there was a problem.
	 */
	public SubscriptionResponse getSubscriptions() {
		log.info("Retrieving subscription plans from Spreedly.");
		try {
			return new SubscriptionResponse(true, 0, null, spreedly.getSubscriptionPlans());
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new SubscriptionResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new SubscriptionResponse(), ex);
		}
	}

	/**
	 * Create a subscriber in Spreedly.
	 * 
	 * @param subscriber
	 *           The Subscriber to create.
	 * @return CreateSubscriberResponse Populated with the returned
	 *         ResponseSubscriber if everything went okay. The status message
	 *         will contain a link to the Subscriber in Spreedly's system.
	 */
	public CreateSubscriberResponse createSubscriber(Subscriber subscriber) {
		try {
			ResponseSubscriber rs = spreedly.createSubscriber(subscriber);
			int statusCode = getStatusCode();
			if (statusCode == Status.CREATED.getStatusCode()) {
				return new CreateSubscriberResponse(true, statusCode, getHeader("Location"), rs);
			} else if (statusCode == Status.FORBIDDEN.getStatusCode()) {
				return new CreateSubscriberResponse(false, statusCode, "No customer ID was specified, or a customer with that ID already exists.", null);
			} else if (statusCode == 422) { return new CreateSubscriberResponse(false, statusCode, "There was a validation error for the provided subscriber data.", null); }
			return new CreateSubscriberResponse(false, statusCode, "An unknown error occured while trying to create the subscriber.", null);
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new CreateSubscriberResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new CreateSubscriberResponse(), ex);
		}
	}

	private int getStatusCode() {
		if (client == null || client.getResponse() == null) { return -1; }

		return client.getResponse().getStatus();
	}

	private String getHeader(String header) {
		if (client == null || client.getHeaders() == null) { return ""; }

		return client.getHeaders().getFirst(header);
	}

	private <T extends AbstractResponse> T generateErrorResponse(T ar, Exception ex) {
		int statusCode = getStatusCode();

		StringBuffer statusMessage = new StringBuffer();
		if (statusCode == -1) {
			statusMessage.append("An unknown error occured.");
		} else {
			final Status internalStatus = Response.Status.fromStatusCode(statusCode);
			if (internalStatus == null) {
				statusMessage.append("Unknown status code specified.");
			} else {
				statusMessage.append(internalStatus.getReasonPhrase());
			}
		}

		if (ex != null) {
			log.debug("Error in Spreedly communication.", ex);
			statusMessage.append(" Exception information: ").append(ex.getMessage()).append(" - ").append(ex.getClass().getSimpleName());
			final Throwable nex = ex.getCause();
			if (nex != null) {
				statusMessage.append(" Nested exception information: ").append(nex.getMessage()).append(" - ").append(nex.getClass().getSimpleName());
			}
		}

		log.warn(String.format("Could not create subscriber: %d - %s", statusCode, statusMessage));
		ar.setOkay(false);
		ar.setStatusCode(statusCode);
		ar.setStatusMessage(statusMessage.toString());
		return ar;
	}

	private void init(String baseAddress, String apiKey) {
		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		bean.setServiceClass(SpreedlyIntegrationService.class);
		bean.setAddress(baseAddress);
		bean.setUsername(apiKey);
		bean.setProvider(new SpreedlyResponseExceptionMapper<Throwable>());
		spreedly = bean.create(SpreedlyIntegrationService.class, new Object[]{});
		client = WebClient.fromClient(WebClient.client(spreedly));
		WebClient.getConfig(client).getInInterceptors().add(new LoggingInInterceptor());
		WebClient.getConfig(client).getOutInterceptors().add(new LoggingOutInterceptor());
	}
}