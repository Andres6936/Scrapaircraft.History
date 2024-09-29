package com.scap;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/pin")
public class PinController {

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "Pin ...";
    }
}
