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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="subscription-plan",nillable=false,required=true)
	private List<Subscription> subscriptions;
	
	public Subscriptions() {
		// empty
	}

	public Subscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Subscriptions [subscriptions= {");
		for (Subscription s : subscriptions) {
			sb.append(" subscription=[").append(s.toString()).append("]");
		}
		sb.append("}");
		return sb.toString();
	}
}