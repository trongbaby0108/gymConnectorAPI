package com.Code.Model.Request;

import lombok.Data;

@Data
public class commentPtRequest {
    private String content;
    private float vote;
    private int ptId;
    private int userId;
}
