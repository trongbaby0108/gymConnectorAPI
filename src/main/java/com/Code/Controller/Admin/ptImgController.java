package com.Code.Controller.Admin;

import com.Code.Entity.PT.personalTrainer;
import com.Code.Entity.PT.picPt;
import com.Code.Exception.NotFoundException;
import com.Code.Model.Response.ptImgResponse;
import com.Code.Service.PT.personalTrainerService;
import com.Code.Service.PT.picPTService;
import com.Code.Util.Uploader;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @SneakyThrows
    @PostMapping("/save")
    public HttpStatus save(@RequestParam("id") int id, @RequestParam("pic") MultipartFile pic){
        personalTrainer pt = personalTrainerService.findById(id);
        if(pt == null) throw new NotFoundException("Pt not found");
        Uploader uploader = new Uploader();
        picPt picPt = new picPt();
        picPt.setPersonalTrainer(pt);
        picPt.setImg(uploader.uploadFile(pic));
        picPTService.save(picPt);
        return HttpStatus.OK;
    }

    @RequestMapping("/getByPt")
    public ResponseEntity<List<ptImgResponse>> getByPt(@RequestParam("id") int id){
        List<ptImgResponse> res = new ArrayList<>();
        for (picPt picPt: picPTService.getByPT(id)) {
            ptImgResponse ptIMGModel = new ptImgResponse(picPt);
            res.add(ptIMGModel);
        }
        return ResponseEntity.ok(res);
    }
}
