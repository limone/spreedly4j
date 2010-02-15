package limone.service.spreedly;

import limone.model.spreedly.Subscription;
import limone.model.spreedly.Subscriptions;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreedlyPaymentProcessor {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String BASE_URL;
	private final String SPREEDLY_API_KEY;
	
	/**
	 * Generic constructor - tries to load the base URL and API key from system properties.
	 * Pass these in via -DBASE_URL and -DSPREEDLY_API_KEY.
	 * Not secure in any particular way, but sure is lazy.
	 */
	protected SpreedlyPaymentProcessor() {
		BASE_URL = System.getProperty("BASE_URL");
		SPREEDLY_API_KEY = System.getProperty("SPREEDLY_API_KEY");
		
		if (BASE_URL == null || BASE_URL.length() == 0) {
			throw new RuntimeException("Base URL was not specified.");
		}
		
		if (SPREEDLY_API_KEY == null || SPREEDLY_API_KEY.length() == 0) {
			throw new RuntimeException("Spreedly API key was not specified.");
		}
	}
	
	/**
	 * A more Spring-like constructor - pass in the parameters for the base URL and API key.
	 * @param BASE_URL
	 * @param SPREEDLY_API_KEY
	 */
	protected SpreedlyPaymentProcessor(String BASE_URL, String SPREEDLY_API_KEY) {
		if (BASE_URL == null || BASE_URL.length() == 0) {
			throw new RuntimeException("Base URL was not specified.");
		}
		
		if (SPREEDLY_API_KEY == null || SPREEDLY_API_KEY.length() == 0) {
			throw new RuntimeException("Spreedly API key was not specified.");
		}
		
		this.BASE_URL = BASE_URL;
		this.SPREEDLY_API_KEY = SPREEDLY_API_KEY;		
	}
	
	public Subscriptions getSubscriptions() {
		log.info("Creating Spreedly integration client.");
		SpreedlyIntegrationService spreedly = JAXRSClientFactory.create(BASE_URL, SpreedlyIntegrationService.class, SPREEDLY_API_KEY, null, null);
		
		log.info("Retrieving subscription plans via Spreedly.");
		Subscriptions subs = spreedly.getSubscriptionPlans();
		for (Subscription sub : subs.getSubscriptions()) {
			log.debug("Subscription: {}", sub);
		}
		
		return subs;
	}
}