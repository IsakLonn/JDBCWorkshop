package se.lexicon.model;
import se.lexicon.util.StringHelper;

import  java.time.LocalDateTime;
public class ShoppingCart{

    //variables
    private int id;
    private LocalDateTime lastUpdate;
    private String orderStatus;
    private String deliveryAddress;
    private String customerReference;
    private boolean paymentApproved;

    public ShoppingCart(int id, LocalDateTime lastUpdate, String orderStatus, String deliveryAddress, String customerReference) {
        this.id = id;
        setLastUpdate(lastUpdate);
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.customerReference = customerReference;
    }
    public ShoppingCart(String orderStatus, String deliveryAddress, String customerReference) {
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.customerReference = customerReference;
        paymentApproved = false;
    }

    //getters
    public int getId() {return id;}
    public LocalDateTime getLastUpdate() {return lastUpdate;}
    public String getOrderStatus() {return orderStatus;}
    public String getDeliveryAdress() {return deliveryAddress;}
    public String getCustomerReference() {return customerReference;}
    public boolean isPaymentApproved() {return paymentApproved;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setLastUpdate(LocalDateTime lastUpdate) {this.lastUpdate = lastUpdate;}
    public void setOrderStatus(String orderStatus) {
        if(StringHelper.isNullOrEmpty(orderStatus))
        {
            throw new IllegalArgumentException("String was null or empty");
        }
            this.orderStatus = orderStatus;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        if(StringHelper.isNullOrEmpty(deliveryAddress))
        {
            throw new IllegalArgumentException("String was null or empty");
        }
        this.deliveryAddress = deliveryAddress;
    }
    public void setCustomerReference(String customerReference) {
        if(StringHelper.isNullOrEmpty(customerReference))
        {
            throw new IllegalArgumentException("String was null or empty");
        }
        this.customerReference = customerReference;
    }
    public void setPaymentApproved(boolean paymentApproved) {this.paymentApproved = paymentApproved;}

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", lastUpdate=" + lastUpdate +
                ", orderStatus='" + orderStatus + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", customerReference='" + customerReference + '\'' +
                ", paymentApproved=" + paymentApproved +
                '}';
    }
}
