package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RequestSubscriber implements Serializable {
	@XmlElement(name="customer_id",nillable=false,required=true)
	private Long customerId;
	
	@XmlElement(name="screen_name",nillable=false,required=true)
	private String screenName;
	
	@XmlElement(nillable=true,required=true)
	private String email;
	
	public RequestSubscriber() {
		// empty
	}

	public RequestSubscriber(Long customerId, String screenName, String email) {
		this.customerId = customerId;
		this.screenName = screenName;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}