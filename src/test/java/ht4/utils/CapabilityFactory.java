package ht4.utils;

import org.openqa.selenium.Capabilities;

public class CapabilityFactory {

    public Capabilities getCapabilities(String browser) {
        Capabilities capabilities;
        if (browser.equals("firefox")) {
            capabilities = OptionsManager.getFirefoxOptions();
        } else {
            capabilities = OptionsManager.getChromeOptions();
        }
        return capabilities;
    }
}
