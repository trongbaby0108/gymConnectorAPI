package com.example.Code.Service.Payment;


import com.example.Code.Entity.Payment.billPt;

import java.util.List;

public interface bill_ptService {
    public void save(billPt bill_pt);
    public List<billPt> getAll();
    public billPt getByUser(int id);
}
