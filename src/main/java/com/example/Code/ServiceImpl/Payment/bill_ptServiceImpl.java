package com.example.Code.ServiceImpl.Payment;

import com.example.Code.Entity.Payment.billPt;
import com.example.Code.Repository.Payment.bill_ptRepository;
import com.example.Code.Service.Payment.bill_ptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class bill_ptServiceImpl implements bill_ptService {
    @Autowired
    private bill_ptRepository bill_ptRepository;

    @Override
    public void save(billPt bill_pt) {
        bill_ptRepository.save(bill_pt);
    }

    @Override
    public List<billPt> getAll() {
        return bill_ptRepository.findAll();
    }

    @Override
    public billPt getByUser(int id) {
        billPt result = null;
        for (billPt bill_pt:bill_ptRepository.findAll()) {
            if(
                    bill_pt.getUser().getId() == id &&
                            bill_pt.getDayStart().isBefore(LocalDateTime.now())&&
                            bill_pt.getDayEnd().isAfter(LocalDateTime.now())
            ){
                result = bill_pt;
            }
        }
        return result;
    }
}
