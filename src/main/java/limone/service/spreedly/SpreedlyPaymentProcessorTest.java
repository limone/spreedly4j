package limone.service.spreedly;

import junit.framework.TestCase;
import limone.model.CreateSubscriberResponse;
import limone.model.SubscriptionResponse;
import limone.model.spreedly.Subscriber;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreedlyPaymentProcessorTest extends TestCase {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testSpreedlyPaymentProcessor() {
		new SpreedlyPaymentProcessor();
	}

	@Test
	public void testSpreedlyPaymentProcessorStringString() {
		String baseUrl = System.getProperty("BASE_URL");
		String apiKey = System.getProperty("SPREEDLY_API_KEY");
		new SpreedlyPaymentProcessor(baseUrl, apiKey);
	}

	@Test
	public void testGetSubscriptions() {
		SpreedlyPaymentProcessor spp = new SpreedlyPaymentProcessor();
		SubscriptionResponse sr = spp.getSubscriptions();
		assertNotNull("Subscription list was null.", sr);
		assertTrue("Subscription response was not okay: " + sr.getStatusCode() + " - " + sr.getStatusMessage(), sr.isOkay());
		assertNotNull("No subscriptions retrieved.", sr.getSubscriptions());
		assertNotNull("No subscriptions retrieved.", sr.getSubscriptions().getSubscriptions());
		assertFalse("No subscriptions retrieved.", sr.getSubscriptions().getSubscriptions().size() == 0);
		log.debug("Subscriptions: {}", sr.toString());
	}
	
	@Test
	public void testCreateSubscriber() {
		SpreedlyPaymentProcessor spp = new SpreedlyPaymentProcessor();
		Subscriber subscriber = new Subscriber(0l, "test");
		CreateSubscriberResponse csr = spp.createSubscriber(subscriber);
		assertNotNull("Subscriber response was null.", csr);
		assertTrue("Create subscriber was not okay: " + csr.getStatusCode() + " - " + csr.getStatusMessage(), csr.isOkay());
		log.debug("Subscriber: {}", csr.toString());
	}
}