package com.Code.Model;

import com.Code.Entity.PT.picPT;
import com.Code.Model.Response.PTResponseModel;
import lombok.Data;

@Data
public class ptImgResponse {
    private PTResponseModel ptResponseModel;
    private String img;

    public ptImgResponse(picPT pic_pt) {
        this.ptResponseModel = new PTResponseModel(pic_pt.getPersonal_trainer());
        this.img = pic_pt.getImg();
    }
}
