package com.luoxd.graduation_project.service;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.request.JobConditionRequest;
import com.luoxd.graduation_project.request.JobSeekerRequest;
import com.luoxd.graduation_project.request.RecruiterRequest;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.response.JobResponse;
import com.luoxd.graduation_project.response.JobSeekerResponse;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    List<Message> getOffMsg(String id);

    int insertRecruiter(Recruiter recruiter);

    int insertJobSeeker(JobSeeker jobSeeker);

    JobSeeker jobSeekerLogin(String username, String password);

    Recruiter recruiterLogin(String username, String password);

    JobSeeker checkJsUsername(String username);

    Recruiter checkReUsername(String username);

    List<JobResponse> queryJobListByReId(Integer userId);

    Integer sendFeedback(Integer jsId, String msg);

    List<Feedback> getFeedbackList(Integer jsId, Integer adminId);

    Admin adminLogin(String username, String password);

    List<Feedback> getNoReplyFeedbackList();

    Integer replyFeedback(Integer adminId,String replyMsg, Integer feedbackId);

    Integer deleteFeedback(Integer feedbackId);

    JobSeekerResponse getJsById(Integer jsId);

    Integer updateJobSeeker(JobSeekerRequest jsRequest);

    String getJobTags(Integer jsId);

    List<IndexPic> getIndexPic();

    Recruiter getRecruiterById(Integer reId);

    Integer updateRecruiter(RecruiterRequest reRequest);

    Company getCompanyById(Integer reCompanyId);

    Integer updateJobCondition(JobConditionRequest jobConditionRequest);
}
