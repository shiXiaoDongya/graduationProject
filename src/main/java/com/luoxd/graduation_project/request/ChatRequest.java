package com.luoxd.graduation_project.request;

import lombok.Data;

@Data
public class ChatRequest {
    private Integer jsId;
    private Integer reId;
    private Integer jobId;
    private String chatContent;
    private String chatSendTime;
    private Integer isJsSend;
    private Integer isRead;
}
