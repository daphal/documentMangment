package com.abcBank.documentMangment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.abcBank.documentMangment.repository.DocumentRepositoryInterface;
import com.abcBank.documentMangment.repository.UserRepositoryInterface;

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

@ContextConfiguration(classes = {UserServiceImplement.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplementTest {
    @MockBean
    private DocumentRepositoryInterface documentRepositoryInterface;

    @MockBean
    private UserRepositoryInterface userRepositoryInterface;

    @Autowired
    private UserServiceImplement userServiceImplement;


    @Test
    void testSaveUser() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("abcUSer");
        userDetails1.setUser_Id(123);
        BaseResponse<UserDetails> actualSaveUserResult = userServiceImplement.saveUser(userDetails1);
        assertEquals("success", actualSaveUserResult.getReasonCode());
        assertSame(userDetails, actualSaveUserResult.getResponseObject());
        assertEquals("Save", actualSaveUserResult.getReasonText());
        verify(userRepositoryInterface).save((UserDetails) any());
    }

    @Test
    void testSaveUser2() throws Exception {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUser_Id()).thenReturn(-1);
        doNothing().when(userDetails).setDocuments((List<Document>) any());
        doNothing().when(userDetails).setUserName((String) any());
        doNothing().when(userDetails).setUser_Id((Integer) any());
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("abcUSer");
        userDetails1.setUser_Id(123);
        BaseResponse<UserDetails> actualSaveUserResult = userServiceImplement.saveUser(userDetails1);
        assertEquals("500", actualSaveUserResult.getReasonCode());
        assertEquals("fail", actualSaveUserResult.getStatus());
        assertEquals("error", actualSaveUserResult.getReasonText());
        verify(userRepositoryInterface).save((UserDetails) any());
        verify(userDetails).getUser_Id();
        verify(userDetails).setDocuments((List<Document>) any());
        verify(userDetails).setUserName((String) any());
        verify(userDetails).setUser_Id((Integer) any());
    }


    @Test
    void testUpadteUser() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("abcUSer");
        userDetails1.setUser_Id(123);
        BaseResponse<UserDetails> actualUpadteUserResult = userServiceImplement.upadteUser(userDetails1);
        assertEquals("success", actualUpadteUserResult.getReasonCode());
        assertSame(userDetails, actualUpadteUserResult.getResponseObject());
        assertEquals("get", actualUpadteUserResult.getReasonText());
        verify(userRepositoryInterface).save((UserDetails) any());
    }


    @Test
    void testUpadteUser2() throws Exception {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUser_Id()).thenReturn(-1);
        doNothing().when(userDetails).setDocuments((List<Document>) any());
        doNothing().when(userDetails).setUserName((String) any());
        doNothing().when(userDetails).setUser_Id((Integer) any());
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("abcUSer");
        userDetails1.setUser_Id(123);
        BaseResponse<UserDetails> actualUpadteUserResult = userServiceImplement.upadteUser(userDetails1);
        assertEquals("500", actualUpadteUserResult.getReasonCode());
        assertEquals("fail", actualUpadteUserResult.getStatus());
        assertEquals("error", actualUpadteUserResult.getReasonText());
        verify(userRepositoryInterface).save((UserDetails) any());
        verify(userDetails).getUser_Id();
        verify(userDetails).setDocuments((List<Document>) any());
        verify(userDetails).setUserName((String) any());
        verify(userDetails).setUser_Id((Integer) any());
    }


    @Test
    void testGetUser() throws Exception {
        UserDetails userDetails = new UserDetails();
        ArrayList<Document> documentList = new ArrayList<>();
        userDetails.setDocuments(documentList);
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);
        Optional<UserDetails> ofResult = Optional.of(userDetails);
        when(userRepositoryInterface.findById((Integer) any())).thenReturn(ofResult);
        BaseResponse<UserDetails> actualUser = userServiceImplement.getUser(1);
        assertEquals("200", actualUser.getReasonCode());
        assertEquals("success", actualUser.getStatus());
        UserDetails responseObject = actualUser.getResponseObject();
        assertSame(userDetails, responseObject);
        assertEquals("get", actualUser.getReasonText());
        assertEquals(documentList, responseObject.getDocuments());
        verify(userRepositoryInterface).findById((Integer) any());
    }


    @Test
    void testGetUser2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("get");
        ArrayList<DocumentLog> documentLogList = new ArrayList<>();
        document.setDocumentLogs(documentLogList);
        document.setDocumentName("get");
        document.setDocumentType("get");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        ArrayList<Document> documentList = new ArrayList<>();
        documentList.add(document);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(documentList);
        userDetails1.setUserName("abcUSer");
        userDetails1.setUser_Id(123);
        Optional<UserDetails> ofResult = Optional.of(userDetails1);
        when(userRepositoryInterface.findById((Integer) any())).thenReturn(ofResult);
        BaseResponse<UserDetails> actualUser = userServiceImplement.getUser(1);
        assertEquals("200", actualUser.getReasonCode());
        assertEquals("success", actualUser.getStatus());
        UserDetails responseObject = actualUser.getResponseObject();
        assertSame(userDetails1, responseObject);
        assertEquals("get", actualUser.getReasonText());
        assertEquals(documentLogList, responseObject.getDocuments());
        verify(userRepositoryInterface).findById((Integer) any());
    }


    @Test
    void testGetUser3() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("get");
        ArrayList<DocumentLog> documentLogList = new ArrayList<>();
        document.setDocumentLogs(documentLogList);
        document.setDocumentName("get");
        document.setDocumentType("get");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("abcUSer");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("get");
        document1.setDocumentLogs(new ArrayList<>());
        document1.setDocumentName("get");
        document1.setDocumentType("get");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);

        ArrayList<Document> documentList = new ArrayList<>();
        documentList.add(document1);
        documentList.add(document);

        UserDetails userDetails2 = new UserDetails();
        userDetails2.setDocuments(documentList);
        userDetails2.setUserName("abcUSer");
        userDetails2.setUser_Id(123);
        Optional<UserDetails> ofResult = Optional.of(userDetails2);
        when(userRepositoryInterface.findById((Integer) any())).thenReturn(ofResult);
        BaseResponse<UserDetails> actualUser = userServiceImplement.getUser(1);
        assertEquals("200", actualUser.getReasonCode());
        assertEquals("success", actualUser.getStatus());
        UserDetails responseObject = actualUser.getResponseObject();
        assertSame(userDetails2, responseObject);
        assertEquals("get", actualUser.getReasonText());
        assertEquals(documentLogList, responseObject.getDocuments());
        verify(userRepositoryInterface).findById((Integer) any());
    }


    @Test
    void testGetUser6() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);
        Document document = mock(Document.class);
        when(document.getDeleted()).thenReturn(false);
        when(document.getDocumentData()).thenReturn("foo");
        doNothing().when(document).setDeleted((Boolean) any());
        doNothing().when(document).setDocumentData((String) any());
        doNothing().when(document).setDocumentLogs((List<DocumentLog>) any());
        doNothing().when(document).setDocumentName((String) any());
        doNothing().when(document).setDocumentType((String) any());
        doNothing().when(document).setDocument_Id((Integer) any());
        doNothing().when(document).setUserDetails((UserDetails) any());
        document.setDeleted(true);
        document.setDocumentData("get");
        document.setDocumentLogs(new ArrayList<>());
        document.setDocumentName("get");
        document.setDocumentType("get");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        ArrayList<Document> documentList = new ArrayList<>();
        documentList.add(document);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(documentList);
        userDetails1.setUserName("abcUSer");
        userDetails1.setUser_Id(123);
        Optional<UserDetails> ofResult = Optional.of(userDetails1);
        when(userRepositoryInterface.findById((Integer) any())).thenReturn(ofResult);
        BaseResponse<UserDetails> actualUser = userServiceImplement.getUser(1);
        assertEquals("200", actualUser.getReasonCode());
        assertEquals("success", actualUser.getStatus());
        UserDetails responseObject = actualUser.getResponseObject();
        assertSame(userDetails1, responseObject);
        assertEquals("get", actualUser.getReasonText());
        assertEquals(documentList, responseObject.getDocuments());
        verify(userRepositoryInterface).findById((Integer) any());
        verify(document).getDeleted();
        verify(document).getDocumentData();
        verify(document).setDeleted((Boolean) any());
        verify(document, atLeast(1)).setDocumentData((String) any());
        verify(document).setDocumentLogs((List<DocumentLog>) any());
        verify(document).setDocumentName((String) any());
        verify(document).setDocumentType((String) any());
        verify(document).setDocument_Id((Integer) any());
        verify(document).setUserDetails((UserDetails) any());
    }


    @Test
    void testGetAllUser() throws Exception {
        when(userRepositoryInterface.findAll()).thenReturn(new ArrayList<>());
        BaseResponse<UserDetails> actualAllUser = userServiceImplement.getAllUser();
        assertEquals("500", actualAllUser.getReasonCode());
        assertEquals("fail", actualAllUser.getStatus());
        assertEquals("error", actualAllUser.getReasonText());
        verify(userRepositoryInterface).findAll();
    }

    @Test
    void testGetAllUser2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("abcUSer");
        userDetails.setUser_Id(123);

        ArrayList<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(userDetails);
        when(userRepositoryInterface.findAll()).thenReturn(userDetailsList);
        BaseResponse<UserDetails> actualAllUser = userServiceImplement.getAllUser();
        assertEquals("success", actualAllUser.getReasonCode());
        assertEquals("OK", actualAllUser.getStatus());
        assertEquals(1, actualAllUser.getResponseListObject().size());
        assertEquals("Get", actualAllUser.getReasonText());
        verify(userRepositoryInterface).findAll();
    }


    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userRepositoryInterface).deleteById((Integer) any());
        BaseResponse<UserDetails> actualDeleteUserResult = userServiceImplement.deleteUser(1);
        assertEquals("200", actualDeleteUserResult.getReasonCode());
        assertEquals("success", actualDeleteUserResult.getStatus());
        assertEquals("deleted", actualDeleteUserResult.getReasonText());
        verify(userRepositoryInterface).deleteById((Integer) any());
    }
}

