package com.luoxd.graduation_project.response;

import com.luoxd.graduation_project.domain.ChildClasses;
import com.luoxd.graduation_project.domain.JobClasses;
import lombok.Data;

import java.util.List;

/**
 * @author luoxd
 * 首页职业分类组件的数据
 */
@Data
public class TestAjaxResponse {
    private String classesName;

    private List<JobClasses> recommendJobClasses;

    private List<ChildClasses> childClassesList;
}
