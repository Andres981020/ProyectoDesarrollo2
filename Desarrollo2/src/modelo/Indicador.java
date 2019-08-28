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
@Table(name = "indicador", catalog = "balanceScoreCard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.findAll", query = "SELECT i FROM Indicador i")
    , @NamedQuery(name = "Indicador.findByCodigoIndicador", query = "SELECT i FROM Indicador i WHERE i.codigoIndicador = :codigoIndicador")
    , @NamedQuery(name = "Indicador.findByDescripcionIndicador", query = "SELECT i FROM Indicador i WHERE i.descripcionIndicador = :descripcionIndicador")
    , @NamedQuery(name = "Indicador.findByFechaIndicador", query = "SELECT i FROM Indicador i WHERE i.fechaIndicador = :fechaIndicador")})
public class Indicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_indicador")
    private String codigoIndicador;
    @Basic(optional = false)
    @Column(name = "descripcion_indicador")
    private String descripcionIndicador;
    @Column(name = "fecha_indicador")
    @Temporal(TemporalType.DATE)
    private Date fechaIndicador;
    @JoinColumn(name = "indicador_objetivo", referencedColumnName = "codigo_objetivo")
    @ManyToOne(optional = false)
    private Objetivo indicadorObjetivo;

    public Indicador() {
    }

    public Indicador(String codigoIndicador) {
        this.codigoIndicador = codigoIndicador;
    }

    public Indicador(String codigoIndicador, String descripcionIndicador) {
        this.codigoIndicador = codigoIndicador;
        this.descripcionIndicador = descripcionIndicador;
    }

    public String getCodigoIndicador() {
        return codigoIndicador;
    }

    public void setCodigoIndicador(String codigoIndicador) {
        this.codigoIndicador = codigoIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public Date getFechaIndicador() {
        return fechaIndicador;
    }

    public void setFechaIndicador(Date fechaIndicador) {
        this.fechaIndicador = fechaIndicador;
    }

    public Objetivo getIndicadorObjetivo() {
        return indicadorObjetivo;
    }

    public void setIndicadorObjetivo(Objetivo indicadorObjetivo) {
        this.indicadorObjetivo = indicadorObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoIndicador != null ? codigoIndicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.codigoIndicador == null && other.codigoIndicador != null) || (this.codigoIndicador != null && !this.codigoIndicador.equals(other.codigoIndicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Indicador[ codigoIndicador=" + codigoIndicador + " ]";
    }
    
}
