package com.luoxd.graduation_project.web;

import com.luoxd.graduation_project.service.UserService;
import com.luoxd.graduation_project.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

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
        return city;
    }

}
