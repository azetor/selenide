package integration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChecksOnMissingElementTest extends IntegrationTest {
  @BeforeEach
  void openTestPage() {
    Configuration.timeout = 10;
    openFile("page_with_jquery.html");
  }

  @Test
  void should_not_exist_is_ok() {
    $("#abracadabra").shouldNot(exist);
  }

  @Test
  void should_not_be_visible_is_ok() {
    $("#abracadabra").shouldNotBe(visible);
  }

  @Test
  void should_not_have_text_is_nok() {
    assertThatThrownBy(() ->
      $("#abracadabra").shouldNotHave(text("Search"))
    ).isInstanceOf(ElementNotFound.class)
      .hasMessageStartingWith("Element not found {#abracadabra}");
  }
}
