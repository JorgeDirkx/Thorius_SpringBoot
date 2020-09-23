package com.jorgedirkx.repositories;

import com.jorgedirkx.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,Long> {

    List<Project> findByName(String name);
}
