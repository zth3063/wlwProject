package com.example.wlw.service;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Technologies;

import java.util.List;

public interface DevicesService {
    public Technologies FindTechnologies(String deviceId);
    public List<Devices> FindALL();
    public void UpdateDevices(Devices devices);
    public void InsertDevices(Devices devices);
    public void DeleteDevices(Devices devices);
    public Devices FindDevicesById(String devicesId);
}
