package com.example.wlw.controller;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Logs;
import com.example.wlw.entity.Orders;
import com.example.wlw.entity.Productions;
import com.example.wlw.serviceimpl.OrdersServiceimpl;
import com.example.wlw.util.HttpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Api(value = "订单查询" , tags = {"订单查询"} ,description = "Orders Controller类")
public class OrdersController {
    @Autowired
    private OrdersServiceimpl ordersServiceimpl;
    @ApiOperation(value = "查询订单的生产计划",notes = "路径oid为待查询订单编号，返回属性messagestate为true表示成功")
    @PostMapping("/ordersProductions/{oid}")
    public HttpMessage<List<Productions>> FindProductions(@PathVariable(value="oid", required=true) String oid){
        HttpMessage<List<Productions>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
        ret.setMessage(ordersServiceimpl.FindOrdersProductions(oid));
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "查询参与订单生产的设备",notes = "路径oid为待查询订单编号，返回属性messagestate为true表示成功")
    @PostMapping("/ordersDevices/{oid}")
    public HttpMessage<List<Devices>> FindDevices(@PathVariable(value="oid", required=true) String oid){
        HttpMessage<List<Devices>> ret = new HttpMessage<>();
        ret.setMessage(ordersServiceimpl.FindOrdersDevices(oid));
        ret.setstate(true);
        return ret;
    }
    @ApiOperation(value = "插入订单",notes = "属性message为Orders类，返回属性messagestate为true表示成功")
    @PostMapping("/ordersInsert")
    public HttpMessage<Integer> InsertOrders(@RequestBody Orders orders){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
        ordersServiceimpl.Insert(orders);
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
    @ApiOperation(value = "查询订单",notes = "属性message为List<Orders>包含所有订单，返回属性messagestate为true表示成功")
    @GetMapping("/ordersFindall")
    public HttpMessage<List<Orders>> FindAll(){
        HttpMessage<List<Orders>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
        ret.setMessage(ordersServiceimpl.FindAll());
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "通过订单号查询订单",notes = "路径oid为订单号，返回属性message为Orders类，属性messagestate为true表示成功")
    @PostMapping("/FindOedersById/{oid}")
    public HttpMessage<Orders> FindById(@PathVariable(value="oid", required=true) String oid){
        HttpMessage<Orders> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(ordersServiceimpl.FindById(oid));
        }catch (Exception e) {
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "根据订单号更新订单",notes = "Body为Orders类，属性messagestate为true表示成功")
    @PostMapping("/UpdateOrdersById")
    public HttpMessage<Integer> UpdateOrdersById(@RequestBody Orders orders){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ordersServiceimpl.UpdateOrdersById(orders);
        }catch (Exception e) {
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }

    @ApiOperation(value = "根据订单号删除订单",notes = "Body为String类，属性messagestate为true表示成功")
    @PostMapping("/DeleteOrdersById")
    public HttpMessage<Integer> DeleteOrdersById(@RequestBody String[] str){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            //删除
            for(int i=0;i<str.length;i++){
                Orders orders= new Orders();
                orders.oid = str[i];
                ordersServiceimpl.DeleteOrdersById(orders);
            }
        }catch (Exception e) {
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }

    @ApiOperation(value = "查询正在进行的订单",notes = "Body为Orders类,startDate必填,返回message为List<Orders>，属性messagestate为true表示成功")
    @PostMapping("/FindOrdersing")
    public HttpMessage<List<Orders>> FindOrdersing(@RequestBody Orders orders){
        HttpMessage<List<Orders>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(ordersServiceimpl.FindOrdersing(orders));
        }catch (Exception e) {
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }

    @ApiOperation(value = "查询订单总数",notes = "属性message为Integer，返回属性messagestate为true表示成功")
    @PostMapping("/ordersFindallCount")
    public HttpMessage<Integer> ordersFindallCount(){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            List<Orders> ordersList = new ArrayList<>();
            ordersList=ordersServiceimpl.FindAll();
            ret.setMessage(ordersList.size());
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查询正在进行的订单总数",notes = "Body为Integer,startDate必填,返回message为List<Orders>，属性messagestate为true表示成功")
    @PostMapping("/FindOrdersingCount")
    public HttpMessage<Integer> FindOrdersingCount(@RequestBody Orders orders){
        HttpMessage<Integer> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            ret.setMessage(ordersServiceimpl.FindOrdersing(orders).size());
        }catch (Exception e) {
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }

    @ApiOperation(value = "查询未指定计划的订单总数",notes = "属性message为返回message为List<Orders>，返回属性messagestate为true表示成功")
    @PostMapping("/NewordersFindall")
    public HttpMessage<List<Orders>> NewordersFindall(){
        HttpMessage<List<Orders>> ret = new HttpMessage<>();
        ret.setstate(true);
        try {
            List<Orders> ordersList;
            List<Orders> ordersList2 = new ArrayList<>();
            ordersList=ordersServiceimpl.FindAll();
            for(int i=0;i<ordersList.size();i++){
                if(ordersList.get(i).planid == null){
                    ordersList2.add(ordersList.get(i));
                }
            }
            ret.setMessage(ordersList2);
        }catch (Exception e){
            ret.setstate(false);
            System.out.println(e);
        }
        return ret;
    }
}
