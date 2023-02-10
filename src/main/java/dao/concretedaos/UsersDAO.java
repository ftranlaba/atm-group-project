package dao.concretedaos;

import dao.AbstractDAO;
import dao.interfaces.IUsersDAO;
import datamodels.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class UsersDAO extends AbstractDAO<User> implements IUsersDAO {
    private static final String TABLE_NAME = "users";
    private static final String ID_COLUMN_NAME = "id_user";
    private static final List<String> COLUMN_NAMES;

    static {
        COLUMN_NAMES = new ArrayList<>();
        COLUMN_NAMES.add("first_name");
        COLUMN_NAMES.add("last_name");
        COLUMN_NAMES.add("address");
        COLUMN_NAMES.add("phonenumber");
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getIdColumnName() {
        return ID_COLUMN_NAME;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected User createEntityFromRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(ID_COLUMN_NAME));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAddress(rs.getString("address"));
        user.setPhoneNumber(rs.getString("phonenumber"));
        return user;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, User entity) throws SQLException {
        ps.setString(1, entity.getFirstName());
        ps.setString(2, entity.getLastName());
        ps.setString(3, entity.getAddress());
        ps.setString(4, entity.getPhoneNumber());
    }
}
