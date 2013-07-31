import com.kmware.automation.elements.selects.PFOneMenu;

import static com.kmware.automation.browser.Browser.browser;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 2:31
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        browser().navigate("a:contains('OneMenu')");
        browser().jq().query(".post").find("form %s", PFOneMenu.class).getEl().expand().selectAt(2);
        browser().navigate("a:contains(\"Button\")",3);
        browser().makeScreenshot();
//        browser().driver().get("http://10.110.56.10:8080/po/LoginForm.jsp");
//        JQUIAutocomplete ac = browser().jq().query("#city", JQUIAutocomplete.class).val("новор").expand();
//        browser().makeScreenshot();
//        ac.selectAt(0);
//        browser().makeScreenshot();
    }
}
