package com.adeady.digibattles

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
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
    @Autowired HelloWorldRepository helloWorldRepository;

    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody HttpEntity list() {

        def items = helloWorldRepository.findAll()
        items.each {
            def link = entityLinks.linkToSingleResource(HelloWorld.class, it.dbId);
            it.add(link)
        }

        return new ResponseEntity(items, HttpStatus.OK)
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody HttpEntity create(@RequestBody HelloWorld helloWorld) {

        def item = helloWorldRepository.save(helloWorld)
        def link = entityLinks.linkToSingleResource(HelloWorld.class, item.dbId);
        item.add(link)

        return new ResponseEntity(item, HttpStatus.CREATED)
    }

    @RequestMapping(value = "/{dbId}", method = RequestMethod.GET)
    @ResponseBody HttpEntity get( @PathVariable("dbId") Long dbId) {

        def item = helloWorldRepository.findOne(dbId)
        def link = entityLinks.linkToSingleResource(HelloWorld.class, dbId);

        item.add(link)

        return new ResponseEntity<HelloWorld>(item, HttpStatus.OK)
    }

    @RequestMapping(value = "/{dbId}/greet", method = RequestMethod.GET)
    @ResponseBody HttpEntity getGreeting( @PathVariable("dbId") Long dbId) {

        def item = helloWorldRepository.findOne(dbId)
        item.content = "Hello, $item.firstName"
        def link = entityLinks.linkToSingleResource(HelloWorld.class, dbId);

        item.add(link)

        return new ResponseEntity<HelloWorld>(item, HttpStatus.OK)
    }


}