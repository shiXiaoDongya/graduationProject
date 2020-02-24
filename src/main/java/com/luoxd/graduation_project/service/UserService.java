package com.luoxd.graduation_project.service;

import com.luoxd.graduation_project.domain.Message;
import com.luoxd.graduation_project.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    List<Message> getOffMsg(String id);
}
