package fr.grenoble.polytech.ricm.entity.panier;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@NamedQueries( {
    @NamedQuery(name = "ModeReglement.findAll", query = "SELECT a FROM ModeReglement a" ),
    @NamedQuery(name = "ModeReglement.findById", query = "SELECT a FROM ModeReglement a WHERE a.id = :id" ),
    @NamedQuery(name = "ModeReglement.findByDesignation", query = "SELECT a FROM ModeReglement a WHERE a.designation = :designation")
})
public class ModeReglement implements Serializable {

    private static final long serialVersionUID = -1447051578332788777L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)    @Column
    private Long id;    
    
    @Column(unique = true, nullable=false)
    private String designation;

    public ModeReglement() {
    }

    public ModeReglement(String designation) {
        this.designation = designation;
    }

    public ModeReglement( Long id, String designation ) {
        this.id = id;
        this.designation = designation;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation( String designation ) {
        this.designation = designation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof ModeReglement ) ) {
            return false;
        }
        ModeReglement other = ( ModeReglement ) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModeReglement[ id=" + id + " ] " + designation;
    }
}
