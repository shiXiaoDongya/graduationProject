package com.luoxd.graduation_project.domain;

import com.luoxd.graduation_project.response.JobResponse;
import lombok.Data;

import java.util.List;

@Data
public class JobRecommend {
    private String tag;
    private List<JobResponse> jobResponseList;
}
