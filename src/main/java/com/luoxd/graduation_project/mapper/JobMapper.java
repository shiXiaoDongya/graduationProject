package com.luoxd.graduation_project.mapper;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.request.ChatRequest;
import com.luoxd.graduation_project.request.JobRequest;
import com.luoxd.graduation_project.request.SearchRequest;
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

    List<Job> queryJobList(SearchRequest request);

    List<Classes> queryClasses();

    List<ChildClasses> queryChildClassesByClassesId(int classsesIdNum);

    List<JobClasses> queryJobClassesByChildClassesId(int childClassesIdNum);

    Job getJobById(Integer jobId);

    List<ChatResponse> queryJsChatUsers(Integer userId);

    List<ChatResponse> queryReChatUsers(Integer userId);

    List<Chat> showChatContent(Integer jsId, Integer reId, Integer jobId);

    Integer jobListCount(SearchRequest searchRequest);

    Integer addJob(JobRequest jobRequest);

    Integer updateJob(JobRequest jobRequest);

    Integer getJsNotReadCount(Integer userId, Integer reId, Integer jobId);

    Integer read(Integer jsId, Integer reId, Integer jobId);

    Integer insertChats(ChatRequest chatRequest);

    List<JobCollection> getCollection(Integer jsId, Integer jobId);

    Integer jobCollection(Integer jsId, Integer jobId);

    ChatResponse getNewChat(Integer reId, Integer jobId);

    List<Job> getJobCollection(Integer jsId);

    Integer cancelCollection(Integer jsId, Integer jobId);

    Integer changeJobClasses(Integer jobId, Integer jobClassesId);

    List<Job> getHobJobList();

    Integer deleteJob(Integer jobId);

    List<Job> getJobRecommend(String tag);
}
