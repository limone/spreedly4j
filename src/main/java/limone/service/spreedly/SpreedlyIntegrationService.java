package limone.service.spreedly;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import limone.model.spreedly.InvoiceRequest;
import limone.model.spreedly.InvoiceResponse;

public interface SpreedlyIntegrationService extends Serializable {
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	@Path("/invoices.xml")
	public InvoiceResponse createInvoice(InvoiceRequest request);
	
	@GET
	@Produces("text/plain")
	@Path("/subscription_plans.xml")
	public String getSubscriptionPlans();
}