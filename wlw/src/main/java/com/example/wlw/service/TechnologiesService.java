package com.example.wlw.service;

import com.example.wlw.entity.Technologies;

import java.util.List;

public interface TechnologiesService {
    public List<Technologies> FindAllTechnologies();
    public Technologies FindTechnologiesById(String techId);
    public void Delete(Technologies technologies);
    public void Insert(Technologies technologies);
}
