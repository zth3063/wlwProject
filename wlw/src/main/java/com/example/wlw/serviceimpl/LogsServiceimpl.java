package com.example.wlw.serviceimpl;

import com.example.wlw.entity.Logs;
import com.example.wlw.mapper.Logsmapper;
import com.example.wlw.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsServiceimpl implements LogsService {
    @Autowired
    private Logsmapper logsmapper;
    @Override
    public Logs FindByLogid(String Logid) {
        return logsmapper.FindByLogid(Logid);
    }

    @Override
    public List<Logs> FindByRecorder(String Recorder) {
        return logsmapper.FindByRecorder(Recorder);
    }

    @Override
    public List<Logs> FindByDate(String Date) {
        return logsmapper.FindByDate(Date);
    }

    @Override
    public List<Logs> FindByDeviceId(String DeviceId) {
        return logsmapper.FindByDeviceId(DeviceId);
    }
    @Override
    public List<Logs> FindAllLogs(){return logsmapper.FindAllLogs();}
    @Override
    public void Insert(Logs logs){logsmapper.Insert(logs);}
    @Override
    public void Update(Logs logs){logsmapper.Update(logs);}
    @Override
    public void Delete(Logs logs){logsmapper.Delete(logs);}
}
