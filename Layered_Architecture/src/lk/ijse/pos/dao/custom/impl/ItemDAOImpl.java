package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");

        ArrayList<Item> allItem = new ArrayList<>();

        while(rst.next()){
            String code = rst.getString(1);
            String description = rst.getString(2);
            BigDecimal price = rst.getBigDecimal(4);
            int qtyOnHand = rst.getInt(3);

            allItem.add(new Item(code, description, qtyOnHand, price));
        }
        return allItem;
    }

    public boolean delete(String code) throws SQLException, ClassNotFoundException {
           return SQLUtil.executeUpdate("DELETE FROM Item WHERE id=?",code);
    }

    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
    return SQLUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
    return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?", code);
        if(rst.next()){
            return new Item(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getBigDecimal(4));
        }
        return null;
    }

    public boolean exist(String code) throws SQLException, ClassNotFoundException {
    return SQLUtil.executeQuery("SELECT code FROM Item WHERE code=?",code).next();
    }

    public String genarateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ArrayList<Item> getItemFromPrice(double price) throws ClassNotFoundException, SQLException {
        return null;
    }
}
