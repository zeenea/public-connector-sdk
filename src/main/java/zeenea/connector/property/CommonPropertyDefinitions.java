package zeenea.connector.property;

import static zeenea.common.properties.CommonProperties.accessUrlProperty;
import static zeenea.common.properties.CommonProperties.catalogCommonProperty;
import static zeenea.common.properties.CommonProperties.dataSourceTypeProperty;
import static zeenea.common.properties.CommonProperties.databaseCommonProperty;
import static zeenea.common.properties.CommonProperties.expressionProperty;
import static zeenea.common.properties.CommonProperties.powerQueryProperty;
import static zeenea.common.properties.CommonProperties.schemaCommonProperty;
import static zeenea.common.properties.CommonProperties.sqlQueryDialectProperty;
import static zeenea.common.properties.CommonProperties.sqlQueryProperty;
import static zeenea.common.properties.CommonProperties.typeCommonProperty;

public final class CommonPropertyDefinitions {
  public static final TagPropertyDefinition CATALOG =
      new TagPropertyDefinition(catalogCommonProperty.defaultName(), catalogCommonProperty.uuid());
  public static final TagPropertyDefinition DATABASE =
      new TagPropertyDefinition(
          databaseCommonProperty.defaultName(), databaseCommonProperty.uuid());
  public static final TagPropertyDefinition SCHEMA =
      new TagPropertyDefinition(schemaCommonProperty.defaultName(), schemaCommonProperty.uuid());
  public static final TagPropertyDefinition TYPE =
      new TagPropertyDefinition(typeCommonProperty.defaultName(), typeCommonProperty.uuid());
  public static final LongTextPropertyDefinition SQL_QUERY =
      new LongTextPropertyDefinition(sqlQueryProperty.defaultName(), sqlQueryProperty.uuid());
  public static final TagPropertyDefinition SQL_QUERY_DIALECT =
      new TagPropertyDefinition(
          sqlQueryDialectProperty.defaultName(), sqlQueryDialectProperty.uuid());
  public static final UrlPropertyDefinition ACCESS_URL =
      new UrlPropertyDefinition(accessUrlProperty.defaultName(), accessUrlProperty.uuid());
  public static final LongTextPropertyDefinition EXPRESSION =
      new LongTextPropertyDefinition(expressionProperty.defaultName(), expressionProperty.uuid());
  public static final LongTextPropertyDefinition POWER_QUERY_EXPRESSION =
      new LongTextPropertyDefinition(powerQueryProperty.defaultName(), powerQueryProperty.uuid());
  public static final TagPropertyDefinition DATASOURCE_TYPE =
      new TagPropertyDefinition(
          dataSourceTypeProperty.defaultName(), dataSourceTypeProperty.uuid());

  private CommonPropertyDefinitions() {}
}
