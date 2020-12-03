package com.yzh.hsqxtszyb.controller;

import com.yzh.hsqxtszyb.model.ZNWG.GJZNWGDataModel;
import com.yzh.hsqxtszyb.model.ZNWG.国家智能网格查询Model;
import com.yzh.hsqxtszyb.model.xzjxh.xzjxhStationModel;
import com.yzh.hsqxtszyb.service.ZhiNengWangGeservice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class ZhiNengWangGeDataController {
    ZhiNengWangGeservice zhiNengWangGeservice=new ZhiNengWangGeservice();
    @CrossOrigin
    @GetMapping("/api/getZhiNengWangGe")
    public List<国家智能网格查询Model> getxzjxhStation(@RequestParam("ybDateTimespan") long ybDateTimespan, @RequestParam("qbDateTimespan") long qbDateTimespan) {

        return zhiNengWangGeservice.获取环保局智能网格数据(ybDateTimespan,qbDateTimespan);
    }
}
