package com.example.wlw.serviceimpl;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Technologies;
import com.example.wlw.mapper.Devicesmapper;
import com.example.wlw.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevicesServiceimpl implements DevicesService {
    @Autowired
    private Devicesmapper decivesmapper;
    @Override
    public Technologies FindTechnologies(String deviceId) {
        return decivesmapper.FindTechnologies(deviceId);
    }
    @Override
    public List<Devices> FindALL(){
        return decivesmapper.FindALL();
    }

    @Override
    public void UpdateDevices(Devices devices) {
        decivesmapper.UpdateDevices(devices);
    }

    @Override
    public void InsertDevices(Devices devices) {
        decivesmapper.InsertDevices(devices);
    }

    @Override
    public void DeleteDevices(Devices devices) {
        decivesmapper.DeleteDevices(devices);
    }
    @Override
    public Devices FindDevicesById(String devicesId){return decivesmapper.FindDevicesById(devicesId);}
}
