package com.luoxd.graduation_project.web;

import com.luoxd.graduation_project.domain.JobClasses;
import com.luoxd.graduation_project.service.UserService;
import com.luoxd.graduation_project.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getUsers")
    public String getUsers(HttpServletRequest request) {
        String[] hotSearch = {"1","2"};
        request.setAttribute("hotSearch",hotSearch);
        return "bootstrap";
    }

    @RequestMapping("/websocket/{id}/{name}")
    public String webSocket(@PathVariable String id,@PathVariable String name, Model model){
        try{
            log.info("跳转到websocket的页面上");
            model.addAttribute("id",id);
            model.addAttribute("username",name);
            return "websocket";
        }
        catch (Exception e){
            log.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "testAjax",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray testAjax(){
        List<JobClasses> testList = new ArrayList<>();
        JobClasses test = new JobClasses();
        test.setTitle("技术");
        test.setRecommend("Java,PHP,web前端,算法工程师");
        test.setSubClasses("[{\"后端开发\":\"后端开发,Java,C++,PHP\"},{\"移动开发\":\"移动开发,HTML5,Android\"}]");
        testList.add(test);
        JobClasses test2 = new JobClasses();
        test2.setTitle("技术2");
        test2.setRecommend("Java,PHP,web前端,算法工程师");
        test2.setSubClasses("[{\"后端开发\":\"后端开发,Java,C++,PHP\"},{\"移动开发\":\"移动开发,HTML5,Android\"}]");
        testList.add(test2);
        JSONArray jsonArray = JSONArray.fromObject(testList);
        return jsonArray;
    }


    /**
     * 登录网页是根据IP获取所在城市
     * @param request
     * @return
     */
    @RequestMapping(value = "getLocation",method = RequestMethod.GET)
    @ResponseBody
    public String getLocation(HttpServletRequest request) {
        String city = null;
        String ipaddre = AddressUtils.getIp(request);
        try {
            city = AddressUtils.getAddresses(ipaddre);
            log.info("IP所在城市："+city);
        } catch (UnsupportedEncodingException e) {
            log.error("获取用户Ip信息失败");
        }
        System.out.println(city);
        return city;
    }

}
