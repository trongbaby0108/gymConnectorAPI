package com.Code.Controller.Client;

import com.Code.Entity.Gym.combo;
import com.Code.Entity.Gym.gymRate;
import com.Code.Entity.PT.personalTrainer;
import com.Code.Entity.PT.ptRate;
import com.Code.Model.Response.PTResponse;
import com.Code.Model.Response.gymRateResponse;
import com.Code.Model.Response.gymResponse;
import com.Code.Model.Response.ptRateResponse;
import com.Code.Service.Gym.comboService;
import com.Code.Service.Gym.gymRateService;
import com.Code.Service.Gym.gymService;
import com.Code.Service.PT.personalTrainerService;
import com.Code.Service.PT.ratePtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private gymService gymService;

    @Autowired
    private personalTrainerService personalTrainerService;

    @Autowired
    private comboService comboService;

    @Autowired
    private gymRateService gymRateService;

    @Autowired
    private ratePtService ratePtService;

    @RequestMapping("/getPT")
    public List<PTResponse> getPT() {
        List<PTResponse> ptModels = new ArrayList<>();
        personalTrainerService.getAll().forEach(pt->{
            if (pt.getAccount().isEnable()) {
                PTResponse ptModel = new PTResponse(pt);
                ptModel.setRate(getPTRate(ptModel.getId()));
                ptModels.add(ptModel);
            };
        });
        return ptModels;
    }


    @GetMapping("/getGym")
    public ResponseEntity<?> getGym() {
        List<gymResponse> res = new ArrayList<>();
        gymService.getAll().forEach(gym -> {
            gymResponse gymModel = new gymResponse(gym);
            gymModel.setRate(getGymRate(gym.getId()));
            res.add(gymModel);
        });
        return ResponseEntity.ok(res);
    }

    public float getGymRate(int id) {
        List<gymRate> gymRates = gymRateService.getByGym(id);
        float res = (float) gymRates.stream().mapToDouble(gymRate::getVote).sum();
        return res / gymRates.size();
    }

    @GetMapping("/getCombo")
    public ResponseEntity<?>  getCombo() {
        return ResponseEntity.ok(comboService.getAll());
    }

    @GetMapping("/getComboByGym/{id}")
    public ResponseEntity<?> getComboByGym( @PathVariable int id) {
        return ResponseEntity.ok(comboService.getByGym(id));
    }

    @RequestMapping("/getPTByGym/{id}")
    public ResponseEntity<?> getPTByGym(@PathVariable int id) {
        List<PTResponse> ptModels = new ArrayList<>();
        for (personalTrainer pt : personalTrainerService.getPTByGym(id)) {
            if (pt.getAccount().isEnable()) {
                PTResponse ptModel = new PTResponse(pt);
                ptModel.setRate(getPTRate(pt.getId()));
                ptModels.add(ptModel);
            }
        }
        return ResponseEntity.ok(ptModels);
    }

    public float getPTRate(int id) {
        float res = 0;
        int count = 0;
        for (com.Code.Entity.PT.ptRate ptRate : ratePtService.getByPT(id)) {
            res += ptRate.getVote();
            count++;
        }
        return res / count;
    }

    @GetMapping("/getRateByGym/{id}")
    public ResponseEntity<?> getJudgeByGym(@PathVariable int id) {
        if(gymService.findGymById(id) == null) ResponseEntity.badRequest();
        List<gymRateResponse> res = new ArrayList<>();
        for (gymRate gymRate : gymRateService.getByGym(id)) {
            gymRateResponse gymRateResponse = new gymRateResponse(gymRate);
            res.add(gymRateResponse);
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getRateByPT/{id}")
    public ResponseEntity<?> getRateByPT(@PathVariable int id) {
        if(personalTrainerService.findById(id) == null) ResponseEntity.badRequest();
        List<ptRateResponse> res = new ArrayList<>();
        for (ptRate judge_pt : ratePtService.getByPT(id)) {
            ptRateResponse ratePtResponse = new ptRateResponse(judge_pt);
            res.add(ratePtResponse);
        }
        return ResponseEntity.ok(res);
    }
}
