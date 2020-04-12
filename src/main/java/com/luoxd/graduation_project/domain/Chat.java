package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class Chat {
    private Integer chatId;
    private Integer jsId;
    private Integer reId;
    private Integer jobId;
    private String chatContent;
    private String chatSendTime;
    private Integer isJsSend;
}
