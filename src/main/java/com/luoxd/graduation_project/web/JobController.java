package com.luoxd.graduation_project.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.request.ChatRequest;
import com.luoxd.graduation_project.request.JobRequest;
import com.luoxd.graduation_project.request.SearchRequest;
import com.luoxd.graduation_project.response.ChatResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.response.JobResponse;
import com.luoxd.graduation_project.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    private static final double PAGESIZE = 2.0;
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "getClasses",method = RequestMethod.GET)
    @ResponseBody
    public List<ClassesResonse> getClasses(){
        List<ClassesResonse> result = jobService.queryClassesList();
        return result;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
//    public String getJobList(HttpServletRequest request,Integer jobClassesId,String jobClassesName,Integer expCondition,Integer eduCondition,
//                             Integer salCondition,Integer finanCondition,Integer sizeCondition,Integer postDateCondition){
        public String getJobList(HttpServletRequest request,SearchRequest searchRequest){
        log.info(searchRequest.toString());
        if(searchRequest.getIndexPage() == null){
            searchRequest.setIndexPage(1);
        }
        searchRequest.setStart((int) ((searchRequest.getIndexPage()-1)*PAGESIZE));
        if(searchRequest.getTotalPage() == null){
            Integer i = jobService.jobListCount(searchRequest);
            Integer totalPage = (int) Math.ceil(i/PAGESIZE);
            searchRequest.setTotalPage(totalPage);
        }
        Integer jsId = (Integer)request.getSession().getAttribute("userId");
        log.info(searchRequest.toString());
        //String keyword = searchRequest.getKeyword();
        List<JobResponse> jobList = jobService.queryJobList(jsId,searchRequest);
        //log.info(jobList.toString());
        request.setAttribute("jobList",jobList);
        request.setAttribute("searchRequest",searchRequest);
//        request.setAttribute("keyword",searchRequest.getKeyword());
//        request.setAttribute("jobClassesId",searchRequest.getJobClassesId());
//        request.setAttribute("jobClassesName",searchRequest.getJobClassesName());
//        request.setAttribute("expCondition",searchRequest.getExpCondition());
//        request.setAttribute("eduCondition",searchRequest.getEduCondition());
//        request.setAttribute("salCondition",searchRequest.getSalCondition());
//        request.setAttribute("finanCondition",searchRequest.getFinanCondition());
//        request.setAttribute("sizeCondition",searchRequest.getSizeCondition());
//        request.setAttribute("postDateCondition",searchRequest.getPostDateCondition());

        return "search";
    }

    @RequestMapping(value = "/getJobDetail",method = RequestMethod.GET)
    public String getJobDetail(HttpServletRequest request,Integer jobId){
        if(jobId!=null){
            log.info("in========"+jobId.toString());
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.setJobId(jobId);
            Integer jsId = (Integer)request.getSession().getAttribute("userId");
            List<JobResponse> job = jobService.queryJobList(jsId,searchRequest);
            log.info("=========="+job.get(0).toString());
            request.setAttribute("job",job.get(0));
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
        int childClassesIdNum = 0;
        if (StringUtils.isNotEmpty(childClassesId)){
            childClassesIdNum = Integer.parseInt(childClassesId);
        }
        List<JobClasses> jobClassesList = jobService.queryJobClassesByChildClassesId(childClassesIdNum);
        return jobClassesList;
    }

    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    public String chat(HttpServletRequest request,Integer reId,Integer jobId){
        String userType = (String)request.getSession().getAttribute("userType");
        if("js".equals(userType)){
            request.setAttribute("reId",reId);
            request.setAttribute("jobId",jobId);
            return "chat";
        }else{
           return "reChat";
        }
    }

    @RequestMapping(value = "/showChatContent",method = RequestMethod.POST)
    @ResponseBody
    public List<Chat> showChatContent(HttpServletRequest request,Integer jsId,Integer reId,Integer jobId){

        String userType = (String)request.getSession().getAttribute("userType");
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

    @RequestMapping(value = "/getChatUsers",method = RequestMethod.POST)
    @ResponseBody
    public List<ChatResponse> getChatUsers(HttpServletRequest request,Integer jsId,Integer reId,Integer jobId){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String userType = (String)request.getSession().getAttribute("userType");
        List<ChatResponse> chatResponse = null;
        if(userId != null){
            chatResponse = jobService.queryChatUsers(userType,userId);
            log.info("===========chatResponse:"+chatResponse.toString());
        }
        return chatResponse;
    }

    @RequestMapping(value = "/addOrUpdateJob",method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdateJob(HttpServletRequest request,JobRequest jobRequest){
        Integer reId = (Integer) request.getSession().getAttribute("userId");
        jobRequest.setReId(reId);
        log.info(jobRequest.toString());
        Integer i = jobService.addOrUpdateJob(jobRequest);
        return "";
    }
    @RequestMapping(value = "/saveChatContent",method = RequestMethod.POST)
    @ResponseBody
    public String saveChatContent(HttpServletRequest request){
        String json = request.getParameter("msg");
        ChatRequest chatRequest = JSONArray.parseObject(json, ChatRequest.class);
        log.info(chatRequest.toString());
        Integer successCode = jobService.insertChats(chatRequest);
        if(successCode > 0){
            return "{\"success\":\"true\"}";
        }else {
            return "{\"success\":\"false\"}";
        }
    }

    @RequestMapping(value="jobCollection",method = RequestMethod.GET)
    @ResponseBody
    public String jobCollection(HttpServletRequest request){
        Integer jsId = Integer.parseInt(request.getParameter("jsId"));
        Integer jobId = Integer.parseInt(request.getParameter("jobId"));
        Integer resultCode = jobService.jobCollection(jsId,jobId);
        if(resultCode > 0){
            return "{\"success\":true}";
        }else{
            return "{\"success\":false}";
        }
    }

    @RequestMapping(value = "/getNewUser",method = RequestMethod.GET)
    @ResponseBody
    public ChatResponse getNewUser(HttpServletRequest request,Integer reId, Integer jobId){
        ChatResponse newChat = jobService.getNewChat(reId,jobId);
        newChat.setJobId(jobId);
        log.info("=====newChat:"+newChat.toString());
        return newChat;
    }
}
