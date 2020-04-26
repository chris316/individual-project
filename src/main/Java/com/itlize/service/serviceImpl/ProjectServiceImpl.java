package com.itlize.service.serviceImpl;


import com.itlize.entity.Project;
import com.itlize.repository.ProjectRepository;
import com.itlize.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public List<Project> getProjects(){return projectRepository.findAll();};

    @Override
    @Transactional
    public Project getProjectById(int id){
        Optional<Project> proj=projectRepository.findById(id);
        if(proj.isPresent())
        {
            return proj.get();
        }
        return null;
    }

    @Override
    @Transactional
    public boolean createProject(Project project)
    {
        /*Project target = projectRepository.findById(project.getId()).get();
        if(target!=null){
            System.out.println("project already exists");
            return false;
        }*/
        try{
            projectRepository.save(project);
        }catch (Exception e){
            System.out.println("Sth wrong happens when creating: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    @Override
    @Transactional
    public boolean deleteProject(int id){
        try{

            projectRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Sth wrong happens when deleting");
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    @Override
    @Transactional
    public boolean deleteProject(Project project){
        try{
            projectRepository.delete(project);
        }catch(Exception e){
            System.out.println("Sth wrong happens when deleting");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void clear(){projectRepository.deleteAll();}
}
