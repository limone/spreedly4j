package limone.service.spreedly;

import java.util.Collections;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import limone.model.AbstractResponse;
import limone.model.CreateInvoiceResponse;
import limone.model.CreateSubscriberResponse;
import limone.model.DeleteSubscriberResponse;
import limone.model.GetSubscriberLinkResponse;
import limone.model.GetSubscriberResponse;
import limone.model.ProcessPaymentResponse;
import limone.model.SubscriptionResponse;
import limone.model.spreedly.InvoiceRequest;
import limone.model.spreedly.InvoiceResponse;
import limone.model.spreedly.PaymentRequest;
import limone.model.spreedly.PaymentResponse;
import limone.model.spreedly.ResponseSubscriber;
import limone.model.spreedly.Subscriber;
import limone.model.spreedly.Subscription;
import limone.model.spreedly.Subscriptions;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.ProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreedlyPaymentProcessor {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private String BASE_URL;
	private String SPREEDLY_API_KEY;
	private SpreedlyIntegrationService spreedly;
	private WebClient client;

	/**
	 * Only here for Spring.
	 */
	protected SpreedlyPaymentProcessor() {
		// empty
	}

	/**
	 * A more Spring-like constructor - pass in the parameters for the base URL
	 * and API key.
	 * 
	 * @param BASE_URL
	 * @param SPREEDLY_API_KEY
	 */
	public SpreedlyPaymentProcessor(String BASE_URL, String SPREEDLY_API_KEY) {
		if (BASE_URL == null || BASE_URL.length() == 0) { throw new IllegalArgumentException("Base URL was not specified."); }

		if (SPREEDLY_API_KEY == null || SPREEDLY_API_KEY.length() == 0) { throw new IllegalArgumentException("Spreedly API key was not specified."); }

		this.BASE_URL = BASE_URL;
		this.SPREEDLY_API_KEY = SPREEDLY_API_KEY;

		log.info("Creating Spreedly integration client.");
		init();
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
	
	public SubscriptionResponse getSubscription(String planName) {
		SubscriptionResponse sr = getSubscriptions();
		if (sr.isOkay()) {
			for (Subscription s : sr.getSubscriptions().getSubscriptions()) {
				if (s.getName().equalsIgnoreCase(planName)) {
					return new SubscriptionResponse(true, Status.OK.getStatusCode(), "Retrieved subscription.", new Subscriptions(Collections.singletonList(s)));
				}
			}
		}
		return new SubscriptionResponse(false, Status.NOT_FOUND.getStatusCode(), String.format("No plan by the name of %s was found.", planName), null);
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
			} else if (statusCode == 422) { 
				return new CreateSubscriberResponse(false, statusCode, "There was a validation error for the provided subscriber data.", null); 
			}
			return new CreateSubscriberResponse(false, statusCode, "An unknown error occured while trying to create the subscriber.", null);
		} catch (SpreedlyException se) {
			return generateErrorResponse(new CreateSubscriberResponse(), se);
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new CreateSubscriberResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new CreateSubscriberResponse(), ex);
		}
	}
	
	public DeleteSubscriberResponse deleteSubscriber(Integer id) {
		try {
			spreedly.deleteSubscriber(id);
			int statusCode = getStatusCode();
			if (statusCode == Status.OK.getStatusCode()) {
				return new DeleteSubscriberResponse(true, statusCode, String.format("Subscriber %d deleted.", id));
			}
			return new DeleteSubscriberResponse(false, statusCode, String.format("Subscriber %d  was not deleted.", id));
		} catch (SpreedlyException se) {
			return generateErrorResponse(new DeleteSubscriberResponse(), se);
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new DeleteSubscriberResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new DeleteSubscriberResponse(), ex);
		}
	}
	
	public CreateInvoiceResponse createInvoice(InvoiceRequest request) {
		try {
			InvoiceResponse resp = spreedly.createInvoice(request);
			int statusCode = getStatusCode();
			if (statusCode == Status.CREATED.getStatusCode()) {
				return new CreateInvoiceResponse(true, statusCode, "Created invoice.", resp);
			} else if (statusCode == Status.NOT_FOUND.getStatusCode()) {
				return new CreateInvoiceResponse(false, statusCode, "The subscription ID specified does not exist.", null);
			} else if (statusCode == Status.FORBIDDEN.getStatusCode()) {
				return new CreateInvoiceResponse(false, statusCode, "The subscription ID specified is disabled.", null);
			} else if (statusCode == 422) {
				return new CreateInvoiceResponse(false, statusCode, "The data provided in the invoice request could not be validated.", null);
			}
			return new CreateInvoiceResponse(false, statusCode, "An unknown error occurred while trying to create an invoice.", null);
		} catch (SpreedlyException se) {
			return generateErrorResponse(new CreateInvoiceResponse(), se);
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new CreateInvoiceResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new CreateInvoiceResponse(), ex);
		}
	}
	
	public ProcessPaymentResponse payInvoice(String token, PaymentRequest request) {
		try {
			PaymentResponse resp = spreedly.processPayment(token, request);
			int statusCode = getStatusCode();
			if (statusCode == Status.OK.getStatusCode()) {
				return new ProcessPaymentResponse(true, statusCode, "Processed payment.", resp);
			} else if (statusCode == Status.NOT_FOUND.getStatusCode()) {
				return new ProcessPaymentResponse(false, statusCode, "No invoice with the specified token could be found.", null);
			} else if (statusCode == Status.FORBIDDEN.getStatusCode()) {
				return new ProcessPaymentResponse(false, statusCode, "Invoice has already been paid.", null);
			} else if (statusCode == 422) {
				return new ProcessPaymentResponse(false, statusCode, "Payment request fails verification.", null);
			} else if (statusCode == 504) {
				return new ProcessPaymentResponse(false, statusCode, "The payment gateway timed out while trying to process the invoice.", null);
			}
			return new ProcessPaymentResponse(false, statusCode, "An unknown error occurred while trying to process a payment request.", null);
		} catch (SpreedlyException se) {
			return generateErrorResponse(new ProcessPaymentResponse(), se);
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new ProcessPaymentResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new ProcessPaymentResponse(), ex);
		}
	}
	
	public GetSubscriberResponse getSubscriber(Long subscriberId) {
		try {
			ResponseSubscriber resp = spreedly.getSubscriber(subscriberId);
			int statusCode = getStatusCode();
			if (statusCode == -1 || statusCode == Status.OK.getStatusCode()) {
				return new GetSubscriberResponse(true, statusCode, "Retrieved subscriber details.", resp);
			} else if (statusCode == Status.NOT_FOUND.getStatusCode()) {
				return new GetSubscriberResponse(false, statusCode, "No subscriber with the specified ID could be found.", null);
			}
			return new GetSubscriberResponse(false, statusCode, "An unknown error occurred while trying to retrieve subscriber information.", null);
		} catch (SpreedlyException se) {
			return generateErrorResponse(new GetSubscriberResponse(), se);
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new GetSubscriberResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new GetSubscriberResponse(), ex);
		}
	}
	
	public GetSubscriberLinkResponse getSubscriberLink(Subscriber s, Integer planId) {
		try {
			ResponseSubscriber rs = null;
			
			// Do they exist?
			GetSubscriberResponse gsr = getSubscriber(s.getCustomerId());
			if (gsr.isOkay()) {
				rs = gsr.getSubscriber();
			} else {
				CreateSubscriberResponse csr = createSubscriber(s);
				if (csr.isOkay()) {
					rs = csr.getSubscriber();
				} else {
					log.error("Could not create subscriber: {} - {}", csr.getStatusCode(), csr.getStatusMessage());
					return new GetSubscriberLinkResponse(false, csr.getStatusCode(), csr.getStatusMessage(), null);
				}
			}
			
			// They exist, get the link
			String remoteUrl = BASE_URL.replace("/api/v4", "");
			return new GetSubscriberLinkResponse(true, Status.OK.getStatusCode(), "Created link.", String.format("%s/subscribers/%d/%s/subscribe/%d", remoteUrl, rs.getCustomerId(), rs.getToken(), planId));
		} catch (WebApplicationException wae) {
			return generateErrorResponse(new GetSubscriberLinkResponse(), wae);
		} catch (Exception ex) {
			return generateErrorResponse(new GetSubscriberLinkResponse(), ex);
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
		if (ex instanceof SpreedlyException) {
			log.error("Spreedly exception.", ex);
			SpreedlyException se = (SpreedlyException)ex;
			ar.setOkay(false);
			ar.setStatusCode(se.getStatusCode());
			ar.setStatusMessage(se.getMessage());
			return ar;
		}
		
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

	private void init() {
		JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
		bean.setServiceClass(SpreedlyIntegrationService.class);
		bean.setAddress(BASE_URL);
		bean.setUsername(SPREEDLY_API_KEY);
		bean.setProvider(new SpreedlyResponseExceptionMapper());
		spreedly = bean.create(SpreedlyIntegrationService.class, new Object[]{});
		client = WebClient.fromClient(WebClient.client(spreedly));
		WebClient.getConfig(client).getInInterceptors().add(new LoggingInInterceptor());
		WebClient.getConfig(client).getOutInterceptors().add(new LoggingOutInterceptor());
		ProviderFactory.getSharedInstance().createResponseExceptionMapper(WebApplicationException.class);
	}
}