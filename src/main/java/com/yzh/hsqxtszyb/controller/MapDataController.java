package com.yzh.hsqxtszyb.controller;

import com.yzh.hsqxtszyb.model.*;
import com.yzh.hsqxtszyb.service.MapService;
import com.yzh.hsqxtszyb.service.SKservice;
import com.yzh.hsqxtszyb.service.SzybDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapDataController {
    MapService mapService=new MapService();
    @GetMapping("/api/getMyjsonCs")
    @CrossOrigin
    public OpenlayersQJMethodBaseModel getMyjsonCs() throws Exception {

        return mapService.getMyjsonCs();
    }
    @CrossOrigin
    @GetMapping("/api/getzdybByTypeStationsTimeSx")
    public OpenlayersQJMethodBaseModel getzdybByTypeStationsTimeSx(@RequestParam("YBType") String YBType, @RequestParam("DataTypeID") String DataTypeID, @RequestParam("StationTye") String StationTye, @RequestParam("DQID") String DQID, @RequestParam("times") long times,@RequestParam("YbSx") int YbSx,@RequestParam("stationlevelType") int stationlevelType,@RequestParam("stationlevel") double stationlevel) {
        return mapService.获取站点预报数据(YBType,DataTypeID,StationTye,DQID,times,YbSx,stationlevelType,stationlevel);
    }
    @CrossOrigin
    @GetMapping("/api/getzdybByTypeStationsQBTime")
    public 地图站点详情Model getzdybByTypeStationsQBTime(@RequestParam("YBType") String YBType, @RequestParam("DataTypeID") String DataTypeID, @RequestParam("StationID") String StationID,  @RequestParam("times") long times,  @RequestParam("stationlevelType") int stationlevelType,  @RequestParam("stationlevel") int stationlevel) {
        return mapService.获取站点预报详情(YBType,DataTypeID,StationID,times,stationlevelType,stationlevel);
    }
    @CrossOrigin
    @GetMapping("/api/getWindJsonByTypeTimeSx")
    public String getWindJsonByTypeTimeSx(@RequestParam("YBType") String YBType, @RequestParam("times") long times,@RequestParam("YbSx") int YbSx,@RequestParam("stationlevelType") int stationlevelType,@RequestParam("stationlevel") double stationlevel,@RequestParam("dlat") double dlat) {
        return mapService.获取风流场Json(YBType,times,YbSx,stationlevelType,stationlevel,dlat);
    }
}
