package zeenea.sdk;

import zeenea.sdk.annotations.Beta;

import java.nio.file.Path;

/**
 * The configuration provided to create a {@link Connector}.
 * Provides basic property access and validation.
 *
 * @see ConnectorFactory
 * @see Connector
 * @since 1.0.0
 */
@Beta
public interface ConnectorConfiguration {

    /**
     * The folder containing the file for this configuration.
     *
     * @return The folder containing the file for this configuration
     */
    Path getConfigurationFolder();

    /**
     * The ConnectorId of the Connection.
     *
     * @return The ConnectorId of the Connection
     */
    String getConnectorId();

    /**
     * The Name (label) of the connection, to be displayed.
     *
     * @return The Name (label) of the connection, to be displayed
     */
    String getConnectionName();

    /**
     * Unique code that identifies the connection.
     *
     * @return The unique code that identifies the connection
     */
    String getConnectionCode();

    /**
     * Returns the value for the given key, as a String.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key The key to get a value for
     * @return The value for the given key, as a String
     */
    String getString(String key);

    /**
     * Returns the value for the given key, as a Long object.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key The key to get a value for
     * @return The value for the given key, as a Long object
     * @throws InvalidConfigurationException when value cannot be parsed as a Long
     */
    Long getLong(String key) throws InvalidConfigurationException;

    /**
     * Returns the value of the given key, as a Boolean object.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key The key to get a value for
     * @return The value of the given key, as a Boolean object
     * @throws InvalidConfigurationException when value cannot be parsed as a Boolean
     */
    Boolean getBoolean(String key) throws InvalidConfigurationException;

    /**
     * Returns the value of the given key, as a Path object.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key The key to get a value for
     * @return The value of the given key, as a Path object
     * @throws InvalidConfigurationException when value cannot be parsed as a Path
     */
    Path getPath(String key) throws InvalidConfigurationException;
}
