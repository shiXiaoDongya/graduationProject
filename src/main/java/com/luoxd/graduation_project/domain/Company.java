package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class Company {
    private Integer companyId;
    private String companyName;
    private String companyHeadImg;
    private String companyDetail;
    private String industry;
    private Integer finanCondition;
    private Integer sizeCondition;
    private String companyTags;
    private String companyPosition;

}
