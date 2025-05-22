package zeenea.connector;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import zeenea.connector.common.filter.FilterConfiguration;
import zeenea.connector.exception.InvalidConfigurationException;

/**
 * The configuration used to create a {@link zeenea.connector.Connection}. Configuration data is
 * populated from mandatory <em>connection configuration files</em> provided to the Scanner.
 * Provides basic property access and validation.
 *
 * @see Connection
 * @see Connector
 */
public interface ConnectionConfiguration {

  /**
   * Gets the home folder path of the scanner.
   *
   * @return the path to the scanner home folder
   */
  Path getScannerHomeFolder();

  /**
   * Gets the unique identifier of the connector.
   *
   * @return the unique identifier of the connector
   */
  String getConnectorId();

  /**
   * Gets the name of the connection.
   *
   * @return the name of the connection
   */
  String getConnectionName();

  /**
   * Gets the code of the connection.
   *
   * @return the code of the connection
   */
  String getConnectionCode();

  /**
   * Gets a string value associated with the specified key.
   *
   * @param key the key for the string value
   * @return the string value associated with the key
   */
  String getString(String key);

  /**
   * Gets a long value associated with the specified key.
   *
   * @param key the key for the long value
   * @return the long value associated with the key
   * @throws InvalidConfigurationException if the configuration is invalid
   */
  Long getLong(String key) throws InvalidConfigurationException;

  /**
   * Gets a boolean value associated with the specified key.
   *
   * @param key the key for the boolean value
   * @return the boolean value associated with the key
   * @throws InvalidConfigurationException if the configuration is invalid
   */
  Boolean getBoolean(String key) throws InvalidConfigurationException;

  /**
   * Gets a path value associated with the specified key.
   *
   * @param key the key for the path value
   * @return the path value associated with the key
   * @throws InvalidConfigurationException if the configuration is invalid
   */
  Path getPath(String key) throws InvalidConfigurationException;

  /**
   * Gets filters defined in connector configuration file.
   *
   * @return Filters object representing filtering rules
   */
  FilterConfiguration getFilters();

  /**
   * Gets a Map associated with the specified key.
   *
   * <p>Example: environment.variables { "DEFAULT_WAREHOUSE":"zeenea_wh",
   * "DEFAULT_SCHEMA":"zeenea_db" }
   *
   * @param key the key for the Map value
   * @return the Map associated with the key, empty if not found
   * @throws InvalidConfigurationException if the value does not correspond to a Map
   */
  Map<String, String> getMap(String key) throws InvalidConfigurationException;

  /**
   * Gets a List associated with the specified key.
   *
   * <p>example: environment.variables = [ "zeenea_db", "music", "artist" ]
   *
   * @param key the key for the List value
   * @return the List associated with the key, empty if not found
   * @throws InvalidConfigurationException if the value does not correspond to a List
   */
  List<String> getList(String key) throws InvalidConfigurationException;

  /**
   * Gets an optional string value associated with the specified key.
   *
   * @param key the key for the string value
   * @return an Optional containing the string value if present, otherwise an empty Optional
   */
  default Optional<String> getStringOptional(String key) {
    return Optional.ofNullable(getString(key));
  }

  /**
   * Gets an optional long value associated with the specified key.
   *
   * @param key the key for the long value
   * @return an Optional containing the long value if present, otherwise an empty Optional
   * @throws InvalidConfigurationException if the configuration is invalid
   */
  default Optional<Long> getLongOptional(String key) throws InvalidConfigurationException {
    return Optional.ofNullable(getLong(key));
  }

  /**
   * Gets an optional boolean value associated with the specified key.
   *
   * @param key the key for the boolean value
   * @return an Optional containing the boolean value if present, otherwise an empty Optional
   * @throws InvalidConfigurationException if the configuration is invalid
   */
  default Optional<Boolean> getBooleanOptional(String key) throws InvalidConfigurationException {
    return Optional.ofNullable(getBoolean(key));
  }

  /**
   * Gets an optional path value associated with the specified key.
   *
   * @param key the key for the path value
   * @return an Optional containing the path value if present, otherwise an empty Optional
   * @throws InvalidConfigurationException if the configuration is invalid
   */
  default Optional<Path> getPathOptional(String key) throws InvalidConfigurationException {
    return Optional.ofNullable(getPath(key));
  }
}
