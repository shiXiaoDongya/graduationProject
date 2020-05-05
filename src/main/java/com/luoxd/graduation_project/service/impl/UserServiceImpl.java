package com.luoxd.graduation_project.service.impl;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.mapper.UserMapper;
import com.luoxd.graduation_project.request.JobSeekerRequest;
import com.luoxd.graduation_project.response.ChildClassesResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.response.JobResponse;
import com.luoxd.graduation_project.response.JobSeekerResponse;
import com.luoxd.graduation_project.service.UserService;
import com.luoxd.graduation_project.utils.Condition2StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public List<Message> getOffMsg(String id) {
        List<Message> messages = new ArrayList<>();
        Message message1 = new Message(1,"1","666666","2",123456L,0);
        Message message2 = new Message(2,"3","7777777","2",123456L,0);
        messages.add(message1);
        messages.add(message2);
        return messages;
    }

    @Override
    public int insertRecruiter(Recruiter recruiter) {
        return userMapper.insertRecruiter(recruiter);
    }

    @Override
    public int insertJobSeeker(JobSeeker jobSeeker) {
        return userMapper.insertJobSeeker(jobSeeker);
    }

    @Override
    public JobSeeker jobSeekerLogin(String username, String password) {
        return userMapper.jobSeekerLogin(username,password);
    }

    @Override
    public Recruiter recruiterLogin(String username, String password) {
        return userMapper.recruiterLogin(username,password);
    }

    @Override
    public JobSeeker checkJsUsername(String username) {
        return userMapper.checkJsUsername(username);
    }

    @Override
    public Recruiter checkReUsername(String username) {
        return userMapper.checkReUsername(username);
    }

    @Override
    public List<JobResponse> queryJobListByReId(Integer userId) {
        List<JobResponse> jobResponseList = new ArrayList<>();
        List<Job> jobList = userMapper.queryJobListByReId(userId);
        if(jobList != null) {
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
                jobResponse.setExpCondition(tempJob.getExpCondition());
                jobResponse.setEduCondition(tempJob.getEduCondition());
                jobResponse.setExpConditionStr(Condition2StrUtils.getExpStr(tempJob.getExpCondition()));
                jobResponse.setEduConditionStr(Condition2StrUtils.getEduStr(tempJob.getEduCondition()));
                jobResponse.setSalary(tempJob.getSalary());
                jobResponse.setReCompanyId(tempJob.getReCompanyId());
                jobResponse.setTagsStr(tempJob.getTag());
                jobResponse.setTags(tags);
                jobResponse.setRecruiterId(tempJob.getRecruiterId());
                jobResponse.setReRealname(tempJob.getReRealname());
                jobResponse.setReCompanyPosition(tempJob.getReCompanyPosition());
                jobResponse.setWorkCity(tempJob.getWorkCity());
                jobResponse.setWorkAddress(tempJob.getWorkAddress());
                jobResponse.setPostDate(tempJob.getPostDate());
                jobResponse.setJobClassesId(tempJob.getJobClassesId());
                jobResponse.setJobClassesName(tempJob.getJobClassesName());
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
    public Integer sendFeedback(Integer jsId, String msg) {
        return userMapper.sendFeedback(jsId,msg);
    }

    @Override
    public List<Feedback> getFeedbackList(Integer jsId, Integer adminId) {
        return userMapper.getFeedbackList(jsId,adminId);
    }

    @Override
    public Admin adminLogin(String username, String password) {
        return userMapper.adminLogin(username,password);
    }

    @Override
    public List<Feedback> getNoReplyFeedbackList() {
        return userMapper.getNoReplyFeedbackList();
    }

    @Override
    public Integer replyFeedback(Integer adminId,String replyMsg, Integer feedbackId) {
        return userMapper.replyFeedback(adminId,replyMsg,feedbackId);
    }

    @Override
    public Integer deleteFeedback(Integer feedbackId) {
        return userMapper.deleteFeedback(feedbackId);
    }

    @Override
    public JobSeekerResponse getJsById(Integer jsId) {
        return userMapper.getJsById(jsId);
    }

    @Override
    public Integer updateJobSeeker(JobSeekerRequest jsRequest) {
        return userMapper.updateJobSeeker(jsRequest);
    }
}
