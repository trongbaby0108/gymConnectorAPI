package com.example.Code.Controller.Home;

import com.example.Code.Entity.Gym.combo;
import com.example.Code.Entity.Gym.gymRate;
import com.example.Code.Entity.PT.personal_trainer;
import com.example.Code.Entity.PT.ptRate;
import com.example.Code.Model.PTResponseModel;
import com.example.Code.Model.gymModel;
import com.example.Code.Model.judge_PTModel;
import com.example.Code.Model.judge_gymModel;
import com.example.Code.Service.Gym.comboService;
import com.example.Code.Service.Gym.gymService;
import com.example.Code.Service.Gym.judge_gymService;
import com.example.Code.Service.PT.judge_ptService;
import com.example.Code.Service.PT.personal_trainerService;
import com.example.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private gymService _gymService;

    @Autowired
    private personal_trainerService personal_trainerService;

    @Autowired
    private comboService comboService;

    @Autowired
    private judge_gymService judge_gymService;

    @Autowired
    private userService userService ;

    @Autowired
    private judge_ptService judge_ptService;

    @RequestMapping("/getPT")
    public List<PTResponseModel> getPT(){
        List<PTResponseModel> ptModels = new ArrayList<>();
        for (personal_trainer pt :personal_trainerService.getAll()){
            if(pt.getAccount().isEnable()){
                PTResponseModel ptModel = new PTResponseModel(pt);
                ptModel.setRate(getPTRate(ptModel.getId()));
                ptModels.add(ptModel);
            }
        }
        return ptModels;
    }

    @GetMapping("/getGym")
    public List<gymModel> getGym(){
        List<gymModel> res = new ArrayList<>();
        _gymService.getAll().forEach(gym -> {
            gymModel gymModel = new gymModel(gym);
            gymModel.setRate(getGymRate(gym.getId()));
            res.add(gymModel);
        });
        return res;
    }

    public float getGymRate(int id){
        float res = 0;
        int count = 0;
        for (gymRate gymRate:judge_gymService.getAll()) {
            res += gymRate.getGym().getId() == id ? gymRate.getVote() : 0;
            count += gymRate.getGym().getId() == id ? 1 : 0;
        }
        return res/count;
    }

    @GetMapping("/getCombo")
    public List<combo> getCombo(){
        return comboService.getAll();
    }

    @GetMapping("/getByGym")
    public List<combo> getByGym(@RequestParam int id){
        return comboService.getByGym(id);
    }

    @RequestMapping("/getPTByGym")
    public List<PTResponseModel> getPTByGym(@RequestParam int id){
        List<PTResponseModel> ptModels = new ArrayList<>();
        for (personal_trainer pt :personal_trainerService.getPTByGym(id)){
            if(pt.getAccount().isEnable()){
                PTResponseModel ptModel = new PTResponseModel(pt);
                ptModel.setRate(getPTRate(pt.getId()));
                ptModels.add(ptModel);
            }
        }
        return ptModels;
    }

    public float getPTRate(int id){
        float res = 0;
        int count = 0;
        for (ptRate ptRate:judge_ptService.getByPT(id)) {
            res += ptRate.getVote();
            count ++;
        }
        return res/count;
    }

    @GetMapping("/getJudgeByGym")
    public List<judge_gymModel> getJudgeByGym(@RequestParam int id){
        List<judge_gymModel> res = new ArrayList<>();
        for (gymRate gymRate: judge_gymService.getByGym(id)) {
            judge_gymModel judge_gymModel = new judge_gymModel(gymRate);
            res.add(judge_gymModel);
        }
        return res;
    }

    @GetMapping("/addComment")
    public String addComment(
            @RequestParam String content,
            @RequestParam float vote ,
            @RequestParam int gymId,
            @RequestParam int userId
    ){
        gymRate gymRate = new gymRate();
        gymRate.setContent(content);
        gymRate.setVote(vote);
        gymRate.setGym(_gymService.findGymById(gymId));
        gymRate.setUser(userService.findById(userId));
        judge_gymService.save(gymRate);
        return "successful";
    }


    @GetMapping("/getJudgeByPT")
    public List<judge_PTModel> getJudgeByPT(@RequestParam int id){
        List<judge_PTModel> res = new ArrayList<>();
        for (ptRate judge_pt: judge_ptService.getByPT(id)) {
            judge_PTModel judge_PTModel = new judge_PTModel(judge_pt);
            res.add(judge_PTModel);
        }
        return res;
    }

    @GetMapping("/addCommentPT")
    public String addCommentPT(
            @RequestParam String content,
            @RequestParam float vote ,
            @RequestParam int ptID,
            @RequestParam int userId
    ){
        ptRate ptRate = new ptRate();
        ptRate.setComment(content);
        ptRate.setVote(vote);
        ptRate.setPersonal_trainer(personal_trainerService.findById(ptID));
        ptRate.setUser(userService.findById(userId));
        judge_ptService.save(ptRate);
        return "successful";
    }
}
