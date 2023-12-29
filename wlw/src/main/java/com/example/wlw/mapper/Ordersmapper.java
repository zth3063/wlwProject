package com.example.wlw.mapper;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Orders;
import com.example.wlw.entity.Productions;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Ordersmapper {
    //查找订单对应的生产计划信息
    @Select("select productions.planid,productions.devicId,productions.startDate,endDate " +
            "from orders,productions " +
            "where orders.planid = productions.planid " +
            "AND orders.oid = #{oid}"
    )
    public List<Productions> FindOrdersProductions(String oid);

    //查找订单对应的所有机器
    @Select("select devices.* " +
            "from orders,productions,devices " +
            "where orders.oid = #{oid} " +
            "AND orders.planid = productions.planid " +
            "AND productions.devicId = devices.deviceId"
    )
    public List<Devices> FindOrdersDevices(String oid);
    //插入一条新的订单
    @Insert("Insert into orders (oid,startDate,deliveryDate) " +
            "values (#{oid},#{startDate},#{deliveryDate})"
    )
    public void Insert(Orders orders);
    //查看所有
    @Select("select * " +
            "from orders "
    )
    public List<Orders> FindAll();
    @Select("select orders.* from orders where orders.oid = #{oid}")
    public Orders FindById(String oid);
    @Update("update orders " +
            "set orders.startDate=#{startDate},orders.deliveryDate=#{deliveryDate},orders.planid=#{planid} " +
            "where orders.oid=#{oid}"
    )
    public void UpdateOrdersById(Orders orders);
    @Delete("delete from orders where orders.oid=#{oid}")
    public void DeleteOrdersById(Orders orders);
    @Select("select orders.* from orders where #{startDate} between orders.startDate and orders.deliveryDate")
    public List<Orders> FindOrdersing(Orders orders);
}
