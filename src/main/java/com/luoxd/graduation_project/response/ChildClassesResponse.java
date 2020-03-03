package com.luoxd.graduation_project.response;

import com.luoxd.graduation_project.domain.JobClasses;
import lombok.Data;

import java.util.List;

@Data
public class ChildClassesResponse {
    private Integer childClassesId;

    private String childClassesName;

    private List<JobClasses> jobClassesList;
}
