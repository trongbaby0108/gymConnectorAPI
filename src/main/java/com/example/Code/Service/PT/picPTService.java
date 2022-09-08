package com.example.Code.Service.PT;

import com.example.Code.Entity.PT.picPT;

import java.util.List;

public interface picPTService {
    public void save(picPT pic_pt);

    public List<picPT> getByPT(int pt);
}
