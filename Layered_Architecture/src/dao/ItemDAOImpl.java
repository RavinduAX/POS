package dao;

import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements CrudDAO<ItemDTO, String> {

    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item");

        ArrayList<ItemDTO> allItem = new ArrayList<>();

        while(rst.next()){
            String code = rst.getString(1);
            String description = rst.getString(2);
            BigDecimal price = rst.getBigDecimal(4);
            int qtyOnHand = rst.getInt(3);

            allItem.add(new ItemDTO(code, description, price, qtyOnHand));
        }
        return allItem;
    }

    public boolean delete(String code) throws SQLException, ClassNotFoundException {
           return SQLUtil.executeUpdate("DELETE FROM Item WHERE id=?",code);
    }

    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {
    return SQLUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }

    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
    return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }

    @Override
    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?", code);
        if(rst.next()){
            return new ItemDTO(rst.getString(1), rst.getString(2), rst.getBigDecimal(4), rst.getInt(3));
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

}
