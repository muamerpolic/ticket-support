package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;
import sun.misc.Contended;
import com.avaje.ebean.annotation.JsonIgnore;


/**
 * Created by muamerpolic on 6/15/17.
 */
@Entity
public class Ticket extends Model{

    @Id
    public Long Id;

    @Constraints.Required
    public String title;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public boolean inProgress;

    @ManyToOne
    @JsonIgnore
    public User user;

    public Ticket(User user, String description, String title) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.inProgress = true;
    }
    public static Finder<Long, Ticket> find = new Finder<Long,Ticket>(Ticket.class);

    public static List<Ticket> findByUser(User user){
        Finder <Long, Ticket> finder = new Finder <>(Ticket.class);
        return finder.where().eq("user", user).findList();
    }
}