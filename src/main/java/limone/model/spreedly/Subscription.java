package limone.model.spreedly;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Subscription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=false,required=true)
	private Integer id;
	
	@XmlElement(nillable=false,required=true)
	private String name;
	
	@XmlElement(nillable=false,required=true)
	private Float amount;
	
	@XmlElement(name="currency-code",nillable=false,required=true)
	private String currencyCode;
	
	@XmlElement(name="created-at",nillable=false,required=true)
	private Date createdAt;
	
	@XmlElement(name="updated-at",nillable=false,required=true)
	private Date updatedAt;
	
	@XmlElement(name="charge-after-first-period",nillable=false,required=true)
	private Boolean chargeAfterFirstPeriod;
	
	@XmlElement(name="charge-later-duration-quantity",nillable=false,required=true)
	private Integer chargeLaterDurationQuantity;
	
	@XmlElement(name="charge-later-duration-units",nillable=false,required=true)
	private String chargeLaterDurationUnits;
	
	@XmlElement(nillable=false,required=true)
	private String description;
	
	@XmlElement(name="duration-quantity",nillable=false,required=true)
	private Integer durationQuantity;
	
	@XmlElement(name="duration-units",nillable=false,required=true)
	private String durationUnits;
	
	@XmlElement(nillable=false,required=true)
	private Boolean enabled;
	
	@XmlElement(name="feature-level",nillable=false,required=true)
	private String featureLevel;
	
	@XmlElement(name="force-recurring",nillable=false,required=true)
	private Boolean forceRecurring;
	
	@XmlElement(name="needs-to-be-renewed",nillable=false,required=true)
	private Boolean needsToBeRenewed;
	
	@XmlElement(name="plan-type",nillable=false,required=true)
	private String planType;
	
	@XmlElement(name="return-url",nillable=false,required=true)
	private String returnUrl;
	
	@XmlElement(nillable=false,required=true)
	private String terms;
	
	@XmlElement(nillable=false,required=true)
	private String price;
	
	public Subscription() {
		// empty
	}

	@Override
	public String toString() {
		return "Subscription [amount=" + amount + ", chargeAfterFirstPeriod=" + chargeAfterFirstPeriod + ", chargeLaterDurationQuantity=" + chargeLaterDurationQuantity + ", chargeLaterDurationUnits=" + chargeLaterDurationUnits + ", createdAt=" + createdAt + ", currencyCode=" + currencyCode + ", description=" + description + ", durationQuantity=" + durationQuantity + ", durationUnits=" + durationUnits + ", enabled=" + enabled + ", featureLevel=" + featureLevel + ", forceRecurring=" + forceRecurring + ", id=" + id + ", name=" + name + ", needsToBeRenewed=" + needsToBeRenewed + ", planType=" + planType + ", price=" + price + ", returnUrl=" + returnUrl + ", terms=" + terms + ", updatedAt=" + updatedAt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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

	public Boolean getChargeAfterFirstPeriod() {
		return chargeAfterFirstPeriod;
	}

	public void setChargeAfterFirstPeriod(Boolean chargeAfterFirstPeriod) {
		this.chargeAfterFirstPeriod = chargeAfterFirstPeriod;
	}

	public Integer getChargeLaterDurationQuantity() {
		return chargeLaterDurationQuantity;
	}

	public void setChargeLaterDurationQuantity(Integer chargeLaterDurationQuantity) {
		this.chargeLaterDurationQuantity = chargeLaterDurationQuantity;
	}

	public String getChargeLaterDurationUnits() {
		return chargeLaterDurationUnits;
	}

	public void setChargeLaterDurationUnits(String chargeLaterDurationUnits) {
		this.chargeLaterDurationUnits = chargeLaterDurationUnits;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDurationQuantity() {
		return durationQuantity;
	}

	public void setDurationQuantity(Integer durationQuantity) {
		this.durationQuantity = durationQuantity;
	}

	public String getDurationUnits() {
		return durationUnits;
	}

	public void setDurationUnits(String durationUnits) {
		this.durationUnits = durationUnits;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFeatureLevel() {
		return featureLevel;
	}

	public void setFeatureLevel(String featureLevel) {
		this.featureLevel = featureLevel;
	}

	public Boolean getForceRecurring() {
		return forceRecurring;
	}

	public void setForceRecurring(Boolean forceRecurring) {
		this.forceRecurring = forceRecurring;
	}

	public Boolean getNeedsToBeRenewed() {
		return needsToBeRenewed;
	}

	public void setNeedsToBeRenewed(Boolean needsToBeRenewed) {
		this.needsToBeRenewed = needsToBeRenewed;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}