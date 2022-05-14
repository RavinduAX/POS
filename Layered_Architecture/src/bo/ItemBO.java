package bo;

import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean deleteItems(String code) throws SQLException, ClassNotFoundException;

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean itemExists(String code) throws SQLException, ClassNotFoundException;

    public String generateNewItemCode() throws SQLException, ClassNotFoundException;
}
