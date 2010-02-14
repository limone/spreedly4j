package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="line-item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseLineItem implements Serializable {
	@XmlElement(nillable=false,required=true)
	private Float amount;
	
	@XmlElement(name="currency-code",nillable=false,required=true)
	private String currencyCode;
	
	@XmlElement(nillable=false,required=true)
	private String description;
	
	@XmlElement(nillable=false,required=true)
	private String price;
	
	public ResponseLineItem() {
		// empty
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