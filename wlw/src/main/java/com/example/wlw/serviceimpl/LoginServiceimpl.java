package com.example.wlw.serviceimpl;


import com.example.wlw.entity.Login;
import com.example.wlw.mapper.Loginmapper;
import com.example.wlw.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceimpl implements LoginService {
    @Autowired
    private Loginmapper loginmapper;
    @Override
    public Login findbyuid(String uid) {
        return loginmapper.findbyuid(uid);
    }
    @Override
    public void insert(Login login){loginmapper.insert(login);};

    @Override
    public int MAXuid(){return loginmapper.MAXuid();}
    @Override
    public int Update(Login login){return loginmapper.Update(login);}
}
