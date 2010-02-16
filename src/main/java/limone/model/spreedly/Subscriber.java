package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="subscriber")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscriber implements Serializable {
	@XmlElement(name="customer-id",nillable=false,required=true)
	private Integer customerId;
	
	@XmlElement(name="screen-name",nillable=false,required=true)
	private String screenName;
	
	public Subscriber() {
		// empty
	}

	public Subscriber(Integer customerId, String screenName) {
		this.customerId = customerId;
		this.screenName = screenName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
}