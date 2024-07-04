package zeenea.connector.common;

import org.jetbrains.annotations.NotNull;

/** Interface representing a reference to a connection in the Zeenea Data Catalog. */
public interface ConnectionReference {

  /**
   * Gets the value of the connection reference.
   *
   * @return the value of the connection reference
   */
  @NotNull
  String getValue();
}
