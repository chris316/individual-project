package com.itlize.service;

import com.itlize.entity.Fans;
import com.itlize.entity.Project;
import com.itlize.entity.ProjectToFans;

import java.util.List;

public interface ProjectToFansService {
    public boolean create(ProjectToFans projectToFans, Project project, Fans fan);
    public boolean delete(ProjectToFans projectToFans);
    public ProjectToFans get(Integer id);
    public ProjectToFans get(Project project, Fans fan);
    public List<ProjectToFans> get(Project project);
    public void clear();
}
