package com.example.wlw.service;

import com.example.wlw.entity.Notices;
import java.util.List;

public interface NoticesService {
    public List<Notices> FindAllNotices();
    public void UpdateNoticesByContent(Notices notices);
    public void InsertNotices(Notices notices);
    public void DeleteNotices(Notices notices);
}
