package limone.model;

import java.io.Serializable;

public abstract class AbstractResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean okay;
	private int statusCode;
	private String statusMessage;
	
	public AbstractResponse() {
		// empty
	}
	
	public AbstractResponse(boolean okay, int statusCode, String statusMessage) {
		this.okay = okay;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public boolean isOkay() {
		return okay;
	}

	public void setOkay(boolean okay) {
		this.okay = okay;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}