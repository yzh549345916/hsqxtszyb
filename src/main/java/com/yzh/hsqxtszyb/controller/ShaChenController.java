package com.yzh.hsqxtszyb.controller;

import com.yzh.hsqxtszyb.model.OpenlayersQJMethodBaseModel;
import com.yzh.hsqxtszyb.model.地图站点详情Model;
import com.yzh.hsqxtszyb.service.MapService;
import com.yzh.hsqxtszyb.service.ShaChenService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShaChenController {
    ShaChenService shaChenService=new ShaChenService();
    @GetMapping("/api/getDustByDateTimeValidTimeIsHeighFcstlevel")
    @CrossOrigin
    public OpenlayersQJMethodBaseModel getDustByDateTimeValidTimeIsHeighFcstlevel(@RequestParam("times") long times,@RequestParam("YbSx") int YbSx, @RequestParam("YBType") String YBType, @RequestParam("DataTypeStr") String DataTypeStr,  @RequestParam("IsHeigh") Boolean IsHeigh,  @RequestParam("Fcstlevel") float Fcstlevel)throws Exception {
        return shaChenService.获取沙尘站点数据(times,YbSx,YBType,DataTypeStr,IsHeigh,Fcstlevel);
    }
    @GetMapping("/api/getDustByDateTimeStationIDIsHeighFcstlevel")
    @CrossOrigin
    public 地图站点详情Model getDustByDateTimeStationIDIsHeighFcstlevel(@RequestParam("times") long times,@RequestParam("StationID") String StationID, @RequestParam("YBType") String YBType, @RequestParam("DataTypeStr") String DataTypeStr,  @RequestParam("IsHeigh") Boolean IsHeigh,  @RequestParam("Fcstlevel") float Fcstlevel)throws Exception {
        return shaChenService.获取站点预报详情(times,StationID,YBType,DataTypeStr,IsHeigh,Fcstlevel);
    }
}
