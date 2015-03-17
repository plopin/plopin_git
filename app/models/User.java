package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * User entity managed by Ebean
 */
@Entity 
@Table(name="user", schema="public")
public class User extends Model {

    private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk")
    public Long pk;
    
    @Constraints.Required
    public String first_name;
    
    @Constraints.Required
    public String last_name;
    
    @Constraints.Required
    public String email;
    
    public String phone;
    
    /**
     * Generic query helper for entity User with pk Long
     */
    public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class); 
    
    /**
     * Return a page of user
     *
     * @param page Page to display
     * @param pageSize Number of users per page
     * @param sortBy User property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<User> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("last_name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }
    
}

