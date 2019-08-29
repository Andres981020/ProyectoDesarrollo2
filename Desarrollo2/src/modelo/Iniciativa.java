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
@Table(name = "iniciativa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Iniciativa.findAll", query = "SELECT i FROM Iniciativa i")
    , @NamedQuery(name = "Iniciativa.findByCodigoIniciativa", query = "SELECT i FROM Iniciativa i WHERE i.codigoIniciativa = :codigoIniciativa")
    , @NamedQuery(name = "Iniciativa.findByDescripcionIniciativa", query = "SELECT i FROM Iniciativa i WHERE i.descripcionIniciativa = :descripcionIniciativa")
    , @NamedQuery(name = "Iniciativa.findByFechaIniciativa", query = "SELECT i FROM Iniciativa i WHERE i.fechaIniciativa = :fechaIniciativa")})
public class Iniciativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_iniciativa")
    private Integer codigoIniciativa;
    @Basic(optional = false)
    @Column(name = "descripcion_iniciativa")
    private String descripcionIniciativa;
    @Column(name = "fecha_iniciativa")
    @Temporal(TemporalType.DATE)
    private Date fechaIniciativa;
    @JoinColumn(name = "iniciativa_objetivo", referencedColumnName = "codigo_objetivo")
    @ManyToOne(optional = false)
    private Objetivo iniciativaObjetivo;

    public Iniciativa() {
    }

    public Iniciativa(Integer codigoIniciativa) {
        this.codigoIniciativa = codigoIniciativa;
    }

    public Iniciativa(Integer codigoIniciativa, String descripcionIniciativa) {
        this.codigoIniciativa = codigoIniciativa;
        this.descripcionIniciativa = descripcionIniciativa;
    }

    public Integer getCodigoIniciativa() {
        return codigoIniciativa;
    }

    public void setCodigoIniciativa(Integer codigoIniciativa) {
        this.codigoIniciativa = codigoIniciativa;
    }

    public String getDescripcionIniciativa() {
        return descripcionIniciativa;
    }

    public void setDescripcionIniciativa(String descripcionIniciativa) {
        this.descripcionIniciativa = descripcionIniciativa;
    }

    public Date getFechaIniciativa() {
        return fechaIniciativa;
    }

    public void setFechaIniciativa(Date fechaIniciativa) {
        this.fechaIniciativa = fechaIniciativa;
    }

    public Objetivo getIniciativaObjetivo() {
        return iniciativaObjetivo;
    }

    public void setIniciativaObjetivo(Objetivo iniciativaObjetivo) {
        this.iniciativaObjetivo = iniciativaObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoIniciativa != null ? codigoIniciativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Iniciativa)) {
            return false;
        }
        Iniciativa other = (Iniciativa) object;
        if ((this.codigoIniciativa == null && other.codigoIniciativa != null) || (this.codigoIniciativa != null && !this.codigoIniciativa.equals(other.codigoIniciativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Iniciativa[ codigoIniciativa=" + codigoIniciativa + " ]";
    }
    
}
