package com.example.wlw.serviceimpl;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Orders;
import com.example.wlw.entity.Productions;
import com.example.wlw.mapper.Ordersmapper;
import com.example.wlw.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceimpl implements OrdersService {
    @Autowired
    private Ordersmapper ordersmapper;

    @Override
    public List<Devices> FindOrdersDevices(String oid) {
        return ordersmapper.FindOrdersDevices(oid);
    }

    @Override
    public void Insert(Orders orders) {
        ordersmapper.Insert(orders);
    }

    @Override
    public List<Orders> FindAll() {
        return ordersmapper.FindAll();
    }

    @Override
    public List<Productions> FindOrdersProductions(String oid) {
        return ordersmapper.FindOrdersProductions(oid);
    }

    @Override
    public Orders FindById(String oid) {
        return ordersmapper.FindById(oid);
    }

    @Override
    public void UpdateOrdersById(Orders orders) {
        ordersmapper.UpdateOrdersById(orders);
    }

    @Override
    public void DeleteOrdersById(Orders orders) {
        ordersmapper.DeleteOrdersById(orders);
    }
    @Override
    public List<Orders> FindOrdersing(Orders orders){return ordersmapper.FindOrdersing(orders);}
}
