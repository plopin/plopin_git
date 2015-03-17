package controllers;

import play.Logger;
import play.Logger.ALogger;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

/**
 * Manage a database of users
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
     * Handle default path requests, redirect to users list
     */
    public static Result index() {
        logger.debug("Calling index() - handle default path requests");
        return GO_HOME;
    }

    /**
     * Display the paginated list of users.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on user names
     */
    public static Result userList(int page, String sortBy, String order, String filter) {
       logger.debug("Calling userList() - Display the paginated list of users");
       return ok(
            userList.render(
                User.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing user.
     *
     * @param pk PK of the user to edit
     */
    public static Result userEdit(Long pk) {
        logger.debug("Calling userEdit() - Display the edit form of existing user");
        Form<User> userForm = form(User.class).fill(
            User.find.byId(pk)
        );
        return ok(
            userEditForm.render(pk, userForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param pk PK of the user to edit
     */
    public static Result update(Long pk) {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            logger.error("User update has errors: " + userForm.errors());
            return badRequest(userEditForm.render(pk, userForm));
        }
        userForm.get().update(pk);
        logger.debug("User update successful. User " + userForm.get().first_name + " " + userForm.get().last_name + " has been updated");
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
            logger.error("User create has errors: " + userForm.errors());
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
            
