package com.adeady.digibattles;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface HelloWorldRepository extends CrudRepository<HelloWorld, Long> {

    List<HelloWorld> findByLastName(String lastName);
}