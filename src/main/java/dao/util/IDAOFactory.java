package dao.util;

import dao.IBaseDAO;
import datamodels.IdInfo;

public interface IDAOFactory {
    IBaseDAO<? extends IdInfo> getDAO(String modal);
}
