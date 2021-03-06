package com.luoxd.graduation_project.service.impl;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.mapper.JobMapper;
import com.luoxd.graduation_project.request.ChatRequest;
import com.luoxd.graduation_project.request.JobRequest;
import com.luoxd.graduation_project.request.SearchRequest;
import com.luoxd.graduation_project.response.ChatResponse;
import com.luoxd.graduation_project.response.ChildClassesResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.response.JobResponse;
import com.luoxd.graduation_project.service.JobService;
import com.luoxd.graduation_project.utils.Condition2StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;


    @Override
    public List<ClassesResonse> queryClassesList() {
        //遍历获取总分类表左右数据
        List<Classes> tempClassesList = jobMapper.queryClassesList();
        log.debug("tempClassesList=====>"+tempClassesList.toString());

        List<ClassesResonse> resonses = new ArrayList<>();
        if(tempClassesList != null) {
            for (Classes tempClasses : tempClassesList) {
                ClassesResonse tempClassesResonse = new ClassesResonse();
                //总分类名称
                tempClassesResonse.setClassesName(tempClasses.getClassesName());

                List<ChildClassesResponse> tempChildClassesResponseList = new ArrayList();
                List<JobClasses> recommend = new ArrayList<>();

                List<ChildClasses> tempChildClassesList = jobMapper.queryChildClassesListByClassesId(tempClasses.getClassesId());
                for (ChildClasses tempChildClasses : tempChildClassesList) {
                    ChildClassesResponse tempChildClassesResponse = new ChildClassesResponse();
                    List<JobClasses> tempJobClassesList = jobMapper.queryJobClassesListByChildClassesId(tempChildClasses.getChildClassesId());
                    tempChildClassesResponse.setChildClassesId(tempChildClasses.getChildClassesId());
                    tempChildClassesResponse.setChildClassesName(tempChildClasses.getChildClassesName());
                    tempChildClassesResponse.setJobClassesList(tempJobClassesList);
                    for (JobClasses tempJobClasses : tempJobClassesList) {
                        if (tempJobClasses.getIsRecommend() == 1) {
                            recommend.add(tempJobClasses);
                        }
                    }
                    tempChildClassesResponseList.add(tempChildClassesResponse);
                }
                tempClassesResonse.setChildClassesList(tempChildClassesResponseList);
                tempClassesResonse.setRecommendJobClassesList(recommend);
                resonses.add(tempClassesResonse);
            }
            return resonses;
        }
        return null;
    }

    @Override
    public List<JobResponse> queryJobList(Integer jsId, SearchRequest searchRequest) {
        List<JobResponse> jobResponseList = new ArrayList<>();
        List<Job> jobList = jobMapper.queryJobList(searchRequest);
        if(jobList != null) {
            log.info("===jobList:"+jobList.toString());
            for (Job tempJob : jobList) {
                JobResponse jobResponse = new JobResponse();
                List<String> tags = null;
                if (tempJob.getTag() != null) {
                    String[] tempTags = tempJob.getTag().split(",");
                    tags = Arrays.asList(tempTags);
                }
                jobResponse.setJobId(tempJob.getJobId());
                jobResponse.setJobName(tempJob.getJobName());
                jobResponse.setJobDetail(tempJob.getJobDetail());
                jobResponse.setExpConditionStr(Condition2StrUtils.getExpStr(tempJob.getExpCondition()));
                jobResponse.setEduConditionStr(Condition2StrUtils.getEduStr(tempJob.getEduCondition()));
                jobResponse.setSalary(tempJob.getSalary());
                jobResponse.setReCompanyId(tempJob.getReCompanyId());
                jobResponse.setTagsStr(tempJob.getTag());
                jobResponse.setTags(tags);
                jobResponse.setRecruiterId(tempJob.getRecruiterId());
                jobResponse.setReRealname(tempJob.getReRealname());
                jobResponse.setReHeadImg(tempJob.getReHeadImg());
                jobResponse.setReCompanyPosition(tempJob.getReCompanyPosition());
                jobResponse.setWorkCity(tempJob.getWorkCity());
                jobResponse.setWorkAddress(tempJob.getWorkAddress());
                jobResponse.setPostDate(tempJob.getPostDate());
                jobResponse.setJobClassesId(tempJob.getJobClassesId());
                jobResponse.setJobClassesName(tempJob.getJobClassesName());
                jobResponse.setCompanyName(tempJob.getCompanyName());
                jobResponse.setCompanyHeadImg(tempJob.getCompanyHeadImg());
                jobResponse.setCompanyDetail(tempJob.getCompanyDetail());
                jobResponse.setIndustry(tempJob.getIndustry());
                jobResponse.setFinanConditionStr(Condition2StrUtils.getFinanStr(tempJob.getFinanCondition()));
                jobResponse.setSizeConditionStr(Condition2StrUtils.getSizeStr(tempJob.getSizeCondition()));
                List<String> companyTags = null;
                if (tempJob.getCompanyTags() != null) {
                    String[] tempCompanyTags = tempJob.getCompanyTags().split(",");
                    companyTags = Arrays.asList(tempCompanyTags);
                }
                jobResponse.setCompanyTags(companyTags);
                if(jsId == null){
                    jobResponse.setIsCollected(0);
                }else{
                    List<JobCollection> jobCollectionList  = jobMapper.getCollection(jsId,jobResponse.getJobId());
                    if(jobCollectionList.size() > 0){
                        jobResponse.setIsCollected(1);
                    }else{
                        jobResponse.setIsCollected(0);
                    }
                }
                jobResponseList.add(jobResponse);
            }
            return jobResponseList;
        }else{
            return null;
        }
    }

    @Override
    public List<Classes> queryClassesList2() {
        return jobMapper.queryClasses();
    }

    @Override
    public List<ChildClasses> queryChildClassesByClassesId(int classsesIdNum) {
        return jobMapper.queryChildClassesByClassesId(classsesIdNum);
    }

    @Override
    public List<JobClasses> queryJobClassesByChildClassesId(int childClassesIdNum) {
        return jobMapper.queryJobClassesByChildClassesId(childClassesIdNum);
    }

    @Override
    public Job getJobById(Integer jobId) {
        return jobMapper.getJobById(jobId);
    }

    @Override
    public List<ChatResponse> queryChatUsers(String userType,Integer userId) {
        if("js".equals(userType)){
            List<ChatResponse> tempChatResponseList = jobMapper.queryJsChatUsers(userId);
            for (ChatResponse tempChatResponse:tempChatResponseList) {
                tempChatResponse.setNotRead(jobMapper.getJsNotReadCount(userId,tempChatResponse.getReId(),tempChatResponse.getJobId()));
            }
            log.info("==========:"+tempChatResponseList.toString());
            return tempChatResponseList;
        }else{
            return jobMapper.queryReChatUsers(userId);
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<Chat> showChatContent(Integer jsId,Integer reId, Integer jobId) {
        List<Chat> chatList = jobMapper.showChatContent(jsId,reId,jobId);
        Integer read = jobMapper.read(jsId,reId,jobId);
        log.info("jobMapper=======jsId:"+jsId+",reId:"+reId+",jobId:"+jobId);
        return chatList;
    }

    @Override
    public Integer jobListCount(SearchRequest searchRequest) {
        return jobMapper.jobListCount(searchRequest);
    }

    @Override
    public Integer addOrUpdateJob(JobRequest jobRequest) {
        Integer result;
        if(jobRequest.getJobId() == null){
            result = jobMapper.addJob(jobRequest);
        }else{
            result = jobMapper.updateJob(jobRequest);
        }
        return result;
    }

    @Override
    public Integer insertChats(ChatRequest chatRequest) {
        return jobMapper.insertChats(chatRequest);
    }

    @Override
    public Integer jobCollection(Integer jsId, Integer jobId) {
        return jobMapper.jobCollection(jsId,jobId);
    }

    @Override
    public ChatResponse getNewChat(Integer reId, Integer jobId) {
        return jobMapper.getNewChat(reId,jobId);
    }

    @Override
    public List<JobResponse> getJobCollection(Integer jsId) {
        List<JobResponse> jobResponseList = new ArrayList<>();
        List<Job> jobList = jobMapper.getJobCollection(jsId);
        if(jobList != null) {
            log.info("===jobList:"+jobList.toString());
            for (Job tempJob : jobList) {
                JobResponse jobResponse = new JobResponse();
                List<String> tags = null;
                if (tempJob.getTag() != null) {
                    String[] tempTags = tempJob.getTag().split(",");
                    tags = Arrays.asList(tempTags);
                }
                jobResponse.setJobId(tempJob.getJobId());
                jobResponse.setJobName(tempJob.getJobName());
                jobResponse.setJobDetail(tempJob.getJobDetail());
                jobResponse.setExpConditionStr(Condition2StrUtils.getExpStr(tempJob.getExpCondition()));
                jobResponse.setEduConditionStr(Condition2StrUtils.getEduStr(tempJob.getEduCondition()));
                jobResponse.setSalary(tempJob.getSalary());
                jobResponse.setReCompanyId(tempJob.getReCompanyId());
                jobResponse.setTags(tags);
                jobResponse.setRecruiterId(tempJob.getRecruiterId());
                jobResponse.setReRealname(tempJob.getReRealname());
                jobResponse.setReCompanyPosition(tempJob.getReCompanyPosition());
                jobResponse.setWorkCity(tempJob.getWorkCity());
                jobResponse.setWorkAddress(tempJob.getWorkAddress());
                jobResponse.setPostDate(tempJob.getPostDate());
                jobResponse.setJobClassesId(tempJob.getJobClassesId());
                jobResponse.setCompanyName(tempJob.getCompanyName());
                jobResponse.setCompanyHeadImg(tempJob.getCompanyHeadImg());
                jobResponse.setIndustry(tempJob.getIndustry());
                jobResponse.setFinanConditionStr(Condition2StrUtils.getFinanStr(tempJob.getFinanCondition()));
                jobResponse.setSizeConditionStr(Condition2StrUtils.getSizeStr(tempJob.getSizeCondition()));
                List<String> companyTags = null;
                if (tempJob.getCompanyTags() != null) {
                    String[] tempCompanyTags = tempJob.getCompanyTags().split(",");
                    companyTags = Arrays.asList(tempCompanyTags);
                }
                jobResponse.setCompanyTags(companyTags);
                jobResponse.setJsId(jsId);
                jobResponseList.add(jobResponse);
            }
            return jobResponseList;
        }else{
            return null;
        }
    }

    @Override
    public Integer cancelCollection(Integer jsId, Integer jobId) {
        return jobMapper.cancelCollection(jsId,jobId);
    }

    @Override
    public Integer changeJobClasses(Integer jobId, Integer jobClassesId) {
        return jobMapper.changeJobClasses(jobId,jobClassesId);
    }

    @Override
    public List<JobResponse> getHobJobList() {
        List<JobResponse> jobResponseList = new ArrayList<>();
        List<Job> jobList = jobMapper.getHobJobList();
        if(jobList != null) {
            log.info("===jobList:"+jobList.toString());
            for (Job tempJob : jobList) {
                JobResponse jobResponse = new JobResponse();
                List<String> tags = null;
                if (tempJob.getTag() != null) {
                    String[] tempTags = tempJob.getTag().split(",");
                    tags = Arrays.asList(tempTags);
                }
                jobResponse.setJobId(tempJob.getJobId());
                jobResponse.setJobName(tempJob.getJobName());
                jobResponse.setJobDetail(tempJob.getJobDetail());
                jobResponse.setExpConditionStr(Condition2StrUtils.getExpStr(tempJob.getExpCondition()));
                jobResponse.setEduConditionStr(Condition2StrUtils.getEduStr(tempJob.getEduCondition()));
                jobResponse.setSalary(tempJob.getSalary());
                jobResponse.setReCompanyId(tempJob.getReCompanyId());
                jobResponse.setTags(tags);
                jobResponse.setRecruiterId(tempJob.getRecruiterId());
                jobResponse.setReRealname(tempJob.getReRealname());
                jobResponse.setReCompanyPosition(tempJob.getReCompanyPosition());
                jobResponse.setWorkCity(tempJob.getWorkCity());
                jobResponse.setWorkAddress(tempJob.getWorkAddress());
                jobResponse.setPostDate(tempJob.getPostDate());
                jobResponse.setJobClassesId(tempJob.getJobClassesId());
                jobResponse.setCompanyName(tempJob.getCompanyName());
                jobResponse.setCompanyHeadImg(tempJob.getCompanyHeadImg());
                jobResponse.setIndustry(tempJob.getIndustry());
                jobResponse.setFinanConditionStr(Condition2StrUtils.getFinanStr(tempJob.getFinanCondition()));
                jobResponse.setSizeConditionStr(Condition2StrUtils.getSizeStr(tempJob.getSizeCondition()));
                List<String> companyTags = null;
                if (tempJob.getCompanyTags() != null) {
                    String[] tempCompanyTags = tempJob.getCompanyTags().split(",");
                    companyTags = Arrays.asList(tempCompanyTags);
                }
                jobResponse.setCompanyTags(companyTags);
                jobResponseList.add(jobResponse);
            }
            return jobResponseList;
        }else{
            return null;
        }
    }

    @Override
    public Integer deleteJob(Integer jobId) {
        return jobMapper.deleteJob(jobId);
    }

    @Override
    public List<JobResponse> getJobRecommend(String tag) {
        List<JobResponse> jobResponseList = new ArrayList<>();
        List<Job> jobList = jobMapper.getJobRecommend(tag);
        if(jobList != null) {
            log.info("===jobList:"+jobList.toString());
            for (Job tempJob : jobList) {
                JobResponse jobResponse = new JobResponse();
                List<String> tags = null;
                if (tempJob.getTag() != null) {
                    String[] tempTags = tempJob.getTag().split(",");
                    tags = Arrays.asList(tempTags);
                }
                jobResponse.setJobId(tempJob.getJobId());
                jobResponse.setJobName(tempJob.getJobName());
                jobResponse.setJobDetail(tempJob.getJobDetail());
                jobResponse.setExpConditionStr(Condition2StrUtils.getExpStr(tempJob.getExpCondition()));
                jobResponse.setEduConditionStr(Condition2StrUtils.getEduStr(tempJob.getEduCondition()));
                jobResponse.setSalary(tempJob.getSalary());
                jobResponse.setReCompanyId(tempJob.getReCompanyId());
                jobResponse.setTags(tags);
                jobResponse.setRecruiterId(tempJob.getRecruiterId());
                jobResponse.setReRealname(tempJob.getReRealname());
                jobResponse.setReCompanyPosition(tempJob.getReCompanyPosition());
                jobResponse.setWorkCity(tempJob.getWorkCity());
                jobResponse.setWorkAddress(tempJob.getWorkAddress());
                jobResponse.setPostDate(tempJob.getPostDate());
                jobResponse.setJobClassesId(tempJob.getJobClassesId());
                jobResponse.setCompanyName(tempJob.getCompanyName());
                jobResponse.setCompanyHeadImg(tempJob.getCompanyHeadImg());
                jobResponse.setIndustry(tempJob.getIndustry());
                jobResponse.setFinanConditionStr(Condition2StrUtils.getFinanStr(tempJob.getFinanCondition()));
                jobResponse.setSizeConditionStr(Condition2StrUtils.getSizeStr(tempJob.getSizeCondition()));
                List<String> companyTags = null;
                if (tempJob.getCompanyTags() != null) {
                    String[] tempCompanyTags = tempJob.getCompanyTags().split(",");
                    companyTags = Arrays.asList(tempCompanyTags);
                }
                jobResponse.setCompanyTags(companyTags);
                jobResponseList.add(jobResponse);
            }
            return jobResponseList;
        }else{
            return null;
        }
    }
}
