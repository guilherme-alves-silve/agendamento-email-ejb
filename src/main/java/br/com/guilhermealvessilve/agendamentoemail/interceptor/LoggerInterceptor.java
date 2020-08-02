package br.com.guilhermealvessilve.agendamentoemail.interceptor;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

@Interceptor
@Priority(1)
@br.com.guilhermealvessilve.agendamentoemail.interceptor.Logger
public class LoggerInterceptor {
	
	@AroundInvoke
	public Object treatException(InvocationContext context) throws Exception {

		final var logger = Logger.getLogger(context.getTarget().getClass().getName());
		
		try {
			return context.proceed();		
		} catch (final Exception ex) {
			if(ex instanceof ConstraintViolationException) {
				logger.info(ex.getMessage());
			} else {
				logger.severe(ex.getMessage());
			}
			
			throw ex;
		}
	}
}
