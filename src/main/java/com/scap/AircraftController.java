package com.scap;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;

import java.util.ArrayList;

@Controller("/aircraft")
public class AircraftController {
    public HttpResponse<Aircraft> findOne() {
        return HttpResponse.status(HttpStatus.OK).body(new Aircraft());
    }

    public HttpResponse<Aircraft[]> findAll() {
        return HttpResponse.status(HttpStatus.OK).body(new Aircraft[]{});
    }

    public HttpResponse<Aircraft> save(Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(new Aircraft());
    }

    public HttpResponse<Aircraft> update(Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(new Aircraft());
    }

    public HttpResponse<Aircraft> delete(Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(new Aircraft());
    }
}
