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

    private Integer companyId;

    private String tag;

    private List<String> tags;

    private Integer recruiterId;

    private String workAddress;

    private String jobClassesId;
}
