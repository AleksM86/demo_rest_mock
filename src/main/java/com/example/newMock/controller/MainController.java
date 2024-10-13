package com.example.newMock.controller;

import com.example.newMock.model.RequestDTO;
import com.example.newMock.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
          String clientId = requestDTO.getClientId();
          char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit, balance;
            String currency = "";
            if (firstDigit == '8'){
                maxLimit = new BigDecimal(2000);
                balance = BigDecimal.valueOf(new Random().nextDouble() * 2000.0)
                        .setScale(2, RoundingMode.HALF_UP);;
                currency = "US";
            } else if (firstDigit == '9'){
                maxLimit = new BigDecimal(1000);
                balance = BigDecimal.valueOf(new Random().nextDouble() * 1000.0)
                        .setScale(2, RoundingMode.HALF_UP);;
                currency = "EU";
            } else {
                maxLimit = new BigDecimal(10000);
                balance = BigDecimal.valueOf(new Random().nextDouble() * 10000.0)
                        .setScale(2, RoundingMode.HALF_UP);
                currency = "RUB";
            }
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setClientId(requestDTO.getClientId());
            responseDTO.setMaxLimit(maxLimit);
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance);
            logger.info("********** RequestDTO **********" +
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            logger.info("********** ResponseDTO **********" +
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
