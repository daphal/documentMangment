package com.abcBank.documentMangment.service;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.UserDetails;

public interface UserServiceInterface {

    BaseResponse<UserDetails> saveUser(UserDetails userDetails) throws Exception;
    public BaseResponse<UserDetails> upadteUser(UserDetails userDetails) throws Exception;
    BaseResponse<UserDetails> getUser(Integer id) throws Exception;
    BaseResponse<UserDetails> getAllUser() throws Exception;
    BaseResponse<UserDetails> deleteUser(Integer id) throws Exception;
    BaseResponse<UserDetails> getUserByDocumentId(Integer documentId);
}
