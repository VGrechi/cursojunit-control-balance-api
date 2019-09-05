package com.cursojunit.controlbalanceapi.repository;

import com.cursojunit.controlbalanceapi.models.Balance;
import com.cursojunit.controlbalanceapi.models.entities.public_.tables.records.BalanceRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import static com.cursojunit.controlbalanceapi.models.entities.public_.tables.Balance.BALANCE;

@Repository
public class BalanceRepository {

    @Autowired
    private DSLContext dslContext;

    public Balance selectBalanceByOrderId(Integer orderId){
        try {
            BalanceRecord balanceRecord = dslContext.selectFrom(BALANCE)
                    .where(BALANCE.ORDERID.eq(orderId))
                    .fetchOne();

            Balance balance = new Balance();
            balance.setBalanceId(balanceRecord.getBalanceid());
            balance.setOrderId(balanceRecord.getOrderid());
            balance.setBalanceValue(balanceRecord.getBalancevalue().doubleValue());
            return balance;
        }catch (Exception e){
            return null;
        }
    }

    public Balance selectBalanceByBalanceId(Integer balanceId) {
        try {
            BalanceRecord balanceRecord = dslContext.selectFrom(BALANCE)
                    .where(BALANCE.BALANCEID.eq(balanceId))
                    .fetchOne();

            Balance balance = new Balance();
            balance.setBalanceId(balanceRecord.getBalanceid());
            balance.setOrderId(balanceRecord.getOrderid());
            balance.setBalanceValue(balanceRecord.getBalancevalue().doubleValue());
            return balance;
        }catch (Exception e){
            return null;
        }
    }

    public Integer insertBalance(Balance balance){
        BalanceRecord balanceRecord = dslContext.insertInto(BALANCE)
                .columns(BALANCE.ORDERID, BALANCE.BALANCEVALUE)
                .values(balance.getOrderId(), BigDecimal.valueOf(balance.getBalanceValue()))
                .returning(BALANCE.BALANCEID).fetchOne();

        return balanceRecord.getBalanceid();
    }

    public void updateBalance(Balance balance){
        dslContext.update(BALANCE)
                .set(BALANCE.BALANCEVALUE, BigDecimal.valueOf(balance.getBalanceValue()))
                .where(BALANCE.BALANCEID.eq(balance.getBalanceId()))
                .execute();
    }


}
