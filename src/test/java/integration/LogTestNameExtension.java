package integration;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogTestNameExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
  private static final Logger log = LoggerFactory.getLogger(LogTestNameExtension.class);

  @Override
  public void beforeAll(ExtensionContext context) {
    log.info("Starting {} @ {}", context.getDisplayName(), BaseIntegrationTest.browser);
  }

  @Override
  public void afterAll(ExtensionContext context) {
    log.info("Finished {} @ {} - {}", context.getDisplayName(), BaseIntegrationTest.browser,
      context.getExecutionException().isPresent() ? "NOK" : "OK");
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    log.info("  starting {} ...", context.getDisplayName());
  }

  @Override
  public void afterEach(ExtensionContext context) {
    log.info("  finished {} - {}", context.getDisplayName(), context.getExecutionException().isPresent() ? "NOK" : "OK");
  }
}
