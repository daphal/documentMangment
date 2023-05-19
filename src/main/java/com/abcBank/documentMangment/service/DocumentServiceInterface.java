package com.abcBank.documentMangment.service;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.UserDetails;


public interface DocumentServiceInterface {

    BaseResponse<Document> saveDocument(Document document) throws Exception;
    BaseResponse<Document> upadteDocument(Document document) throws Exception;

    BaseResponse<Document> getDocument(Integer id) throws Exception;

    BaseResponse<Document> getAllDocument() throws Exception;

    BaseResponse<Document> deleteDocument(Integer id) throws Exception;
    BaseResponse<UserDetails> getDocumentByUser(Integer id) throws Exception;

}
