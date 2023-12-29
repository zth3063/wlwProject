package com.example.wlw.mapper;

import com.example.wlw.entity.Sets;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface Setsmapper {
    @Select("select sets.rights " +
            "from sets " +
            "where sets.position = #{position}"
    )
    public String FindRightsByPosition(String position);
    @Update("update sets " +
            "SET sets.rights = #{rights} " +
            "where sets.position = #{position}"
    )
    public void SetRightByPosition(Sets sets);
    @Select("select sets.* from sets")
    public List<Sets> FindAllPosition();
    @Delete("delete from sets where sets.position = #{position}")
    public void Delect(Sets sets);
    @Insert("INSERT INTO sets (position,rights) VALUES (#{position},#{rights})")
    public void Insert(Sets sets);
}
