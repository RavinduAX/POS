package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.CrudDAO;
import dao.DAOFactory;
import dao.custom.*;
import dao.custom.impl.*;
import db.DBConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.Customer;
import entity.Item;
import entity.OrderDetails;
import entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();

            if (orderDAO.exist(dto.getOrderId())) {
            }
            connection.setAutoCommit(false);
            boolean save = orderDAO.save(new Orders(dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId()));

            if (!save) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//            stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

            for (OrderDetailDTO detailDTO : dto.getOrderDetails()) {

                boolean save1 = orderDetailsDAO.save(new OrderDetails(detailDTO.getOid(), detailDTO.getItemCode(), detailDTO.getQty(), detailDTO.getUnitPrice()));

                if (!save1) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
                ItemDTO item = searchItem(detailDTO.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());

                //DI
                boolean update = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice()));

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

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer search = customerDAO.search(id);
        return new CustomerDTO(search.getId(), search.getName(), search.getAddress());
    }

    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item search = itemDAO.search(code);
        return new ItemDTO(search.getCode(), search.getDescription(), search.getUnitPrice(), search.getQtyOnHand());
    }

    public boolean checkItemIsAvailable(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException{
        return customerDAO.exist(id);
    }

    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.genarateNewId();
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer entity : all) {
            allCustomers.add(new CustomerDTO(entity.getId(), entity.getName(), entity.getAddress()));
        }
        return allCustomers;
    }

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item entity : all) {
            allItems.add(new ItemDTO(entity.getCode(), entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand()));
        }
        return allItems;
    }
}
