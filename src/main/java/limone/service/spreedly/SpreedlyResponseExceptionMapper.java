package limone.service.spreedly;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreedlyResponseExceptionMapper implements ResponseExceptionMapper<SpreedlyException> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public SpreedlyException fromResponse(Response r) {
		return new SpreedlyException(r.getStatus(), dumpInput(r));
	}
	
	private String dumpInput(Response r) {
		InputStreamReader isr = new InputStreamReader((InputStream)r.getEntity());
		StringBuffer sb = new StringBuffer();
		char[] c = new char[1];
		try {
			while (isr.read(c) != -1) {
				sb.append(c);
			}
		} catch (IOException ie) {
			log.warn("Could not read from entity.", ie);
		}
		
		return sb.toString();
	}
}