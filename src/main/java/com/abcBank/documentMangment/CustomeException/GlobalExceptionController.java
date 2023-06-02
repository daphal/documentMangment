package com.abcBank.documentMangment.CustomeException;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.CommonResponseData;
import com.abcBank.documentMangment.model.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {
    BaseResponse response = new BaseResponse<>();
    @ExceptionHandler(FileOwnarException.class)
    public ResponseEntity<BaseResponse<?>> fileOwner(FileOwnarException exception){
        response.setStatus(CommonResponseData.FAIL);
        response.setReasonText(exception.getMessage());
        response.setReasonCode(CommonResponseData.FAIL);
        response.setResponseObject(null);
        ResponseEntity responseEntity = new ResponseEntity<>(response, null, HttpStatus.EXPECTATION_FAILED);
        return  responseEntity;
    }
    @ExceptionHandler(FileTypeException.class)
    public ResponseEntity<BaseResponse<?>>  fileType(FileTypeException exception){
        response.setStatus(CommonResponseData.FAIL);
        response.setReasonText(exception.getMessage());
        response.setReasonCode(CommonResponseData.FAIL);
        response.setResponseObject(null);
        ResponseEntity responseEntity = new ResponseEntity<>(response, null, HttpStatus.EXPECTATION_FAILED);
        return  responseEntity;
    }
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<BaseResponse<?>>  unknowException(Exception exception){
        response.setStatus(CommonResponseData.FAIL);
        response.setReasonCode(CommonResponseData.FAIL);
        response.setReasonText(exception.getMessage());
        response.setResponseObject(null);
        ResponseEntity responseEntity = new ResponseEntity<>(response, null, HttpStatus.EXPECTATION_FAILED);
        return  responseEntity;
    }
}
