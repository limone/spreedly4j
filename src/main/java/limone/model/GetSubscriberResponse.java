package limone.model;

import limone.model.spreedly.ResponseSubscriber;

public class GetSubscriberResponse extends AbstractResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResponseSubscriber subscriber;
	
	public GetSubscriberResponse() {
		super();
	}

	public GetSubscriberResponse(boolean okay, int statusCode, String statusMessage, ResponseSubscriber subscriber) {
		super(okay, statusCode, statusMessage);
		this.subscriber = subscriber;
	}

	public ResponseSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(ResponseSubscriber subscriber) {
		this.subscriber = subscriber;
	}
}