package com.luoxd.graduation_project.web;


import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.response.ChatResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
    public String getJobList(HttpServletRequest request,Integer jobClassesId,String jobClassesName,Integer expCondition,Integer eduCondition,
                             Integer salCondition,Integer finanCondition,Integer sizeCondition,Integer postDateCondition){
        String keyword = request.getParameter("keyword");
        List<Job> jobList = jobService.queryJobList();
        for (Job tempJob:jobList) {
            List<String> tags = new ArrayList<>();
            tags.add("Java");
            tags.add("PHP");
            tempJob.setTags(tags);
        }
        log.info(jobList.toString());
        request.setAttribute("jobList",jobList);
        request.setAttribute("keyword",keyword);
        request.setAttribute("jobClassesId",jobClassesId);
        request.setAttribute("jobClassesName",jobClassesName);
        request.setAttribute("expCondition",expCondition);
        request.setAttribute("eduCondition",eduCondition);
        request.setAttribute("salCondition",salCondition);
        request.setAttribute("finanCondition",finanCondition);
        request.setAttribute("sizeCondition",sizeCondition);
        request.setAttribute("postDateCondition",postDateCondition);
        return "search";
    }

    @RequestMapping(value = "/getJobDetail",method = RequestMethod.GET)
    public String getJobDetail(HttpServletRequest request,Integer jobId){
        if(jobId!=null){
            log.info("in========"+jobId.toString());
            Job job = jobService.getJobById(jobId);
            request.setAttribute("job",job);
        }
        return "jobDetail";
    }

    @RequestMapping(value = "/getClasses2",method = RequestMethod.GET)
    @ResponseBody
    public List<Classes> getClasses2(HttpServletRequest request){
        String jobId = request.getParameter("jobId");
        List<Classes> classesList = jobService.queryClassesList2();
        return classesList;
    }

    @RequestMapping(value = "/getChildClasses",method = RequestMethod.GET)
    @ResponseBody
    public List<ChildClasses> getChildClasses(HttpServletRequest request){
        String classesId = request.getParameter("classesId");
        log.info("classesId===>"+classesId);
        int classsesIdNum = 0;
        if (StringUtils.isNotEmpty(classesId)){
            classsesIdNum = Integer.parseInt(classesId);
        }
        List<ChildClasses> childClassesList = jobService.queryChildClassesByClassesId(classsesIdNum);
        return childClassesList;
    }

    @RequestMapping(value = "/getJobClasses",method = RequestMethod.GET)
    @ResponseBody
    public List<JobClasses> getJobClasses(HttpServletRequest request){
        String childClassesId = request.getParameter("childClassesId");
        log.info("classesId===>"+childClassesId);
        int childClassesIdNum = 0;
        if (StringUtils.isNotEmpty(childClassesId)){
            childClassesIdNum = Integer.parseInt(childClassesId);
        }
        List<JobClasses> jobClassesList = jobService.queryJobClassesByChildClassesId(childClassesIdNum);
        return jobClassesList;
    }

    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    public String chat(HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String userType = (String)request.getSession().getAttribute("userType");
        if(userId != null){
            List<ChatResponse> chatResponse = jobService.queryChatUsers(userType,userId);
            log.info("===========chatResponse:"+chatResponse.toString());
            request.setAttribute("chatList",chatResponse);
        }
        if("js".equals(userType)){
            return "chat";
        }else{
           return "reChat";
        }
    }

    @RequestMapping(value = "/showChatContent",method = RequestMethod.POST)
    @ResponseBody
    public List<Chat> showChatContent(HttpServletRequest request,Integer jsId,Integer reId,Integer jobId){

        String userType = (String)request.getSession().getAttribute("usetType");
        List<Chat> chatList = null;
        if("js".equals(userType)){
            Integer tempJsId = (Integer) request.getSession().getAttribute("userId");
            log.info("=============jsId:"+tempJsId+",reId:"+reId+",jobId:"+jobId);
            chatList = jobService.showChatContent(tempJsId,reId,jobId);
            request.setAttribute("reId",reId);
        }else{
            Integer tempReId = (Integer) request.getSession().getAttribute("userId");
            log.info("=============jsId:"+jsId+",reId:"+tempReId+",jobId:"+jobId);
            chatList = jobService.showChatContent(jsId,tempReId,jobId);
            request.setAttribute("jsId",jsId);
        }
        log.info(chatList.toString());
        return chatList;
    }

    @RequestMapping(value = "/chatContent",method = RequestMethod.GET)
    public String chatContent(HttpServletRequest request,Integer jsId,Integer reId,Integer jobId){
        String userType = (String)request.getSession().getAttribute("usetType");
        List<Chat> chatList = null;
        if("js".equals(userType)){
            Integer tempJsId = (Integer) request.getSession().getAttribute("userId");
            log.info("=============jsId:"+tempJsId+",reId:"+reId+",jobId:"+jobId);
            chatList = jobService.showChatContent(tempJsId,reId,jobId);
            request.setAttribute("reId",reId);
        }else{
            Integer tempReId = (Integer) request.getSession().getAttribute("userId");
            log.info("=============jsId:"+jsId+",reId:"+tempReId+",jobId:"+jobId);
            chatList = jobService.showChatContent(jsId,tempReId,jobId);
            request.setAttribute("jsId",jsId);
        }
        request.setAttribute("chatList",chatList);
        return "chatContent";
    }
}
