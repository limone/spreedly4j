package limone.model.spreedly;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="subscription-plans")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscriptions implements Serializable {
	@XmlElement(name="subscription-plan",nillable=false,required=true)
	private List<Subscription> subscriptions;
	
	public Subscriptions() {
		// empty
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
}