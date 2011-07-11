package limone.model;

public class GetSubscriberLinkResponse extends AbstractResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;

	public GetSubscriberLinkResponse() {
		super();
	}

	public GetSubscriberLinkResponse(boolean okay, int statusCode, String statusMessage, String url) {
		super(okay, statusCode, statusMessage);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}