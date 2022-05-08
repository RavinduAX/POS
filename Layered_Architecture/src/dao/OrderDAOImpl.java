package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements CrudDAO {


    @Override
    public ArrayList<Object> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Object dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Object dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(Object id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Object id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String genarateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
