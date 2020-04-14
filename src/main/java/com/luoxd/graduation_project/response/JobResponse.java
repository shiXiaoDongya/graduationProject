package com.luoxd.graduation_project.response;

import lombok.Data;

import java.util.List;

@Data
public class JobResponse {
    private Integer jobId;

    private String jobName;

    private String jobDetail;

    private String expConditionStr;

    private String eduConditionStr;

    private Integer salary;

    private Integer reCompanyId;

    private List<String> tags;

    private Integer recruiterId;

    private String reRealname;

    private String reCompanyPosition;

    private String workCity;

    private String workAddress;

    private String jobClassesId;

    private String companyName;

    private String companyHeadImg;

    private String industry;

    private String finanConditionStr;

    private String sizeConditionStr;

    private List<String> companyTags;
}
