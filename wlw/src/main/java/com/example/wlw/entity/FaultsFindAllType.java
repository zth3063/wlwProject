package com.example.wlw.entity;
import lombok.Data;

@Data
public class FaultsFindAllType {
    public String faultType;
    public String faultPart;
    public Integer faultCount;
    public Integer faultId;
}
