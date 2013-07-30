import com.kmware.automation.elements.primefaces.PFOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.kmware.automation.browser.Browser.browser;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 2:31
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        final Logger log = LoggerFactory.getLogger(Main.class);
        browser().navigate("a:contains('OneMenu')");
        browser().jq().query(".post").find("form %s", PFOneMenu.class).getEl().expand().selectAt(2);
        browser().navigate("a:contains(\"Button\")",3);
    }
}
