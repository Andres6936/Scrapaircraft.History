package com.scap;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

@Controller("/aircraft")
public class AircraftController {

    @Get("/findOne")
    public HttpResponse<Aircraft> findOne() {
        return HttpResponse.status(HttpStatus.OK).body(new Aircraft());
    }

    @Get("/findMany")
    public HttpResponse<Aircraft[]> findAll() {
        return HttpResponse.status(HttpStatus.OK).body(new Aircraft[]{});
    }

    @Put("/save")
    public HttpResponse<Aircraft> save(@Body Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(aircraft);
    }

    @Post("/update")
    public HttpResponse<Aircraft> update(@Body Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(aircraft);
    }

    @Delete("/delete")
    public HttpResponse<Aircraft> delete(@Body Aircraft aircraft) {
        return HttpResponse.status(HttpStatus.OK).body(aircraft);
    }
}
