package com.wmsdemo.entity;

import lombok.Data;

@Data
public class RecordResponse extends  Record{

    private String username;
    private String adminname;
    private String goodsname;
    private String storagename;
    private String goodstypename;
}
