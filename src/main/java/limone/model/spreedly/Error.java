package limone.model.spreedly;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Error implements Serializable {
	@XmlValue
	private String error;
	
	public Error() {
		// empty
	}

	public Error(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return String.format("Error [error=%s]", error);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}