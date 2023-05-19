package com.abcBank.documentMangment.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private String reasonCode;
    private String reasonText;
    private T responseObject;
    private List<T> responseListObject;
}
