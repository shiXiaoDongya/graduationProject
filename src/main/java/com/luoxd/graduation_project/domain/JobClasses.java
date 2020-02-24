package com.luoxd.graduation_project.domain;

import lombok.Data;

@Data
public class JobClasses {
    //职位分类总标题
    private String title;

    //该职位分类的推荐分类,多个分类用","隔开
    private String recommend;

    //该职位分类的子分类，格式:[{"后端开发":"后端开发,Java,C++,PHP..."},{"移动开发":"移动开发,HTML5,Android..."}]
    private String subClasses;
}
