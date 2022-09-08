package com.example.Code.Service.Payment;


import com.example.Code.Entity.Payment.billGym;

import java.util.List;

public interface bill_gymService {
    public void save(billGym bill_gym);
    public List<billGym> getAll();
    public billGym getByUser(int id);
}
