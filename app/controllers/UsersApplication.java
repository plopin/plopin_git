package controllers;

import play.Logger;
import play.Logger.ALogger;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

/**
 * Manage a database of computers
 */
public class UsersApplication extends Controller {

	private static final ALogger logger = Logger.of(UsersApplication.class);
    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.UsersApplication.userList(0, "last_name", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public static Result userList(int page, String sortBy, String order, String filter) {
        return ok(
            userList.render(
                User.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public static Result userEdit(Long pk) {
        Form<User> userForm = form(User.class).fill(
            User.find.byId(pk)
        );
        logger.debug("test debug1");
        logger.error("test error1");
        return ok(
            userEditForm.render(pk, userForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public static Result update(Long pk) {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(userEditForm.render(pk, userForm));
        }
        userForm.get().update(pk);
        flash("success", "User " + userForm.get().first_name + " " + userForm.get().last_name + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new user form'.
     */
    public static Result create() {
        Form<User> userEditForm = form(User.class);
        return ok(
            userCreateForm.render(userEditForm)
        );
    }
    
    /**
     * Handle the 'new user form' submission 
     */
    public static Result save() {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(userCreateForm.render(userForm));
        }
        userForm.get().save();
        flash("success", "User " + userForm.get().first_name + " " + userForm.get().last_name + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle user deletion
     */
    public static Result delete(Long pk) {
        User.find.ref(pk).delete();
        flash("success", "User has been deleted");
        return GO_HOME;
    }
    

}
            
