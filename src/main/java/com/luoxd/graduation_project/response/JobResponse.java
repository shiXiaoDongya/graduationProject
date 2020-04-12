package com.luoxd.graduation_project.response;

import lombok.Data;

import java.util.List;

@Data
public class JobResponse {
    private Integer jobId;

    private String jobName;

    private String jobDetail;

    private Integer expCondition;

    private Integer eduCondition;

    private Integer salary;

    private Integer companyId;

    private List<String> tags;

    private Integer recruiterId;

    private String workCity;

    private String workAddress;

    private String jobClassesId;
}
