package com.luoxd.graduation_project.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatResponse {
    private Integer jsId;
    private String jsRealname;
    private String jsHeadImg;
    private String jsCollege;
    private Integer reId;
    private String reRealname;
    private String reHeadImg;
    @JsonProperty("reCompany")
    private String companyName;
    private String reCompanyPosition;
    private Integer jobId;
    private String jobName;
    private Integer notRead;
}
