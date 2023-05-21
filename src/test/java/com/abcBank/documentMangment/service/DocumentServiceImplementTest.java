package com.abcBank.documentMangment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.DocumentLog;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.repository.DocumentLogRepositoryInterface;
import com.abcBank.documentMangment.repository.DocumentRepositoryInterface;
import com.abcBank.documentMangment.repository.UserRepositoryInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DocumentServiceImplement.class})
@ExtendWith(SpringExtension.class)
class DocumentServiceImplementTest {
    @MockBean
    private DocumentLogRepositoryInterface documentLogRepositoryInterface;

    @MockBean
    private DocumentRepositoryInterface documentRepositoryInterface;

    @Autowired
    private DocumentServiceImplement documentServiceImplement;

    @MockBean
    private UserRepositoryInterface userRepositoryInterface;

    /**
     * Method under test: {@link DocumentServiceImplement#saveDocument(Document)}
     */
    @Test
    void testSaveDocument() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        BaseResponse<Document> actualSaveDocumentResult = documentServiceImplement.saveDocument(document);
        assertEquals("fail", actualSaveDocumentResult.getStatus());
        assertNull(actualSaveDocumentResult.getResponseObject());
        assertEquals("File type should me pdf", actualSaveDocumentResult.getReasonText());
    }


    @Test
    void testSaveDocument2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);
        Document document = mock(Document.class);
        when(document.getDocumentType()).thenReturn("Document Type");
        doNothing().when(document).setDeleted((Boolean) any());
        doNothing().when(document).setDocumentData((String) any());
        doNothing().when(document).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document).setDocumentName((String) any());
        doNothing().when(document).setDocumentType((String) any());
        doNothing().when(document).setDocument_Id((Integer) any());
        doNothing().when(document).setUserDetails((UserDetails) any());
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        BaseResponse<Document> actualSaveDocumentResult = documentServiceImplement.saveDocument(document);
        assertEquals("fail", actualSaveDocumentResult.getStatus());
        assertNull(actualSaveDocumentResult.getResponseObject());
        assertEquals("File type should me pdf", actualSaveDocumentResult.getReasonText());
        verify(document).getDocumentType();
        verify(document).setDeleted((Boolean) any());
        verify(document).setDocumentData((String) any());
        verify(document).setDocumentLogs((List<DocumentLog>) any());
        verify(document).setDocumentName((String) any());
        verify(document).setDocumentType((String) any());
        verify(document).setDocument_Id((Integer) any());
        verify(document).setUserDetails((UserDetails) any());
    }

    @Test
    void testSaveDocument3() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        DocumentLog documentLog = new DocumentLog();
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);
        Document document2 = mock(Document.class);
        when(document2.getDocumentData()).thenReturn("Document Data");
        when(document2.getDocumentType()).thenReturn("pdf");
        doNothing().when(document2).setDeleted((Boolean) any());
        doNothing().when(document2).setDocumentData((String) any());
        doNothing().when(document2).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document2).setDocumentName((String) any());
        doNothing().when(document2).setDocumentType((String) any());
        doNothing().when(document2).setDocument_Id((Integer) any());
        doNothing().when(document2).setUserDetails((UserDetails) any());
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        BaseResponse<Document> actualSaveDocumentResult = documentServiceImplement.saveDocument(document2);
        assertEquals("success", actualSaveDocumentResult.getReasonCode());
        assertSame(document1, actualSaveDocumentResult.getResponseObject());
        assertEquals("Saved", actualSaveDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentRepositoryInterface).save((Document) any());
        verify(document2).getDocumentData();
        verify(document2).getDocumentType();
        verify(document2).setDeleted((Boolean) any());
        verify(document2, atLeast(1)).setDocumentData((String) any());
        verify(document2).setDocumentLogs((List<DocumentLog>) any());
        verify(document2).setDocumentName((String) any());
        verify(document2).setDocumentType((String) any());
        verify(document2).setDocument_Id((Integer) any());
        verify(document2).setUserDetails((UserDetails) any());
    }


    @Test
    void testSaveDocument4() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(123);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);
        Document document2 = mock(Document.class);
        when(document2.getDocumentData()).thenReturn("Document Data");
        when(document2.getDocumentType()).thenReturn("pdf");
        doNothing().when(document2).setDeleted((Boolean) any());
        doNothing().when(document2).setDocumentData((String) any());
        doNothing().when(document2).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document2).setDocumentName((String) any());
        doNothing().when(document2).setDocumentType((String) any());
        doNothing().when(document2).setDocument_Id((Integer) any());
        doNothing().when(document2).setUserDetails((UserDetails) any());
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        BaseResponse<Document> actualSaveDocumentResult = documentServiceImplement.saveDocument(document2);
        assertEquals("success", actualSaveDocumentResult.getReasonCode());
        assertSame(document1, actualSaveDocumentResult.getResponseObject());
        assertEquals("Saved", actualSaveDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentLog).getDocumentLog_Id();
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).save((Document) any());
        verify(document2).getDocumentData();
        verify(document2).getDocumentType();
        verify(document2).setDeleted((Boolean) any());
        verify(document2, atLeast(1)).setDocumentData((String) any());
        verify(document2).setDocumentLogs((List<DocumentLog>) any());
        verify(document2).setDocumentName((String) any());
        verify(document2).setDocumentType((String) any());
        verify(document2).setDocument_Id((Integer) any());
        verify(document2).setUserDetails((UserDetails) any());
    }


    @Test
    void testSaveDocument5() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(0);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);
        Document document2 = mock(Document.class);
        when(document2.getDocumentData()).thenReturn("Document Data");
        when(document2.getDocumentType()).thenReturn("pdf");
        doNothing().when(document2).setDeleted((Boolean) any());
        doNothing().when(document2).setDocumentData((String) any());
        doNothing().when(document2).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document2).setDocumentName((String) any());
        doNothing().when(document2).setDocumentType((String) any());
        doNothing().when(document2).setDocument_Id((Integer) any());
        doNothing().when(document2).setUserDetails((UserDetails) any());
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        BaseResponse<Document> actualSaveDocumentResult = documentServiceImplement.saveDocument(document2);
        assertEquals("fail", actualSaveDocumentResult.getStatus());
        assertEquals("Document is updated but log is not update", actualSaveDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentLog).getDocumentLog_Id();
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).save((Document) any());
        verify(document2).getDocumentData();
        verify(document2).getDocumentType();
        verify(document2).setDeleted((Boolean) any());
        verify(document2, atLeast(1)).setDocumentData((String) any());
        verify(document2).setDocumentLogs((List<DocumentLog>) any());
        verify(document2).setDocumentName((String) any());
        verify(document2).setDocumentType((String) any());
        verify(document2).setDocument_Id((Integer) any());
        verify(document2).setUserDetails((UserDetails) any());
    }


    @Test
    void testSaveDocument6() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(123);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);
        Document document1 = mock(Document.class);
        when(document1.getDocument_Id()).thenReturn(0);
        doNothing().when(document1).setDeleted((Boolean) any());
        doNothing().when(document1).setDocumentData((String) any());
        doNothing().when(document1).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document1).setDocumentName((String) any());
        doNothing().when(document1).setDocumentType((String) any());
        doNothing().when(document1).setDocument_Id((Integer) any());
        doNothing().when(document1).setUserDetails((UserDetails) any());
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);
        Document document2 = mock(Document.class);
        when(document2.getDocumentData()).thenReturn("Document Data");
        when(document2.getDocumentType()).thenReturn("pdf");
        doNothing().when(document2).setDeleted((Boolean) any());
        doNothing().when(document2).setDocumentData((String) any());
        doNothing().when(document2).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document2).setDocumentName((String) any());
        doNothing().when(document2).setDocumentType((String) any());
        doNothing().when(document2).setDocument_Id((Integer) any());
        doNothing().when(document2).setUserDetails((UserDetails) any());
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        BaseResponse<Document> actualSaveDocumentResult = documentServiceImplement.saveDocument(document2);
        assertEquals("fail", actualSaveDocumentResult.getStatus());
        assertEquals("Document is not save", actualSaveDocumentResult.getReasonText());
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).save((Document) any());
        verify(document1).getDocument_Id();
        verify(document1).setDeleted((Boolean) any());
        verify(document1).setDocumentData((String) any());
        verify(document1).setDocumentLogs((List<DocumentLog>) any());
        verify(document1).setDocumentName((String) any());
        verify(document1).setDocumentType((String) any());
        verify(document1).setDocument_Id((Integer) any());
        verify(document1).setUserDetails((UserDetails) any());
        verify(document2).getDocumentData();
        verify(document2).getDocumentType();
        verify(document2).setDeleted((Boolean) any());
        verify(document2, atLeast(1)).setDocumentData((String) any());
        verify(document2).setDocumentLogs((List<DocumentLog>) any());
        verify(document2).setDocumentName((String) any());
        verify(document2).setDocumentType((String) any());
        verify(document2).setDocument_Id((Integer) any());
        verify(document2).setUserDetails((UserDetails) any());
    }

    @Test
    void testUpadteDocument() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        BaseResponse<Document> actualUpadteDocumentResult = documentServiceImplement.upadteDocument(document);
        assertEquals("fail", actualUpadteDocumentResult.getStatus());
        assertNull(actualUpadteDocumentResult.getResponseObject());
        assertEquals("File type should me pdf", actualUpadteDocumentResult.getReasonText());
    }


    @Test
    void testUpadteDocument2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);
        Document document = mock(Document.class);
        when(document.getDocumentType()).thenReturn("Document Type");
        doNothing().when(document).setDeleted((Boolean) any());
        doNothing().when(document).setDocumentData((String) any());
        doNothing().when(document).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document).setDocumentName((String) any());
        doNothing().when(document).setDocumentType((String) any());
        doNothing().when(document).setDocument_Id((Integer) any());
        doNothing().when(document).setUserDetails((UserDetails) any());
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        BaseResponse<Document> actualUpadteDocumentResult = documentServiceImplement.upadteDocument(document);
        assertEquals("fail", actualUpadteDocumentResult.getStatus());
        assertNull(actualUpadteDocumentResult.getResponseObject());
        assertEquals("File type should me pdf", actualUpadteDocumentResult.getReasonText());
        verify(document).getDocumentType();
        verify(document).setDeleted((Boolean) any());
        verify(document).setDocumentData((String) any());
        verify(document).setDocumentLogs((List<DocumentLog>) any());
        verify(document).setDocumentName((String) any());
        verify(document).setDocumentType((String) any());
        verify(document).setDocument_Id((Integer) any());
        verify(document).setUserDetails((UserDetails) any());
    }


    @Test
    void testUpadteDocument3() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        DocumentLog documentLog = new DocumentLog();
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);

        Document document2 = new Document();
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document2);
        when(documentRepositoryInterface.getById((Integer) any())).thenReturn(document1);

        UserDetails userDetails3 = new UserDetails();
        userDetails3.setDocuments(new ArrayList<>());
        userDetails3.setUserName("demoUser");
        userDetails3.setUser_Id(123);
        when(userRepositoryInterface.getById((Integer) any())).thenReturn(userDetails3);

        UserDetails userDetails4 = new UserDetails();
        userDetails4.setDocuments(new ArrayList<>());
        userDetails4.setUserName("demoUser");
        userDetails4.setUser_Id(123);

        UserDetails userDetails5 = new UserDetails();
        userDetails5.setDocuments(new ArrayList<>());
        userDetails5.setUserName("demoUser");
        userDetails5.setUser_Id(123);
        Document document3 = mock(Document.class);
        when(document3.getDocumentData()).thenReturn("Document Data");
        when(document3.getUserDetails()).thenReturn(userDetails5);
        when(document3.getDocument_Id()).thenReturn(123);
        when(document3.getDocumentType()).thenReturn("pdf");
        doNothing().when(document3).setDeleted((Boolean) any());
        doNothing().when(document3).setDocumentData((String) any());
        doNothing().when(document3).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document3).setDocumentName((String) any());
        doNothing().when(document3).setDocumentType((String) any());
        doNothing().when(document3).setDocument_Id((Integer) any());
        doNothing().when(document3).setUserDetails((UserDetails) any());
        document3.setDeleted(true);
        document3.setDocumentData("Document Data");
        document3.setDocumentLogs(new ArrayList<>());
        document3.setDocumentName("Document Name");
        document3.setDocumentType("Document Type");
        document3.setDocument_Id(123);
        document3.setUserDetails(userDetails4);
        BaseResponse<Document> actualUpadteDocumentResult = documentServiceImplement.upadteDocument(document3);
        assertEquals("success", actualUpadteDocumentResult.getReasonCode());
        assertEquals("Updated", actualUpadteDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentRepositoryInterface).getById((Integer) any());
        verify(documentRepositoryInterface).save((Document) any());
        verify(userRepositoryInterface).getById((Integer) any());
        verify(document3, atLeast(1)).getUserDetails();
        verify(document3, atLeast(1)).getDocument_Id();
        verify(document3).getDocumentData();
        verify(document3).getDocumentType();
        verify(document3).setDeleted((Boolean) any());
        verify(document3, atLeast(1)).setDocumentData((String) any());
        verify(document3).setDocumentLogs((List<DocumentLog>) any());
        verify(document3).setDocumentName((String) any());
        verify(document3).setDocumentType((String) any());
        verify(document3).setDocument_Id((Integer) any());
        verify(document3, atLeast(1)).setUserDetails((UserDetails) any());
    }


    @Test
    void testUpadteDocument4() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(123);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);

        Document document2 = new Document();
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document2);
        when(documentRepositoryInterface.getById((Integer) any())).thenReturn(document1);

        UserDetails userDetails3 = new UserDetails();
        userDetails3.setDocuments(new ArrayList<>());
        userDetails3.setUserName("demoUser");
        userDetails3.setUser_Id(123);
        when(userRepositoryInterface.getById((Integer) any())).thenReturn(userDetails3);

        UserDetails userDetails4 = new UserDetails();
        userDetails4.setDocuments(new ArrayList<>());
        userDetails4.setUserName("demoUser");
        userDetails4.setUser_Id(123);

        UserDetails userDetails5 = new UserDetails();
        userDetails5.setDocuments(new ArrayList<>());
        userDetails5.setUserName("demoUser");
        userDetails5.setUser_Id(123);
        Document document3 = mock(Document.class);
        when(document3.getDocumentData()).thenReturn("Document Data");
        when(document3.getUserDetails()).thenReturn(userDetails5);
        when(document3.getDocument_Id()).thenReturn(123);
        when(document3.getDocumentType()).thenReturn("pdf");
        doNothing().when(document3).setDeleted((Boolean) any());
        doNothing().when(document3).setDocumentData((String) any());
        doNothing().when(document3).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document3).setDocumentName((String) any());
        doNothing().when(document3).setDocumentType((String) any());
        doNothing().when(document3).setDocument_Id((Integer) any());
        doNothing().when(document3).setUserDetails((UserDetails) any());
        document3.setDeleted(true);
        document3.setDocumentData("Document Data");
        document3.setDocumentLogs(new ArrayList<>());
        document3.setDocumentName("Document Name");
        document3.setDocumentType("Document Type");
        document3.setDocument_Id(123);
        document3.setUserDetails(userDetails4);
        BaseResponse<Document> actualUpadteDocumentResult = documentServiceImplement.upadteDocument(document3);
        assertEquals("success", actualUpadteDocumentResult.getReasonCode());
        assertEquals("Updated", actualUpadteDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentLog).getDocumentLog_Id();
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).getById((Integer) any());
        verify(documentRepositoryInterface).save((Document) any());
        verify(userRepositoryInterface).getById((Integer) any());
        verify(document3, atLeast(1)).getUserDetails();
        verify(document3, atLeast(1)).getDocument_Id();
        verify(document3).getDocumentData();
        verify(document3).getDocumentType();
        verify(document3).setDeleted((Boolean) any());
        verify(document3, atLeast(1)).setDocumentData((String) any());
        verify(document3).setDocumentLogs((List<DocumentLog>) any());
        verify(document3).setDocumentName((String) any());
        verify(document3).setDocumentType((String) any());
        verify(document3).setDocument_Id((Integer) any());
        verify(document3, atLeast(1)).setUserDetails((UserDetails) any());
    }


    @Test
    void testUpadteDocument5() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(0);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);

        Document document2 = new Document();
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document2);
        when(documentRepositoryInterface.getById((Integer) any())).thenReturn(document1);

        UserDetails userDetails3 = new UserDetails();
        userDetails3.setDocuments(new ArrayList<>());
        userDetails3.setUserName("demoUser");
        userDetails3.setUser_Id(123);
        when(userRepositoryInterface.getById((Integer) any())).thenReturn(userDetails3);

        UserDetails userDetails4 = new UserDetails();
        userDetails4.setDocuments(new ArrayList<>());
        userDetails4.setUserName("demoUser");
        userDetails4.setUser_Id(123);

        UserDetails userDetails5 = new UserDetails();
        userDetails5.setDocuments(new ArrayList<>());
        userDetails5.setUserName("demoUser");
        userDetails5.setUser_Id(123);
        Document document3 = mock(Document.class);
        when(document3.getDocumentData()).thenReturn("Document Data");
        when(document3.getUserDetails()).thenReturn(userDetails5);
        when(document3.getDocument_Id()).thenReturn(123);
        when(document3.getDocumentType()).thenReturn("pdf");
        doNothing().when(document3).setDeleted((Boolean) any());
        doNothing().when(document3).setDocumentData((String) any());
        doNothing().when(document3).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document3).setDocumentName((String) any());
        doNothing().when(document3).setDocumentType((String) any());
        doNothing().when(document3).setDocument_Id((Integer) any());
        doNothing().when(document3).setUserDetails((UserDetails) any());
        document3.setDeleted(true);
        document3.setDocumentData("Document Data");
        document3.setDocumentLogs(new ArrayList<>());
        document3.setDocumentName("Document Name");
        document3.setDocumentType("Document Type");
        document3.setDocument_Id(123);
        document3.setUserDetails(userDetails4);
        BaseResponse<Document> actualUpadteDocumentResult = documentServiceImplement.upadteDocument(document3);
        assertEquals("fail", actualUpadteDocumentResult.getStatus());
        assertEquals("Document is updated but log is not update", actualUpadteDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentLog).getDocumentLog_Id();
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).getById((Integer) any());
        verify(documentRepositoryInterface).save((Document) any());
        verify(userRepositoryInterface).getById((Integer) any());
        verify(document3, atLeast(1)).getUserDetails();
        verify(document3, atLeast(1)).getDocument_Id();
        verify(document3).getDocumentData();
        verify(document3).getDocumentType();
        verify(document3).setDeleted((Boolean) any());
        verify(document3, atLeast(1)).setDocumentData((String) any());
        verify(document3).setDocumentLogs((List<DocumentLog>) any());
        verify(document3).setDocumentName((String) any());
        verify(document3).setDocumentType((String) any());
        verify(document3).setDocument_Id((Integer) any());
        verify(document3, atLeast(1)).setUserDetails((UserDetails) any());
    }



    @Test
    void testGetDocument2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("42");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        Optional<Document> ofResult = Optional.of(document);
        when(documentRepositoryInterface.findById((Integer) any())).thenReturn(ofResult);
        BaseResponse<Document> actualDocument = documentServiceImplement.getDocument(1);
        assertEquals("success", actualDocument.getReasonCode());
        Document responseObject = actualDocument.getResponseObject();
        assertSame(document, responseObject);
        assertEquals("ã", responseObject.getDocumentData());
        verify(documentRepositoryInterface).findById((Integer) any());
    }


    @Test
    void testGetDocument3() throws Exception {
        when(documentRepositoryInterface.findById((Integer) any())).thenReturn(Optional.empty());
        BaseResponse<Document> actualDocument = documentServiceImplement.getDocument(1);
        assertEquals("fail", actualDocument.getStatus());
        assertEquals("Internal error occur", actualDocument.getReasonText());
        verify(documentRepositoryInterface).findById((Integer) any());
    }


    @Test
    void testDeleteDocument() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        DocumentLog documentLog = new DocumentLog();
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);

        Document document2 = new Document();
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document2);
        when(documentRepositoryInterface.getById((Integer) any())).thenReturn(document1);
        BaseResponse<Document> actualDeleteDocumentResult = documentServiceImplement.deleteDocument(1);
        assertEquals("success", actualDeleteDocumentResult.getReasonCode());
        assertNull(actualDeleteDocumentResult.getResponseObject());
        assertEquals("Document is deleted", actualDeleteDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentRepositoryInterface).getById((Integer) any());
        verify(documentRepositoryInterface).save((Document) any());
    }


    @Test
    void testDeleteDocument2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(123);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);

        Document document2 = new Document();
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document2);
        when(documentRepositoryInterface.getById((Integer) any())).thenReturn(document1);
        BaseResponse<Document> actualDeleteDocumentResult = documentServiceImplement.deleteDocument(1);
        assertEquals("success", actualDeleteDocumentResult.getReasonCode());
        assertNull(actualDeleteDocumentResult.getResponseObject());
        assertEquals("Document is deleted", actualDeleteDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentLog).getDocumentLog_Id();
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).getById((Integer) any());
        verify(documentRepositoryInterface).save((Document) any());
    }


    @Test
    void testDeleteDocument3() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(0);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);

        Document document2 = new Document();
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document2);
        when(documentRepositoryInterface.getById((Integer) any())).thenReturn(document1);
        BaseResponse<Document> actualDeleteDocumentResult = documentServiceImplement.deleteDocument(1);
        assertEquals("fail", actualDeleteDocumentResult.getStatus());
        assertEquals("Document is log is updated ", actualDeleteDocumentResult.getReasonText());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentLog).getDocumentLog_Id();
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).getById((Integer) any());
        verify(documentRepositoryInterface).save((Document) any());
    }


    @Test
    void testDeleteDocument4() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("Document Name");
        document.setDocumentType("Document Type");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);
        DocumentLog documentLog = mock(DocumentLog.class);
        when(documentLog.getDocumentLog_Id()).thenReturn(null);
        doNothing().when(documentLog).setDocumentLog_Id((Integer) any());
        doNothing().when(documentLog).setDocumentModifedTime((LocalDateTime) any());
        doNothing().when(documentLog).setDocuments((Document) any());
        documentLog.setDocumentLog_Id(123);
        documentLog.setDocumentModifedTime(LocalDateTime.of(1, 1, 1, 1, 1));
        documentLog.setDocuments(document);
        when(documentLogRepositoryInterface.save((DocumentLog) any())).thenReturn(documentLog);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("demoUser");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("Document Name");
        document1.setDocumentType("Document Type");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(new ArrayList<>());
        userDetails2.setUserName("demoUser");
        userDetails2.setUser_Id(123);

        Document document2 = new Document();
        document2.setDeleted(true);
        document2.setDocumentData("Document Data");
        document2.setDocumentLogs(new ArrayList<>());
        document2.setDocumentName("Document Name");
        document2.setDocumentType("Document Type");
        document2.setDocument_Id(123);
        document2.setUserDetails(userDetails2);
        when(documentRepositoryInterface.save((Document) any())).thenReturn(document2);
        when(documentRepositoryInterface.getById((Integer) any())).thenReturn(document1);
        BaseResponse<Document> actualDeleteDocumentResult = documentServiceImplement.deleteDocument(1);
        assertNull(actualDeleteDocumentResult.getReasonCode());
        assertEquals("fail", actualDeleteDocumentResult.getStatus());
        assertNull(actualDeleteDocumentResult.getResponseObject());
        verify(documentLogRepositoryInterface).save((DocumentLog) any());
        verify(documentLog).getDocumentLog_Id();
        verify(documentLog).setDocumentLog_Id((Integer) any());
        verify(documentLog).setDocumentModifedTime((LocalDateTime) any());
        verify(documentLog).setDocuments((Document) any());
        verify(documentRepositoryInterface).getById((Integer) any());
        verify(documentRepositoryInterface).save((Document) any());
    }


    @Test
    void testGetDocumentByUser() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("demoUser");
        userDetails.setUser_Id(123);
        Optional<UserDetails> ofResult = Optional.of(userDetails);
        when(userRepositoryInterface.findById((Integer) any())).thenReturn(ofResult);
        BaseResponse<UserDetails> actualDocumentByUser = documentServiceImplement.getDocumentByUser(1);
        assertEquals("success", actualDocumentByUser.getReasonCode());
        assertSame(userDetails, actualDocumentByUser.getResponseObject());
        assertEquals("Suceess", actualDocumentByUser.getReasonText());
        verify(userRepositoryInterface).findById((Integer) any());
    }


}

