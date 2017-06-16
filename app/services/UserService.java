package services;

import models.*;
import java.util.*;
import com.avaje.ebean.*;

/**
 * Created by muamerpolic on 6/16/17.
 */
public class UserService {


    public User Create(User model){
        Ebean.save(model);
        return model;
    }

    public void Delete (long userId){
        User toDelete = User.find.byId(userId);
        Ebean.delete(toDelete);
    }

    public User Update (User model){
        User newUser = User.find.byId(model.Id);
        newUser = model;
        Ebean.update(newUser);
        return newUser;
    }

    public User GetUser(long userId){
        return User.find.byId(userId);
    }

    public List<User> GetUsers(){
        return User.find.all();
    }
}
