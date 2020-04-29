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

    JobSeeker jobSeekerLogin(String username, String password);

    Recruiter recruiterLogin(String username, String password);

    JobSeeker checkJsUsername(String username);

    Recruiter checkReUsername(String username);

    List<Job> queryJobListByReId(Integer userId);

    Integer sendFeedback(Integer jsId, String msg);

    List<Feedback> getFeedbackList(Integer jsId, Integer adminId);

    Admin adminLogin(String username, String password);
}
