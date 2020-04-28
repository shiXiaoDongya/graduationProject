package com.luoxd.graduation_project.service;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.request.ChatRequest;
import com.luoxd.graduation_project.request.JobRequest;
import com.luoxd.graduation_project.request.SearchRequest;
import com.luoxd.graduation_project.response.ChatResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.response.JobResponse;

import java.util.List;

public interface JobService {
    List<ClassesResonse> queryClassesList();

    List<JobResponse> queryJobList(SearchRequest searchRequest);

    List<Classes> queryClassesList2();

    List<ChildClasses> queryChildClassesByClassesId(int classsesIdNum);

    List<JobClasses> queryJobClassesByChildClassesId(int childClassesIdNum);

    Job getJobById(Integer jobId);

    List<ChatResponse> queryChatUsers(String userType,Integer userId);

    List<Chat> showChatContent(Integer jsId,Integer reId, Integer jobId);

    Integer jobListCount(SearchRequest searchRequest);

    Integer addOrUpdateJob(JobRequest jobRequest);

    Integer insertChats(ChatRequest chatRequest);
}
