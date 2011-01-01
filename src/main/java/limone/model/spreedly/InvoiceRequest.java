package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="subscription_plan_id",nillable=false,required=true)
	private Integer subscriptionPlanId;
	
	@XmlElement(nillable=false,required=true)
	private RequestSubscriber subscriber;
	
	public InvoiceRequest() {
		// empty
	}

	public InvoiceRequest(Integer subscriptionPlanId, RequestSubscriber subscriber) {
		this.subscriptionPlanId = subscriptionPlanId;
		this.subscriber = subscriber;
	}

	public Integer getSubscriptionPlanId() {
		return subscriptionPlanId;
	}

	public void setSubscriptionPlanId(Integer subscriptionPlanId) {
		this.subscriptionPlanId = subscriptionPlanId;
	}

	public RequestSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(RequestSubscriber subscriber) {
		this.subscriber = subscriber;
	}
}