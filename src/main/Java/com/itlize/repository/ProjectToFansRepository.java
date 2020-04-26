package com.itlize.repository;

import com.itlize.entity.Fans;
import com.itlize.entity.Project;
import com.itlize.entity.ProjectToFans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectToFansRepository extends JpaRepository<ProjectToFans,Integer> {
    public List<ProjectToFans> findByProject(Project project);
    public List<ProjectToFans> findByProjectAndFans(Project project, Fans fan);
}
