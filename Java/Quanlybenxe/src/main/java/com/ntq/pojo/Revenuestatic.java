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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "revenuestatic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revenuestatic.findAll", query = "SELECT r FROM Revenuestatic r"),
    @NamedQuery(name = "Revenuestatic.findByRevenueId", query = "SELECT r FROM Revenuestatic r WHERE r.revenueId = :revenueId"),
    @NamedQuery(name = "Revenuestatic.findByDate", query = "SELECT r FROM Revenuestatic r WHERE r.date = :date"),
    @NamedQuery(name = "Revenuestatic.findByTotalBookings", query = "SELECT r FROM Revenuestatic r WHERE r.totalBookings = :totalBookings"),
    @NamedQuery(name = "Revenuestatic.findByTotalRevenue", query = "SELECT r FROM Revenuestatic r WHERE r.totalRevenue = :totalRevenue"),
    @NamedQuery(name = "Revenuestatic.findByCreatedAt", query = "SELECT r FROM Revenuestatic r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Revenuestatic.findByUpdatedAt", query = "SELECT r FROM Revenuestatic r WHERE r.updatedAt = :updatedAt")})
public class Revenuestatic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "revenue_id")
    private Integer revenueId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_bookings")
    private int totalBookings;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Revenuestatic() {
    }

    public Revenuestatic(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public Revenuestatic(Integer revenueId, Date date, int totalBookings, BigDecimal totalRevenue) {
        this.revenueId = revenueId;
        this.date = date;
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
    }

    public Integer getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
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
        hash += (revenueId != null ? revenueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Revenuestatic)) {
            return false;
        }
        Revenuestatic other = (Revenuestatic) object;
        if ((this.revenueId == null && other.revenueId != null) || (this.revenueId != null && !this.revenueId.equals(other.revenueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Revenuestatic[ revenueId=" + revenueId + " ]";
    }
    
}
