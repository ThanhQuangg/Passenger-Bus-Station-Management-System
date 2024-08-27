/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "shipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shipment.findAll", query = "SELECT s FROM Shipment s"),
    @NamedQuery(name = "Shipment.findByShipmentId", query = "SELECT s FROM Shipment s WHERE s.shipmentId = :shipmentId"),
    @NamedQuery(name = "Shipment.findBySenderName", query = "SELECT s FROM Shipment s WHERE s.senderName = :senderName"),
    @NamedQuery(name = "Shipment.findByReceiverName", query = "SELECT s FROM Shipment s WHERE s.receiverName = :receiverName"),
    @NamedQuery(name = "Shipment.findByPickupLocation", query = "SELECT s FROM Shipment s WHERE s.pickupLocation = :pickupLocation"),
    @NamedQuery(name = "Shipment.findByDropoffLocation", query = "SELECT s FROM Shipment s WHERE s.dropoffLocation = :dropoffLocation"),
    @NamedQuery(name = "Shipment.findByShipmentDate", query = "SELECT s FROM Shipment s WHERE s.shipmentDate = :shipmentDate"),
    @NamedQuery(name = "Shipment.findByStatus", query = "SELECT s FROM Shipment s WHERE s.status = :status"),
    @NamedQuery(name = "Shipment.findByPrice", query = "SELECT s FROM Shipment s WHERE s.price = :price"),
    @NamedQuery(name = "Shipment.findBySenderPhone", query = "SELECT s FROM Shipment s WHERE s.senderPhone = :senderPhone"),
    @NamedQuery(name = "Shipment.findBySenderEmail", query = "SELECT s FROM Shipment s WHERE s.senderEmail = :senderEmail"),
    @NamedQuery(name = "Shipment.findByReceiverPhone", query = "SELECT s FROM Shipment s WHERE s.receiverPhone = :receiverPhone"),
    @NamedQuery(name = "Shipment.findByReceiverEmail", query = "SELECT s FROM Shipment s WHERE s.receiverEmail = :receiverEmail"),
    @NamedQuery(name = "Shipment.findByCreatedAt", query = "SELECT s FROM Shipment s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "Shipment.findByUpdatedAt", query = "SELECT s FROM Shipment s WHERE s.updatedAt = :updatedAt")})
public class Shipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shipment_id")
    private Integer shipmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sender_name")
    private String senderName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "receiver_name")
    private String receiverName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pickup_location")
    private String pickupLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "dropoff_location")
    private String dropoffLocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Size(max = 20)
    @Column(name = "SenderPhone")
    private String senderPhone;
    @Size(max = 255)
    @Column(name = "SenderEmail")
    private String senderEmail;
    @Size(max = 20)
    @Column(name = "ReceiverPhone")
    private String receiverPhone;
    @Size(max = 255)
    @Column(name = "ReceiverEmail")
    private String receiverEmail;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Shipment() {
    }

    public Shipment(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Shipment(Integer shipmentId, String senderName, String receiverName, String pickupLocation, String dropoffLocation, Date shipmentDate, String status, BigDecimal price) {
        this.shipmentId = shipmentId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.shipmentDate = shipmentDate;
        this.status = status;
        this.price = price;
    }

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipmentId != null ? shipmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shipment)) {
            return false;
        }
        Shipment other = (Shipment) object;
        if ((this.shipmentId == null && other.shipmentId != null) || (this.shipmentId != null && !this.shipmentId.equals(other.shipmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Shipment[ shipmentId=" + shipmentId + " ]";
    }
    
}
