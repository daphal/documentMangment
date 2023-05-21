package com.abcBank.documentMangment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.Document;
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
        userDetails.setUserName("xyzuser");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("xyzuser");
        userDetails1.setUser_Id(123);
        BaseResponse<UserDetails> actualSaveUserResult = userServiceImplement.saveUser(userDetails1);
        assertEquals("success", actualSaveUserResult.getReasonCode());
        assertSame(userDetails, actualSaveUserResult.getResponseObject());
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
        userDetails.setUserName("xyzuser");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("xyzuser");
        userDetails1.setUser_Id(123);
        assertEquals("fail", userServiceImplement.saveUser(userDetails1).getStatus());
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
        userDetails.setUserName("xyzuser");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("xyzuser");
        userDetails1.setUser_Id(123);
        BaseResponse<UserDetails> actualUpadteUserResult = userServiceImplement.upadteUser(userDetails1);
        assertEquals("success", actualUpadteUserResult.getReasonCode());
        assertSame(userDetails, actualUpadteUserResult.getResponseObject());
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
        userDetails.setUserName("xyzuser");
        userDetails.setUser_Id(123);
        when(userRepositoryInterface.save((UserDetails) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("xyzuser");
        userDetails1.setUser_Id(123);
        assertEquals("fail", userServiceImplement.upadteUser(userDetails1).getStatus());
        verify(userRepositoryInterface).save((UserDetails) any());
        verify(userDetails).getUser_Id();
        verify(userDetails).setDocuments((List<Document>) any());
        verify(userDetails).setUserName((String) any());
        verify(userDetails).setUser_Id((Integer) any());
    }

   
    @Test
    void testGetUser() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("xyzuser");
        userDetails.setUser_Id(123);
        Optional<UserDetails> ofResult = Optional.of(userDetails);
        when(userRepositoryInterface.findById((Integer) any())).thenReturn(ofResult);
        BaseResponse<UserDetails> actualUser = userServiceImplement.getUser(1);
        assertEquals("success", actualUser.getReasonCode());
        assertSame(userDetails, actualUser.getResponseObject());
        verify(userRepositoryInterface).findById((Integer) any());
    }

  
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUser2() throws Exception {
        when(userRepositoryInterface.findById((Integer) any())).thenReturn(null);
        userServiceImplement.getUser(1);
    }

  
    @Test
    void testGetAllUser() throws Exception {
        when(userRepositoryInterface.findAll()).thenReturn(new ArrayList<>());
        assertEquals("fail", userServiceImplement.getAllUser().getStatus());
        verify(userRepositoryInterface).findAll();
    }

   
    @Test
    void testGetAllUser2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("xyzuser");
        userDetails.setUser_Id(123);

        ArrayList<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(userDetails);
        when(userRepositoryInterface.findAll()).thenReturn(userDetailsList);
        BaseResponse<UserDetails> actualAllUser = userServiceImplement.getAllUser();
        assertEquals("success", actualAllUser.getReasonCode());
        assertEquals(1, actualAllUser.getResponseListObject().size());
        verify(userRepositoryInterface).findAll();
    }

   
    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userRepositoryInterface).deleteById((Integer) any());
        assertEquals("success", userServiceImplement.deleteUser(1).getStatus());
        verify(userRepositoryInterface).deleteById((Integer) any());
    }
}

