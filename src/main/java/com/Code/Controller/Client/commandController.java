package com.Code.Controller.Client;

import com.Code.Entity.Gym.gymRate;
import com.Code.Entity.PT.ptRate;
import com.Code.Model.Request.addCommentPtRequest;
import com.Code.Service.Gym.gymRateService;
import com.Code.Service.Gym.gymService;
import com.Code.Service.PT.personalTrainerService;
import com.Code.Service.PT.ratePtService;
import com.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/ptRate")
public class commandController {

    @Autowired
    private ratePtService ratePtService;

    @Autowired
    private userService userService;

    @Autowired
    private personalTrainerService personalTrainerService;

    @Autowired
    private gymRateService gymRateService;

    @Autowired
    private gymService gymService;

    @PostMapping("/addPtComment")
    public String addPtComment(@RequestBody addCommentPtRequest addCommentPtRequest) {
        ptRate ptRate = new ptRate();
        ptRate.setComment(addCommentPtRequest.getContent());
        ptRate.setVote(addCommentPtRequest.getVote());
        ptRate.setPersonalTrainer(personalTrainerService.findById(addCommentPtRequest.getPtId()));
        ptRate.setUser(userService.findById(addCommentPtRequest.getUserId()));
        ratePtService.save(ptRate);
        return "successful";
    }
    @GetMapping("/addGymComment")
    public String addComment(@RequestParam String content, @RequestParam float vote, @RequestParam int gymId, @RequestParam int userId) {
        gymRate gymRate = new gymRate();
        gymRate.setContent(content);
        gymRate.setVote(vote);
        gymRate.setGym(gymService.findGymById(gymId));
        gymRate.setUser(userService.findById(userId));
        gymRateService.save(gymRate);
        return "successful";
    }
}
