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

public class TicketController extends Controller{
    @Inject FormFactory formFactory;
    @Inject TicketService ticketService;
    @Inject UserService userService;
    public Result getAllTickets(){
        return ok(toJson(ticketService.GetTicketForUser(SecurityController.getUser())));
    }

    public Result createTicket(){
        Form<Ticket> form = formFactory.form(Ticket.class).bindFromRequest();
        if (form.hasErrors()){
            return badRequest(form.errorsAsJson());
        }
        Ticket ticket = form.get();
        ticket.user = SecurityController.getUser();
        ticket.inProgress = true;
        ticketService.Create(ticket);
        return ok(Json.toJson(ticket));
    }

    public void deleteTicket(long ticketId){
        ticketService.Delete(ticketId);
    }
    public Result changeTicketStatus (String ticketId){
        Logger.info(ticketId);
        long ticketID = Long.parseLong(ticketId);
        Ticket ticket = ticketService.GetTicket(ticketID);
        ticketService.changeProgress(ticket);
        return ok(Json.toJson(ticket));

    }
    public Result updateTicket(long ticketId){
        Form<Ticket> form = formFactory.form(Ticket.class).bindFromRequest();
        if (form.hasErrors()){
            return badRequest(form.errorsAsJson());
        }
        Ticket ticket = form.get();
        ticketService.Update(ticket, ticketId);

        return ok(Json.toJson(ticket));
    }
}
