/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.orders;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhtl.courses.Cart;
import minhtl.courses.CourseDAO;
import minhtl.courses.CourseDTO;
import minhtl.utils.DBUtils;

/**
 *
 * @author tlmin
 */
public class OrderDAO {

    private static final String CREATE_ORDER = "INSERT INTO tblOrders (orderId, cusName, cusPhone, cusEmail, orderDate, orderTotal, paymentTypeId, paymentStatus) VALUES (?,?,?,?,?,?,?,?)";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO tblOrderDetails (detailId, orderId, courseId, courseFee, orderQuantity) VALUES (?,?,?,?,?)";
    private static final String GET_ORDER_ID_LIST = "SELECT orderId FROM tblOrders";
    private static final String GET_ORDER_DETAIL_ID_LIST = "SELECT detailId FROM tblOrderDetails";
    private static final String SEARCH_PAYMENT_TYPE_ID = "SELECT paymentTypeId FROM tblPaymentTypes WHERE paymentTypeName=?";
    private static final String SEARCH_ORDER_BY_ID = "SELECT S1.cusName, S1.cusPhone, S1.cusEmail, S1.orderDate, S1.orderTotal, S2.paymentTypeName as paymentType, S1.paymentStatus"
            + " FROM tblOrders as S1, tblPaymentTypes as S2 WHERE orderId=? AND S1.paymentTypeId = S2.paymentTypeId";
    private static final String GET_ORDER_LIST = "SELECT courseName FROM tblOrderDetails as S1, tblCourses as S2 WHERE S1.orderId=? AND S1.courseId = S2.courseId";

    public String generateOrderId() throws SQLException {
        List<String> orderIdList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String newOrderId;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_ID_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderId = rs.getString("orderId");
                    orderIdList.add(orderId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        int order = orderIdList.size() + 1;
        for (int i = 0; i < orderIdList.size(); i++) {
            int curOrder = (Integer.parseInt(orderIdList.get(i).substring(1)));
            if (curOrder == order) {
                order += 1;
            }
        }
        newOrderId = "O" + String.format("%02d", order);
        return newOrderId;
    }

    public String generateOrderDetailId() throws SQLException {
        List<String> detailIdList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String newDetailId;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_DETAIL_ID_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String detailId = rs.getString("detailId");
                    detailIdList.add(detailId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        int detail = detailIdList.size() + 1;
        for (int i = 0; i < detailIdList.size(); i++) {
            int curDetail = (Integer.parseInt(detailIdList.get(i).substring(1)));
            if (curDetail == detail) {
                detail += 1;
            }
        }
        newDetailId = "D" + String.format("%02d", detail);
        return newDetailId;
    }

    public String getPaymentTypeId(String paymentType) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String paymentTypeId = "";
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_PAYMENT_TYPE_ID);
            if (conn != null) {
                ptm.setString(1, paymentType);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    paymentTypeId = rs.getString("paymentTypeId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return paymentTypeId;
    }

    public boolean createOrder(Cart cart, String orderId, String cusName, String cusPhone, String cusEmail, String paymentType) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                double total = 0;
                for (CourseDTO course : cart.getCart().values()) {
                    total += course.getCourseFee() * course.getCourseQuantity();
                }
                ptm = conn.prepareStatement(CREATE_ORDER);
                ptm.setString(1, orderId);
                ptm.setString(2, cusName);
                ptm.setString(3, cusPhone);
                ptm.setString(4, cusEmail);
                ptm.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ptm.setDouble(6, total);
                ptm.setString(7, getPaymentTypeId(paymentType));
                if ("cash".equals(paymentType)) {
                    ptm.setBoolean(8, false);
                } else {
                    ptm.setBoolean(8, true);
                }
                check = ptm.executeUpdate() > 0;
                if (check) {
                    for (String courseId : cart.getCart().keySet()) {
                        if (check) {
                            CourseDAO dao = new CourseDAO();
                            CourseDTO course = cart.getCart().get(courseId);
                            String detailId = generateOrderDetailId();
                            ptm = conn.prepareStatement(CREATE_ORDER_DETAIL);
                            ptm.setString(1, detailId);
                            ptm.setString(2, orderId);
                            ptm.setString(3, courseId);
                            ptm.setDouble(4, course.getCourseFee());
                            ptm.setInt(5, course.getCourseQuantity());
                            check = ptm.executeUpdate() > 0;
                            if (check) {
                                dao.updateQuantity(courseId, course.getCourseQuantity());
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public OrderDTO getOrderById(String orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_ORDER_BY_ID);
            if (conn != null) {
                ptm.setString(1, orderId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String cusName = rs.getString("cusName");
                    String cusPhone = rs.getString("cusPhone");
                    String cusEmail = rs.getString("cusEmail");
                    Date orderDate = rs.getDate("orderDate");
                    double orderTotal = rs.getDouble("orderTotal");
                    String paymentType = rs.getString("paymentType");
                    Boolean orderStatus = rs.getBoolean("paymentStatus");
                    String paymentStatus;
                    if (orderStatus) {
                        paymentStatus = "Paid";
                    } else {
                        paymentStatus = "Not yet";
                    }
                    order = new OrderDTO(orderId, cusName, orderDate, cusPhone, cusEmail, paymentType, orderTotal, paymentStatus);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return order;
    }

    public List<String> getOrderList(String orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<String> orderList = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_ORDER_LIST);
            if (conn != null) {
                ptm.setString(1, orderId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String courseName = rs.getString("courseName");
                    orderList.add(courseName);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return orderList;

    }
}
