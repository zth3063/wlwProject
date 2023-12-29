package com.example.wlw.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.wlw.entity.Sets;
import com.example.wlw.serviceimpl.SetsServiceimpl;
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
@Api(value = "权限设置" , tags = {"权限设置"} ,description = "Sets Controller类")
public class SetsController {
    @Autowired
    private SetsServiceimpl setsServiceimpl;
    @PostMapping("/sets/{position}")
    @ApiOperation(value = "查询职位权力",notes = "路径position为待查询职位，返回属性message为该表示职位权力的json数据，返回属性messagestate为true表示成功")
    public HttpMessage<JSONObject> FindRightsByPosition(@PathVariable(value="position", required=true) String position){
        HttpMessage<JSONObject> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(JSONObject.parseObject(setsServiceimpl.FindRightsByPosition(position)));
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "设置职位权力",notes = "position职位名必填、JsRights为职位权力json数据必填，返回属性messagestate为true表示成功")
    @PostMapping("/setsRights")
    public HttpMessage<Integer> SetRightByPosition(@RequestBody Sets sets){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        sets.rights = sets.jsRights.toJSONString();
        try {
            setsServiceimpl.SetRightByPosition(sets);
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "查询所有职位权力",notes = "message为json数据包含职位名和权力，返回属性messagestate为true表示成功")
    @PostMapping("/FindAllRights")
    public HttpMessage<JSONArray> FindAllPosition(){
        HttpMessage<JSONArray> ret = new HttpMessage<>();
        ret.message = new JSONArray();
        ret.setstate(true);
        List<Sets> sets = setsServiceimpl.FindAllPosition();
        try {
            for (Sets set : sets) {
                JSONObject jsonObject = JSONObject.parseObject(set.rights);
                jsonObject.put("position", set.position);
                ret.message.add(jsonObject);
            }
        }catch (Exception e){
            System.out.println(e);
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "删除职位",notes = "Body为一个string数组，值为待删除的职位名称,message为json数据包含剩余职位名和权力，返回属性messagestate为true表示成功")
    @PostMapping("/DelectRights")
    public HttpMessage<JSONArray> DelectPosition(@RequestBody String[] str){
        HttpMessage<JSONArray> ret = new HttpMessage<>();
        try {
        //删除
        for(int i=0;i<str.length;i++){
            Sets sets = new Sets();
            sets.position = str[i];
            setsServiceimpl.Delect(sets);
        }
        //发送新的
        ret.message = new JSONArray();
        ret.setstate(true);
        List<Sets> sets = setsServiceimpl.FindAllPosition();
            for (Sets set : sets) {
                JSONObject jsonObject = JSONObject.parseObject(set.rights);
                jsonObject.put("position", set.position);
                ret.message.add(jsonObject);
            }
        }catch (Exception e){
            System.out.println(e);
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "添加职位",notes = "Body包含两个键值对position为职位名，jsRights为职位权力，返回属性messagestate为true表示成功")
    @PostMapping("/InsertRights")
    public HttpMessage<Integer> InsertPosition(@RequestBody Sets sets){
        HttpMessage<Integer> ret = new HttpMessage<>();
        try {
            sets.rights = JSON.toJSONString(sets.jsRights);
            ret.setstate(true);
            setsServiceimpl.Insert(sets);
        }catch (Exception e){
            System.out.println(e);
            ret.setstate(false);
        }
        return ret;
    }

}
