package com.codeborne.selenide.webdriver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxDriverFactoryTest {

  private Proxy proxy = mock(Proxy.class);
  private FirefoxDriverFactory driverFactory;

  @Before
  public void setUp() {
    driverFactory = new FirefoxDriverFactory();
  }

  @After
  public void tearDown() {
    System.clearProperty("capabilities.some.cap");
    System.clearProperty("firefoxprofile.some.cap");
  }

  @Test
  public void transfersStringCapabilitiesFromSystemPropsToDriver() {
    System.setProperty("capabilities.some.cap", "abcd");
    assertThat(driverFactory.createCommonCapabilities(proxy).getCapability("some.cap"), is("abcd"));
  }

  @Test
  public void transfersBooleanCapabilitiesFromSystemPropsToDriver() {
    System.setProperty("capabilities.some.cap", "true");
    assertThat(driverFactory.createCommonCapabilities(proxy).getCapability("some.cap"), is(true));
  }

  @Test
  public void transfersIntegerCapabilitiesFromSystemPropsToDriver() {
    System.setProperty("capabilities.some.cap", "25");
    assertThat(driverFactory.createCommonCapabilities(proxy).getCapability("some.cap"), is(25));
  }

  @Test
  public void transferIntegerFirefoxProfilePreferencesFromSystemPropsToDriver() {
    System.setProperty("firefoxprofile.some.cap", "25");
    FirefoxProfile profile = driverFactory.createFirefoxOptions(proxy).getProfile();
    assertThat(profile.getIntegerPreference("some.cap", 0), is(25));

  }

  @Test
  public void transferBooleanFirefoxProfilePreferencesFromSystemPropsToDriver() {
    System.setProperty("firefoxprofile.some.cap", "false");
    FirefoxProfile profile = driverFactory.createFirefoxOptions(proxy).getProfile();
    assertThat(profile.getBooleanPreference("some.cap", true), is(false));
  }

  @Test
  public void transferStringFirefoxProfilePreferencesFromSystemPropsToDriver() {
    System.setProperty("firefoxprofile.some.cap", "abdd");
    FirefoxProfile profile = driverFactory.createFirefoxOptions(proxy).getProfile();
    assertThat(profile.getStringPreference("some.cap", "sjlj"), is("abdd"));
  }

}
