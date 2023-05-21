package com.abcBank.documentMangment.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.service.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserServiceInterface userServiceInterface;


    @Test
    void testGetUserById() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("janedoe");
        userDetails.setUser_Id(123);

        BaseResponse<UserDetails> baseResponse = new BaseResponse<>();
        baseResponse.setReasonCode("Just cause");
        baseResponse.setReasonText("Just cause");
        baseResponse.setResponseListObject(new ArrayList<>());
        baseResponse.setResponseObject(userDetails);
        baseResponse.setStatus("Status");
        when(userServiceInterface.getUser((Integer) any())).thenReturn(baseResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/api/getUserById/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":\"Status\",\"reasonCode\":\"Just cause\",\"reasonText\":\"Just cause\",\"responseObject\":{\"user_Id\":123"
                                        + ",\"userName\":\"janedoe\",\"documents\":[]},\"responseListObject\":[]}"));
    }


    @Test
    void testSaveUser() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("janedoe");
        userDetails.setUser_Id(123);

        BaseResponse<UserDetails> baseResponse = new BaseResponse<>();
        baseResponse.setReasonCode("Just cause");
        baseResponse.setReasonText("Just cause");
        baseResponse.setResponseListObject(new ArrayList<>());
        baseResponse.setResponseObject(userDetails);
        baseResponse.setStatus("Status");
        when(userServiceInterface.saveUser((UserDetails) any())).thenReturn(baseResponse);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("janedoe");
        userDetails1.setUser_Id(123);
        String content = (new ObjectMapper()).writeValueAsString(userDetails1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/api/saveUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":\"Status\",\"reasonCode\":null,\"reasonText\":\"Just cause\",\"responseObject\":{\"user_Id\":123,\"userName"
                                        + "\":\"janedoe\",\"documents\":[]},\"responseListObject\":null}"));
    }


    @Test
    void testSaveUser2() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new ArrayList<>());
        userDetails.setUserName("janedoe");
        userDetails.setUser_Id(123);

        BaseResponse<UserDetails> baseResponse = new BaseResponse<>();
        baseResponse.setReasonCode("success");
        baseResponse.setReasonText("Just cause");
        baseResponse.setResponseListObject(new ArrayList<>());
        baseResponse.setResponseObject(userDetails);
        baseResponse.setStatus("Status");
        when(userServiceInterface.saveUser((UserDetails) any())).thenReturn(baseResponse);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new ArrayList<>());
        userDetails1.setUserName("janedoe");
        userDetails1.setUser_Id(123);
        String content = (new ObjectMapper()).writeValueAsString(userDetails1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/api/saveUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":\"Ok\",\"reasonCode\":null,\"reasonText\":\"Just cause\",\"responseObject\":{\"user_Id\":123,\"userName"
                                        + "\":\"janedoe\",\"documents\":[]},\"responseListObject\":null}"));
    }
}

