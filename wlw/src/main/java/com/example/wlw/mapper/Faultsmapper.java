package com.example.wlw.mapper;

import com.example.wlw.entity.Faults;
import com.example.wlw.entity.FaultsFindAllType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface Faultsmapper {
    @Select("select MIN(faults.faultId) as faultId,faults.faultType,faults.faultPart,COUNT(faults.faultPart) as faultCount " +
            "from faults " +
            "Group By faults.faultType,faults.faultPart"
    )
    public List<FaultsFindAllType> FindAllFaultType();
    @Select("select faults.* from faults where faults.faultPart IS NULL ")
    public List<Faults> FindAllFaultNotFinish();
}
