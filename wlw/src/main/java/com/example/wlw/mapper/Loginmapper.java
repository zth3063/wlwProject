package com.example.wlw.mapper;

import com.example.wlw.entity.Login;
import com.example.wlw.entity.Logs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Loginmapper {
    @Select("select uname,upassword,uposition " +
            "from `user`" +
            "where uid = #{uid}"
    )
    public Login findbyuid(String uid);
    @Insert("insert into user (uid,uname,upassword,uposition) " +
            "values (#{uid},#{uname},#{upassword},#{uposition})"
    )
    public void insert(Login login);
    @Select("select MAX(uid) " +
            "from user "
    )
    public int MAXuid();
    @Update("update user " +
            "set user.uname = #{uname},user.upassword = #{upassword},user.uposition = #{uposition} " +
            "where user.uid=#{uid}"
    )
    public int Update(Login login);

}
