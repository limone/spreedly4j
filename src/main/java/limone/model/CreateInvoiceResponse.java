package limone.model;

import limone.model.spreedly.InvoiceResponse;

public class CreateInvoiceResponse extends AbstractResponse {
	private InvoiceResponse response;

	public CreateInvoiceResponse() {
		super();
	}

	public CreateInvoiceResponse(boolean okay, int statusCode, String statusMessage, InvoiceResponse response) {
		super(okay, statusCode, statusMessage);
		this.response = response;
	}

	public InvoiceResponse getResponse() {
		return response;
	}

	public void setResponse(InvoiceResponse response) {
		this.response = response;
	}
}