package com.adeady.digibattles.monster

import org.springframework.hateoas.ResourceSupport

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

//todo: split up entity from resource
@Entity
public class TalentTreeDefinition extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long treeId

    @OneToOne
    MonsterDefinition monster

}
