package com.luoxd.graduation_project.web;

import com.alibaba.fastjson.JSON;
import com.luoxd.graduation_project.base.BaseResult;
import com.luoxd.graduation_project.domain.Classes;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.service.ClassesService;
import com.luoxd.graduation_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @RequestMapping(value = "testAjax",method = RequestMethod.GET)
    @ResponseBody
    public List<ClassesResonse> testAjax(){
        List<ClassesResonse> result = classesService.queryClassesList();
        return result;
    }
}
