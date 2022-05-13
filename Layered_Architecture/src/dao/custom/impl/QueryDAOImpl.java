package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.QueryDAO;
import model.CustomDTO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomDTO> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException{
<<<<<<< HEAD
        ResultSet rst = SQLUtil.executeQuery("SELECT Orders.oid, Orders.date, Orders.customerID, OrderDetails.itemCode, OrderDetails.qty, OrderDetails.unitPrice, from Orders inner join OrderDetails on Orders.oid = OrderDetails.oid where Orders.oid = ?", id);
=======
        String sql = "SELECT Orders.oid, Orders.date, Orders.customerID, OrderDetails.itemCode, OrderDetails.qty, OrderDetails.unitPrice, from Orders inner join OrderDetails on Orders.oid = OrderDetails.oid where Orders.oid = ?";
        ResultSet rst = SQLUtil.executeQuery(sql, id);
>>>>>>> 3f12fdd9b2a3488ac0e441ae60d6f3e9195be128

        ArrayList<CustomDTO> orderRecords = new ArrayList<>();

        while(rst.next()){
<<<<<<< HEAD
            orderRecords.add(new CustomDTO(rst.getString(1), LocalDate.parse(rst.getString(2)), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getBigDecimal(6)));
        }

=======
            String oid = rst.getString(1);
            String date = rst.getString(1);
            String customerID = rst.getString(1);
            String itemCode = rst.getString(1);
            int qty = rst.getInt(5);
            BigDecimal unitPrice = rst.getBigDecimal(6);

            CustomDTO customDTO = new CustomDTO();
            customDTO.setOid(oid);
            customDTO.setOrderDate(LocalDate.now());
            customDTO.setCustomerId(customerID);
            customDTO.setItemCode(itemCode);
            customDTO.setQty(qty);
            customDTO.setUnitPrice(unitPrice);

            orderRecords.add(customDTO);
        }
>>>>>>> 3f12fdd9b2a3488ac0e441ae60d6f3e9195be128
        return orderRecords;
    }
}
