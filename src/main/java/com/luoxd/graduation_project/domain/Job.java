package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class Job {
    private Integer jobId;

    private String jobName;

    private String jobClassesId;

    private Integer expCondition;

    private Integer eduCondition;

    private Integer companyId;

    private String tag;

    private Integer recruiterId;

    private String workAddress;
}
