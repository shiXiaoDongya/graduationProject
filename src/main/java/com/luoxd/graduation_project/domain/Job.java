package com.luoxd.graduation_project.domain;

import lombok.Data;

import java.util.List;

@Data
public class Job {
    private Integer jobId;

    private String jobName;

    private String jobDetail;

    private Integer expCondition;

    private Integer eduCondition;

    private Integer salary;

    private Integer reCompanyId;

    private String tag;

    private Integer recruiterId;

    private String reRealname;

    private String reCompanyPosition;

    private String workCity;

    private String workAddress;

    private String jobClassesId;

    private String companyName;

    private String industry;

    private Integer finanCondition;

    private Integer sizeCondition;
}
