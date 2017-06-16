package services;

import models.*;
import java.util.*;
import com.avaje.ebean.*;

/**
 * Created by muamerpolic on 6/16/17.
 */
public class TicketService {

    public Ticket Create(Ticket model){
        Ebean.save(model);
        return model;
    }
    public void Delete (long ticketId){
        Ticket toDelete = Ticket.find.byId(ticketId);
        Ebean.delete(toDelete);
    }
    public Ticket Update (Ticket model){
        Ticket newTicket = Ticket.find.byId(model.Id);
        newTicket = model;
        Ebean.update(newTicket);
        return newTicket;
    }

    public Ticket GetTicket(long ticketId){
        return Ticket.find.byId(ticketId);
    }

    public List<Ticket> GetTickets(){
        return Ticket.find.all();
    }

    public List<Ticket> GetTicketForUser(User user){
        return Ticket.findByUser(user);
    }
}
