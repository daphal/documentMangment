package com.abcBank.documentMangment.service;

import com.abcBank.documentMangment.model.*;
import com.abcBank.documentMangment.repository.DocumentLogRepositoryInterface;
import com.abcBank.documentMangment.repository.DocumentRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class DocumentServiceImplement implements DocumentServiceInterface{
    @Autowired
    DocumentRepositoryInterface documentRepositoryInterface;
    @Autowired
    UserServiceInterface userServiceInterface;

    @Autowired
    DocumentLogRepositoryInterface documentLogRepositoryInterface;

    @Override
    public BaseResponse<Document> saveDocument(Document document) throws Exception {
        BaseResponse<Document> response = new BaseResponse<>();
        document.setDocumentData(GetEncodeBase64(document.getDocumentData().getBytes()));
        document=documentRepositoryInterface.save(document);
        if(document.getDocument_Id()>0){
            DocumentLog documentLog=new DocumentLog();
            documentLog.setDocument(document);
            documentLog=documentLogRepositoryInterface.save(documentLog);
            if(documentLog.getDocumentLog_Id()>0){
                response.setResponseObject(document);
                response.setReasonCode(CommonResponseData.SUCCESS);
            }
            else {
                response.setReasonText("Document is updated but log is not update");
                response.setStatus(CommonResponseData.FAIL);
            }
        }
        else{
            response.setReasonText("Document is not save");
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }

    private String GetEncodeBase64(byte[] bytes) {
        return  Base64.getUrlEncoder().encodeToString(bytes);
    }

   /* @Override
    public BaseResponse<Document> upadteDocument(Document document) throws Exception {
        return null;
    }
*/
    @Override
    public BaseResponse<Document> getDocument(Integer id) throws Exception {

        BaseResponse<Document> response = new BaseResponse<>();
        Optional<Document> document= documentRepositoryInterface.findById(id);

        if(document.isPresent()){
            Document decodeDocument=getDecodeDocument(document);
            response.setResponseObject(decodeDocument);
            response.setReasonCode(CommonResponseData.SUCCESS);
        }
        else{
            response.setStatus(CommonResponseData.FAIL);
        }
        return response;
    }

    private Document getDecodeDocument(Optional<Document> document) {
        Document decodeDocument =document.get();
        byte[] actualByte = Base64.getDecoder()
                .decode(decodeDocument.getDocumentData());
        decodeDocument.setDocumentData(new String(actualByte));
        return decodeDocument;
    }

    @Override
    public BaseResponse<Document> getAllDocument() throws Exception {
        return null;
    }

    @Override
    public BaseResponse<Document> deleteDocument(Integer id) throws Exception {
        BaseResponse<Document> response = new BaseResponse<>();
        try {
            documentRepositoryInterface.deleteById(id);
            Optional<Document> document= documentRepositoryInterface.findById(id);
            if(document.isPresent()){
                DocumentLog documentLog=new DocumentLog();
                documentLog.setDocument(document.get());
                documentLog=documentLogRepositoryInterface.save(documentLog);
                if(documentLog.getDocumentLog_Id()>0){
                    response.setResponseObject(null);
                    response.setReasonCode(CommonResponseData.SUCCESS);
                    response.setReasonCode(CommonResponseData.SUCCESS);
                    response.setReasonText("Document is deleted");
                }
                else {
                    response.setReasonText("Document is not present ");
                    response.setStatus(CommonResponseData.FAIL);
                }

            }
            return response;
        }
        catch (Exception ex){
            response.setStatus(CommonResponseData.FAIL);
            response.setReasonCode(ex.getMessage());
            response.setResponseObject(null);
            return  response;
        }

    }

    @Override
    public BaseResponse<UserDetails> getDocumentByUser(Integer id) throws Exception {
        return userServiceInterface.getUser(id);
    }
}
