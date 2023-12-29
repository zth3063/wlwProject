package com.example.wlw.controller;

import com.example.wlw.entity.Login;
import com.example.wlw.entity.Logs;
import com.example.wlw.entity.Sets;
import com.example.wlw.serviceimpl.LogsServiceimpl;
import com.example.wlw.util.HttpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "日志记录" , tags = {"日志记录"} ,description = "Logs Controller类")
public class LogsController {
    @Autowired
    private LogsServiceimpl logsServiceimpl;
    @ApiOperation(value = "通过日志号查询日志",notes = "路径logid为日志号，返回属性message为Logs类，属性messagestate为true表示成功")
    @PostMapping("/logsByLogid/{logid}")
    public HttpMessage<Logs> FindByLogid(@PathVariable(value="logid", required=true) String logid){
        HttpMessage<Logs> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(logsServiceimpl.FindByLogid(logid));
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "通过操作人查询日志",notes = "路径recorder为操作人uid，返回属性message为List<Logs>类，属性messagestate为true表示成功")
    @PostMapping("/logsByRecorder/{recorder}")
    public HttpMessage<List<Logs>> FindByRecorder(@PathVariable(value="recorder", required=true) String recorder){
        HttpMessage<List<Logs>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(logsServiceimpl.FindByRecorder(recorder));
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "通过日期查询日志",notes = "路径date为查询日期，返回属性message为List<Logs>类，属性messagestate为true表示成功")
    @PostMapping("/logsByDate/{date}")
    public HttpMessage<List<Logs>> FindByDate(@PathVariable(value="date", required=true) String date){
        HttpMessage<List<Logs>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(logsServiceimpl.FindByDate(date));
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "通过设备号查询日志",notes = "路径date为查询日期，返回属性message为List<Logs>类，属性messagestate为true表示成功")
    @PostMapping("/logsByDeviceId/{deviceId}")
    public HttpMessage<List<Logs>> FindByDeviceId(@PathVariable(value="deviceId", required=true) String deviceId){
        HttpMessage<List<Logs>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(logsServiceimpl.FindByDeviceId(deviceId));
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "查询所有日志",notes = "返回属性message为List<Logs>类，属性messagestate为true表示成功")
    @PostMapping("/FindAllLogs")
    public HttpMessage<List<Logs>> FindAllLogs(){
        HttpMessage<List<Logs>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(logsServiceimpl.FindAllLogs());
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "根据日志号更新日志",notes = "Body为Logs类，属性messagestate为true表示成功")
    @PostMapping("/UpdateLogs")
    public HttpMessage<Integer> UpdateLogs(@RequestBody Logs logs){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            logsServiceimpl.Update(logs);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "插入日志",notes = "Body为Logs类，属性messagestate为true表示成功")
    @PostMapping("/InsertLogs")
    public HttpMessage<Integer> InsertLogs(@RequestBody Logs logs){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            logsServiceimpl.Insert(logs);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "根据日志号删除日志",notes = "Body为String类，属性messagestate为true表示成功")
    @PostMapping("/DeleteLogs")
    public HttpMessage<Integer> DeleteLogs(@RequestBody String[] str){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            //删除
            for(int i=0;i<str.length;i++){
                Logs logs= new Logs();
                logs.logid = str[i];
                logsServiceimpl.Delete(logs);
            }
        }catch (Exception e) {
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }
}
