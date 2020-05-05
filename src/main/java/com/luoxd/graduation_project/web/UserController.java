package com.luoxd.graduation_project.web;

import com.luoxd.graduation_project.domain.*;
import com.luoxd.graduation_project.request.JobSeekerRequest;
import com.luoxd.graduation_project.request.SearchRequest;
import com.luoxd.graduation_project.response.JobResponse;
import com.luoxd.graduation_project.response.JobSeekerResponse;
import com.luoxd.graduation_project.service.JobService;
import com.luoxd.graduation_project.service.UserService;
import com.luoxd.graduation_project.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    private String mynewpic;

    @RequestMapping(value = "getUsers")
    public String getUsers(HttpServletRequest request) {
        String[] hotSearch = {"1", "2"};
        request.setAttribute("hotSearch", hotSearch);
        return "bootstrap";
    }

    @RequestMapping("/websocket/{id}/{name}")
    public String webSocket(@PathVariable String id, @PathVariable String name, Model model) {
        try {
            log.info("跳转到websocket的页面上");
            model.addAttribute("id", id);
            model.addAttribute("username", name);
            return "websocket";
        } catch (Exception e) {
            log.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }
    }


    /**
     * 登录网页是根据IP获取所在城市
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getLocation", method = RequestMethod.GET)
    @ResponseBody
    public String getLocation(HttpServletRequest request) {
        String city = null;
        String ipaddre = AddressUtils.getIp(request);
        try {
            city = AddressUtils.getAddresses(ipaddre);
            log.info("IP所在城市：" + city);
        } catch (UnsupportedEncodingException e) {
            log.error("获取用户Ip信息失败");
        }
        return city;
    }

    // uploadFile
    @RequestMapping("/saveCompanyPic")
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile companyPic)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = companyPic.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径
        String relativePath = System.getProperty("user.dir");
        String saveFilePath = relativePath + "\\src\\main\\resources\\companyPic";
        System.out.println("相对路径--" + saveFilePath);
        // 上传图片
        if (companyPic != null && oldFileName != null && oldFileName.length() > 0) {
            // 新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            System.out.println(saveFilePath + "\\" + newFileName);
            // 新图片
            File newFile = new File(saveFilePath + "\\" + newFileName);
            // 将内存中的数据写入磁盘
            companyPic.transferTo(newFile);
            // 将新图片名称返回到前端
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "成功啦");
            map.put("url", newFileName);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("error", "图片不合法");
            return map;
        }
    }

    @RequestMapping("/register")
    public String register(@Param("username") String username, @Param("password") String password, @Param("confirmPassword") String confirmPassword,
                           @Param("realname") String realname, @Param("idCard") String idCard, @Param("phone") String phone,
                           @Param("email") String email, @Param("sq") String sq, @Param("comfirmSq") String comfirmSq, @Param("company") String company,
                           @Param("companyPosition") String companyPosition, @Param("companyPic") MultipartFile companyPic, @Param("id") String id, HttpServletRequest request) throws IOException {
        if (id.equals("re")) {
            Recruiter recruiter = new Recruiter();
            recruiter.setReUsername(username);
            recruiter.setRePassword(password);
            recruiter.setReRealname(realname);
            recruiter.setReIdCard(idCard);
            recruiter.setReGender("男");
            recruiter.setRePhone(phone);
            recruiter.setReEmail(email);
            recruiter.setReSq(sq);
            recruiter.setReCompany(company);
            recruiter.setReCompanyPosition(companyPosition);
            // 原始名称
            String oldFileName = companyPic.getOriginalFilename(); // 获取上传文件的原名
            // 存储图片的虚拟本地路径
            String relativePath = System.getProperty("user.dir");
            String saveFilePath = relativePath + "\\src\\main\\resources\\companyPic";
            // 上传图片
            if (companyPic != null && oldFileName != null && oldFileName.length() > 0) {
                // 新的图片名称
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                recruiter.setReCompanyPic(newFileName);
                System.out.println(saveFilePath + "\\" + newFileName);
                // 新图片
                File newFile = new File(saveFilePath + "\\" + newFileName);
                // 将内存中的数据写入磁盘
                companyPic.transferTo(newFile);
                log.info(recruiter.toString());
                userService.insertRecruiter(recruiter);
                Recruiter re = userService.recruiterLogin(recruiter.getReUsername(), recruiter.getRePassword());
                request.getSession().setAttribute("userType", "re");
                request.getSession().setAttribute("userId", re.getReId());
                request.getSession().setAttribute("userName", re.getReUsername());
                return "index";
            } else {
                return "redirect:index.html";
            }
        } else {
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setJsUsername(username);
            jobSeeker.setJsPassword(password);
            jobSeeker.setJsRealname(realname);
            jobSeeker.setJsIdCard(idCard);
            jobSeeker.setJsGender("男");
            jobSeeker.setJsPhone(phone);
            jobSeeker.setJsEmail(email);
            jobSeeker.setJsSq(sq);
            userService.insertJobSeeker(jobSeeker);
            JobSeeker js = userService.jobSeekerLogin(jobSeeker.getJsUsername(), jobSeeker.getJsPassword());
            request.getSession().setAttribute("userType", "js");
            request.getSession().setAttribute("userId", js.getJsId());
            request.getSession().setAttribute("userName", js.getJsUsername());
            return "index";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        log.info("====username:" + username + ",password:" + password + ",id:" + id);
        switch (id) {
            case "js":
                JobSeeker js = userService.jobSeekerLogin(username, password);
                if (js != null) {
                    request.getSession().setAttribute("userType", "js");
                    request.getSession().setAttribute("userId", js.getJsId());
                    request.getSession().setAttribute("userName", js.getJsUsername());
                    return "{\"success\":\"true\",\"userType\":\"js\",\"msg\":\"\"}";
                }
                break;
            case "re":
                Recruiter re = userService.recruiterLogin(username, password);
                if (re != null) {
                    request.getSession().setAttribute("userType", "re");
                    request.getSession().setAttribute("userId", re.getReId());
                    request.getSession().setAttribute("userName", re.getReUsername());
                    return "{\"success\":\"true\",\"userType\":\"re\",\"msg\":\"\"}";
                }
                break;
            case "admin":
                Admin admin = userService.adminLogin(username, password);
                if (admin != null) {
                    request.getSession().setAttribute("userType", "admin");
                    request.getSession().setAttribute("userId", admin.getAdminId());
                    request.getSession().setAttribute("userName", admin.getAdminUsername());
                    return "{\"success\":\"true\",\"userType\":\"admin\",\"msg\":\"\"}";
                }
                break;
            default:
                ;
        }
//        if("js".equals(id)){
//            JobSeeker js = userService.jobSeekerLogin(username,password);
//            if(js != null){
//                request.getSession().setAttribute("userType","js");
//                request.getSession().setAttribute("userId",js.getJsId());
//                request.getSession().setAttribute("userName",js.getJsUsername());
//                return "{\"success\":\"true\",\"userType\":\"js\",\"msg\":\"\"}";
//            }
//        }else{
//            Recruiter re = userService.recruiterLogin(username,password);
//            request.getSession().setAttribute("userType","re");
//            request.getSession().setAttribute("userId",re.getReId());
//            request.getSession().setAttribute("userName",re.getReUsername());
//            return "{\"success\":\"true\",\"userType\":\"re\",\"msg\":\"\"}";
//        }
//        HttpSession session = request.getSession();
//        session.setAttribute("user","test");
        return "{\"success\":\"false\",\"msg\":\"账号或密码错误！\"}";

    }

    @RequestMapping(value = "/getLoginIndex", method = RequestMethod.GET)
    public String getLoginIndex(HttpServletRequest request) {
        if (request.getSession().getAttribute("userName") != null) {
            return "index";
        } else {
            return "redirect:index.html";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus) {
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:index.html";
    }


    @RequestMapping(value = "/getReIndex", method = RequestMethod.GET)
    public String getReIndex(HttpServletRequest request) {
        return "reIndex";
    }

    @RequestMapping("/getAdminIndex")
    public String getAdminIndex(HttpServletRequest request) {
        return "adminIndex";
    }

    @RequestMapping("/jsPersonCenter")
    public String jsPersonCenter(HttpServletRequest request) {
        String type = request.getParameter("type");
        if (!StringUtils.isEmpty(type)) {
            switch (type) {
                case "js":
                    return "jsPersonCenter";
                case "re":
                    return "reIndex";
                case "admin":
                    return "adminIndex";
                default:
                    ;
            }
        }
        return "redirect:index.html";
    }

    @RequestMapping("/toJsInfo")
    public String toJsInfo(HttpServletRequest request) {
        Integer jsId = (Integer) request.getSession().getAttribute("userId");
        JobSeekerResponse jsResponse = userService.getJsById(jsId);
        log.info("====jsResponse:" + jsResponse.toString());
        request.setAttribute("js", jsResponse);
        return "jsInfo";
    }

    @RequestMapping(value = "/usernameCheck", method = RequestMethod.GET)
    @ResponseBody
    public String usernameCheck(String username, String id) {
        log.info("========username:" + username + ",id:" + id);
        if ("js".equals(id)) {
            JobSeeker jobSeeker = userService.checkJsUsername(username);
            if (jobSeeker == null) {
                return "{\"exist\":\"false\"}";
            }
        } else {
            Recruiter recruiter = userService.checkReUsername(username);
            if (recruiter == null) {
                return "{\"exist\":\"false\"}";
            }
        }
        return "{\"exist\":\"true\"}";
    }

    @RequestMapping("/turnJobManage")
    public String turnJobManage(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        List<JobResponse> jobList = userService.queryJobListByReId(userId);
        //log.info(jobList.toString());
        request.setAttribute("jobList", jobList);
        return "jobManage";
    }

    @RequestMapping("/turnReInfo")
    public String turnReInfo(HttpServletRequest request) {
        return "reInfo";
    }


    @RequestMapping("/turnJsFeedback")
    public String turnJsFeedback(HttpServletRequest request) {
        return "jsFeedback";
    }


    @RequestMapping("/turnJsRecommend")
    public String turnJsRecommend(HttpServletRequest request) {
        return "jsRecommend";
    }

    @RequestMapping("/sendFeedback")
    @ResponseBody
    public String sendFeedback(HttpServletRequest request) {
        String msg = request.getParameter("msg");
        Integer jsId = (Integer) request.getSession().getAttribute("userId");
        log.info(msg);
        Integer resultCode = userService.sendFeedback(jsId, msg);
        if (resultCode > 0) {
            return "{\"success\":true}";
        } else {
            return "{\"success\":false}";
        }
    }

    @RequestMapping("/getFeedback")
    @ResponseBody
    public List<Feedback> getFeedback(HttpServletRequest request) {
        Integer jsId = (Integer) request.getSession().getAttribute("userId");
        List<Feedback> feedbackList = userService.getFeedbackList(jsId, null);
        log.info(feedbackList.toString());
        return feedbackList;
    }

    @RequestMapping("/turnAdminFeedback")
    public String turnAdminFeedback(HttpServletRequest request) {
        return "adminFeedback";
    }

    @RequestMapping("/getMyFeedback")
    @ResponseBody
    public List<Feedback> getMyFeedback(HttpServletRequest request) {
        Integer adminId = (Integer) request.getSession().getAttribute("userId");
        List<Feedback> myFeedbackList = userService.getFeedbackList(null, adminId);
        return myFeedbackList;
    }

    @RequestMapping("/getNoReplyFeedback")
    @ResponseBody
    public List<Feedback> getNoReplyFeedback(HttpServletRequest request) {
        List<Feedback> noReplyFeedbackList = userService.getNoReplyFeedbackList();
        return noReplyFeedbackList;
    }

    @RequestMapping("/replyFeedback")
    @ResponseBody
    public String replyFeedback(HttpServletRequest request) {
        Integer adminId = (Integer) request.getSession().getAttribute("userId");
        Integer feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        String replyMsg = request.getParameter("msg");
        log.info("feedbackId:" + feedbackId + ",replyMsg:" + replyMsg);
        Integer resultCode = userService.replyFeedback(adminId, replyMsg, feedbackId);
        if (resultCode > 0) {
            return "{\"success\":true}";
        } else {
            return "{\"success\":false}";
        }
    }

    @RequestMapping(value = "/turnJsJobCollection", method = RequestMethod.GET)
    public String turnJsJobCollection(HttpServletRequest request) {
        return "jsJobCollection";
    }

    @RequestMapping(value = "/deleteFeedback", method = RequestMethod.GET)
    @ResponseBody
    public String deleteFeedback(HttpServletRequest request, Integer feedbackId) {
        Integer resultCode = userService.deleteFeedback(feedbackId);
        if (resultCode > 0) {
            return "{\"success\":true}";
        } else {
            return "{\"success\":false}";
        }
    }

    @RequestMapping(value = "/turnAdminJobManage", method = RequestMethod.GET)
    public String turnAdminJobManage(HttpServletRequest request) {
        return "adminJobManage";
    }

    @RequestMapping(value = "/changeJsInfo", method = RequestMethod.POST)
    public String changeJsInfo(HttpServletRequest request,JobSeekerRequest jsRequest) throws IOException {
        Integer jsId = (Integer)request.getSession().getAttribute("userId");
        jsRequest.setJsId(jsId);
        if(jsRequest.getJsHeadImg() != null) {
            // 原始名称
            String oldFileName = jsRequest.getJsHeadImg().getOriginalFilename(); // 获取上传文件的原名
            // 存储图片的虚拟本地路径
            String relativePath = System.getProperty("user.dir");
            String saveFilePath = relativePath + "\\src\\main\\resources\\static\\jsHeadImg";
            // 上传图片
            if (jsRequest.getJsHeadImg() != null && oldFileName != null && oldFileName.length() > 0) {
                // 新的图片名称
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                jsRequest.setNewJsHeadImg(newFileName);
                System.out.println(saveFilePath + "\\" + newFileName);
                // 新图片
                File newFile = new File(saveFilePath + "\\" + newFileName);
                // 将内存中的数据写入磁盘
                jsRequest.getJsHeadImg().transferTo(newFile);
                File file = new File(saveFilePath + "\\" + jsRequest.getOldJsHeadImg());
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        Integer resultCode = userService.updateJobSeeker(jsRequest);
        return "jsPersonCenter";
    }

    @RequestMapping("/getJobTags")
    @ResponseBody
    public String getJobTags(HttpServletRequest request){
        Integer jsId = (Integer)request.getSession().getAttribute("userId");
        String jobTags = userService.getJobTags(jsId);
        if(!(StringUtils.isEmpty(jobTags))){
            return "{\"success\":true}";
        }
        return "{\"success\":false}";
    }

    @RequestMapping("/turnJobRecommend")
    public String turnJobRecommend(HttpServletRequest request){
        return "jobRecommend";
    }
}
