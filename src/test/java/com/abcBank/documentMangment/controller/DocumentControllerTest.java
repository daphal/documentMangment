package com.abcBank.documentMangment.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.abcBank.documentMangment.model.BaseResponse;
import com.abcBank.documentMangment.model.Document;
import com.abcBank.documentMangment.model.UserDetails;
import com.abcBank.documentMangment.service.DocumentServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
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

@ContextConfiguration(classes = {DocumentController.class})
@ExtendWith(SpringExtension.class)
class DocumentControllerTest {
    @Autowired
    private DocumentController documentController;

    @MockBean
    private DocumentServiceInterface documentServiceInterface;


    @Test
    void testSaveDocument() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new HashSet<>());
        userDetails.setUserName("Username");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentName("Document Name");
        document.setDocumentType("PDF");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        BaseResponse<Document> baseResponse = new BaseResponse<>();
        baseResponse.setReasonCode("Just cause");
        baseResponse.setReasonText("Just cause");
        baseResponse.setResponseListObject(new ArrayList<>());
        baseResponse.setResponseObject(document);
        baseResponse.setStatus("Status");
        when(documentServiceInterface.saveDocument((Document) any())).thenReturn(baseResponse);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new HashSet<>());
        userDetails1.setUserName("Username");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentName("Document Name");
        document1.setDocumentType("pdf");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);
        String content = (new ObjectMapper()).writeValueAsString(document1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/saveDocument")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(documentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":\"Status\",\"reasonCode\":\"Just cause\",\"reasonText\":\"Just cause\",\"responseObject\":{\"document_Id"
                                        + "\":123,\"documentName\":\"Document Name\",\"documentData\":\"Document Data\",\"documentType\":\"" +
                                        "PDF\",\"deleted\":true},\"responseListObject\":[]}"));
    }


    @Test
    void testDeleteDocument() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new HashSet<>());
        userDetails.setUserName("Username");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentName("Document Name");
        document.setDocumentType("pdf");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        BaseResponse<Document> baseResponse = new BaseResponse<>();
        baseResponse.setReasonCode("Just cause");
        baseResponse.setReasonText("Just cause");
        baseResponse.setResponseListObject(new ArrayList<>());
        baseResponse.setResponseObject(document);
        baseResponse.setStatus("Status");
        when(documentServiceInterface.deleteDocument((Integer) any())).thenReturn(baseResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/api/deleteDocument/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(documentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":\"Status\",\"reasonCode\":\"Just cause\",\"reasonText\":\"Just cause\",\"responseObject\":{\"document_Id"
                                        + "\":123,\"documentName\":\"Document Name\",\"documentData\":\"Document Data\",\"documentType\":\"" +
                                        "pdf\",\"deleted\":true},\"responseListObject\":[]}"));
    }


    @Test
    void testGetDocumentById() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new HashSet<>());
        userDetails.setUserName("Username");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentName("Document Name");
        document.setDocumentType("pdf");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        BaseResponse<Document> baseResponse = new BaseResponse<>();
        baseResponse.setReasonCode("Just cause");
        baseResponse.setReasonText("Just cause");
        baseResponse.setResponseListObject(new ArrayList<>());
        baseResponse.setResponseObject(document);
        baseResponse.setStatus("Status");
        when(documentServiceInterface.getDocument((Integer) any())).thenReturn(baseResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/api/getDocument/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(documentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":\"Status\",\"reasonCode\":\"Just cause\",\"reasonText\":\"Just cause\",\"responseObject\":{\"document_Id"
                                        + "\":123,\"documentName\":\"Document Name\",\"documentData\":\"Document Data\",\"documentType\":\"pdf\",\"deleted\":true},\"responseListObject\":[]}"));
    }


    @Test
    void testUpdateDocument() throws Exception {
        UserDetails userDetails = new UserDetails();
        userDetails.setDocuments(new HashSet<>());
        userDetails.setUserName("Username");
        userDetails.setUser_Id(123);

        Document document = new Document();
        document.setDeleted(true);
        document.setDocumentData("Document Data");
        document.setDocumentName("Document Name");
        document.setDocumentType("pdf");
        document.setDocument_Id(123);
        document.setUserDetails(userDetails);

        BaseResponse<Document> baseResponse = new BaseResponse<>();
        baseResponse.setReasonCode("Just cause");
        baseResponse.setReasonText("Just cause");
        baseResponse.setResponseListObject(new ArrayList<>());
        baseResponse.setResponseObject(document);
        baseResponse.setStatus("Status");
        when(documentServiceInterface.upadteDocument((Document) any())).thenReturn(baseResponse);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setDocuments(new HashSet<>());
        userDetails1.setUserName("Username");
        userDetails1.setUser_Id(123);

        Document document1 = new Document();
        document1.setDeleted(true);
        document1.setDocumentData("Document Data");
        document1.setDocumentName("Document Name");
        document1.setDocumentType("pdf");
        document1.setDocument_Id(123);
        document1.setUserDetails(userDetails1);
        String content = (new ObjectMapper()).writeValueAsString(document1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/v1/api/updateDocument")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(documentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":\"Status\",\"reasonCode\":\"Just cause\",\"reasonText\":\"Just cause\",\"responseObject\":{\"document_Id"
                                        + "\":123,\"documentName\":\"Document Name\",\"documentData\":\"Document Data\",\"documentType\":\"" +
                                        "pdf\",\"deleted\":true},\"responseListObject\":[]}"));
    }
}

