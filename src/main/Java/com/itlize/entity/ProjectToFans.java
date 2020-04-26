package com.itlize.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class ProjectToFans {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(targetEntity = Project.class,cascade = CascadeType.DETACH)
    private Project project;

    @ManyToOne(targetEntity = Fans.class,cascade = CascadeType.DETACH)
    private Fans fans;

    @Override
    public String toString() {
        return "ProjectToFans{" +
                "id=" + id +
                ", project=" + project +
                ", fan=" + fans +
                '}';
    }

    public String toJson() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"project\":\"" + project +
                "\", \"fan\":\"" + fans +
                "\"}";
    }

    public ProjectToFans() {
    }
    public ProjectToFans(Project projectId, Fans fanId) {
        this.project = projectId;
        this.fans = fanId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Fans getFans() {
        return fans;
    }

    public void setFans(Fans fans) {
        this.fans = fans;
    }

}
