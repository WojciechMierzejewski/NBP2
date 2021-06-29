package com.example.pjatk.nbp.service;

import com.example.pjatk.nbp.model.NbpResource;
import com.example.pjatk.nbp.model.Rate;
import com.example.pjatk.nbp.model.Source;
import com.example.pjatk.nbp.repository.NbpRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NbpService {
    private final RestTemplate restTemplate;
    private final NbpRepository nbpRepository;


    public NbpService(RestTemplate restTemplate, NbpRepository nbpRepository) {
        this.restTemplate = restTemplate;
        this.nbpRepository = nbpRepository;
    }
//jak zrobić, aby NbpResource dostał midy z listy json rates
    // działa, ale mid = 0
//{
//    "table": "A",
//        "currency": "dolar amerykański",
//        "code": "USD",
//        "rates": [
//    {
//        "no": "121/A/NBP/2021",
//            "effectiveDate": "2021-06-25",
//            "mid": 3.7749                     <-te midy
//    },
//    {
//        "no": "122/A/NBP/2021",
//            "effectiveDate": "2021-06-28",
//            "mid": 3.7762                     <-te midy
//    }
//    ]
//}

    public NbpResource calculateSourceForCurrency(String currency, int numberOfDays) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/last/" + numberOfDays;;
        //ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<NbpResource> responseEntity = restTemplate.getForEntity(url, NbpResource.class);
        NbpResource nbpResource = responseEntity.getBody();
        return nbpRepository.save(nbpResource);
    }

}
