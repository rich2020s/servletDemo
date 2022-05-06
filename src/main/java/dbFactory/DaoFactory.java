package dbFactory;

import dao.UserDao;
import dao.UserDaoImpl;

public class DaoFactory {
    private static UserDao userDao = null;
    private DaoFactory(){};
    public static UserDao getUserDao() {
        if (userDao == null) {
             userDao = new UserDaoImpl();
        }
        return userDao;
    }
}
