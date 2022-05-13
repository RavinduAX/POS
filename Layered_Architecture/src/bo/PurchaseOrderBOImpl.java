package bo;

import dao.CrudDAO;
import dao.custom.*;
import dao.custom.impl.*;
import db.DBConnection;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderBOImpl {

    CustomerDAO customerDAO = new CustomerDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();

    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();

            if (orderDAO.exist(orderId)) {
            }
            connection.setAutoCommit(false);
            boolean save = orderDAO.save(new OrderDTO(orderId, orderDate, customerId));

            if (save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//            stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

            for (OrderDetailDTO detail : orderDetails) {

                boolean save1 = orderDetailsDAO.save(detail);

                if (!save1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
//                ItemDTO item = findItem(detail.getItemCode());
                ItemDTO item = null;
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                //DI
                CrudDAO<ItemDTO, String> itemDAO = new ItemDAOImpl();
                boolean update = itemDAO.update(item);

                if (!update) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

     //   return false;
    }
}
