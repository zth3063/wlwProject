package com.example.wlw.mapper;

import com.example.wlw.entity.Sets;
import com.example.wlw.entity.Technologies;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Technologiesmapper {
    @Select("select technologies.* from technologies")
    public List<Technologies> FindAllTechnologies();
    @Select("select technologies.* from technologies where technologies.techId = #{techId}")
    public Technologies FindTechnologiesById(String techId);
    @Delete("delete from technologies where technologies.techId = #{techId}")
    public void Delete(Technologies technologies);
    @Insert("INSERT INTO technologies (techId,manual,deviceDrawing,assembDrawing,sparePartsList) " +
            "VALUES (#{techId},#{manual},#{deviceDrawing},#{assembDrawing},#{sparePartsList})")
    public void Insert(Technologies technologies);
}
