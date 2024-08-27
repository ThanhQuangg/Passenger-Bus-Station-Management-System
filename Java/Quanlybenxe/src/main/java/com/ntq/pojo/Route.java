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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "route")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findByRouteID", query = "SELECT r FROM Route r WHERE r.routeID = :routeID"),
    @NamedQuery(name = "Route.findByStartLocation", query = "SELECT r FROM Route r WHERE r.startLocation = :startLocation"),
    @NamedQuery(name = "Route.findByEndLocation", query = "SELECT r FROM Route r WHERE r.endLocation = :endLocation"),
    @NamedQuery(name = "Route.findByDistance", query = "SELECT r FROM Route r WHERE r.distance = :distance"),
    @NamedQuery(name = "Route.findByCreatedAt", query = "SELECT r FROM Route r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Route.findByUpdatedAt", query = "SELECT r FROM Route r WHERE r.updatedAt = :updatedAt"),
    @NamedQuery(name = "Route.findByName", query = "SELECT r FROM Route r WHERE r.name = :name"),
    @NamedQuery(name = "Route.findByTicketPrice", query = "SELECT r FROM Route r WHERE r.ticketPrice = :ticketPrice"),
    @NamedQuery(name = "Route.findByEstimatedDuration", query = "SELECT r FROM Route r WHERE r.estimatedDuration = :estimatedDuration")})
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RouteID")
    private Integer  routeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "StartLocation")
    private String startLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EndLocation")
    private String endLocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Distance")
    private String distance;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ticketPrice")
    private String ticketPrice;
    @Column(name = "EstimatedDuration")
//    @Temporal(TemporalType.TIME)
    private String estimatedDuration;

    public Route() {
    }

    public Route(Integer  routeID) {
        this.routeID = routeID;
    }

    public Route(Integer  routeID, String startLocation, String endLocation, String distance, String ticketPrice, String estimatedDuration) {
        this.routeID = routeID;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.ticketPrice = ticketPrice;
        this.estimatedDuration = estimatedDuration;
    }

    public Integer  getRouteID() {
        return routeID;
    }

    public void setRouteID(Integer  routeID) {
        this.routeID = routeID;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(String estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routeID != null ? routeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.routeID == null && other.routeID != null) || (this.routeID != null && !this.routeID.equals(other.routeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Route[ routeID=" + routeID + " ]";
    }
    
}
