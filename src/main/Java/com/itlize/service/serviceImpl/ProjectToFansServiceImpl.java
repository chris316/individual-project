package com.itlize.service.serviceImpl;

import com.itlize.entity.Fans;
import com.itlize.entity.Project;
import com.itlize.entity.ProjectToFans;
import com.itlize.repository.FansRepository;
import com.itlize.repository.ProjectRepository;
import com.itlize.repository.ProjectToFansRepository;
import com.itlize.service.ProjectToFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectToFansServiceImpl implements ProjectToFansService {
    @Autowired
    private ProjectToFansRepository projectToFansRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FansRepository fansRepository;

    @Override
    @Transactional
    public boolean create(ProjectToFans projectToFans, Project project, Fans fan) {
        if(projectToFans==null || project==null || fan==null)
            return false;
        if(!projectToFansRepository.findByProjectAndFans(project,fan).isEmpty()){
            System.out.println(projectToFansRepository.findByProjectAndFans(project,fan));
            System.out.println("project has already added the fan");
            return false;
        }
        try{
            System.out.println("Trying to link projects and fan");
            projectToFansRepository.save(projectToFans);
            project.addProjectToFans(projectToFans);
            fan.addProjects(projectToFans);
            projectRepository.save(project);
            fansRepository.save(fan);
        }catch (Exception e){
            System.out.println("Sth wrong happens when creating " + projectToFans.toString());
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(ProjectToFans projectToFans) {
        if(projectToFans==null){
            System.out.println("null input for deleting projectToResource");
        }
        try{
            projectToFansRepository.delete(projectToFans);
        }catch (Exception e){
            System.out.println("Sth wrong happens when deleting " + projectToFans.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public ProjectToFans get(Integer id) {
        Optional<ProjectToFans> target = projectToFansRepository.findById(id);
        if(target.isPresent()){
            return target.get();
        }
        return null;
    }

    @Override
    @Transactional
    public ProjectToFans get(Project project, Fans fan) {
        List<ProjectToFans> res = projectToFansRepository.findByProjectAndFans(project,fan);
        if(res.size()>0){
            return res.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public List<ProjectToFans> get(Project project) {
        return projectToFansRepository.findByProject(project);
    }

    @Override
    @Transactional
    public void clear() {
        projectToFansRepository.deleteAll();
    }



}
