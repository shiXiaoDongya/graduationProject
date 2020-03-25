package com.luoxd.graduation_project.web;

import com.luoxd.graduation_project.domain.Recruiter;
import com.luoxd.graduation_project.service.UserService;
import com.luoxd.graduation_project.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private String mynewpic;

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

    // uploadFile
    @RequestMapping("/saveCompanyPic")
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile companyPic)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = companyPic.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径
        String relativePath =  System.getProperty("user.dir");
        String saveFilePath = relativePath + "\\src\\main\\resources\\companyPic";
        System.out.println("相对路径--"+saveFilePath);
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
    public String register(@Param("username") String username, @Param("password")String password, @Param("confirmPassword")String confirmPassword,
                           @Param("realname")String realname,@Param("idCard")String idCard, @Param("phone")String phone,
                           @Param("email")String email, @Param("sq")String sq, @Param("comfirmSq")String comfirmSq, @Param("company")String company,
                           @Param("companyPosition")String companyPosition,@Param("companyPic")MultipartFile companyPic,@Param("id")String id) throws IOException {
        if(id.equals("re")){
            Recruiter recruiter = new Recruiter();
            recruiter.setUsername(username);
            recruiter.setPassword(password);
            recruiter.setRealname(realname);
            recruiter.setIdCard(idCard);
            recruiter.setGender("男");
            recruiter.setPhone(phone);
            recruiter.setEmail(email);
            recruiter.setSq(sq);
            recruiter.setCompany(company);
            recruiter.setCompanyPosition(companyPosition);
            // 原始名称
            String oldFileName = companyPic.getOriginalFilename(); // 获取上传文件的原名
            // 存储图片的虚拟本地路径
            String relativePath = System.getProperty("user.dir");
            String saveFilePath = relativePath + "\\src\\main\\resources\\companyPic";
            // 上传图片
            if (companyPic != null && oldFileName != null && oldFileName.length() > 0) {
                // 新的图片名称
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                recruiter.setCompanyPic(newFileName);
                System.out.println(saveFilePath + "\\" + newFileName);
                // 新图片
                File newFile = new File(saveFilePath + "\\" + newFileName);
                // 将内存中的数据写入磁盘
                companyPic.transferTo(newFile);
                log.info(recruiter.toString());
                userService.insertRecruiter(recruiter);
                return "redirect:regist.html";
            } else {
                return "redirect:regist.html";
            }
        }
        else{
            return "redirect:regist.html";
        }
    }
}
