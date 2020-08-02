package br.com.guilhermealvessilve.agendamentoemail.exception.mapper;

import br.com.guilhermealvessilve.agendamentoemail.dto.MensagemErroDto;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException>  {

    @Override
    public Response toResponse(final ConstraintViolationException ex) {

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(MensagemErroDto.build(
                        ex.getConstraintViolations()
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.toList())))
                .build();
    }
}