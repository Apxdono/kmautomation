package com.kmware.automation.jquery;

import org.openqa.selenium.remote.RemoteWebDriver;

public class jQueryBrowser extends jQueryFactory {
  private RemoteWebDriver drv;

  public jQueryBrowser(RemoteWebDriver drv) {
    this.drv = drv;
    setJs(drv);
  }

  public RemoteWebDriver getDrv() {
    return drv;
  }

  public jQueryBrowser get(String url) {
    drv.get(url);
    return this;
  }

}
