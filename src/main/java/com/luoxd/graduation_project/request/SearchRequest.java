package com.luoxd.graduation_project.request;

import lombok.Data;

@Data
public class SearchRequest {
    private String keyword;
    private Integer jobClassesId;
    private String jobClassesName;
    private Integer expCondition;
    private Integer eduCondition;
    private Integer salCondition;
    private Integer finanCondition;
    private Integer sizeCondition;
    private Integer postDateCondition;
}
