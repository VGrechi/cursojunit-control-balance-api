package com.cursojunit.controlbalanceapi.controller;

import com.cursojunit.controlbalanceapi.models.Balance;
import com.cursojunit.controlbalanceapi.models.dto.BalanceDTO;
import com.cursojunit.controlbalanceapi.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;


    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Balance> postBalance(@RequestBody BalanceDTO balanceDTO){
        if(balanceDTO.getOrderId() == null || balanceDTO.getOrderId() <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(balanceDTO.getEventId() == null || balanceDTO.getEventId() <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(balanceDTO.getBalanceValue() == null || balanceDTO.getBalanceValue() <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Balance balance = balanceService.createBalance(balanceDTO);

        if(balance == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping(value = "/{balanceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Balance> getBalanceByBalanceId(@PathVariable Integer balanceId){
        if(balanceId == null || balanceId <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Balance balance = balanceService.getBalanceByBalaceId(balanceId);

        if(balance == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Balance> getBalanceByOrderId(@RequestParam Integer orderId){
        if(orderId == null || orderId <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Balance balance = balanceService.getBalanceByOrderId(orderId);

        if(balance == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(balance, HttpStatus.OK);
    }


}
