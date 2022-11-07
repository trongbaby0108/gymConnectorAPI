package com.Code.Service.PT;

import com.Code.Entity.PT.ptRate;

import java.util.List;

public interface ratePtService {
    public List<ptRate> getByPT(int id);
    public void save(ptRate judge_pt);
}
