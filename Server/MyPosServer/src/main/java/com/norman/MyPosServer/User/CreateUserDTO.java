package com.norman.MyPosServer.User;

import java.util.List;
public class CreateUserDTO {

    private String username;
    private String password;
    private String[] auths;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String[] getAuths() { return auths; }
    public void setAuths(String[] auths) { this.auths = auths; }

}
