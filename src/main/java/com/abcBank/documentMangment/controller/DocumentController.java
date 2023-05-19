package com.abcBank.documentMangment.controller;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.service.DocumentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Base64;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin("*")
public class DocumentController {
    @Autowired
    private DocumentServiceInterface documentServiceInterface;

    @PostMapping("/saveDocument")
    public ResponseEntity<BaseResponse<?>> saveDocument(@Valid @RequestBody Document document, HttpServletRequest request) throws Exception {
        BaseResponse<UserDetails> baseResponse = new BaseResponse<>();
        BaseResponse<Document> d=documentServiceInterface.saveDocument(document);
        ResponseEntity responseEntity=new ResponseEntity<>(d,null,HttpStatus.ACCEPTED);
        return  responseEntity;

    }

    @PatchMapping("/updateDocument")
    public ResponseEntity<BaseResponse<?>> updateDocument(@Valid @RequestBody Document document, HttpServletRequest request) throws Exception {
        BaseResponse<UserDetails> baseResponse = new BaseResponse<>();
        BaseResponse<Document> upadteDocument=documentServiceInterface.upadteDocument(document);
        ResponseEntity responseEntity=new ResponseEntity<>(upadteDocument,null,HttpStatus.ACCEPTED);
        return  responseEntity;

    }

    @GetMapping("/getDocument/{id}")
    public ResponseEntity<BaseResponse<?>> getDocumentById(@PathVariable Integer id, HttpServletRequest request) throws Exception {

        BaseResponse<Document> document=documentServiceInterface.getDocument(id);
        ResponseEntity responseEntity=new ResponseEntity<>(document,null,HttpStatus.ACCEPTED);
        return  responseEntity;

    }
    @DeleteMapping("/deleteDocument/{id}")
    public ResponseEntity<BaseResponse<?>> deleteDocument(@PathVariable Integer id, HttpServletRequest request) throws Exception {

        BaseResponse<Document> d=documentServiceInterface.deleteDocument(id);
        ResponseEntity responseEntity=new ResponseEntity<>(d,null,HttpStatus.ACCEPTED);
        return  responseEntity;

    }



}

