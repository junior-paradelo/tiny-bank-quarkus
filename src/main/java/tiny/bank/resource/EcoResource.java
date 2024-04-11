package tiny.bank.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/greeting")
public class EcoResource {

	@GET
	public String greet() {
		return "Hello world!";
	}
}
