package com.adeady.digibattles.monster;

import com.adeady.digibattles.HelloWorld;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MonsterRepository extends CrudRepository<MonsterDefinition, Long> {

    List<HelloWorld> findByName(String lastName);
}