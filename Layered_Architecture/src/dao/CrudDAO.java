package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO {

    public ArrayList<Object> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(Object dto) throws SQLException, ClassNotFoundException;
    public boolean update(Object dto) throws SQLException, ClassNotFoundException;
    public boolean exist(Object id) throws SQLException, ClassNotFoundException;
    public boolean delete(Object id) throws SQLException, ClassNotFoundException;
    public String genarateNewId() throws SQLException, ClassNotFoundException;

}
