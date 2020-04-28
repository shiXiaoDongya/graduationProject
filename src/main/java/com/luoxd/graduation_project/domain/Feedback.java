package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class Feedback {
    private Integer feedbackId;
    private Integer jsId;
    private String feedbackContent;
    private String 	feedbackSendTime;
    private Integer adminId;
    private String feedbackReply;
    private String feedbackReplytime;
}
