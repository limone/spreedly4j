package limone.model.spreedly;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="line-item")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentResponseLineItem extends ResponseLineItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="subscription-plan-version-id",nillable=false,required=true)
	private Integer subscriptionPlanVersionId;
	
	public PaymentResponseLineItem() {
		// empty
	}

	public Integer getSubscriptionPlanVersionId() {
		return subscriptionPlanVersionId;
	}

	public void setSubscriptionPlanVersionId(Integer subscriptionPlanVersionId) {
		this.subscriptionPlanVersionId = subscriptionPlanVersionId;
	}
}