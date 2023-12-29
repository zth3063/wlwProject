package com.example.wlw.mapper;

import com.example.wlw.entity.Logs;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Logsmapper {
    @Select("SELECT logs.* " +
            "from logs " +
            "where logs.logid = #{Logid}"
    )
    public Logs FindByLogid(String Logid);
    @Select("SELECT logs.* " +
            "from logs " +
            "where logs.Recorder = #{Recorder}"
    )
    public List<Logs> FindByRecorder(String Recorder);
    @Select("SELECT logs.* " +
            "from logs " +
            "where logs.logDate = #{Date}"
    )
    public List<Logs> FindByDate(String Date);
    @Select("SELECT logs.* " +
            "from logs " +
            "where logs.deviceId = #{DeviceId}"
    )
    public List<Logs> FindByDeviceId(String DeviceId);
    @Select("select logs.* from logs")
    public List<Logs> FindAllLogs();
    @Insert("insert into logs (logid,deviceId,logDate,Content,Recorder) " +
            "values (#{logid},#{deviceId},#{logDate},#{Content},#{Recorder})"
    )
    public void Insert(Logs logs);
    @Update("update logs " +
            "set logs.deviceId=#{deviceId},logs.logDate=#{logDate},logs.Content=#{Content},logs.Recorder=#{Recorder} " +
            "where logs.logid = #{logid}"
    )
    public void Update(Logs logs);
    @Delete("delete from logs where logs.logid = #{logid}")
    public void Delete(Logs logs);
}
