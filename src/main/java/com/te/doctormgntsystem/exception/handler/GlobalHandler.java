package com.te.doctormgntsystem.exception.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.te.doctormgntsystem.dto.Response;
import com.te.doctormgntsystem.exception.DataNotFoundException;
import com.te.doctormgntsystem.exception.TimeSlotException;


@ControllerAdvice
public class GlobalHandler {
	
	
//	// handleMethodArgumentNotValid : triggers when @Valid fails
//		@Override
//		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//				HttpHeaders headers, HttpStatus status, WebRequest request) {
//			return ResponseEntity.ok(Response.builder().error(true)
//					.message("Validation Issue")
//					.data(ex.getBindingResult().getFieldErrors().stream().map(error -> {
//						String[] split = error.getField().split(".");
//						String field = split.length == 1 ? error.getField() : split[split.length - 1];
//						return field.substring(0, 1).toUpperCase() + field.substring(1) + " : " + error.getDefaultMessage();
//					}).collect(Collectors.toList())).build());
//		}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleInvalidInput(MethodArgumentNotValidException methodArgumentNotValidException) {
//		Map<String, String> invalidInputErrors = new HashMap<>();
//		methodArgumentNotValidException.getBindingResult().getFieldErrors()
//				.forEach(errors -> invalidInputErrors.put(errors.getField(), errors.getDefaultMessage()));
//
//		return invalidInputErrors;
//	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }



	@ExceptionHandler(value = DataNotFoundException.class)
	public ResponseEntity<Response> handler(DataNotFoundException e){
		return ResponseEntity.badRequest().body(Response.builder().error(true).message("Data Not Found").data(null).build());
	}
	@ExceptionHandler(value = TimeSlotException.class)
	public ResponseEntity<Response> timeHandler(TimeSlotException e){
		return ResponseEntity.badRequest().body(Response.builder().error(true).message(e.getMessage()).data(null).build());
	}
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Response> mainHandler(Exception e){
		return ResponseEntity.badRequest().body(Response.builder().error(true).message(e.getMessage()).data(null).build());
	}

}
