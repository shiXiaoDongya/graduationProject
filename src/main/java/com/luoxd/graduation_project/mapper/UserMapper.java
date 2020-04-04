package com.luoxd.graduation_project.mapper;

import com.luoxd.graduation_project.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> getUsers();

    String getOffMsg(String id);

    List<Classes> queryClassesList();

    List<ChildClasses> queryChildClassesListByClassesId(Integer classesId);

    List<JobClasses> queryJobClassesListByChildClassesId(Integer childClassesId);

    int insertRecruiter(Recruiter recruiter);

    int insertJobSeeker(JobSeeker jobSeeker);
}
