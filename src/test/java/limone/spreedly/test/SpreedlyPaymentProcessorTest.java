package limone.spreedly.test;

import junit.framework.TestCase;
import limone.model.CreateInvoiceResponse;
import limone.model.CreateSubscriberResponse;
import limone.model.ProcessPaymentResponse;
import limone.model.SubscriptionResponse;
import limone.model.spreedly.CreditCard;
import limone.model.spreedly.InvoiceRequest;
import limone.model.spreedly.PaymentRequest;
import limone.model.spreedly.RequestSubscriber;
import limone.model.spreedly.Subscriber;
import limone.model.spreedly.Subscription;
import limone.service.spreedly.SpreedlyPaymentProcessor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreedlyPaymentProcessorTest extends TestCase {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private String BASE_URL;
	private String SPREEDLY_API_KEY;
	
	@Override
	protected void setUp() throws Exception {
		BASE_URL = System.getProperty("BASE_URL");
		SPREEDLY_API_KEY = System.getProperty("SPREEDLY_API_KEY");
	}

	@Test
	public void testSpreedlyPaymentProcessor() {
		new SpreedlyPaymentProcessor(BASE_URL, SPREEDLY_API_KEY);
	}

	@Test
	public void testSpreedlyPaymentProcessorStringString() {
		String baseUrl = System.getProperty("BASE_URL");
		String apiKey = System.getProperty("SPREEDLY_API_KEY");
		new SpreedlyPaymentProcessor(baseUrl, apiKey);
	}

	@Test
	public void testGetSubscriptions() {
		SpreedlyPaymentProcessor spp = new SpreedlyPaymentProcessor(BASE_URL, SPREEDLY_API_KEY);
		SubscriptionResponse sr = spp.getSubscriptions();
		assertNotNull("Subscription list was null.", sr);
		assertTrue("Subscription response was not okay: " + sr.getStatusCode() + " - " + sr.getStatusMessage(), sr.isOkay());
		assertNotNull("No subscriptions retrieved.", sr.getSubscriptions());
		assertNotNull("No subscriptions retrieved.", sr.getSubscriptions().getSubscriptions());
		assertFalse("No subscriptions retrieved.", sr.getSubscriptions().getSubscriptions().size() == 0);
		log.debug("Subscriptions: {}", sr.toString());
	}
	
	@Test
	public void testDeleteSubscriber() {
		SpreedlyPaymentProcessor spp = new SpreedlyPaymentProcessor(BASE_URL, SPREEDLY_API_KEY);
		spp.deleteSubscriber(0);
	}
	
	@Test
	public void testCreateSubscriber() {
		SpreedlyPaymentProcessor spp = new SpreedlyPaymentProcessor(BASE_URL, SPREEDLY_API_KEY);
		Subscriber subscriber = new Subscriber(0l, "test");
		CreateSubscriberResponse csr = spp.createSubscriber(subscriber);
		assertNotNull("Subscriber response was null.", csr);
		assertTrue("Create subscriber was not okay: " + csr.getStatusCode() + " - " + csr.getStatusMessage(), csr.isOkay());
		log.debug("Subscriber: {}", csr.toString());
	}
	
	@Test
	public void testCreateInvoice() {
		SpreedlyPaymentProcessor spp = new SpreedlyPaymentProcessor(BASE_URL, SPREEDLY_API_KEY);
		Subscription s = spp.getSubscriptions().getSubscriptions().getSubscriptions().get(0);
		RequestSubscriber subscriber = new RequestSubscriber(0, "Test", "test@limone.me");
		InvoiceRequest req = new InvoiceRequest(s.getId(), subscriber);
		CreateInvoiceResponse resp = spp.createInvoice(req);
		assertNotNull("Create invoice response was null.", resp);
		assertTrue("Create invoice response was not okay: " + resp.getStatusCode() + " - " + resp.getStatusMessage(), resp.isOkay());
	}
	
	@Test
	public void testProcessPayment() {
		SpreedlyPaymentProcessor spp = new SpreedlyPaymentProcessor(BASE_URL, SPREEDLY_API_KEY);
		Subscription s = spp.getSubscriptions().getSubscriptions().getSubscriptions().get(0);
		RequestSubscriber subscriber = new RequestSubscriber(0, "Test", "test@limone.me");
		InvoiceRequest req = new InvoiceRequest(s.getId(), subscriber);
		CreateInvoiceResponse resp = spp.createInvoice(req);
		assertNotNull("Create invoice response was null.", resp);
		assertTrue("Create invoice response was not okay: " + resp.getStatusCode() + " - " + resp.getStatusMessage(), resp.isOkay());
		
		CreditCard cc = new CreditCard("4222222222222", "visa", "234", "1", "2011", "Joe", "Bob");
		PaymentRequest pr = new PaymentRequest(cc);
		ProcessPaymentResponse ppr = spp.payInvoice(resp.getResponse().getToken(), pr);
		assertNotNull("Process payment response was null.", ppr);
		assertTrue("Could not process payment: " + ppr.getStatusCode() + " - " + ppr.getStatusMessage(), ppr.isOkay());
	}
}