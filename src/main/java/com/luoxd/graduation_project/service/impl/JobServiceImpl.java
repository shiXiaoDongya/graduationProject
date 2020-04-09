package com.luoxd.graduation_project.service.impl;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.mapper.JobMapper;
import com.luoxd.graduation_project.response.ChatResponse;
import com.luoxd.graduation_project.response.ChildClassesResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<Job> queryJobList() {
        return jobMapper.queryJobList();
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
            return jobMapper.queryJsChatUsers(userId);
        }else{
            return jobMapper.queryReChatUsers(userId);
        }
    }

    @Override
    public List<Chat> showChatContent(Integer jsId,Integer reId, Integer jobId) {
        log.info("jobMapper=======jsId:"+jsId+",reId:"+reId+",jobId:"+jobId);
        return jobMapper.showChatContent(jsId,reId,jobId);
    }
}
