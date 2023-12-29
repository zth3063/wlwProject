package com.example.wlw.service;

import com.example.wlw.entity.Faults;
import com.example.wlw.entity.FaultsFindAllType;

import java.util.List;
import java.util.Map;

public interface FaultsService {
    public List<FaultsFindAllType> FindAllFaultType();
    public List<Faults> FindAllFaultNotFinish();
}
