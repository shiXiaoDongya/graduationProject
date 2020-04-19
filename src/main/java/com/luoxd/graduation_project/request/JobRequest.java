package com.luoxd.graduation_project.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobRequest {
    private Integer jobId;
    private String jobName;
    private String jobDetail;
    private Integer expCondition;
    private Integer eduCondition;
    private Integer salary;
    private String workCity;
    private String workAddress;
    private Integer jobClassesId;
    private String tag;
    private Integer reId;
}
