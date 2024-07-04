package zeenea.connector;

import java.util.Collections;

public final class TestUtils {

  public static String longString(int count) {
    return String.join("", Collections.nCopies(count, "a"));
  }
}
