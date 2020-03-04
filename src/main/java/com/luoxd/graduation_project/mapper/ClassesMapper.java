package com.luoxd.graduation_project.mapper;

import com.luoxd.graduation_project.domain.ChildClasses;
import com.luoxd.graduation_project.domain.Classes;
import com.luoxd.graduation_project.domain.JobClasses;
import com.luoxd.graduation_project.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassesMapper {
    List<Classes> queryClassesList();

    List<ChildClasses> queryChildClassesListByClassesId(Integer classesId);

    List<JobClasses> queryJobClassesListByChildClassesId(Integer childClassesId);
}