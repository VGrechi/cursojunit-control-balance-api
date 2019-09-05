package com.cursojunit.controlbalanceapi.service;

import com.cursojunit.controlbalanceapi.models.Balance;
import com.cursojunit.controlbalanceapi.models.BalanceContract;
import com.cursojunit.controlbalanceapi.models.dto.BalanceDTO;
import com.cursojunit.controlbalanceapi.repository.BalanceContractRepository;
import com.cursojunit.controlbalanceapi.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private BalanceContractRepository balanceContractRepository;

    public Balance createBalance(BalanceDTO balanceDTO){
        Balance balance = balanceRepository.selectBalanceByOrderId(balanceDTO.getOrderId());

        BalanceContract balanceContract = balanceContractRepository.selectBalanceContractsByEventId(balanceDTO.getEventId());
        if(balanceContract != null) return null;

        BalanceContract contract = new BalanceContract();
        contract.setEventId(balanceDTO.getEventId());
        contract.setBalanceValue(balanceDTO.getBalanceValue());

        if(balance == null){
            balance = new Balance();
            balance.setOrderId(balanceDTO.getOrderId());
            balance.setBalanceValue(balanceDTO.getBalanceValue());
            Integer balanceId = balanceRepository.insertBalance(balance);
            balance.setBalanceId(balanceId);

            contract.setBalance(balance);
            balanceContractRepository.insertBalanceContract(contract);
        }else{
            contract.setBalance(balance);
            balanceContractRepository.insertBalanceContract(contract);

            balance.setBalanceValue(balance.getBalanceValue() + balanceDTO.getBalanceValue());
            balanceRepository.updateBalance(balance);
        }

        return balance;
    }

    public Balance getBalanceByBalaceId(Integer balanceId) {
        Balance balance = balanceRepository.selectBalanceByBalanceId(balanceId);

        if(balance == null) return null;

        List<BalanceContract> balanceContractList = balanceContractRepository.selectBalanceContractsByBalanceId(balanceId);
        balance.setBalanceContracts(balanceContractList);

        return balance;
    }

    public Balance getBalanceByOrderId(Integer orderId) {
        Balance balance = balanceRepository.selectBalanceByOrderId(orderId);

        if(balance == null) return null;

        List<BalanceContract> balanceContractList = balanceContractRepository.selectBalanceContractsByBalanceId(balance.getBalanceId());
        balance.setBalanceContracts(balanceContractList);

        return balance;
    }
}
