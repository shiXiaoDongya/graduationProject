package com.luoxd.graduation_project.domain;

import lombok.Data;

import java.util.List;

@Data
public class JobClasses {
   private Integer jobClassesId;

   private String jobClassesName;

   /**
    *是否是推荐的职位分类，0否，1是
    */
   private Integer isRecommend;

   private String jobClassesUrl;
}
