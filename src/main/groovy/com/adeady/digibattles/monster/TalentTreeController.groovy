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
@ExposesResourceFor(TalentTreeDefinition.class)
@RequestMapping("/talent")
public class TalentTreeController {

    @Autowired EntityLinks entityLinks;
    @Autowired TalentTreeRepository talentRepository;

    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody HttpEntity list() {

        def items = talentRepository.findAll()
        items.each {
            addLinks(it)
        }

        return new ResponseEntity(items, HttpStatus.OK)
    }

    void addLinks(talentTree) {
        def selfLink = entityLinks.linkToSingleResource(TalentTreeDefinition, talentTree.treeId)
        def monsterLink = entityLinks.linkForSingleResource(MonsterDefinition, talentTree.monster.monsterId).withRel("monster")
        talentTree.add(selfLink)
        talentTree.add(monsterLink)
    }

    @RequestMapping(value = "/{talentId}", method = RequestMethod.GET)
    @ResponseBody HttpEntity get(@PathVariable Long talentId) {

        def monster = talentRepository.findOne(talentId)
        addLinks(monster)

        return new ResponseEntity(monster, HttpStatus.OK)
    }

}