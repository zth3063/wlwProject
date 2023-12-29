package com.example.wlw.service;


import com.example.wlw.entity.Login;
import com.example.wlw.entity.Logs;

import java.util.List;

public interface LoginService {
    public Login findbyuid(String uid);
    public void insert(Login login);

    public int MAXuid();
    public int Update(Login login);
}
