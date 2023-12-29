package com.example.wlw.controller;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Logs;
import com.example.wlw.entity.Technologies;
import com.example.wlw.serviceimpl.DevicesServiceimpl;
import com.example.wlw.util.HttpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "设备查询" , tags = {"设备查询"} ,description = "Devices Controller类")
public class DevicesController {
    @Autowired
    private DevicesServiceimpl devicesServiceimpl;
    @ApiOperation(value = "根据设备deviceId查询设备使用手册",notes = "路径deviceId为设备Id，返回属性messagestate为true表示成功")
    @PostMapping("/DevicesTechnologies/{deviceId}")
    public HttpMessage<Technologies> FindTechnologies(@PathVariable(value="deviceId", required=true) String deviceId){
        HttpMessage<Technologies> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(devicesServiceimpl.FindTechnologies(deviceId));
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查询所有设备",notes = "message为List<Technologies>类，返回属性messagestate为true表示成功")
    @GetMapping ("/DevicesAll")
    public HttpMessage<List<Devices>> FindTechnologies(){
        HttpMessage<List<Devices>> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(devicesServiceimpl.FindALL());
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "根据设备号更新设备",notes = "Body为Devices类，属性messagestate为true表示成功")
    @PostMapping("/UpdateDevices")
    public HttpMessage<Integer> UpdateDevices(@RequestBody Devices devices){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            devicesServiceimpl.UpdateDevices(devices);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "插入设备信息",notes = "Body为Logs类，属性messagestate为true表示成功")
    @PostMapping("/InsertDevices")
    public HttpMessage<Integer> InsertLogs(@RequestBody Devices devices){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            devicesServiceimpl.InsertDevices(devices);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "根据设备号删除设备",notes = "Body为String类，属性messagestate为true表示成功")
    @PostMapping("/DeleteDevices")
    public HttpMessage<Integer> DeleteLogs(@RequestBody String[] str){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            //删除
            for(int i=0;i<str.length;i++){
                Devices devices= new Devices();
                devices.deviceId = str[i];
                devicesServiceimpl.DeleteDevices(devices);
            }
        }catch (Exception e) {
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }

    @ApiOperation(value = "根据设备号查找设备",notes = "Body为v类，属性messagestate为true表示成功")
    @PostMapping("/FindDevicesById/{deviceId}")
    public HttpMessage<Devices> DeleteLogs(@PathVariable(value="deviceId", required=true) String deviceId){
        HttpMessage<Devices> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(devicesServiceimpl.FindDevicesById(deviceId));
        }catch (Exception e) {
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }

    @ApiOperation(value = "查询所有故障设备",notes = "message为List<Technologies>类，返回属性messagestate为true表示成功")
    @PostMapping ("/DevicesAllFault")
    public HttpMessage<List<Devices>> DevicesAllFault(){
        HttpMessage<List<Devices>> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            List<Devices> devicesFault = devicesServiceimpl.FindALL();
            List<Devices> faults = new ArrayList<Devices>();
            for(int i=0;i<devicesFault.size();i++){
                if(devicesFault.get(i).deviceStatus.equals("故障")){
                    faults.add(devicesFault.get(i));
                }
            }
            ret.setMessage(faults);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查询设备数量",notes = "message为Integer类，返回属性messagestate为true表示成功")
    @PostMapping ("/DevicesAllCount")
    public HttpMessage<Integer> DevicesAllCount(){
        HttpMessage<Integer> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(devicesServiceimpl.FindALL().size());
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查询空闲设备数量",notes = "message为Integer类，返回属性messagestate为true表示成功")
    @PostMapping ("/DevicesAllCountDeviceStatus")
    public HttpMessage<Integer> DevicesAllCountDeviceStatus(){
        HttpMessage<Integer> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            List<Devices> devicesList = devicesServiceimpl.FindALL();
            int count=0;
            for(int i=0;i<devicesList.size();i++){
                if(devicesList.get(i).deviceStatus.equals("正常")){
                    count++;
                }
            }
            ret.setMessage(count);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查询空闲设备类型数量",notes = "message为List<Map<String,Integer>>类，返回属性messagestate为true表示成功")
    @PostMapping ("/DevicesAllDeviceStatus")
    public HttpMessage<List<Map<String,Integer>>> DevicesAllDeviceStatus(){
        HttpMessage<List<Map<String,Integer>>> ret  = new HttpMessage<>();
        ret.setstate(true);
        try {
            List<Map<String,Integer>> message = new ArrayList<>();
            List<Devices> devicesList = devicesServiceimpl.FindALL();
            for(int i=0;i<devicesList.size();i++){
                if(devicesList.get(i).deviceStatus.equals("正常") || devicesList.get(i).deviceStatus.equals("运行中")){
                    int state = 0;
                    for(int j=0;j<message.size();j++){
                        if(message.get(j).containsKey(devicesList.get(i).deviceType)){
                            message.get(j).put(devicesList.get(i).deviceType,message.get(j).get(devicesList.get(i).deviceType)+1);
                            state = 1;
                            break;
                        }
                    }
                    if(state == 0){
                        Map<String, Integer> a = new ManagedMap<>();
                        a.put(devicesList.get(i).deviceType,1);
                        message.add(a);
                    }
                }
            }
            ret.setMessage(message);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
}
