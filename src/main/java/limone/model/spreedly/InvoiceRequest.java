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
	private InvoiceRequestSubscriber subscriber;
	
	public InvoiceRequest() {
		// empty
	}

	public InvoiceRequest(Long subscriptionPlanId, InvoiceRequestSubscriber subscriber) {
		this.subscriptionPlanId = subscriptionPlanId;
		this.subscriber = subscriber;
	}

	public Long getSubscriptionPlanId() {
		return subscriptionPlanId;
	}

	public void setSubscriptionPlanId(Long subscriptionPlanId) {
		this.subscriptionPlanId = subscriptionPlanId;
	}

	public InvoiceRequestSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(InvoiceRequestSubscriber subscriber) {
		this.subscriber = subscriber;
	}
}