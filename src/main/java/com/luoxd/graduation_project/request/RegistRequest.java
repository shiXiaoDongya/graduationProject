package com.luoxd.graduation_project.request;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegistRequest {
    String username;
    String password;
    String confirmPassword;
    String phone;
    String email;
    String sq;
    String comfirmSq;
    String company;
    MultipartFile companyPic;
    String id;
}
