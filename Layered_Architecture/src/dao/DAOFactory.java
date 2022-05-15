package dao;

import db.DBConnection;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory=new DAOFactory() : daoFactory ;
    }

}
