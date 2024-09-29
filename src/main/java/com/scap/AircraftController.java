package com.scap;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
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

    public HttpResponse<Aircraft> save(@Body Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(aircraft);
    }

    public HttpResponse<Aircraft> update(@Body Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(aircraft);
    }

    public HttpResponse<Aircraft> delete(@Body Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(aircraft);
    }
}
