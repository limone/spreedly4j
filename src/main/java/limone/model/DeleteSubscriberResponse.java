package limone.model;

public class DeleteSubscriberResponse extends AbstractResponse {
	public DeleteSubscriberResponse() {
		// empty
	}

	public DeleteSubscriberResponse(boolean okay, int statusCode, String statusMessage) {
		super(okay, statusCode, statusMessage);
	}
}