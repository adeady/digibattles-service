package com.adeady.digibattles.monster

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.hateoas.ResourceSupport

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

//todo: split up entity from resource
@Entity
public class MonsterDefinition extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long monsterId

    String name
    String type
    String icon

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "monster")
    TalentTreeDefinition talentTree
}
