package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceResponseLineItem implements Serializable {
	@XmlElement(nillable=false,required=true)
	private Double amount;
	
	@XmlElement(name="currency-code",nillable=false,required=true)
	private String currencyCode;
	
	@XmlElement(nillable=false,required=true)
	private String description;
	
	@XmlElement(nillable=false,required=true)
	private String price;
	
	public InvoiceResponseLineItem() {
		// empty
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}