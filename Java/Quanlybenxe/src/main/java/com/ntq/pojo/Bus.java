package com.ntq.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "bus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b"),
    @NamedQuery(name = "Bus.findByBusID", query = "SELECT b FROM Bus b WHERE b.busID = :busID"),
    @NamedQuery(name = "Bus.findByCompanyId", query = "SELECT b FROM Bus b WHERE b.companyID.companyID = :companyId"),
    @NamedQuery(name = "Bus.findByPlateNumber", query = "SELECT b FROM Bus b WHERE b.plateNumber = :plateNumber"),
    @NamedQuery(name = "Bus.findByCapacity", query = "SELECT b FROM Bus b WHERE b.capacity = :capacity"),
    @NamedQuery(name = "Bus.findByAvatar", query = "SELECT b FROM Bus b WHERE b.avatar = :avatar"),
    @NamedQuery(name = "Bus.findByCreatedAt", query = "SELECT b FROM Bus b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Bus.findByUpdatedAt", query = "SELECT b FROM Bus b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "Bus.findByName", query = "SELECT b FROM Bus b WHERE b.name = :name")})
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BusID")
    private Integer busID;
    @Size(max = 20)
    @Column(name = "PlateNumber")
    private String plateNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Capacity")
    private String capacity;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    @ManyToOne
    private Category categoryID;
    @JoinColumn(name = "CompanyID", referencedColumnName = "CompanyID")
    @ManyToOne
    private Company companyID;
    @Transient
    private MultipartFile file;

    public Bus() {
    }

    public Bus(Integer busID) {
        this.busID = busID;
    }

    public Bus(Integer busID, String capacity) {
        this.busID = busID;
        this.capacity = capacity;
    }

    public Integer getBusID() {
        return busID;
    }

    public void setBusID(Integer busID) {
        this.busID = busID;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Category  getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category  categoryID) {
        this.categoryID = categoryID;
    }

    public Company  getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company  companyID) {
        this.companyID = companyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (busID != null ? busID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.busID == null && other.busID != null) || (this.busID != null && !this.busID.equals(other.busID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntq.pojo.Bus[ busID=" + busID + " ]";
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
