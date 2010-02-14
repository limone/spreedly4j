package limone.spreedly.test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import junit.framework.TestCase;
import limone.model.spreedly.InvoiceRequest;
import limone.model.spreedly.InvoiceRequestSubscriber;
import limone.model.spreedly.InvoiceResponse;
import limone.service.spreedly.SpreedlyIntegrationService;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCreateInvoice extends TestCase {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private String BASE_URL;
	private String SPREEDLY_API_KEY;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		BASE_URL = System.getProperty("BASE_URL");
		SPREEDLY_API_KEY = System.getProperty("SPREEDLY_API_KEY");
	}
	
	@Test
	public void testGetSubscriptionPlans() {
		log.info("Creating Spreedly integration client.");
		SpreedlyIntegrationService spreedly = JAXRSClientFactory.create(BASE_URL, SpreedlyIntegrationService.class, SPREEDLY_API_KEY, null, null);
		
		log.info("Retrieving subscription plans via Spreedly.");
		String resp = spreedly.getSubscriptionPlans();
		
		log.debug("Response: {}", resp);
	}

	@Test
	public void testCreateInvoice() {
		log.debug("Creating subscriber.");
		InvoiceRequestSubscriber subscriber = new InvoiceRequestSubscriber(0l, "test", "test@limone.me");
		log.debug("Creating invoice.");
		InvoiceRequest request = new InvoiceRequest(4348l, subscriber);
		
		log.info("Creating Spreedly integration client.");
		SpreedlyIntegrationService spreedly = JAXRSClientFactory.create(BASE_URL, SpreedlyIntegrationService.class, SPREEDLY_API_KEY, null, null);
		
		log.info("Creating invoice via Spreedly.");
		InvoiceResponse resp = spreedly.createInvoice(request);
		
		log.debug("Response: {}", dump(resp));
	}
	
	public static String dump(Object o) {
		StringBuffer buffer = new StringBuffer();
		Class<?> oClass = o.getClass();
		if (oClass.isArray()) {
			buffer.append("[ ");
			for (int i = 0; i > Array.getLength(o); i++) {
				if (i < 0) buffer.append(",");
				Object value = Array.get(o, i);
				buffer.append(value.getClass().isArray() ? dump(value) : value.toString());
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
}