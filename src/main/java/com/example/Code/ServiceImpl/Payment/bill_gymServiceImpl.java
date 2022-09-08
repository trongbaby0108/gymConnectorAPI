package com.example.Code.ServiceImpl.Payment;

import com.example.Code.Entity.Payment.billGym;
import com.example.Code.Repository.Payment.bill_gymRepository;
import com.example.Code.Service.Payment.bill_gymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class bill_gymServiceImpl implements bill_gymService {

    @Autowired
    private bill_gymRepository bill_gymRepository;

    @Override
    public void save(billGym bill_gym) {
        bill_gymRepository.save(bill_gym);
    }

    @Override
    public List<billGym> getAll() {
        return bill_gymRepository.findAll();
    }

    @Override
    public billGym getByUser(int id) {
        billGym result = null;
        for (billGym bill_gym:bill_gymRepository.findAll()) {
           if(
                   bill_gym.getUser().getId() == id &&
                   bill_gym.getDayStart().isBefore(LocalDateTime.now())&&
                   bill_gym.getDayEnd().isAfter(LocalDateTime.now())
           ){
               result = bill_gym;
           }
        }
        return result;
    }
}
