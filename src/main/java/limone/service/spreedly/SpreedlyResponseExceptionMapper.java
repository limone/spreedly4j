package limone.service.spreedly;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreedlyResponseExceptionMapper<T extends Throwable> implements ResponseExceptionMapper<T> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public T fromResponse(Response r) {
		dumpInput(r);
		return (T) new Exception("boom.");
	}
	
	private void dumpInput(Response r) {
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
		
		log.debug(c.toString());
	}
}