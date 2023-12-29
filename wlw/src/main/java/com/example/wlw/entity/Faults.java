package com.example.wlw.entity;

import lombok.Data;

@Data
public class Faults {
    public String faultId;
    public String deviceId;
    public String faultType;
    public String faultDate;
    public String reporter;
    public String faultPart;
}
