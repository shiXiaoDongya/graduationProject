package com.luoxd.graduation_project.service;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.response.ChatResponse;
import com.luoxd.graduation_project.response.ClassesResonse;

import java.util.List;

public interface JobService {
    List<ClassesResonse> queryClassesList();

    List<Job> queryJobList();

    List<Classes> queryClassesList2();

    List<ChildClasses> queryChildClassesByClassesId(int classsesIdNum);

    List<JobClasses> queryJobClassesByChildClassesId(int childClassesIdNum);

    Job getJobById(Integer jobId);

    List<ChatResponse> queryChatUsers(String userType,Integer userId);

    List<Chat> showChatContent(Integer jsId,Integer reId, Integer jobId);
}
