/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.orders;

import java.sql.Date;

/**
 *
 * @author tlmin
 */
public class OrderDTO {

    private String orderId;
    private String cusName;
    private Date orderDate;
    private String cusPhone;
    private String cusEmail;
    private String paymentType;
    private double orderTotal;
    private String paymentStatus;

    public OrderDTO(String orderId, String cusName, Date orderDate, String cusPhone, String cusEmail, String paymentType, double orderTotal, String paymentStatus) {
        this.orderId = orderId;
        this.cusName = cusName;
        this.orderDate = orderDate;
        this.cusPhone = cusPhone;
        this.cusEmail = cusEmail;
        this.paymentType = paymentType;
        this.orderTotal = orderTotal;
        this.paymentStatus = paymentStatus;
    }

    OrderDTO() {
        this.orderId = "";
        this.cusName = "";
        this.orderDate = new Date(System.currentTimeMillis());;
        this.cusPhone = "";
        this.cusEmail = "";
        this.paymentType = "";
        this.orderTotal = 0;
        this.paymentStatus = "";
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
