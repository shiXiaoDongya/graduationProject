package com.luoxd.graduation_project.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class JobSeekerRequest {
    private Integer jsId;
    private String jsRealname;
    private String jsGender;
    private Integer jsAge;
    private String jsPhone;
    private String jsEmail;
    private String oldJsHeadImg;
    private String newJsHeadImg;
    private MultipartFile jsHeadImg;
}
