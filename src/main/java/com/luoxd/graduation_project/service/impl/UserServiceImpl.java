package com.luoxd.graduation_project.service.impl;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.mapper.UserMapper;
import com.luoxd.graduation_project.response.ChildClassesResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
