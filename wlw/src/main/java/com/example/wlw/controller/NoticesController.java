package com.example.wlw.controller;

import com.example.wlw.entity.Logs;
import com.example.wlw.entity.Notices;
import com.example.wlw.serviceimpl.NoticesServiceimpl;
import com.example.wlw.util.HttpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "公告管理" , tags = {"公告管理"} ,description = "Notices Controller类")
public class NoticesController {
    @Autowired
    NoticesServiceimpl noticesServiceimpl;
    @ApiOperation(value = "更新公告",notes = "Body为Notices类，属性messagestate为true表示成功")
    @PostMapping("/UpdateNotices")
    public HttpMessage<Integer> UpdateNotices(@RequestBody Notices notices){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            noticesServiceimpl.UpdateNoticesByContent(notices);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "插入公告",notes = "Body为Notices类，属性messagestate为true表示成功")
    @PostMapping("/InsertNotices")
    public HttpMessage<Integer> InsertNotices(@RequestBody Notices notices){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            noticesServiceimpl.InsertNotices(notices);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "删除公告",notes = "Body为Notices类，属性messagestate为true表示成功")
    @PostMapping("/DeleteNotices")
    public HttpMessage<Integer> DeleteNotices(@RequestBody Notices notices){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            noticesServiceimpl.DeleteNotices(notices);
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查询所有公告",notes = "Body为Notices类，属性messagestate为true表示成功")
    @PostMapping("/FindAllNotices")
    public HttpMessage<List<Notices>> FindAllNotices(){
        HttpMessage<List<Notices>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(noticesServiceimpl.FindAllNotices());
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }
}
