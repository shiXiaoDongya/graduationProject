package com.luoxd.graduation_project.service;

import com.luoxd.graduation_project.domain.Classes;
import com.luoxd.graduation_project.response.ClassesResonse;

import java.util.List;

public interface ClassesService {
    List<ClassesResonse> queryClassesList();
}