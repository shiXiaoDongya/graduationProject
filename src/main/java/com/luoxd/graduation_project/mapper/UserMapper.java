package com.luoxd.graduation_project.mapper;

import com.luoxd.graduation_project.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> getUsers();

    String getOffMsg(String id);
}
