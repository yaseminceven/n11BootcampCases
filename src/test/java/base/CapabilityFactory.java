package base;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

//class for setting capabilities for each browser type
public class CapabilityFactory {
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public DesiredCapabilities getCapabilities(String browser) {
        switch (browser) {
            case "chrome":
                capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
                break;
            case "edge":
                capabilities.setCapability("edgeOptions", getEdgeOptions());
                break;
            default:
                break;
        }
        return capabilities;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("headless");
        options.addArguments("private");
        options.addArguments("start-maximized");
        return options;
    }


    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("incognito");
        return options;
    }
}

