package com.luoxd.graduation_project.response;

import lombok.Data;

import java.util.List;

@Data
public class JobResponse {
    private Integer jobId;

    private String jobName;

    private String jobDetail;

    private Integer expCondition;

    private String expConditionStr;

    private Integer eduCondition;

    private String eduConditionStr;

    private Integer salary;

    private Integer reCompanyId;

    private String tagsStr;

    private List<String> tags;

    private Integer recruiterId;

    private String reRealname;

    private String reHeadImg;

    private String reCompanyPosition;

    private String workCity;

    private String workAddress;

    private String postDate;

    private String jobClassesId;

    private String jobClassesName;

    private String companyName;

    private String companyHeadImg;

    private String companyDetail;

    private String industry;

    private String finanConditionStr;

    private String sizeConditionStr;

    private List<String> companyTags;

    private Integer jsId;

    private Integer isCollected;
}
