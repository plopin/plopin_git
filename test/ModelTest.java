import org.junit.*;

import java.util.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.*;

import com.avaje.ebean.*;

public class ModelTest {
    
    @Test
    public void findById() {
        running(fakeApplication(), new Runnable() {
           public void run() {
               User lopin = User.find.byId(1l);
               assertThat(lopin.last_name).isEqualTo("Lopin");
           }
        });
    }
    
    @Test
    public void pagination() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
           public void run() {
               Page<User> users = User.page(1, 10, "last_name", "ASC", "");
               assertThat(users.getTotalRowCount()).isEqualTo(12);
               assertThat(users.getList().size()).isEqualTo(2);
           }
        });
    }
    
}
