package com.yzh.hsqxtszyb.controller;

import com.yzh.hsqxtszyb.model.xzjxh.xzjxhStationModel;
import com.yzh.hsqxtszyb.service.ZhiNengWangGeservice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class ZhiNengWangGeDataController {
    ZhiNengWangGeservice zhiNengWangGeservice=new ZhiNengWangGeservice();
    @CrossOrigin
    @GetMapping("/api/getZhiNengWangGe")
    public void getxzjxhStation() {
        zhiNengWangGeservice.获取环保局智能网格数据();
        return;
    }
}
