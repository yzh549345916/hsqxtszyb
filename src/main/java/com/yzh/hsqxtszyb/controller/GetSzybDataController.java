package com.yzh.hsqxtszyb.controller;


import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.service.SKservice;
import com.yzh.hsqxtszyb.service.SzybDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetSzybDataController {
    SzybDataService szybDataService=new SzybDataService();

    @GetMapping("/api/GetAllStations")
    @CrossOrigin
    public List<站点信息> stationList() throws Exception {
        return szybDataService.获取所有站点();
    }
    @GetMapping("/api/GetStations")
    @CrossOrigin
    public List<web站点信息> stationListbyID(@RequestParam(name="id",required=false) String[] id) throws Exception {

        return szybDataService.根据ID获取地区(id);
    }
    @GetMapping("/api/GetAdminCode")
    @CrossOrigin
    public List<地区Model> stationListbyID(@RequestParam(name="id",required=false) String id) throws Exception {

        return szybDataService.根据ID获取地区Tree(id);
    }
    @CrossOrigin
    @GetMapping("/api/GetStationsByID")
    public List<web站点信息> GetStationsByID(@RequestParam("id") String id,@RequestParam("StationType") String StationType) {
        return szybDataService.根据ID获取站点(id,StationType);
    }


    @CrossOrigin
    @GetMapping("/api/getqtzdybAndSk")
    public 数值预报查询Model searchResult(@RequestParam("id") String id,@RequestParam("datatype") String datatype,@RequestParam("times") long times) {
        return szybDataService.GetSzybAndSkByTimeAndStionID(id,datatype,times);
    }
    @CrossOrigin
    @GetMapping("/api/getqtzdybByStations")
    public List<数值预报查询Model> getqtzdybByStations(@RequestParam("ids") String ids, @RequestParam("datatype") String datatype, @RequestParam("times") long times) {
        return szybDataService.GetSzybByTimeAndStionIDs(ids,datatype,times);
    }
    @CrossOrigin
    @GetMapping("/api/getqtzdybAndSkWithoutDataType")
    public 数值预报方法多要素查询Model getqtzdybAndSkWithoutDataType(@RequestParam("id") String id,@RequestParam("times") long times) {
        return szybDataService.GetSzybAndSkByTimeAndStionIDNoDataType(id,times);
    }
    @CrossOrigin
    @GetMapping("/api/GETCS2")
    public List<SK_Tem_Hour_Model> 测试2() throws Exception{
        SKservice sKservice=new SKservice();
        return sKservice.根据站点起止时间获取温度();
    }
}
