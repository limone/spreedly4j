package limone.service.spreedly;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import limone.model.spreedly.InvoiceRequest;
import limone.model.spreedly.InvoiceResponse;
import limone.model.spreedly.PaymentRequest;
import limone.model.spreedly.PaymentResponse;
import limone.model.spreedly.ResponseSubscriber;
import limone.model.spreedly.Subscriber;
import limone.model.spreedly.Subscriptions;

public interface SpreedlyIntegrationService extends Serializable {
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	@Path("/invoices.xml")
	public InvoiceResponse createInvoice(InvoiceRequest request);
	
	@GET
	@Produces("application/xml")
	@Path("/subscription_plans.xml")
	public Subscriptions getSubscriptionPlans();
	
	@PUT
	@Consumes("application/xml")
	@Produces("application/xml")
	@Path("/invoices/{token}/pay.xml")
	public PaymentResponse processPayment(PaymentRequest request);
	
	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	@Path("/subscribers.xml")
	public ResponseSubscriber createSubscriber(Subscriber s);
}