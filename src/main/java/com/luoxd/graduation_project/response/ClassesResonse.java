package com.luoxd.graduation_project.response;

import com.luoxd.graduation_project.domain.JobClasses;
import lombok.Data;

import java.util.List;

@Data
public class ClassesResonse {
    private String classesName;

    private List<JobClasses> recommendJobClassesList;

    private List<ChildClassesResponse> childClassesList;
}
