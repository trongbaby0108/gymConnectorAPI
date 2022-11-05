package com.Code.Controller.PT;

import com.Code.Entity.PT.picPT;
import com.Code.Model.Response.ptImgResponse;
import com.Code.Service.PT.picPTService;
import com.Code.Util.Uploader;
import com.Code.Service.PT.personalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/picPT")
public class ptImgController {
    @Autowired
    private picPTService picPTService;

    @Autowired
    private personalTrainerService personalTrainerService;

    @PostMapping("/save")
    public String save(
            @RequestParam("id") int id,
            @RequestParam("pic") MultipartFile pic
    ){
        Uploader uploader = new Uploader();
        picPT picPT = new picPT();
        picPT.setPersonal_trainer(personalTrainerService.findById(id));
        picPT.setImg(uploader.uploadFile(pic));
        picPTService.save(picPT);
        return "Successful";
    }

    @RequestMapping("/getByPt")
    public List<ptImgResponse> getByPt(
            @RequestParam("id") int id
    ){
        List<ptImgResponse> res = new ArrayList<>();
        for (picPT pic_pt: picPTService.getByPT(id)) {
            ptImgResponse ptIMGModel = new ptImgResponse(pic_pt);
            res.add(ptIMGModel);
        }
        return res;
    }
}
