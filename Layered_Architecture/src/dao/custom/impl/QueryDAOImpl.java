package dao.custom.impl;

import dao.custom.QueryDAO;

import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public void searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException{
        String sql = "SELECT Orders.oid, Orders.date, Orders.customerID, OrderDetails.itemCode, OrderDetails.qty, OrderDetails.unitPrice, from Orders inner join OrderDetails on Orders.oid = OrderDetails.oid where Orders.oid = \"OID-001\";\n";
    }
}
