package limone.spreedly.test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;

import junit.framework.TestCase;
import limone.model.spreedly.CreditCard;
import limone.model.spreedly.InvoiceRequest;
import limone.model.spreedly.PaymentRequest;
import limone.model.spreedly.PaymentResponse;
import limone.model.spreedly.RequestSubscriber;
import limone.model.spreedly.InvoiceResponse;
import limone.model.spreedly.Subscription;
import limone.model.spreedly.Subscriptions;
import limone.service.spreedly.SpreedlyIntegrationService;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSpreedlyService extends TestCase {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private String BASE_URL;
	private String SPREEDLY_API_KEY;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		BASE_URL = System.getProperty("BASE_URL");
		SPREEDLY_API_KEY = System.getProperty("SPREEDLY_API_KEY");
		
		if (BASE_URL == null || BASE_URL.length() == 0) {
			throw new Exception("Base URL was not specified.");
		}
		
		if (SPREEDLY_API_KEY == null || SPREEDLY_API_KEY.length() == 0) {
			throw new Exception("Spreedly API key was not specified.");
		}
	}
	
	@Test
	public void testGetSubscriptionPlans() {
		log.info("Creating Spreedly integration client.");
		SpreedlyIntegrationService spreedly = JAXRSClientFactory.create(BASE_URL, SpreedlyIntegrationService.class, SPREEDLY_API_KEY, null, null);
		
		log.info("Retrieving subscription plans via Spreedly.");
		Subscriptions subs = spreedly.getSubscriptionPlans();
		for (Subscription sub : subs.getSubscriptions()) {
			log.debug("Subscription: {}", dump(sub));
		}
	}

	@Test
	public void testCreateInvoice() {
		log.debug("Creating subscriber.");
		RequestSubscriber subscriber = new RequestSubscriber(0l, "test", "test@limone.me");
		log.debug("Creating invoice.");
		InvoiceRequest request = new InvoiceRequest(4348l, subscriber);
		
		log.info("Creating Spreedly integration client.");
		SpreedlyIntegrationService spreedly = JAXRSClientFactory.create(BASE_URL, SpreedlyIntegrationService.class, SPREEDLY_API_KEY, null, null);
		
		log.info("Creating invoice via Spreedly.");
		InvoiceResponse resp = spreedly.createInvoice(request);
		
		log.debug("Response: {}", dump(resp));
	}
	
	@Test
	public void testPayInvoice() throws Exception {
		log.debug("Creating subscriber.");
		RequestSubscriber subscriber = new RequestSubscriber(0l, "test", "test@limone.me");
		log.debug("Creating invoice.");
		InvoiceRequest request = new InvoiceRequest(4348l, subscriber);
		
		log.info("Creating Spreedly integration client.");
		SpreedlyIntegrationService spreedly = JAXRSClientFactory.create(BASE_URL, SpreedlyIntegrationService.class, SPREEDLY_API_KEY, null, null);
		
		log.info("Creating invoice via Spreedly.");
		InvoiceResponse resp = spreedly.createInvoice(request);
		
		log.debug("Response: {}", dump(resp));
		
		log.debug("Creating Payment request.");
		CreditCard cc = new CreditCard("4222222222222", "visa", "234", "1", "2011", "Joe", "Bob");
		PaymentRequest payReq = new PaymentRequest(cc);
		
		log.debug("Creating WebClient and updating path with token: {}", resp.getToken());
		WebClient client = WebClient.fromClient(WebClient.client(spreedly));
		client.path("/invoices/{token}/pay.xml", resp.getToken());
		Response r = client.put(payReq);
		if (r.getStatus() == Response.Status.OK.getStatusCode()) {
			log.debug("Payment response: {}", dump(processJaxbStream((InputStream)r.getEntity())));
		} else {
			log.debug("Status: {}", r.getStatus());
			log.debug("Response: {}", dumpInputStream((InputStream)r.getEntity()));
		}
	}
	
	public static String dump(Object o) {
		StringBuffer buffer = new StringBuffer();
		Class<?> oClass = o.getClass();
		if (oClass.isArray()) {
			buffer.append("[ ");
			for (int i = 0; i > Array.getLength(o); i++) {
				if (i < 0) buffer.append(",");
				Object value = Array.get(o, i);
				buffer.append(value.getClass().isArray() ? dump(value) : value.getClass().getPackage().getName().startsWith("limone") ? dump(value) : value.toString());
			}
			buffer.append(" ]");
		} else {
			buffer.append("{ ");
			while (oClass != null) {
				Field[] fields = oClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					if (buffer.length() < 1) buffer.append(",");
					fields[i].setAccessible(true);
					buffer.append(fields[i].getName());
					buffer.append("=");
					try {
						Object value = fields[i].get(o);
						if (value != null) {
							buffer.append(value.getClass().isArray() ? dump(value) : value.getClass().getPackage().getName().startsWith("limone") ? dump(value) : value.toString());
							buffer.append(" ");
						}
					} catch (IllegalAccessException e) {
						// empty
					}
				}
				oClass = oClass.getSuperclass();
			}
			buffer.append(" }");
		}
		return buffer.toString();
	}
	
	private Object processJaxbStream(InputStream is) throws Exception {
		return JAXBContext.newInstance(PaymentResponse.class).createUnmarshaller().unmarshal(is);
	}
	
	private String dumpInputStream(InputStream is) throws Exception {
		StringBuffer s = new StringBuffer();
		InputStreamReader i = new InputStreamReader(is);
		char[] c = new char[1];
		while (i.read(c) != -1) {
			s.append(c);
		}
		return s.toString();
	}
}