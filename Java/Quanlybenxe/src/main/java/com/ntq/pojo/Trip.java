package com.ntq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "trip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
    @NamedQuery(name = "Trip.findByTripID", query = "SELECT t FROM Trip t WHERE t.tripID = :tripID"),
    @NamedQuery(name = "Trip.findByBusId", query = "SELECT t FROM Trip t WHERE t.busID.busID = :busId"),
    @NamedQuery(name = "Trip.findByDepartureTime", query = "SELECT t FROM Trip t WHERE t.departureTime = :departureTime"),
    @NamedQuery(name = "Trip.findByArrivalTime", query = "SELECT t FROM Trip t WHERE t.arrivalTime = :arrivalTime"),
    @NamedQuery(name = "Trip.findByTicketPrice", query = "SELECT t FROM Trip t WHERE t.ticketPrice = :ticketPrice"),
    @NamedQuery(name = "Trip.findByStatus", query = "SELECT t FROM Trip t WHERE t.status = :status"),
    @NamedQuery(name = "Trip.findByCreatedAt", query = "SELECT t FROM Trip t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Trip.findByUpdatedAt", query = "SELECT t FROM Trip t WHERE t.updatedAt = :updatedAt")})
public class Trip implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TripID")
    private Integer tripID;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DepartureTime")
//    @Temporal(TemporalType.TIMESTAMP)
    private String departureTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ArrivalTime")
//    @Temporal(TemporalType.TIMESTAMP)
    private String arrivalTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TicketPrice")
    private String ticketPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "tripID")
    @JsonIgnore
    private Set<Booking> bookingSet;
    @JoinColumn(name = "BusID", referencedColumnName = "BusID")
    @ManyToOne
    private Bus busID;
    @JoinColumn(name = "RouteID", referencedColumnName = "RouteID")
    @ManyToOne
    private Route routeID;
    @JoinColumn(name = "CompanyID", referencedColumnName = "CompanyID")
    @ManyToOne
    private Company companyID;

    
    public Trip() {
    }

    public Trip(Integer tripID) {
        this.tripID = tripID;
    }

    public Trip(Integer tripID, String departureTime, String arrivalTime, String ticketPrice, String status) {
        this.tripID = tripID;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.ticketPrice = ticketPrice;
        this.status = status;
    }

    public Integer getTripID() {
        return tripID;
    }

    public void setTripID(Integer tripID) {
        this.tripID = tripID;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @XmlTransient
    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public Bus getBusID() {
        return busID;
    }

    public void setBusID(Bus busID) {
        this.busID = busID;
    }

    public Route getRouteID() {
        return routeID;
    }

    public void setRouteID(Route routeID) {
        this.routeID = routeID;
    }
    
    public Company getCompanyID() {
        return companyID;
    }

    
    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripID != null ? tripID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.tripID == null && other.tripID != null) || (this.tripID != null && !this.tripID.equals(other.tripID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Trip[ tripID=" + tripID + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    

    
    

}
