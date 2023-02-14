package dao.util;

import dao.IBaseDAO;

public interface IDAOFactory {
    IBaseDAO getDAO(String modal);
}
