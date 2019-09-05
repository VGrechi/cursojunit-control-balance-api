package com.cursojunit.controlbalanceapi.repository;

import com.cursojunit.controlbalanceapi.models.BalanceContract;

import com.cursojunit.controlbalanceapi.models.entities.public_.tables.records.BalancecontractRecord;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import static com.cursojunit.controlbalanceapi.models.entities.public_.tables.Balancecontract.BALANCECONTRACT;

@Repository
public class BalanceContractRepository {

    @Autowired
    private DSLContext dslContext;

    public Integer insertBalanceContract(BalanceContract balanceContract){
        BalancecontractRecord balancecontractRecord = dslContext.insertInto(BALANCECONTRACT)
                .columns(BALANCECONTRACT.BALANCEID,
                        BALANCECONTRACT.EVENTID,
                        BALANCECONTRACT.BALANCEVALUE)
                .values(balanceContract.getBalance().getBalanceId(),
                        balanceContract.getEventId(),
                        BigDecimal.valueOf(balanceContract.getBalanceValue()))
                .returning(BALANCECONTRACT.BALANCECONTRACTID).fetchOne();

        return balancecontractRecord.getBalancecontractid();
    }

    public List<BalanceContract> selectBalanceContractsByBalanceId(Integer balanceId) {
        return dslContext.select(BALANCECONTRACT.BALANCECONTRACTID,
                    BALANCECONTRACT.EVENTID,
                    BALANCECONTRACT.BALANCEVALUE)
                .from(BALANCECONTRACT)
                .where(BALANCECONTRACT.BALANCEID.eq(balanceId))
                .fetch().map(mapper -> {
                    BalanceContract contract = new BalanceContract();
                    contract.setBalanceContractId((Integer) mapper.getValue(0));
                    contract.setEventId((Integer) mapper.getValue(1));
                    contract.setBalanceValue(((BigDecimal) mapper.getValue(2)).doubleValue());
                    return contract;
                });
    }

    public BalanceContract selectBalanceContractsByEventId(Integer eventId) {
        try{
            BalancecontractRecord balancecontractRecord = dslContext.selectFrom(BALANCECONTRACT)
                .where(BALANCECONTRACT.EVENTID.eq(eventId))
                .fetchAny();

            BalanceContract contract = new BalanceContract();
            contract.setBalanceContractId(balancecontractRecord.getBalancecontractid());
            contract.setEventId(balancecontractRecord.getEventid());
            contract.setBalanceValue(balancecontractRecord.getBalancevalue().doubleValue());
            return contract;
        }catch (Exception e){
            return null;
        }
    }
}
