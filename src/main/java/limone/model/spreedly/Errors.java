package limone.model.spreedly;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="errors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Errors implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(nillable=false,required=true)
	private List<Error> error;
	
	public Errors() {
		// empty
	}

	public List<Error> getError() {
		return error;
	}

	public void setErrors(List<Error> error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Errors: {");
		for (Error e : error) {
			sb.append(e.toString());
		}
		sb.append("}");
		return sb.toString();
	}
}