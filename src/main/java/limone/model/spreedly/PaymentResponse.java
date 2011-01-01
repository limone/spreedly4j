package limone.model.spreedly;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="invoice")
public class PaymentResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=false,required=true)
	private Boolean closed;
	
	@XmlElement(name="created-at",nillable=false,required=true)
	private Date createdAt;
	
	@XmlElement(name="updated-at",nillable=false,required=true)
	private Date updatedAt;
	
	@XmlElement(nillable=false,required=true)
	private String token;
	
	@XmlElement(name="subscriber-store-credit",nillable=false,required=true)
	private String subscriberStoreCredit;
	
	@XmlElement(name="customer-id",nillable=false,required=true)
	private Long customerId;
	
	@XmlElement(nillable=false,required=true)
	private String price;
	
	@XmlElement(nillable=false,required=true)
	private Float amount;
	
	@XmlElement(name="currency-code",nillable=false,required=true)
	private String currencyCode;
	
	@XmlElement(name="line-items",nillable=false,required=true)
	private List<PaymentResponseLineItem> lineItems;
	
	@XmlElement(nillable=false,required=true)
	private ResponseSubscriber subscriber;
	
	public PaymentResponse() {
		// empty
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSubscriberStoreCredit() {
		return subscriberStoreCredit;
	}

	public void setSubscriberStoreCredit(String subscriberStoreCredit) {
		this.subscriberStoreCredit = subscriberStoreCredit;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public List<PaymentResponseLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<PaymentResponseLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public ResponseSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(ResponseSubscriber subscriber) {
		this.subscriber = subscriber;
	}
}