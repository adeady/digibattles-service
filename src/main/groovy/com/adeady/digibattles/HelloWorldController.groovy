package com.adeady.digibattles

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*

@Controller
@ExposesResourceFor(HelloWorld.class)
@RequestMapping("/greeting")
public class HelloWorldController {

    @Autowired EntityLinks entityLinks;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody HttpEntity<HelloWorld> greeting(
            @PathVariable("name") String name) {

        HelloWorld greeting = new HelloWorld("Hello, $name")
        def link = entityLinks.linkToSingleResource(HelloWorld.class, name);

        greeting.add(link)

        return new ResponseEntity<HelloWorld>(greeting, HttpStatus.OK)
    }
}