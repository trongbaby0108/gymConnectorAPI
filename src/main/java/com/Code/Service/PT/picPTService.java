package com.Code.Service.PT;

import com.Code.Entity.PT.picPT;

import java.util.List;

public interface picPTService {
    public void save(picPT pic_pt);

    public List<picPT> getByPT(int pt);
}
