package dao;

import java.util.List;

/**
 * @author Moussa
 */
public final class QueryUtil {
    private QueryUtil() {
    }

    public static String entityByIdQuery(String tableName) {
        return String.format("SELECT * FROM %s WHERE %s_id = (?)", tableName, tableName);
    }

    public static String updateQuery(String tableName, List<String> columnNames) {
        StringBuilder query = new StringBuilder(String.format("UPDATE %s SET ", tableName));
        for (int i = 0; i < columnNames.size(); i++) {
            query.append(columnNames.get(i)).append(" = (?)");
            if (i != columnNames.size() - 1) {
                query.append(", ");
            }
        }
        query.append(String.format(" WHERE %s_id = (?)", tableName));
        return query.toString();
    }

    public static String createQuery(String tableName, List<String> columnNames) {
        StringBuilder query = new StringBuilder(String.format("INSERT INTO %s (", tableName));
        for (int i = 0; i < columnNames.size(); i++) {
            query.append(columnNames.get(i));
            if (i != columnNames.size() - 1) {
                query.append(", ");
            }
        }
        query.append(") VALUES (");
        for (int i = 0; i < columnNames.size(); i++) {
            query.append("?");
            if (i != columnNames.size() - 1) {
                query.append(", ");
            }
        }
        query.append(")");
        return query.toString();
    }

    public static String deleteQuery(String tableName) {
        return String.format("DELETE FROM %s WHERE %s_id = (?)", tableName, tableName);
    }

    public static String selectAllQuery(String tableName) {
        return String.format("SELECT * FROM %s", tableName);
    }
}