package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class Chat {
    private Integer chatId;
    private Integer jsId;
    private String jsRealname;
    private Integer reId;
    private String reRealname;
    private String companyName;
    private String reCompanyPosition;
    private Integer jobId;
    private String chatContent;
    private String chatSendTime;
    private Integer isJsSend;
}
