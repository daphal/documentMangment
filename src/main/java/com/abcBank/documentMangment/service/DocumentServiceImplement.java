package com.abcBank.documentMangment.service;

import com.abcBank.documentMangment.CustomeException.FileOwnarException;
import com.abcBank.documentMangment.CustomeException.FileTypeException;
import com.abcBank.documentMangment.model.*;
import com.abcBank.documentMangment.repository.DocumentLogRepositoryInterface;
import com.abcBank.documentMangment.repository.DocumentRepositoryInterface;
import com.abcBank.documentMangment.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class DocumentServiceImplement implements DocumentServiceInterface {
    @Autowired
    DocumentRepositoryInterface documentRepositoryInterface;
    @Autowired
    UserRepositoryInterface userRepositoryInterface;
    @Autowired
    DocumentLogRepositoryInterface documentLogRepositoryInterface;

    @Override
    public BaseResponse<Document> saveDocument(Document document) throws Exception {
        BaseResponse<Document> response = new BaseResponse<>();
            if (document.getDocumentType().toLowerCase(Locale.ROOT).equals("pdf")) {
                document.setDocumentData(GetEncodeBase64(document.getDocumentData().getBytes()));
                document = documentRepositoryInterface.save(document);
                if (document.getDocument_Id() > 0) {
                    DocumentLog documentLog = new DocumentLog();
                    documentLog.setDocuments(document);
                    documentLog = documentLogRepositoryInterface.save(documentLog);
                    if (documentLog.getDocumentLog_Id() > 0) {
                        response.setResponseObject(document);
                        response.setStatus(CommonResponseData.SUCCESS);
                        response.setReasonCode(CommonResponseData.SUCCESS);
                        response.setReasonText("Saved");
                    } else {
                        response.setReasonText("Document is updated but log is not update");
                        response.setStatus(CommonResponseData.FAIL);
                        response.setReasonCode(CommonResponseData.FAIL);
                    }
                } else {
                    response.setReasonText("Document is not save");
                    response.setStatus(CommonResponseData.FAIL);
                    response.setReasonCode(CommonResponseData.FAIL);
                }
                return response;
            } else {
                throw new FileTypeException("File type should me pdf");
            }
    }
    private String GetEncodeBase64(byte[] bytes) {
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    @Override
    public BaseResponse<Document> upadteDocument(Document document) throws Exception {
        BaseResponse<Document> response = new BaseResponse<>();
            if (document.getDocumentType().toLowerCase(Locale.ROOT).equals("pdf")) {
                if (document.getDocument_Id() > 0 &&
                        documentRepositoryInterface.getById(document.getDocument_Id()).getUserDetails().getUser_Id()
                                .equals(document.getUserDetails().getUser_Id())) {
                    document.setDocumentData(GetEncodeBase64(document.getDocumentData().getBytes()));
                    document.setUserDetails(userRepositoryInterface.getById(document.getUserDetails().getUser_Id()));
                    Document document1 = documentRepositoryInterface.save(document);
                    if (document.getDocument_Id() > 0) {
                        DocumentLog documentLog = new DocumentLog();
                        documentLog.setDocuments(document);
                        documentLog = documentLogRepositoryInterface.save(documentLog);
                        if (documentLog.getDocumentLog_Id() > 0) {
                            response.setResponseObject(document);
                            response.setReasonText("Updated");
                            response.setStatus(CommonResponseData.SUCCESS);
                            response.setReasonCode(CommonResponseData.SUCCESS);
                        } else {
                            response.setReasonText("Document is updated but log is not update");
                            response.setStatus(CommonResponseData.FAIL);
                            response.setReasonCode(CommonResponseData.FAIL);
                        }
                    }
                    return response;
                } else {
                    throw new FileOwnarException("Your are not own this file and you cant edit this file");
                }
            } else {
                throw new FileTypeException("File type should me pdf");
            }
    }

    @Override
    public BaseResponse<Document> getDocument(Integer id) throws Exception {
        BaseResponse<Document> response = new BaseResponse<>();
        Document document = documentRepositoryInterface.findById(id).get();
        if (document != null && document.getDeleted() == false) {
            Document decodeDocument = getDecodeDocument(document);
            UserDetails userDetails = decodeDocument.getUserDetails();
            decodeDocument.setUserDetails(userDetails);
            response.setResponseObject(decodeDocument);
            response.setStatus(CommonResponseData.SUCCESS);
            response.setReasonText("Get");
            response.setReasonCode(CommonResponseData.SUCCESS);
        } else {
            response.setReasonText("Internal error occur");
            response.setStatus(CommonResponseData.FAIL);
            response.setReasonText("Fail");
            response.setReasonCode(CommonResponseData.SUCCESS);
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

    @Override
    public BaseResponse<Document> getAllDocument() throws Exception {
        BaseResponse<Document> response = new BaseResponse<>();
        List<Document> document = documentRepositoryInterface.findAll();
        if (!document.isEmpty()) {
            document = document.stream().distinct()
                    .filter(n -> {
                        n = getDecodeDocument(n);
                        return n.getDeleted() == false;
                    })
                    .collect(toList());
            response.setResponseListObject(document);
            response.setStatus(CommonResponseData.SUCCESS);
            response.setReasonText("Get");
            response.setReasonCode(CommonResponseData.SUCCESS);
        } else {
            response.setReasonText("Internal error occur");
            response.setStatus(CommonResponseData.FAIL);
            response.setReasonText("Fail");
            response.setReasonCode(CommonResponseData.SUCCESS);
        }
        return response;
    }

    @Override
    public BaseResponse<Document> deleteDocument(Integer id) throws Exception {
        BaseResponse<Document> response = new BaseResponse<>();
            Document document = documentRepositoryInterface.getById(id);
            document.setDocumentLogs(null);
            if (document != null) {
                document.setDeleted(true);
                document = documentRepositoryInterface.save(document);
                DocumentLog documentLog = new DocumentLog();
                documentLog.setDocuments(document);
                documentLog = documentLogRepositoryInterface.save(documentLog);
                if (documentLog.getDocumentLog_Id() > 0) {
                    response.setResponseObject(null);
                    response.setReasonCode(CommonResponseData.SUCCESS);
                    response.setStatus(CommonResponseData.SUCCESS);
                    response.setReasonText("Document is deleted");
                } else {
                    response.setReasonText("Document is log is updated ");
                    response.setStatus(CommonResponseData.FAIL);
                    response.setReasonCode(CommonResponseData.FAIL);
                }
            } else {
                response.setReasonText("Document is not found ");
                response.setReasonCode(CommonResponseData.FAIL);
                response.setStatus(CommonResponseData.FAIL);
            }
            return response;
    }

    @Override
    public BaseResponse<UserDetails> getDocumentByUser(Integer id) throws Exception {
        BaseResponse<UserDetails> response = new BaseResponse<>();
        Optional<UserDetails> userDetails;
        userDetails = userRepositoryInterface.findById(id);
        UserDetails processUserDetails = userDetails.get();
        if (userDetails != null) {
            response.setReasonText("Suceess");
            response.setStatus(CommonResponseData.SUCCESS);
            response.setResponseObject(processUserDetails);
            response.setReasonCode(CommonResponseData.SUCCESS);
        } else {
            response.setReasonText("Not found");
            response.setReasonCode(CommonResponseData.FAIL);
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }
}