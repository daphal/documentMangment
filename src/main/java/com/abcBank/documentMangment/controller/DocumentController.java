package com.abcBank.documentMangment.controller;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.CommonResponseData;
import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.service.DocumentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1/api")
@CrossOrigin("*")
public class DocumentController {
    @Autowired
    private DocumentServiceInterface documentServiceInterface;
    @PostMapping("/saveDocument")
    public ResponseEntity<BaseResponse<?>> saveDocument(@Valid @RequestBody Document document, HttpServletRequest request) throws Exception {
        BaseResponse<Document> baseResponse = documentServiceInterface.saveDocument(document);
        ResponseEntity responseEntity;
        if(baseResponse.getStatus()== CommonResponseData.SUCCESS) {
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.ACCEPTED);
        }
        else{
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
    @PatchMapping("/updateDocument")
    public ResponseEntity<BaseResponse<?>> updateDocument(@Valid @RequestBody Document document, HttpServletRequest request) throws Exception {
        BaseResponse<Document> baseResponse = documentServiceInterface.upadteDocument(document);
        ResponseEntity responseEntity;
        if(baseResponse.getStatus()== CommonResponseData.SUCCESS) {
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.ACCEPTED);
        }
        else{
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
    @GetMapping("/getDocument/{id}")
    public ResponseEntity<BaseResponse<?>> getDocumentById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        BaseResponse<Document> baseResponse = documentServiceInterface.getDocument(id);
        ResponseEntity responseEntity;
        if(baseResponse.getStatus()== CommonResponseData.SUCCESS) {
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.ACCEPTED);
        }
        else{
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
    @DeleteMapping("/deleteDocument/{id}")
    public ResponseEntity<BaseResponse<?>> deleteDocument(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        BaseResponse<Document> baseResponse = documentServiceInterface.deleteDocument(id);
        ResponseEntity responseEntity;
        if(baseResponse.getStatus()== CommonResponseData.SUCCESS) {
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.ACCEPTED);
        }
        else{
            responseEntity = new ResponseEntity<>(baseResponse, null, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }
}

