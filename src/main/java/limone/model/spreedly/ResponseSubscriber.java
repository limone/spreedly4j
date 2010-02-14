package limone.model.spreedly;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseSubscriber implements Serializable {
	@XmlElement(name="customer-id",nillable=false,required=true)
	private Long customerId;
	
	@XmlElement(name="screen-name",nillable=false,required=true)
	private String screenName;
	
	@XmlElement(nillable=true,required=true)
	private String email;
	
	@XmlElement(name="billing-first-name",nillable=true,required=true)
	private String billingFirstName;
	
	@XmlElement(name="billing-last-name",nillable=true,required=true)
	private String billingLastName;
	
	@XmlElement(nillable=false,required=true)
	private Boolean active;
	
	@XmlElement(name="on-trial",nillable=false,required=true)
	private Boolean onTrial;
	
	@XmlElement(name="created-at",nillable=false,required=true)
	private Date createdAt;
	
	@XmlElement(name="updated-at",nillable=false,required=true)
	private Date updatedAt;
	
	@XmlElement(name="active-until",nillable=true,required=true)
	private Date activeUntil;
	
	@XmlElement(nillable=false,required=true)
	private Boolean recurring;
	
	@XmlElement(name="eligible-for-free-trial",nillable=false,required=true)
	private Boolean eligibleForFreeTrial;
	
	@XmlElement(name="lifetime-subscription",nillable=false,required=true)
	private Boolean lifetimeSubscription;
	
	@XmlElement(name="store-credit",nillable=false,required=true)
	private Float storeCredit;
	
	@XmlElement(name="store-credit-currency-code",nillable=false,required=true)
	private String storeCreditCurrencyCode;
	
	@XmlElement(nillable=false,required=true)
	private String token;
	
	@XmlElement(name="card-expires-before-next-auto-renew",nillable=false,required=true)
	private Boolean cardExpiresBeforeNextAutoRenew;
	
	@XmlElement(name="subscription-plan-name",defaultValue="\u0000",nillable=false,required=true)
	private String subscriptionPlanName;
	
	@XmlElement(name="feature-level",defaultValue="\u0000",nillable=false,required=true)
	private String featureLevel;
	
	public ResponseSubscriber() {
		// empty
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

	public String getBillingFirstName() {
		return billingFirstName;
	}

	public void setBillingFirstName(String billingFirstName) {
		this.billingFirstName = billingFirstName;
	}

	public String getBillingLastName() {
		return billingLastName;
	}

	public void setBillingLastName(String billingLastName) {
		this.billingLastName = billingLastName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getOnTrial() {
		return onTrial;
	}

	public void setOnTrial(Boolean onTrial) {
		this.onTrial = onTrial;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}

	public Boolean getRecurring() {
		return recurring;
	}

	public void setRecurring(Boolean recurring) {
		this.recurring = recurring;
	}

	public Boolean getEligibleForFreeTrial() {
		return eligibleForFreeTrial;
	}

	public void setEligibleForFreeTrial(Boolean eligibleForFreeTrial) {
		this.eligibleForFreeTrial = eligibleForFreeTrial;
	}

	public Boolean getLifetimeSubscription() {
		return lifetimeSubscription;
	}

	public void setLifetimeSubscription(Boolean lifetimeSubscription) {
		this.lifetimeSubscription = lifetimeSubscription;
	}

	public Float getStoreCredit() {
		return storeCredit;
	}

	public void setStoreCredit(Float storeCredit) {
		this.storeCredit = storeCredit;
	}

	public String getStoreCreditCurrencyCode() {
		return storeCreditCurrencyCode;
	}

	public void setStoreCreditCurrencyCode(String storeCreditCurrencyCode) {
		this.storeCreditCurrencyCode = storeCreditCurrencyCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getCardExpiresBeforeNextAutoRenew() {
		return cardExpiresBeforeNextAutoRenew;
	}

	public void setCardExpiresBeforeNextAutoRenew(Boolean cardExpiresBeforeNextAutoRenew) {
		this.cardExpiresBeforeNextAutoRenew = cardExpiresBeforeNextAutoRenew;
	}

	public String getSubscriptionPlanName() {
		return subscriptionPlanName;
	}

	public void setSubscriptionPlanName(String subscriptionPlanName) {
		this.subscriptionPlanName = subscriptionPlanName;
	}

	public String getFeatureLevel() {
		return featureLevel;
	}

	public void setFeatureLevel(String featureLevel) {
		this.featureLevel = featureLevel;
	}
}