package com.example.wlw.mapper;

import com.example.wlw.entity.Notices;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Noticesmapper {
    @Select("select notices.* from notices")
    public List<Notices> FindAllNotices();
    @Update("update notices set notices.noticeDate=#{noticeDate},notices.noticeState=#{noticeState} " +
            "where notices.noticeContent=#{noticeContent}"
    )
    public void UpdateNoticesByContent(Notices notices);
    @Insert("insert into notices (noticeDate,noticeContent,noticeState) " +
            "values (#{noticeDate},#{noticeContent},#{noticeState})"
    )
    public void InsertNotices(Notices notices);
    @Delete("delete from notices where notices.noticeContent=#{noticeContent}")
    public void DeleteNotices(Notices notices);
}
