package com.example.wlw.mapper;

import com.example.wlw.entity.Devices;
import com.example.wlw.entity.Technologies;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Devicesmapper {
    @Select("select technologies.* " +
            "from technologies,devices " +
            "where devices.deviceId = #{deviceId} " +
            "AND devices.techId = technologies.techId"
    )
    public Technologies FindTechnologies(String deviceId);
    @Select("select devices.* " +
            "from devices "
    )
    public List<Devices> FindALL();
    @Update("update devices " +
            "set devices.faultId = #{faultId},devices.repairId = #{repairId},devices.maintenanceId = #{maintenanceId},devices.deviceName = #{deviceName},devices.deviceType = #{deviceType},devices.deviceStatus = #{deviceStatus},devices.effciency = #{effciency},devices.manufacturer = #{manufacturer},devices.model = #{model} " +
            "where devices.deviceId = #{deviceId}"
    )
    public void UpdateDevices(Devices devices);
    @Insert("INSERT INTO devices (deviceId,faultId,repairId,maintenanceId,deviceName,deviceType,deviceStatus,effciency,manufacturer,model) " +
            "values (#{deviceId},#{faultId},#{repairId},#{maintenanceId},#{deviceName},#{deviceType},#{deviceStatus},#{effciency},#{manufacturer},#{model})"
    )
    public void InsertDevices(Devices devices);
    @Delete("delete from devices where devices.deviceId = #{deviceId}")
    public void DeleteDevices(Devices devices);
    @Select("select devices.* from devices where devices.deviceId=#{deviceId}")
    public Devices FindDevicesById(String devicesId);
}
