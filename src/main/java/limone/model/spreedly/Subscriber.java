package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="subscriber")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscriber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="customer-id",nillable=false,required=true)
	private Long customerId;
	
	@XmlElement(name="screen-name",nillable=false,required=true)
	private String screenName;
	
	public Subscriber() {
		// empty
	}

	public Subscriber(Long customerId, String screenName) {
		this.customerId = customerId;
		this.screenName = screenName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
}