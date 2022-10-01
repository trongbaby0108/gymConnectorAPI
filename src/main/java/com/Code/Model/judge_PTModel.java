package com.Code.Model;

import com.Code.Entity.PT.ptRate;
import lombok.Data;

@Data
public class judge_PTModel {
    private int id ;
    private String content ;
    private float vote ;
    private String name ;
    private String avatar;

    public judge_PTModel(ptRate judge_pt) {
        this.id = judge_pt.getId();
        this.content = judge_pt.getComment();
        this.vote = judge_pt.getVote();
        this.avatar = judge_pt.getUser().getAvatar();
        this.name= judge_pt.getUser().getName();
    }
}
