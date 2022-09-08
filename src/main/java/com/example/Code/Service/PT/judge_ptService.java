package com.example.Code.Service.PT;

import com.example.Code.Entity.PT.ptRate;

import java.util.List;

public interface judge_ptService {
    public List<ptRate> getByPT(int id);
    public void save(ptRate judge_pt);
}
