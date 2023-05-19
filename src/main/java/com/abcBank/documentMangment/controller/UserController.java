package com.abcBank.documentMangment.controller;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.CommonResponseData;
import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/v2/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @PostMapping("/saveUser")
    public ResponseEntity<BaseResponse<?>> saveUser(@Valid @RequestBody UserDetails userDetails, HttpServletRequest request) throws Exception {
        BaseResponse<UserDetails> baseResponse = new BaseResponse<>();

        if (userDetails != null) {
            BaseResponse<UserDetails> userBaseResponse = userServiceInterface.saveUser(userDetails);
            if (userBaseResponse.getReasonCode().equalsIgnoreCase(CommonResponseData.SUCCESS)) {
                baseResponse.setStatus("Ok");
                baseResponse.setResponseObject(userBaseResponse.getResponseObject());
                baseResponse.setReasonText(userBaseResponse.getReasonText());
                return new ResponseEntity<BaseResponse<?>>(baseResponse, null, HttpStatus.ACCEPTED);
            } else {
                baseResponse.setStatus(userBaseResponse.getStatus());
                baseResponse.setResponseObject(userBaseResponse.getResponseObject());
                baseResponse.setReasonText(userBaseResponse.getReasonText());
                return new ResponseEntity<BaseResponse<?>>(baseResponse, null, HttpStatus.ACCEPTED);
            }
        } else {
            baseResponse.setStatus(CommonResponseData.FAIL);
            baseResponse.setReasonText(CommonResponseData.INPUT_OBJECT_NULL);
            return new ResponseEntity<BaseResponse<?>>(baseResponse, null, HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<BaseResponse<?>> getUserById(@PathVariable Integer id, HttpServletRequest request) throws Exception {

        BaseResponse<UserDetails> d=userServiceInterface.getUser(id);
        ResponseEntity responseEntity=new ResponseEntity<>(d,null,HttpStatus.ACCEPTED);
        return  responseEntity;

    }


}
