package com.adeady.digibattles.monster

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@ExposesResourceFor(MonsterDefinition.class)
@RequestMapping("/monster")
public class MonsterController {

    @Autowired EntityLinks entityLinks;
    @Autowired MonsterRepository monsterRepository;

    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody HttpEntity list() {

        def items = monsterRepository.findAll()
        items.each {
            addLinks(it)
        }

        return new ResponseEntity(items, HttpStatus.OK)
    }

    void addLinks(monster) {
        def link = entityLinks.linkToSingleResource(MonsterDefinition, monster.monsterId)
        monster.add(link)
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody HttpEntity create(@RequestBody MonsterDefinition monster) {

        monster = monsterRepository.save(monster)
        addLinks(monster)

        return new ResponseEntity(monster, HttpStatus.CREATED)
    }

    @RequestMapping(value = "/{monsterId}", method = RequestMethod.GET)
    @ResponseBody HttpEntity get(@PathVariable Long monsterId) {

        def monster = monsterRepository.findOne(monsterId)
        addLinks(monster)

        return new ResponseEntity(monster, HttpStatus.OK)
    }

}