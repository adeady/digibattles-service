package com.adeady.digibattles

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.ResourceSupport

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//todo: split up entity from resource
@Entity
public class HelloWorld extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long dbId;

    String firstName;
    String lastName;
    String content;
}
