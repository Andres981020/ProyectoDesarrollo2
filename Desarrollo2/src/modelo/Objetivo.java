/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "objetivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivo.findAll", query = "SELECT o FROM Objetivo o")
    , @NamedQuery(name = "Objetivo.findByCodigoObjetivo", query = "SELECT o FROM Objetivo o WHERE o.codigoObjetivo = :codigoObjetivo")
    , @NamedQuery(name = "Objetivo.findByDescripcionObjetivo", query = "SELECT o FROM Objetivo o WHERE o.descripcionObjetivo = :descripcionObjetivo")
    , @NamedQuery(name = "Objetivo.findByFecha", query = "SELECT o FROM Objetivo o WHERE o.fecha = :fecha")
    , @NamedQuery(name = "Objetivo.findByPerspectiva", query = "SELECT o FROM Objetivo o WHERE o.perspectiva = :perspectiva")})
public class Objetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_objetivo")
    private String codigoObjetivo;
    @Basic(optional = false)
    @Column(name = "descripcion_objetivo")
    private String descripcionObjetivo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "perspectiva")
    private String perspectiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicadorObjetivo")
    private Collection<Indicador> indicadorCollection;
    @JoinColumn(name = "creador_objetivo", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Usuario creadorObjetivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iniciativaObjetivo")
    private Collection<Iniciativa> iniciativaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metaObjetivo")
    private Collection<Meta> metaCollection;

    public Objetivo() {
    }

    public Objetivo(String codigoObjetivo) {
        this.codigoObjetivo = codigoObjetivo;
    }

    public Objetivo(String codigoObjetivo, String descripcionObjetivo) {
        this.codigoObjetivo = codigoObjetivo;
        this.descripcionObjetivo = descripcionObjetivo;
    }

    public String getCodigoObjetivo() {
        return codigoObjetivo;
    }

    public void setCodigoObjetivo(String codigoObjetivo) {
        this.codigoObjetivo = codigoObjetivo;
    }

    public String getDescripcionObjetivo() {
        return descripcionObjetivo;
    }

    public void setDescripcionObjetivo(String descripcionObjetivo) {
        this.descripcionObjetivo = descripcionObjetivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPerspectiva() {
        return perspectiva;
    }

    public void setPerspectiva(String perspectiva) {
        this.perspectiva = perspectiva;
    }

    @XmlTransient
    public Collection<Indicador> getIndicadorCollection() {
        return indicadorCollection;
    }

    public void setIndicadorCollection(Collection<Indicador> indicadorCollection) {
        this.indicadorCollection = indicadorCollection;
    }

    public Usuario getCreadorObjetivo() {
        return creadorObjetivo;
    }

    public void setCreadorObjetivo(Usuario creadorObjetivo) {
        this.creadorObjetivo = creadorObjetivo;
    }

    @XmlTransient
    public Collection<Iniciativa> getIniciativaCollection() {
        return iniciativaCollection;
    }

    public void setIniciativaCollection(Collection<Iniciativa> iniciativaCollection) {
        this.iniciativaCollection = iniciativaCollection;
    }

    @XmlTransient
    public Collection<Meta> getMetaCollection() {
        return metaCollection;
    }

    public void setMetaCollection(Collection<Meta> metaCollection) {
        this.metaCollection = metaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoObjetivo != null ? codigoObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivo)) {
            return false;
        }
        Objetivo other = (Objetivo) object;
        if ((this.codigoObjetivo == null && other.codigoObjetivo != null) || (this.codigoObjetivo != null && !this.codigoObjetivo.equals(other.codigoObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Objetivo[ codigoObjetivo=" + codigoObjetivo + " ]";
    }
    
}
