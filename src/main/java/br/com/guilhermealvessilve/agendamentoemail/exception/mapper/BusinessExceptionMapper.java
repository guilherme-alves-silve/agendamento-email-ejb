package br.com.guilhermealvessilve.agendamentoemail.exception.mapper;

import br.com.guilhermealvessilve.agendamentoemail.dto.MensagemErroDto;
import br.com.guilhermealvessilve.agendamentoemail.exception.BusinessException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper  implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(final BusinessException exception) {
		return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(MensagemErroDto.build(exception.getMensagens()))
                .build();
	}
}
