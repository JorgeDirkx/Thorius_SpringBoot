package com.jorgedirkx.repositories;

import com.jorgedirkx.entities.Bug;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BugRepository extends CrudRepository<Bug,Integer> {

    /*List<Bug> findByName(String name);*/
}
