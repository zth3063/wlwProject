package com.example.wlw.controller;

import com.example.wlw.entity.Faults;
import com.example.wlw.entity.FaultsFindAllType;
import com.example.wlw.entity.Technologies;
import com.example.wlw.serviceimpl.FaultsServiceimpl;
import com.example.wlw.util.HttpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "故障查询" , tags = {"故障查询"} ,description = "Faults Controller类")
public class FaultsController {
    @Autowired
    private FaultsServiceimpl faultsServiceimpl;
    @ApiOperation(value = "查找所有故障的部件损坏情况",notes = "message为Map<String,Map<String,List<Integer>>>,返回属性messagestate为true表示成功")
    @PostMapping("/FindAllFaultType")
    public HttpMessage<List<FaultsFindAllType>> FindAllFaultType(){
        HttpMessage<List<FaultsFindAllType>> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(faultsServiceimpl.FindAllFaultType());
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查找所有未解决故障",notes = "message为List<Faults>,返回属性messagestate为true表示成功")
    @PostMapping("/FindAllFaultNotFinish")
    public HttpMessage<List<Faults>> FindAllFaultNotFinish(){
        HttpMessage<List<Faults>> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(faultsServiceimpl.FindAllFaultNotFinish());
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
}
