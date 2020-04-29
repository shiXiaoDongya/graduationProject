package com.luoxd.graduation_project.service;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.response.JobResponse;

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
}
