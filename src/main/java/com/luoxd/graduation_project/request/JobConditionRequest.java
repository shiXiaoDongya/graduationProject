package com.luoxd.graduation_project.request;

import lombok.Data;

@Data
public class JobConditionRequest {
    private Integer jsId;
    private Integer jsExp;
    private Integer jsEdu;
    private Integer jsSal;
    private String jsCollege;
    private String jsTag;
}
