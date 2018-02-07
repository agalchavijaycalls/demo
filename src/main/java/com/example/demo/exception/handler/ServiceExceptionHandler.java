package com.example.demo.exception.handler;

import com.asoft.ainstitute.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Component
public class ServiceExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

  public void handle(Exception exception) throws ServiceException {
    if (exception instanceof ServiceException) {
      LOGGER.info("ServiceException : " + exception.getMessage());
      throw (ServiceException) exception;
    } else {
      LOGGER.error("Exception : ", exception);
      while (exception != null) {
        //        if (exception instanceof MySQLIntegrityConstraintViolationException) {
        //          throw new ServiceException(exception.getMessage(), exception);
        //        } else {
        if (exception instanceof ConstraintViolationException) {
          StringBuilder errorMessageStringBuilder = new StringBuilder();
          for (ConstraintViolation<?> violation : ((ConstraintViolationException) exception)
              .getConstraintViolations()) {
            errorMessageStringBuilder.append(violation.getPropertyPath() + " - " + violation.getMessage() + ". ");
          }
          throw new ServiceException(errorMessageStringBuilder.toString());
        } else {
          if (exception.getCause() == null || exception == exception.getCause()) {
            throw new ServiceException(exception.getMessage(), exception);
          }
        }
        //        }
        exception = (Exception) exception.getCause();
      }
    }
  }
}
