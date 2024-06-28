package controllers.authentication;

import handlers.users.UsersHandler;
import models.User;

public class Login {
    private final String username;
    private final String password;

    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }


    public User login(){
        try{
           String user_id = UsersHandler.login(username, password);
           if(user_id != null){
               return UsersHandler.getProfile(user_id);
           }
           return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
