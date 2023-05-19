package com.abcBank.documentMangment.service;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.CommonResponseData;
import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.repository.DocumentRepositoryInterface;
import com.abcBank.documentMangment.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImplement implements UserServiceInterface{
    @Autowired
    UserRepositoryInterface userRepositoryInterface;
    @Autowired
    DocumentRepositoryInterface documentRepositoryInterface;
    @Override
    public BaseResponse<UserDetails> saveUser(UserDetails userDetails) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        userDetails =userRepositoryInterface.save(userDetails);
        if(userDetails.getUser_Id()>0){
            response.setResponseObject(userDetails);
            response.setReasonCode(CommonResponseData.SUCCESS);
        }
        else{
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }

    @Override
    public BaseResponse<UserDetails> upadteUser(UserDetails userDetails) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        userDetails =userRepositoryInterface.save(userDetails);
        if(userDetails.getUser_Id()>0){
            response.setResponseObject(userDetails);
            response.setReasonCode(CommonResponseData.SUCCESS);
        }
        else{
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }


    @Override
    public BaseResponse<UserDetails> getUser(Integer id) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        Optional<UserDetails> userDetails;
        userDetails =userRepositoryInterface.findById(id);
        UserDetails processUserDetails=userDetails.get();
        if(userDetails !=null){
            response.setResponseObject(processUserDetails);
            response.setReasonCode(CommonResponseData.SUCCESS);
        }
        else{
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }

    @Override
    public BaseResponse<UserDetails> getAllUser() throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        List<UserDetails> userDetails;
        userDetails =userRepositoryInterface.findAll();
        if(!userDetails.isEmpty()){
            response.setResponseListObject(userDetails);
            response.setReasonCode(CommonResponseData.SUCCESS);
        }
        else{
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }

    @Override
    public BaseResponse<UserDetails> deleteUser(Integer id) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        userRepositoryInterface.deleteById(id);
        response.setStatus("success");
        return response;
    }
}
