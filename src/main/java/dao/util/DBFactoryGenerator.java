package dao.util;

import dao.util.enums.DBConnectionType;
import dao.util.enums.UnsupportedFactoryException;

public abstract class DBFactoryGenerator {
    public static IDAOFactory getFactory(DBConnectionType connectionType){
        switch(connectionType){
            case JDBC:
                return new JDBCDAOFactory();
            default:
                throw new UnsupportedFactoryException("JDBC Factory Type not supported.");
        }
    }
}