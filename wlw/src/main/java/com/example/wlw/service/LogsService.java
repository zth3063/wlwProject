package com.example.wlw.service;

import com.example.wlw.entity.Logs;

import java.util.List;

public interface LogsService {
    public Logs FindByLogid(String Logid);
    public List<Logs> FindByRecorder(String Recorder);
    public List<Logs> FindByDate(String Date);
    public List<Logs> FindByDeviceId(String DeviceId);
    public List<Logs> FindAllLogs();
    public void Insert(Logs logs);
    public void Update(Logs logs);
    public void Delete(Logs logs);
}
