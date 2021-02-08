package zeenea.sdk;

import zeenea.sdk.annotations.Beta;

import java.nio.file.Path;

@Beta
public interface ConnectorConfiguration {

    /**
     * The folder containing the file for this configuration.
     *
     * @return the folder containing the file for this configuration
     */
    Path getConfigurationFolder();

    /**
     * The ConnectorId of the Connection.
     *
     * @return the ConnectorId of the Connection
     */
    String getConnectorId();

    /**
     * The Name (label) of the connection, to be displayed.
     *
     * @return the Name (label) of the connection, to be displayed
     */
    String getConnectionName();

    /**
     * Unique code that identifies the connection.
     *
     * @return the unique code that identifies the connection
     */
    String getConnectionCode();

    /**
     * Returns the value for the given key, as a String.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key the key to get a value for
     * @return the value for the given key, as a String
     */
    String getString(String key);

    /**
     * Returns the value for the given key, as a Long object.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key the key to get a value for
     * @return the value for the given key, as a Long object
     * @throws InvalidConfigurationException when value cannot be parsed as a Long
     */
    Long getLong(String key) throws InvalidConfigurationException;

    /**
     * Returns the value of the given key, as a Boolean object.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key the key to get a value for
     * @return the value of the given key, as a Boolean object
     * @throws InvalidConfigurationException when value cannot be parsed as a Boolean
     */
    Boolean getBoolean(String key) throws InvalidConfigurationException;

    /**
     * Returns the value of the given key, as a Path object.
     * No value or an empty value will be returned as {@code null}.
     *
     * @param key the key to get a value for
     * @return the value of the given key, as a Path object
     * @throws InvalidConfigurationException when value cannot be parsed as a Path
     */
    Path getPath(String key) throws InvalidConfigurationException;
}
