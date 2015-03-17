import org.junit.*;

import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {
    
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                
                assertThat(browser.$("header h1").first().getText()).isEqualTo("Users database");
                assertThat(browser.$("section h1").first().getText()).isEqualTo("12 users found");

                assertThat(browser.$("#pagination li.current").first().getText()).isEqualTo("Displaying 1 to 10 of 12");

                browser.$("#pagination li.next a").click();

                assertThat(browser.$("#pagination li.current").first().getText()).isEqualTo("Displaying 11 to 12 of 12");
                browser.$("#searchbox").text("j");
                browser.$("#searchsubmit").click();

                assertThat(browser.$("section h1").first().getText()).isEqualTo("3 users found");
                browser.$("a", withText("Dow")).click();

                assertThat(browser.$("section h1").first().getText()).isEqualTo("Edit user");

                browser.$("#first_name").text("");
                browser.$("input.primary").click();

                assertThat(browser.$("div.clearfix.error").size()).isEqualTo(1);
                assertThat(browser.$("div.clearfix.error").first().getId()).isEqualTo("first_name_field");

                browser.$("#last_name").text("");
                browser.$("input.primary").click();

                assertThat(browser.$("div.clearfix.error").size()).isEqualTo(2);
                assertThat(browser.$("div.clearfix.error label", 0).getText()).isEqualTo("User first name");
                assertThat(browser.$("div.clearfix.error label", 1).getText()).isEqualTo("User last name");

                browser.$("#email").text("");
                browser.$("input.primary").click();

                assertThat(browser.$("div.clearfix.error").size()).isEqualTo(3);
                assertThat(browser.$("div.clearfix.error label", 0).getText()).isEqualTo("User first name");
                assertThat(browser.$("div.clearfix.error label", 1).getText()).isEqualTo("User last name");
                assertThat(browser.$("div.clearfix.error label", 2).getText()).isEqualTo("User email");

                browser.$("#first_name").text("First");
                browser.$("#last_name").text("Last");
                browser.$("#email").text("test@test.com");
                browser.$("input.primary").click();

                assertThat(browser.$("section h1").first().getText()).isEqualTo("12 users found");
                assertThat(browser.$(".alert-message").first().getText()).isEqualTo("Done! User First Last has been updated");

                browser.$("#searchbox").text("Last");
                browser.$("#searchsubmit").click();

                browser.$("a", withText("First")).click();
                browser.$("input.danger").click();

                assertThat(browser.$("section h1").first().getText()).isEqualTo("11 users found");
                assertThat(browser.$(".alert-message").first().getText()).isEqualTo("Done! User has been deleted");

                browser.$("#searchbox").text("L");
                browser.$("#searchsubmit").click();

                assertThat(browser.$("section h1").first().getText()).isEqualTo("2 users found");
                
            }
        });
    }
  
}
