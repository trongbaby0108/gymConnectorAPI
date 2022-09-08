package com.example.Code.Model;

import com.example.Code.Entity.PT.picPT;
import lombok.Data;

@Data
public class ptIMGModel {
    private PTResponseModel ptResponseModel;
    private String img;

    public ptIMGModel(picPT pic_pt) {
        this.ptResponseModel = new PTResponseModel(pic_pt.getPersonal_trainer());
        this.img = pic_pt.getImg();
    }
}
