package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=false,required=true)
	private String number;
	
	@XmlElement(name="card_type",nillable=false,required=true)
	private String cardType;
	
	@XmlElement(name="verification_value",nillable=false,required=true)
	private String verificationValue;
	
	@XmlElement(nillable=false,required=true)
	private String month;
	
	@XmlElement(nillable=false,required=true)
	private String year;
	
	@XmlElement(name="first_name",nillable=false,required=true)
	private String firstName;
	
	@XmlElement(name="last_name",nillable=false,required=true)
	private String lastName;
	
	public CreditCard() {
		// empty
	}

	public String getNumber() {
		return number;
	}

	public CreditCard(String number, String cardType, String verificationValue, String month, String year, String firstName, String lastName) {
		this.number = number;
		this.cardType = cardType;
		this.verificationValue = verificationValue;
		this.month = month;
		this.year = year;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getVerificationValue() {
		return verificationValue;
	}

	public void setVerificationValue(String verificationValue) {
		this.verificationValue = verificationValue;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}