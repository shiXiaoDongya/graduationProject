package com.luoxd.graduation_project.service.impl;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.mapper.UserMapper;
import com.luoxd.graduation_project.response.ChildClassesResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public List<ClassesResonse> queryClassesList() {
        //遍历获取总分类表左右数据
        List<Classes> tempClassesList = userMapper.queryClassesList();

        List<ClassesResonse> resonses = new ArrayList<>();
        for (Classes tempClasses:tempClassesList) {
            ClassesResonse tempClassesResonse = new ClassesResonse();
            //总分类名称
            tempClassesResonse.setClassesName(tempClasses.getClassesName());

            List<ChildClassesResponse> tempChildClassesResponseList = new ArrayList();
            List<JobClasses> recommend = new ArrayList<>();

            List<ChildClasses> tempChildClassesList = userMapper.queryChildClassesListByClassesId(tempClasses.getClassesId());
            for (ChildClasses tempChildClasses:tempChildClassesList) {
                ChildClassesResponse tempChildClassesResponse = new ChildClassesResponse();
                List<JobClasses> tempJobClassesList = userMapper.queryJobClassesListByChildClassesId(tempChildClasses.getChildClassesId());
                tempChildClassesResponse.setJobClassesList(tempJobClassesList);
                for (JobClasses tempJobClasses:tempJobClassesList) {
                    if(tempJobClasses.getIsRecommend() == 1){
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
}
