package fr.grenoble.polytech.ricm.entity.utilisateur;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@NamedQueries( {
    @NamedQuery(name = "Role.findAll", query = "SELECT a FROM Role a" ),
    @NamedQuery(name = "Role.findByLibelle", query = "SELECT a FROM Role a WHERE a.libelle = :Libelle")
})
public class Role implements Serializable {

    private static final long serialVersionUID = -1447051578332788777L;
    
    @Id @Column(unique = true, nullable=false, length=17)
    private String libelle;    
        
    public Role() {
    }

    public Role(String libelle) {
        this.libelle = libelle;        
    }
    
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle( String libelle ) {
        this.libelle = libelle;
    }        
    
	@Override
    public int hashCode() {        
        return this.libelle.hashCode();
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof Role ) ) {
            return false;
        }
        Role other = ( Role ) object;
        if ( ( this.libelle == null && other.libelle != null ) || ( this.libelle != null && !this.libelle.equals( other.libelle ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role[ id=" + libelle + " ] " + libelle;
    }
}
