package com.Code.Controller.Home;

import com.Code.Entity.Gym.combo;
import com.Code.Entity.Gym.gymRate;
import com.Code.Entity.PT.personalTrainer;
import com.Code.Entity.PT.ptRate;
import com.Code.Model.Request.commentPtRequest;
import com.Code.Model.Response.PTResponse;
import com.Code.Model.Response.gymRateResponse;
import com.Code.Model.Response.gymResponse;
import com.Code.Model.Response.ptRateResponse;
import com.Code.Service.Auth.AccountService;
import com.Code.Service.Gym.comboService;
import com.Code.Service.Gym.gymService;
import com.Code.Service.Gym.judgeGymService;
import com.Code.Service.PT.judgePtService;
import com.Code.Service.PT.personalTrainerService;
import com.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private gymService _gymService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private personalTrainerService personal_trainerService;

    @Autowired
    private comboService comboService;

    @Autowired
    private judgeGymService judge_gymService;

    @Autowired
    private userService userService;

    @Autowired
    private judgePtService judge_ptService;

    @RequestMapping("/getPT")
    public List<PTResponse> getPT() {
        List<PTResponse> ptModels = new ArrayList<>();
        for (personalTrainer pt : personal_trainerService.getAll()) {
            if (pt.getAccount().isEnable()) {
                PTResponse ptModel = new PTResponse(pt);
                ptModel.setRate(getPTRate(ptModel.getId()));
                ptModels.add(ptModel);
            }
        }
        return ptModels;
    }

    @GetMapping("/getGym")
    public List<gymResponse> getGym() {
        List<gymResponse> res = new ArrayList<>();
        _gymService.getAll().forEach(gym -> {
            gymResponse gymModel = new gymResponse(gym);
            gymModel.setRate(getGymRate(gym.getId()));
            res.add(gymModel);
        });
        return res;
    }

    public float getGymRate(int id) {
        float res = 0;
        int count = 0;
        for (com.Code.Entity.Gym.gymRate gymRate : judge_gymService.getAll()) {
            res += gymRate.getGym().getId() == id ? gymRate.getVote() : 0;
            count += gymRate.getGym().getId() == id ? 1 : 0;
        }
        return res / count;
    }

    @GetMapping("/getCombo")
    public List<combo> getCombo() {
        return comboService.getAll();
    }

    @GetMapping("/getByGym")
    public List<combo> getByGym(@RequestParam int id) {
        return comboService.getByGym(id);
    }

    @RequestMapping("/getPTByGym/gym={id}")
    public List<PTResponse> getPTByGym(@PathVariable int id) {
        List<PTResponse> ptModels = new ArrayList<>();
        for (personalTrainer pt : personal_trainerService.getPTByGym(id)) {
            if (pt.getAccount().isEnable()) {
                PTResponse ptModel = new PTResponse(pt);
                ptModel.setRate(getPTRate(pt.getId()));
                ptModels.add(ptModel);
            }
        }
        return ptModels;
    }

    public float getPTRate(int id) {
        float res = 0;
        int count = 0;
        for (com.Code.Entity.PT.ptRate ptRate : judge_ptService.getByPT(id)) {
            res += ptRate.getVote();
            count++;
        }
        return res / count;
    }

    @GetMapping("/getJudgeByGym")
    public List<gymRateResponse> getJudgeByGym(@RequestParam int id) {
        List<gymRateResponse> res = new ArrayList<>();
        for (gymRate gymRate : judge_gymService.getByGym(id)) {
            gymRateResponse judge_gymModel = new gymRateResponse(gymRate);
            res.add(judge_gymModel);
        }
        return res;
    }

    @GetMapping("/addComment")
    public String addComment(@RequestParam String content, @RequestParam float vote, @RequestParam int gymId, @RequestParam int userId) {
        gymRate gymRate = new gymRate();
        gymRate.setContent(content);
        gymRate.setVote(vote);
        gymRate.setGym(_gymService.findGymById(gymId));
        gymRate.setUser(userService.findById(userId));
        judge_gymService.save(gymRate);
        return "successful";
    }


    @GetMapping("/getRateByPT")
    public List<ptRateResponse> getRateByPT(@RequestParam int id) {
        List<ptRateResponse> res = new ArrayList<>();
        for (ptRate judge_pt : judge_ptService.getByPT(id)) {
            ptRateResponse ratePtResponse = new ptRateResponse(judge_pt);
            res.add(ratePtResponse);
        }
        return res;
    }

    @PostMapping("/addCommentPT")
    public String addCommentPT(@RequestBody commentPtRequest commentPtRequest) {
        ptRate ptRate = new ptRate();
        ptRate.setComment(commentPtRequest.getContent());
        ptRate.setVote(commentPtRequest.getVote());
        ptRate.setPersonalTrainer(personal_trainerService.findById(commentPtRequest.getPtId()));
        ptRate.setUser(userService.findById(commentPtRequest.getUserId()));
        judge_ptService.save(ptRate);
        return "successful";
    }

//    @GetMapping("/createAdmin")
//    public String createAdmin(){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        Account account = new Account("admin",bCryptPasswordEncoder.encode("1234"),"admin@gmail.com","12341234",true, role.ADMIN, typeAccount.NORMAL);
//        accountService.save(account);
//        return "successful";
//    }
}
