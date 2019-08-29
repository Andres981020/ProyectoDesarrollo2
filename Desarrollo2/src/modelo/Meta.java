/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "meta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meta.findAll", query = "SELECT m FROM Meta m")
    , @NamedQuery(name = "Meta.findByCodigoMeta", query = "SELECT m FROM Meta m WHERE m.codigoMeta = :codigoMeta")
    , @NamedQuery(name = "Meta.findByDescripcionMeta", query = "SELECT m FROM Meta m WHERE m.descripcionMeta = :descripcionMeta")
    , @NamedQuery(name = "Meta.findByFechaMeta", query = "SELECT m FROM Meta m WHERE m.fechaMeta = :fechaMeta")})
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_meta")
    private Integer codigoMeta;
    @Basic(optional = false)
    @Column(name = "descripcion_meta")
    private String descripcionMeta;
    @Column(name = "fecha_meta")
    @Temporal(TemporalType.DATE)
    private Date fechaMeta;
    @JoinColumn(name = "meta_objetivo", referencedColumnName = "codigo_objetivo")
    @ManyToOne(optional = false)
    private Objetivo metaObjetivo;

    public Meta() {
    }

    public Meta(Integer codigoMeta) {
        this.codigoMeta = codigoMeta;
    }

    public Meta(Integer codigoMeta, String descripcionMeta) {
        this.codigoMeta = codigoMeta;
        this.descripcionMeta = descripcionMeta;
    }

    public Integer getCodigoMeta() {
        return codigoMeta;
    }

    public void setCodigoMeta(Integer codigoMeta) {
        this.codigoMeta = codigoMeta;
    }

    public String getDescripcionMeta() {
        return descripcionMeta;
    }

    public void setDescripcionMeta(String descripcionMeta) {
        this.descripcionMeta = descripcionMeta;
    }

    public Date getFechaMeta() {
        return fechaMeta;
    }

    public void setFechaMeta(Date fechaMeta) {
        this.fechaMeta = fechaMeta;
    }

    public Objetivo getMetaObjetivo() {
        return metaObjetivo;
    }

    public void setMetaObjetivo(Objetivo metaObjetivo) {
        this.metaObjetivo = metaObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMeta != null ? codigoMeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meta)) {
            return false;
        }
        Meta other = (Meta) object;
        if ((this.codigoMeta == null && other.codigoMeta != null) || (this.codigoMeta != null && !this.codigoMeta.equals(other.codigoMeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Meta[ codigoMeta=" + codigoMeta + " ]";
    }
    
}
