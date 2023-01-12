/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.UASpws;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abian
 */
@Entity
@Table(name = "finaluas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finaluas.findAll", query = "SELECT f FROM Finaluas f"),
    @NamedQuery(name = "Finaluas.findById", query = "SELECT f FROM Finaluas f WHERE f.id = :id"),
    @NamedQuery(name = "Finaluas.findByName", query = "SELECT f FROM Finaluas f WHERE f.name = :name"),
    @NamedQuery(name = "Finaluas.findByNik", query = "SELECT f FROM Finaluas f WHERE f.nik = :nik"),
    @NamedQuery(name = "Finaluas.findByAddress", query = "SELECT f FROM Finaluas f WHERE f.address = :address")})
public class Finaluas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "nik")
    private String nik;
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "photo")
    private byte[] photo;

    public Finaluas() {
    }

    public Finaluas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Finaluas)) {
            return false;
        }
        Finaluas other = (Finaluas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.c.UASpws.Finaluas[ id=" + id + " ]";
    }
    
}
