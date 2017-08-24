import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class TestSimpleGridTest {

    RemoteWebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability("recordVideo", false);

        driver = new RemoteWebDriver(getGridUrlFromEnvironment(), capability);
        driver.get("http://www.google.com");
    }

    @Test
    public void shouldGetGoogleTitle() {
        Assert.assertEquals("Google",driver.getTitle());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private URL getGridUrlFromEnvironment() {
        URL gridUrl = null;
        String gridAddress = System.getenv("GRID_ADDRESS");

        if(gridAddress!=null){
            try {
                gridUrl = new URL(gridAddress);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return gridUrl;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[10][0]);
    }
}