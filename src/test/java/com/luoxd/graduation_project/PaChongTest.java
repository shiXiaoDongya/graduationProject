package com.luoxd.graduation_project;

import com.luoxd.graduation_project.domain.ChildClasses;
import com.luoxd.graduation_project.domain.Classes;
import com.luoxd.graduation_project.domain.JobClasses;
import com.luoxd.graduation_project.mapper.JobMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = GraduationProjectApplication.class)
public class PaChongTest{

    @Autowired
    private JobMapper jobMapper;

    @Test
    public void testPaChong(){
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet("https://www.zhipin.com/guangzhou/");
        //设置请求头，将爬虫伪装成浏览器
        request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
//        HttpHost proxy = new HttpHost("60.13.42.232", 9999);
//        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//        request.setConfig(config);
        try {
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
                //System.out.println(html);

                //6.Jsoup解析html
                Document document = Jsoup.parse(html);
                //像js一样，通过标签获取title
                Elements divs = document.getElementsByClass("menu-sub");
                int classesId = 1;
                int childClassesId = 1;
                int jobClassesId = 1;
                for(Element div:divs){
                    Elements pList = div.getElementsByTag("p");
                    for (Element p:pList){
                        System.out.println(p.text());
                        Classes classes = new Classes();
                        classes.setClassesId(classesId);
                        classes.setClassesName(p.text());
                        int i = jobMapper.insertToClasses(classes);
                        Elements liList = div.getElementsByTag("li");
                        for(Element li:liList){
                            Element h4 = li.getElementsByTag("h4").first();
                            System.out.println("--"+h4.text());
                            ChildClasses childClasses = new ChildClasses();
                            childClasses.setChildClassesId(childClassesId);
                            childClasses.setChildClassesName(h4.text());
                            childClasses.setClassesId(classesId);
                            int j = jobMapper.insertToChildClasses(childClasses);
                            Elements alist = li.getElementsByTag("a");
                            for (Element a:alist) {
                                System.out.println("----"+a.text());
                                JobClasses jobClasses = new JobClasses();
                                jobClasses.setJobClassesId(jobClassesId);
                                jobClasses.setJobClassesName(a.text());
                                jobClasses.setChildClassesId(childClassesId);
                                int k = jobMapper.insertToJobClasses(jobClasses);
                                jobClassesId++;
                            }
                            childClassesId++;
                        }
                        classesId++;
                    }
                }


            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    @Test
    public void testPaChongRecommend(){
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet("https://www.zhipin.com/guangzhou/");
        //设置请求头，将爬虫伪装成浏览器
        request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
//        HttpHost proxy = new HttpHost("60.13.42.232", 9999);
//        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//        request.setConfig(config);
        try {
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
                //System.out.println(html);

                //6.Jsoup解析html
                Document document = Jsoup.parse(html);
                //像js一样，通过标签获取title
                Element div = document.getElementsByClass("job-menu").first();
                Elements ddList = div.getElementsByTag("dd");
                for (Element dd:ddList) {
                    Elements aList = dd.getElementsByTag("a");
                    for(Element a:aList){
                        System.out.println(a.text());
                        jobMapper.updateRecommendByName(a.text());
                    }
                }


            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }
}
