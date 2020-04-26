package com.itlize.controller;

import com.itlize.entity.*;
import com.itlize.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private FansService fansService;
    @Autowired
    private ProjectToFansService projectToFansService;
    @Autowired
    private UserService userService;

    private User getCurrentUser(Principal principal) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        return userService.findByUsername(authenticationToken.getName());
    }


    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestParam(name="projectName") String projectName, @RequestParam(name="address") String address, @RequestParam(name="type") String type, @RequestParam(name="size") Integer size, @RequestParam(name="client") String client){
        Project projectToAdd = new Project();
        projectToAdd.setProjectName(projectName);
        projectToAdd.setAddress(address);
        projectToAdd.setProjectType(type);
        projectToAdd.setProjectSize(size);
        projectToAdd.setClient(client);
        boolean isSuccessful=projectService.createProject(projectToAdd);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when creating new project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectToAdd.toString(),HttpStatus.OK);
    }

    @PostMapping("/deleteProject")
    public ResponseEntity<?> deleteProject(@RequestParam(name="projectId") Integer projectId){
        Project projectToDelete=projectService.getProjectById(projectId);
        if(projectToDelete==null){
            return new ResponseEntity<>("{\"error\":\"project does not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        boolean isSuccessful=projectService.deleteProject(projectToDelete);
        String body=projectToDelete.toString();
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/readProjects")
    public List<Project> read(){
        return projectService.getProjects();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @GetMapping("/getProjectFans")
    public List<Fans>getFans(@RequestParam(name="projectId")Integer projectId) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            List<Fans> fans = new ArrayList<Fans>();
            return fans;
        }
        List<ProjectToFans> pToFans = projectToFansService.get(project);
        List<Fans> fans = new ArrayList<Fans>();
        for(ProjectToFans projectToFan:pToFans)
        {
            fans.add(projectToFan.getFans());
        }
        return fans;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @ResponseBody
    @PostMapping("/addFanToProject")
    public boolean addFan(@RequestParam(name = "projectId")Integer projectId, @RequestParam(name = "fanId")Integer fanId){
        Project project = projectService.getProjectById(projectId);
        if(project == null){
            return false;
        }
        Fans fan = fansService.getFanById(fanId);
        if(fan==null){
            return false;
        }
        ProjectToFans ptr = new ProjectToFans();
        System.out.println(ptr.getProject());
        System.out.println(ptr.getFans());
        System.out.println(project.getId());
        System.out.println(fan.getId());
        boolean isSuccessful=projectToFansService.create(ptr,project,fan);
        if(!isSuccessful){
            return false;
        }
        return true;
    }

    @PostMapping("/deleteFanFromProject")
    public ResponseEntity<?> deleteFan(@RequestParam(name = "projectId")Integer projectId, @RequestParam(name = "fanId")Integer fanId){
        Project project = projectService.getProjectById(projectId);
        if(project == null){
            return new ResponseEntity<>("{\"error\":\"project not found!\"}",HttpStatus.BAD_REQUEST);
        }

        Fans fan = fansService.getFanById(fanId);
        if(fan==null){
            return new ResponseEntity<>("{\"error\":\"fan does not exist!\"}",HttpStatus.BAD_REQUEST);
        }

        ProjectToFans ptr = projectToFansService.get(project,fan);
        boolean isSuccessful = projectToFansService.delete(ptr);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when deleting fan from current project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ptr.toJson(),HttpStatus.OK);
    }


}
