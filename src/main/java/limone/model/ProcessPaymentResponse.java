package limone.model;

import limone.model.spreedly.PaymentResponse;

public class ProcessPaymentResponse extends AbstractResponse {
	private PaymentResponse response;
	
	public ProcessPaymentResponse() {
		// emtpy
	}

	public ProcessPaymentResponse(boolean okay, int statusCode, String statusMessage, PaymentResponse response) {
		super(okay, statusCode, statusMessage);
		this.response = response;
	}

	public PaymentResponse getResponse() {
		return response;
	}

	public void setResponse(PaymentResponse response) {
		this.response = response;
	}
}