package com.luoxd.graduation_project.service;

import com.luoxd.graduation_project.domain.ChildClasses;
import com.luoxd.graduation_project.domain.Classes;
import com.luoxd.graduation_project.domain.Job;
import com.luoxd.graduation_project.domain.JobClasses;
import com.luoxd.graduation_project.response.ClassesResonse;

import java.util.List;

public interface JobService {
    List<ClassesResonse> queryClassesList();

    List<Job> queryJobList();

    List<Classes> queryClassesList2();

    List<ChildClasses> queryChildClassesByClassesId(int classsesIdNum);

    List<JobClasses> queryJobClassesByChildClassesId(int childClassesIdNum);
}
