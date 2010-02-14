package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceRequest implements Serializable {
	@XmlElement(name="subscription_plan_id",nillable=false,required=true)
	private Long subscriptionPlanId;
	
	@XmlElement(nillable=false,required=true)
	private RequestSubscriber subscriber;
	
	public InvoiceRequest() {
		// empty
	}

	public InvoiceRequest(Long subscriptionPlanId, RequestSubscriber subscriber) {
		this.subscriptionPlanId = subscriptionPlanId;
		this.subscriber = subscriber;
	}

	public Long getSubscriptionPlanId() {
		return subscriptionPlanId;
	}

	public void setSubscriptionPlanId(Long subscriptionPlanId) {
		this.subscriptionPlanId = subscriptionPlanId;
	}

	public RequestSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(RequestSubscriber subscriber) {
		this.subscriber = subscriber;
	}
}