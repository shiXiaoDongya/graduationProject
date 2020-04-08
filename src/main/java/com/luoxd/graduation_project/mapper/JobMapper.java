package com.luoxd.graduation_project.mapper;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.response.ChatResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JobMapper {
    List<Classes> queryClassesList();

    List<ChildClasses> queryChildClassesListByClassesId(Integer classesId);

    List<JobClasses> queryJobClassesListByChildClassesId(Integer childClassesId);

    int insertToClasses(Classes classes);

    int insertToChildClasses(ChildClasses classes);

    int insertToJobClasses(JobClasses jobClasses);

    int updateRecommendByName(String jobClassesName);

    List<Job> queryJobList();

    List<Classes> queryClasses();

    List<ChildClasses> queryChildClassesByClassesId(int classsesIdNum);

    List<JobClasses> queryJobClassesByChildClassesId(int childClassesIdNum);

    Job getJobById(Integer jobId);

    List<ChatResponse> queryChatUsers(Integer userId);
}
