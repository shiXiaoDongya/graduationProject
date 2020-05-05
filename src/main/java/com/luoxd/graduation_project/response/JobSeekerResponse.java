package com.luoxd.graduation_project.response;

import lombok.Data;

@Data
public class JobSeekerResponse {
    private Integer jsId;
    private String jsUsername;
    private String jsRealname;
    private String jsHeadImg;
    private Integer jsAge;
    private String jsGender;
    private String jsPhone;
    private String jsEmail;
    private String jsCollege;
}
