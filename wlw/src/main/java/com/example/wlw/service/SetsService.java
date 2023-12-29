package com.example.wlw.service;

import com.example.wlw.entity.Sets;

import java.util.List;
import java.util.Map;

public interface SetsService {
    public String FindRightsByPosition(String position);
    public void SetRightByPosition(Sets sets);
    public List<Sets> FindAllPosition();
    public void Delect(Sets sets);
    public void Insert(Sets sets);
}
