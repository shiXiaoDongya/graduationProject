package com.luoxd.graduation_project.service.impl;

import com.luoxd.graduation_project.domain.ChildClasses;
import com.luoxd.graduation_project.domain.Classes;
import com.luoxd.graduation_project.domain.JobClasses;
import com.luoxd.graduation_project.mapper.ClassesMapper;
import com.luoxd.graduation_project.mapper.UserMapper;
import com.luoxd.graduation_project.response.ChildClassesResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.service.ClassesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public List<ClassesResonse> queryClassesList() {
        //遍历获取总分类表左右数据
        List<Classes> tempClassesList = classesMapper.queryClassesList();
        log.debug("tempClassesList=====>"+tempClassesList.toString());

        List<ClassesResonse> resonses = new ArrayList<>();
        if(tempClassesList != null) {
            for (Classes tempClasses : tempClassesList) {
                ClassesResonse tempClassesResonse = new ClassesResonse();
                //总分类名称
                tempClassesResonse.setClassesName(tempClasses.getClassesName());

                List<ChildClassesResponse> tempChildClassesResponseList = new ArrayList();
                List<JobClasses> recommend = new ArrayList<>();

                List<ChildClasses> tempChildClassesList = classesMapper.queryChildClassesListByClassesId(tempClasses.getClassesId());
                for (ChildClasses tempChildClasses : tempChildClassesList) {
                    ChildClassesResponse tempChildClassesResponse = new ChildClassesResponse();
                    List<JobClasses> tempJobClassesList = classesMapper.queryJobClassesListByChildClassesId(tempChildClasses.getChildClassesId());
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
}
