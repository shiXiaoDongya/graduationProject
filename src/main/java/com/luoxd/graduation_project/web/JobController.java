package com.luoxd.graduation_project.web;


import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.request.SearchRequest;
import com.luoxd.graduation_project.response.ChatResponse;
import com.luoxd.graduation_project.response.ClassesResonse;
import com.luoxd.graduation_project.response.JobResponse;
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
        log.info("==========setStart:"+searchRequest.getStart());
        if(searchRequest.getTotalPage() == null){
            Integer i = jobService.jobListCount(searchRequest);
            Integer totalPage = (int) Math.ceil(i/PAGESIZE);
            searchRequest.setTotalPage(totalPage);
        }
        log.info("======================indexPage:"+searchRequest.getIndexPage());
        log.info("======================totalPage:"+searchRequest.getTotalPage());
        //String keyword = searchRequest.getKeyword();
        List<JobResponse> jobList = jobService.queryJobList(searchRequest);
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
            List<JobResponse> job = jobService.queryJobList(searchRequest);
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

    @RequestMapping(value = "/chatContent",method = RequestMethod.GET)
    public String chatContent(HttpServletRequest request,Integer jsId,Integer reId,Integer jobId){
        String userType = (String)request.getSession().getAttribute("userType");
        List<Chat> chatList = null;
        if("js".equals(userType)){
            Integer tempJsId = (Integer) request.getSession().getAttribute("userId");
            log.info("=============jsId:"+tempJsId+",reId:"+reId+",jobId:"+jobId);
            chatList = jobService.showChatContent(tempJsId,reId,jobId);
            log.info(chatList.toString());
            request.setAttribute("reId",reId);
        }else{
            Integer tempReId = (Integer) request.getSession().getAttribute("userId");
            log.info("=============jsId:"+jsId+",reId:"+tempReId+",jobId:"+jobId);
            chatList = jobService.showChatContent(jsId,tempReId,jobId);
            request.setAttribute("jsId",jsId);
        }
        log.info(chatList.toString());
        request.setAttribute("jobId",jobId);
        request.setAttribute("chatList",chatList);
        return "chatContent";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request){
        log.info("=============in");
        return "";
    }
}
