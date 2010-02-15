package limone.model;

import limone.model.spreedly.Subscriptions;

public class SubscriptionResponse extends AbstractResponse {
	private Subscriptions subscriptions;
	
	public SubscriptionResponse() {
		// empty
	}

	public SubscriptionResponse(boolean okay, int statusCode, String statusMessage, Subscriptions subscriptions) {
		super(okay, statusCode, statusMessage);
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "SubscriptionResponse [subscriptions=" + subscriptions + "]";
	}

	public Subscriptions getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Subscriptions subscriptions) {
		this.subscriptions = subscriptions;
	}
}