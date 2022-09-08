package com.example.Code.ServiceImpl.PT;

import com.example.Code.Entity.PT.picPT;
import com.example.Code.Repository.Gym.picPTRepository;
import com.example.Code.Service.PT.picPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class picPTServiceImpl implements picPTService {

    @Autowired
    private picPTRepository picPTRepository;

    @Override
    public void save(picPT pic_pt) {
        picPTRepository.save(pic_pt);
    }

    @Override
    public List<picPT> getByPT(int pt) {
        List<picPT> res = new ArrayList<>();
        for (picPT pic_pt: picPTRepository.findAll()) {
            if(pic_pt.getPersonal_trainer().getId() == pt){
                res.add(pic_pt);
            }
        }
        return res;
    }
}
