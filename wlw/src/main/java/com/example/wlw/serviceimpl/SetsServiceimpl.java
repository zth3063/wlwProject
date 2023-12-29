package com.example.wlw.serviceimpl;

import com.example.wlw.entity.Sets;
import com.example.wlw.mapper.Setsmapper;
import com.example.wlw.service.SetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SetsServiceimpl implements SetsService {
    @Autowired
    private Setsmapper setsmapper;
    @Override
    public String FindRightsByPosition(String position) {
        return setsmapper.FindRightsByPosition(position);
    }
    @Override
    public void SetRightByPosition(Sets sets){
        setsmapper.SetRightByPosition(sets);
    }
    @Override
    public List<Sets> FindAllPosition(){return setsmapper.FindAllPosition();}
    @Override
    public void Delect(Sets sets){setsmapper.Delect(sets);};
    @Override
    public void Insert(Sets sets){setsmapper.Insert(sets);}
}
