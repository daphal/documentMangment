package com.abcBank.documentMangment.service;

import com.abcBank.documentMangment.model.*;
import com.abcBank.documentMangment.repository.DocumentRepositoryInterface;
import com.abcBank.documentMangment.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImplement implements UserServiceInterface {
    @Autowired
    UserRepositoryInterface userRepositoryInterface;
    @Autowired
    DocumentRepositoryInterface documentRepositoryInterface;
    @Override
    public BaseResponse<UserDetails> saveUser(UserDetails userDetails) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        userDetails = userRepositoryInterface.save(userDetails);
        if (userDetails.getUser_Id() > 0) {
            response.setResponseObject(userDetails);
            response.setReasonText("Save");
            response.setReasonCode("200");
            response.setReasonCode(CommonResponseData.SUCCESS);
        } else {
            response.setReasonText("error");
            response.setReasonCode("500");
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }
    @Override
    public BaseResponse<UserDetails> upadteUser(UserDetails userDetails) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        userDetails = userRepositoryInterface.save(userDetails);
        if (userDetails.getUser_Id() > 0) {
            response.setResponseObject(userDetails);
            response.setReasonText("get");
            response.setReasonCode("200");
            response.setReasonCode(CommonResponseData.SUCCESS);
        } else {
            response.setReasonText("error");
            response.setReasonCode("500");
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }
    @Override
    public BaseResponse<UserDetails> getUser(Integer id) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        Optional<UserDetails> userDetails;
        userDetails = userRepositoryInterface.findById(id);
        UserDetails processUserDetails = userDetails.get();
        List<Document> documentsList = null;
        List<Document> documents = processUserDetails.getDocuments();
        if (!documents.isEmpty()) {
            documentsList = documents.stream().distinct()
                    .filter(n -> {
                        n = getDecodeDocument(n);
                        return n.getDeleted() == false;
                    })
                    .collect(toList());
        }
        processUserDetails.setDocuments(documentsList);
        if (userDetails != null) {
            response.setReasonText("get");
            response.setReasonCode("200");
            response.setStatus(CommonResponseData.SUCCESS);
            response.setResponseObject(processUserDetails);
        } else {
            response.setReasonText("error");
            response.setReasonCode("500");
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }
    @Override
    public BaseResponse<UserDetails> getAllUser() throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        List<UserDetails> userDetails;
        userDetails = userRepositoryInterface.findAll();
        if (!userDetails.isEmpty()) {
            response.setResponseListObject(userDetails);
            response.setReasonText("Get");
            response.setReasonCode("200");
            response.setStatus("OK");
            response.setReasonCode(CommonResponseData.SUCCESS);
        } else {
            response.setReasonText("error");
            response.setReasonCode("500");
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }
    @Override
    public BaseResponse<UserDetails> deleteUser(Integer id) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        userRepositoryInterface.deleteById(id);
        response.setStatus("success");
        response.setReasonText("deleted");
        response.setReasonCode("200");
        return response;
    }
    @Override
    public BaseResponse<UserDetails> getUserByDocumentId(Integer documentId) {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        UserDetails userDetails = null;
        Document document = null;
        try {
            document = documentRepositoryInterface.findById(documentId).get();
            userDetails = document.getUserDetails();
            List<Document> documents = userDetails.getDocuments();
            List<Document> documentsList = null;
            if (!documents.isEmpty()) {
                documentsList = documents.stream().distinct()
                        .filter(n -> {
                            n = getDecodeDocument(n);
                            return n.getDeleted() == false && n.getDocument_Id()==documentId;
                        })
                        .collect(toList());
            }
            userDetails.setDocuments(documentsList);
            if (userDetails != null) {
                response.setReasonText("get");
                response.setReasonCode("200");
                response.setStatus(CommonResponseData.SUCCESS);
                response.setResponseObject(userDetails);
            } else {
                response.setReasonText("error");
                response.setReasonCode("500");
                response.setStatus(CommonResponseData.FAIL);
            }
        } catch (Exception e) {
            response.setReasonText(e.getMessage());
            response.setReasonCode("500");
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }
    private Document getDecodeDocument(Document document) {
        Document decodeDocument = document;
        byte[] actualByte = Base64.getDecoder()
                .decode(decodeDocument.getDocumentData());
        decodeDocument.setDocumentData(new String(actualByte));
        return decodeDocument;
    }
}