package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RequestSubscriber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="customer_id",nillable=false,required=true)
	private Integer customerId;
	
	@XmlElement(name="screen_name",nillable=false,required=true)
	private String screenName;
	
	@XmlElement(nillable=true,required=true)
	private String email;
	
	public RequestSubscriber() {
		// empty
	}

	public RequestSubscriber(Integer customerId, String screenName, String email) {
		this.customerId = customerId;
		this.screenName = screenName;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}