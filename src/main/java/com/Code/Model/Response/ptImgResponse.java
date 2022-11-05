package com.Code.Model.Response;

import com.Code.Entity.PT.picPT;
import lombok.Data;

@Data
public class ptImgResponse {
    private PTResponse ptResponseModel;
    private String img;

    public ptImgResponse(picPT pic_pt) {
        this.ptResponseModel = new PTResponse(pic_pt.getPersonal_trainer());
        this.img = pic_pt.getImg();
    }
}
