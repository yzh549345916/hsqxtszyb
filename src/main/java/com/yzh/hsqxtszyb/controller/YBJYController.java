package com.yzh.hsqxtszyb.controller;

import com.yzh.hsqxtszyb.model.web站点信息;
import com.yzh.hsqxtszyb.model.预报检验.多元融合检验结果;
import com.yzh.hsqxtszyb.model.预报检验.多源融合实况统计Model;
import com.yzh.hsqxtszyb.service.YBJYservice;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YBJYController {

    @GetMapping("/api/getDYRHJianYanBystationLevlStrAndsDateStrAndeDateStr")
    @CrossOrigin
    public List<多元融合检验结果> getDYRHJianYanBystationLevlStrAndsDateStrAndeDateStr(@RequestParam("eleName") String eleName, @RequestParam("stationLevlStr") String stationLevlStr, @RequestParam("sDateStr") String sDateStr, @RequestParam("eDateStr") String eDateStr)throws Exception {
        YBJYservice ybjyService=new YBJYservice();
        return ybjyService.根据要素站点级别起止时间获取多元融合实况检验结果(eleName,stationLevlStr,sDateStr,eDateStr);
    }
    @GetMapping("/api/getDYRHJianYanTJBystationLevlStrAndsDateStrAndeDateStr")
    @CrossOrigin
    public List<多源融合实况统计Model> getDYRHJianYanTJBystationLevlStrAndsDateStrAndeDateStr(@RequestParam("stationLevlStr") String stationLevlStr, @RequestParam("sDateStr") String sDateStr, @RequestParam("eDateStr") String eDateStr)throws Exception {
        YBJYservice ybjyService=new YBJYservice();
        return ybjyService.根据站点级别起止时间获取多元融合实况检验统计结果(stationLevlStr,sDateStr,eDateStr);
    }
    @GetMapping("/api/getDYRHJianYanStations")
    @CrossOrigin
    public List<web站点信息> getDYRHJianYanStations(@RequestParam("stationLevlStr") String stationLevlStr)throws Exception {
        YBJYservice ybjyService=new YBJYservice();
        return ybjyService.根据站点级别获取多源融合实况站点(stationLevlStr);
    }
    @CrossOrigin
    @GetMapping("/api/getDYRHJianYanStationsByIDAndLevel")
    public List<web站点信息> getDYRHJianYanStationsByIDAndLevel(@RequestParam("adminCode") String adminCode,@RequestParam("stationLevlStr") String stationLevlStr) {
        YBJYservice ybjyService=new YBJYservice();
        return ybjyService.根据站点级别地区ID获取多源融合实况站点(adminCode,stationLevlStr);
    }

    @GetMapping("/api/getDYRHJianYanByStationIDsStrAndsDateStrAndeDateStr")
    @CrossOrigin
    public List<多元融合检验结果> getDYRHJianYanBystationIDsStrAndsDateStrAndeDateStr(@RequestParam("eleName") String eleName, @RequestParam("stationIDs") String stationIDs, @RequestParam("sDateStr") String sDateStr, @RequestParam("eDateStr") String eDateStr)throws Exception {
        YBJYservice ybjyService=new YBJYservice();
        return ybjyService.根据要素站点起止时间获取多元融合实况检验结果(eleName,stationIDs,sDateStr,eDateStr);
    }

    @GetMapping("/api/getDYRHPreBySdateAndEdate")
    @CrossOrigin
    public ResponseEntity<FileSystemResource> getDYRHPreBySdateAndEdate(@RequestParam("sDateStr") String sDateStr, @RequestParam("eDateStr") String eDateStr)throws Exception {

        YBJYservice ybjyService=new YBJYservice();
        return ybjyService.根据起止时间合并多源融合实况降水数据(sDateStr,eDateStr);
    }
}
