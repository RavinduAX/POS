package dao;

import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements CrudDAO<OrderDetailDTO, String> {
    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",dto.getOid(), dto.getItemCode(), dto.getUnitPrice(), dto.getQty());
    }

    @Override
    public boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String genarateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

}
