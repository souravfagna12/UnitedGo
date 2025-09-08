package com.ual.utility;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ual.exception.UnitedGoException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(UnitedGoException.class)
	public ResponseEntity<Error> exceptionHandler(UnitedGoException e) {
		Error err= new Error();
		err.setErrCode(HttpStatus.BAD_REQUEST.value());
		err.setErrMsg(e.getMessage());
		err.setTime(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.OK);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Error> constraintException(ConstraintViolationException e) {
		Error err= new Error();
		err.setErrCode(HttpStatus.BAD_REQUEST.value());
		err.setErrMsg(e.getMessage());
		err.setTime(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.OK);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Error> methorderror(MethodArgumentTypeMismatchException e) {
		Error err= new Error();
		err.setErrCode(HttpStatus.BAD_REQUEST.value());
		err.setErrMsg(e.getMessage());
		err.setTime(LocalDate.now());
		return new ResponseEntity<>(err,HttpStatus.OK);
	}
}
