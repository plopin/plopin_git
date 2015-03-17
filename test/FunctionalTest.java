import org.junit.*;

import java.util.*;

import play.filters.csrf.CSRF;
import play.filters.csrf.CSRFFilter;
import play.mvc.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class FunctionalTest {

    @Test
    public void redirectHomePage() {
        running(fakeApplication(), new Runnable() {
           public void run() {
               Result result = callAction(controllers.routes.ref.UsersApplication.index());

               assertThat(status(result)).isEqualTo(SEE_OTHER);
               assertThat(redirectLocation(result)).isEqualTo("/users");
           }
        });
    }
    
    @Test
    public void listUsersOnTheFirstPage() {
        running(fakeApplication(), new Runnable() {
           public void run() {
               Result result = callAction(controllers.routes.ref.UsersApplication.userList(0, "last_name", "asc", ""));

               assertThat(status(result)).isEqualTo(OK);
               assertThat(contentAsString(result)).contains("12 users found");
           }
        });
    }
    
    @Test
    public void filterUserByName() {
        running(fakeApplication(), new Runnable() {
           public void run() {
               Result result = callAction(controllers.routes.ref.UsersApplication.userList(0, "last_name", "asc", "j"));

               assertThat(status(result)).isEqualTo(OK);
               assertThat(contentAsString(result)).contains("3 users found");
           }
        });
    }

    
    
    
    @Test
    public void createANewUser() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String,String> data = new HashMap<String,String>();
                Result result = callAction(
                		controllers.routes.ref.UsersApplication.save(),
                		fakeRequest().withSession(CSRF.TokenName(), CSRFFilter.apply$default$5().generateToken()));

                assertThat(status(result)).isEqualTo(BAD_REQUEST);
                
                data = new HashMap<String,String>();
                data.put("first_name", "First");
                data.put("last_name", "Last");
                data.put("email", "test@test.com");
                data.put("pk", "13");
                
                result = callAction(
                    controllers.routes.ref.UsersApplication.save(), 
                    fakeRequest().withSession(CSRF.TokenName(), CSRFFilter.apply$default$5().generateToken()).withFormUrlEncodedBody(data)
                );
                
                assertThat(status(result)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(result)).isEqualTo("/users");
                assertThat(flash(result).get("success")).isEqualTo("User First Last has been created");
                
                result = callAction(controllers.routes.ref.UsersApplication.userList(0, "last_name", "asc", "Last"));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("One user found");
                
            }
        });
    }
    
}
