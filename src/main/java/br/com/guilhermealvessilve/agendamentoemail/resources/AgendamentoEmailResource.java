package br.com.guilhermealvessilve.agendamentoemail.resources;

import br.com.guilhermealvessilve.agendamentoemail.business.AgendamentoEmailBusiness;
import br.com.guilhermealvessilve.agendamentoemail.dto.AgendamentoEmailDto;
import br.com.guilhermealvessilve.agendamentoemail.exception.BusinessException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/agendamentoemail")
public class AgendamentoEmailResource {

	@Inject
	private AgendamentoEmailBusiness business;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaAgendamentosEmail() {

		final var emails = business.lista();
		return Response.ok(emails).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvaAgendamentoEmail(AgendamentoEmailDto dto) throws BusinessException {
		
		business.salva(dto);
		return Response
				.status(201)
				.build();
	}
}
