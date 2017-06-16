
package controllers;
import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import services.TicketService;
import services.UserService;

import static play.libs.Json.toJson;
import javax.inject.Inject;
/**
 * Created by muamerpolic on 6/16/17.
 */
public class UserController extends  Controller{
    @Inject FormFactory formFactory;
    @Inject UserService userService;

    public Result createUser(){
        Form<User> form = formFactory.form(User.class).bindFromRequest();
        if (form.hasErrors()){
            return badRequest(form.errorsAsJson());
        }
        User user = form.get();
        Logger.info(user.emailAdress);
        userService.Create(user);
        return ok(Json.toJson(user));
    }

    public Result getAllUsers(){
        return ok(Json.toJson(userService.GetUsers()));
    }

    public Result getUserById(long userId){
        return ok(Json.toJson(userService.GetUser(userId)));
    }

}
