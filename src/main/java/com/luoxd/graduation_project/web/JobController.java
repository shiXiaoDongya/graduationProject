package com.luoxd.graduation_project.web;


import com.luoxd.graduation_project.domain.Job;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "getClasses",method = RequestMethod.GET)
    @ResponseBody
    public List<ClassesResonse> getClasses(){
        List<ClassesResonse> result = jobService.queryClassesList();
        return result;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String getJobList(HttpServletRequest request){
        String keyword = request.getParameter("keyword");
        List<Job> jobList = jobService.queryJobList();
        log.info(jobList.toString());
        request.setAttribute("jobList",jobList);
        return "search";
    }

    @RequestMapping(value = "/getJobDetail",method = RequestMethod.GET)
    public String getJobDetail(HttpServletRequest request){
        String jobId = request.getParameter("jobId");
        log.info(jobId);
        return "jobDetail";
    }
}
