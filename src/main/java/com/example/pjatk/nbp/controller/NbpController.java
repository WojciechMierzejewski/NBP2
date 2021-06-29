package com.example.pjatk.nbp.controller;

import com.example.pjatk.nbp.model.NbpResource;
import com.example.pjatk.nbp.service.NbpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }
    @ApiOperation(value = "get rates", response = NbpResource.class, notes = "This method will return currency calculations")

    @GetMapping(value = "/{currency}/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NbpResource> calculateSourceForCurrency(
            @ApiParam(name = "currency",
                    type = "String",
                    value = "currency",
                    required = true,
                    defaultValue = "not cheap")

            @PathVariable String currency, @RequestParam(defaultValue = "1")
            int numberOfDays){
            return ResponseEntity.ok(nbpService.calculateSourceForCurrency(currency, numberOfDays));
    }


}
