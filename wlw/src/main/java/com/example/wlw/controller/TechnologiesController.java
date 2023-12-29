package com.example.wlw.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.wlw.entity.Sets;
import com.example.wlw.entity.Technologies;
import com.example.wlw.serviceimpl.SetsServiceimpl;
import com.example.wlw.serviceimpl.TechnologiesServiceimpl;
import com.example.wlw.util.HttpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "学习资料" , tags = {"学习资料"} ,description = "Technologies Controller类")
public class TechnologiesController {
    @Autowired
    private TechnologiesServiceimpl technologiesServiceimpl;
    @PostMapping("/FindAllTechnologies")
    @ApiOperation(value = "查询所有学习资料",notes = "返回属性message为所有学习资料的json数据，返回属性messagestate为true表示成功")
    public HttpMessage<List<Technologies>> FindAllTechnologies(){
        HttpMessage<List<Technologies>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(technologiesServiceimpl.FindAllTechnologies());
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
    @PostMapping("/Technologies/{techId}")
    @ApiOperation(value = "通过techId查询学习资料",notes = "返回属性message为该学习资料表示的json数据，返回属性messagestate为true表示成功")
    public HttpMessage<Technologies> FindAllTechnologies(@PathVariable(value="techId", required=true) String techId){
        HttpMessage<Technologies> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(technologiesServiceimpl.FindTechnologiesById(techId));
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "删除学习资料",notes = "Body为一个string数组，值为待删除的学习资料Id,message为json数据包含剩余职位名和权力，返回属性messagestate为true表示成功")
    @PostMapping("/DelectTechnologies")
    public HttpMessage<List<Technologies>> DelectPosition(@RequestBody String[] str){
        HttpMessage<List<Technologies>> ret = new HttpMessage<>();
        try {
            //删除
            for(int i=0;i<str.length;i++){
                Technologies technologies = new Technologies();
                technologies.techId = str[i];
                technologiesServiceimpl.Delete(technologies);
            }
            //发送新的
            ret.setMessage(technologiesServiceimpl.FindAllTechnologies());
        }catch (Exception e){
            System.out.println(e);
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "添加学习资料",notes = "Body为Technologies类，返回属性messagestate为true表示成功")
    @PostMapping("/InsertTechnologies")
    public HttpMessage<Integer> InsertPosition(@RequestBody Technologies technologies){
        HttpMessage<Integer> ret = new HttpMessage<>();
        try {
            ret.setstate(true);
            technologiesServiceimpl.Insert(technologies);
        }catch (Exception e){
            System.out.println(e);
            ret.setstate(false);
        }
        return ret;
    }

    @PostMapping("/FindAllTechnologiesIsNull")
    @ApiOperation(value = "查询所有不完善的学习资料",notes = "返回属性message为所有学习资料的json数据，返回属性messagestate为true表示成功")
    public HttpMessage<List<Technologies>> FindAllTechnologiesIsNull(){
        HttpMessage<List<Technologies>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            List<Technologies> technologiesList = technologiesServiceimpl.FindAllTechnologies();
            List<Technologies> technologiesList2 = new ArrayList<>();
            for(int i=0;i<technologiesList.size();i++){
                //Technologies每个属性是否有一个是null
                if(technologiesList.get(i).assembDrawing == null || technologiesList.get(i).deviceDrawing == null || technologiesList.get(i).manual == null || technologiesList.get(i).sparePartsList == null){
                    technologiesList2.add(technologiesList.get(i));
                }
            }
            ret.setMessage(technologiesList2);
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
}
