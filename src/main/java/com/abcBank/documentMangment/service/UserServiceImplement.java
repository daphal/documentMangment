package com.abcBank.documentMangment.service;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.CommonResponseData;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImplement implements UserServiceInterface{
    @Autowired
    UserRepositoryInterface userRepositoryInterface;

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
        UserDetails userDetails;
        userDetails =userRepositoryInterface.getById(id);
        if(userDetails !=null){
            response.setResponseObject(userDetails);
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
