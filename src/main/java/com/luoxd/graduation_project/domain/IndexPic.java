package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class IndexPic {
    private Integer indexPicId;
    private String pic;
    private Integer jobId;
    private String jobName;
    private String updateTime;
    private Integer updateUser;
    private String adminUsername;
}
