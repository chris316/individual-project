package com.itlize.service;


import com.itlize.entity.Project;

import java.util.List;

public interface ProjectService {

    public List<Project> getProjects();
    public Project getProjectById(int id);
    public boolean createProject(Project project);
    public boolean deleteProject(int id);
    public boolean deleteProject(Project project);
    public void clear();

}
