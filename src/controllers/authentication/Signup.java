package controllers.authentication;

import handlers.users.UsersHandler;

public class Signup {
    private final String username;
    private final String password;

    public Signup(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean signup(){
        try {
            UsersHandler.signup(username, password);
            return true;
        } catch(Exception e){
            System.out.println(e);
            return false;
        }

    }

}
