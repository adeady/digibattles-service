package com.adeady.digibattles.monster

import org.springframework.hateoas.ResourceSupport

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//todo: split up entity from resource
@Entity
public class MonsterDefinition extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long monsterId;

    String name;
    String type;
    String icon;
}
