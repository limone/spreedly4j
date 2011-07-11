package limone.model;

public class DeleteSubscriberResponse extends AbstractResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteSubscriberResponse() {
		// empty
	}

	public DeleteSubscriberResponse(boolean okay, int statusCode, String statusMessage) {
		super(okay, statusCode, statusMessage);
	}
}