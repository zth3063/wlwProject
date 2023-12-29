package com.example.wlw.service;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Orders;
import com.example.wlw.entity.Productions;

import java.util.List;

public interface OrdersService {
    public List<Devices> FindOrdersDevices(String oid);
    public void Insert(Orders orders);
    public List<Orders> FindAll();
    public List<Productions> FindOrdersProductions(String oid);
    public Orders FindById(String oid);
    public void UpdateOrdersById(Orders orders);
    public void DeleteOrdersById(Orders orders);
    public List<Orders> FindOrdersing(Orders orders);
}
