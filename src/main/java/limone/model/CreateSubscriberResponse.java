package limone.model;

import limone.model.spreedly.ResponseSubscriber;

public class CreateSubscriberResponse extends AbstractResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResponseSubscriber subscriber;
	
	public CreateSubscriberResponse() {
		// empty
	}
	
	public CreateSubscriberResponse(boolean okay, int statusCode, String statusMessage, ResponseSubscriber subscriber) {
		super(okay, statusCode, statusMessage);
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "CreateSubscriberResponse [subscriber=" + subscriber.toString() + "]";
	}

	public ResponseSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(ResponseSubscriber subscriber) {
		this.subscriber = subscriber;
	}
}