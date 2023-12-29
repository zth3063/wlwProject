package com.example.wlw.serviceimpl;

import com.example.wlw.entity.Technologies;
import com.example.wlw.mapper.Technologiesmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TechnologiesServiceimpl {
    @Autowired
    private Technologiesmapper technologiesmapper;
    public List<Technologies> FindAllTechnologies(){return technologiesmapper.FindAllTechnologies();}
    public Technologies FindTechnologiesById(String techId){return technologiesmapper.FindTechnologiesById(techId);}
    public void Delete(Technologies technologies){technologiesmapper.Delete(technologies);}
    public void Insert(Technologies technologies){technologiesmapper.Insert(technologies);}

}
