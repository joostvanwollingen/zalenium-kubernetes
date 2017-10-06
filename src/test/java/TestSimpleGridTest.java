import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class TestSimpleGridTest {

    RemoteWebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();

        String httpProxy = "zalenium-proxy.c.bolcom-pro-zalenium.internal:3128";
        String sslProxy = "zalenium-proxy.c.bolcom-pro-zalenium.internal:3128";
        String ftpProxy = "zalenium-proxy.c.bolcom-pro-zalenium.internal:3128";

        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy(httpProxy);
        proxy.setSslProxy(sslProxy);
        proxy.setFtpProxy(ftpProxy);

        capability.setCapability(CapabilityType.PROXY, proxy);
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        capability.setCapability("recordVideo", false);

        driver = new RemoteWebDriver(getGridUrlFromEnvironment(), capability);
        driver.get("http://www.test2.bol.com");
    }

    @Test
    public void shouldGetGoogleTitle() {
        driver.get("https://www.test2.bol.com");
        Assert.assertEquals("Google",driver.getTitle());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private URL getGridUrlFromEnvironment() {
        URL gridUrl = null;
//        String gridAddress = System.getenv("GRID_ADDRESS");
        String gridAddress = "https://zalenium.bol.io/wd/hub";

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