package com.adeady.digibattles

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.ResourceSupport

class HelloWorld extends ResourceSupport {

    private final String content;

    @JsonCreator
    public HelloWorld(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
