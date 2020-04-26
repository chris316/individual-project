package com.itlize.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="project")
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="projectName")
    private String projectName;

    @Column(name="address")
    private String address;

    @Column(name="projectType")
    private String projectType;

    @Column(name="projectSize")
    private int projectSize;

    @Column(name="client")
    private String client;

    @JsonIgnore
    @OneToMany(targetEntity = ProjectToFans.class,cascade = CascadeType.REMOVE, mappedBy = "project")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectToFans> fans = new HashSet<ProjectToFans>();

    public Project(){};

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public int getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize = projectSize;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Collection<ProjectToFans> getFans() {
        return fans;
    }
    public void addProjectToFans(ProjectToFans vFans){
        if(fans.contains(vFans)){
            return ;
        }
        fans.add(vFans);
        vFans.setProject(this);
    }

    public void removeProjectToFans(ProjectToFans vFans){
        if(!fans.contains(vFans)){
            return ;
        }
        fans.remove(vFans);
        vFans.setProject(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFans(Collection<ProjectToFans> fans) {
        this.fans = fans;
    }

    @Override
    public String toString(){
        return "Project [id: "+id+"\nProject Name: "+projectName+"\nAddress: "+address+"\nProject Type: "+projectType+"\nProject Size: "+projectSize+"\nClient Name: "+client+"]";

    }
}
