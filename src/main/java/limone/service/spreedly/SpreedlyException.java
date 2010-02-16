package limone.service.spreedly;

public class SpreedlyException extends Exception {
	private final int statusCode;
	
	public SpreedlyException(int statusCode, String message, Throwable exception) {
		super(message, exception);
		this.statusCode = statusCode;
	}

	public SpreedlyException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}