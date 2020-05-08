package com.luoxd.graduation_project.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RecruiterRequest {
    private Integer reId;
    private String reRealname;
    private String reGender;
    private String rePhone;
    private String reEmail;
    private String oldReHeadImg;
    private String newReHeadImg;
    private MultipartFile reHeadImg;
}
