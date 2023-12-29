package com.example.wlw.serviceimpl;

import com.example.wlw.entity.Notices;
import com.example.wlw.mapper.Noticesmapper;
import com.example.wlw.service.NoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoticesServiceimpl implements NoticesService {
    @Autowired
    Noticesmapper noticesmapper;
    @Override
    public List<Notices> FindAllNotices() {
        return noticesmapper.FindAllNotices();
    }

    @Override
    public void UpdateNoticesByContent(Notices notices) {
        noticesmapper.UpdateNoticesByContent(notices);
    }

    @Override
    public void InsertNotices(Notices notices) {
        noticesmapper.InsertNotices(notices);
    }

    @Override
    public void DeleteNotices(Notices notices) {
        noticesmapper.DeleteNotices(notices);
    }
}
