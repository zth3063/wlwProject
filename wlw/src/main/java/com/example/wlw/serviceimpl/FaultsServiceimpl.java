package com.example.wlw.serviceimpl;

import com.example.wlw.entity.Faults;
import com.example.wlw.entity.FaultsFindAllType;
import com.example.wlw.mapper.Faultsmapper;
import com.example.wlw.service.FaultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class FaultsServiceimpl implements FaultsService {
    @Autowired
    private Faultsmapper faultsmapper;
    @Override
    public List<FaultsFindAllType> FindAllFaultType() {
        return faultsmapper.FindAllFaultType();
    }

    @Override
    public List<Faults> FindAllFaultNotFinish() {
        return faultsmapper.FindAllFaultNotFinish();
    }
}
