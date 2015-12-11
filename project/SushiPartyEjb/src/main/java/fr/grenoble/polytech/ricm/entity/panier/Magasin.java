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
    @NamedQuery(name = "Magasin.findAll", query = "SELECT a FROM Magasin a" ),
    @NamedQuery(name = "Magasin.findById", query = "SELECT a FROM Magasin a WHERE a.id = :id" ),    
    @NamedQuery(name = "Magasin.findByNom", query = "SELECT a FROM Magasin a WHERE a.nom = :Nom"),
    @NamedQuery(name = "Magasin.findByCodePostal", query = "SELECT a FROM Magasin a WHERE a.codePostal = :codePostal")
})
public class Magasin implements Serializable {

    private static final long serialVersionUID = -1447051578332788777L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)    @Column
    private Long id;    
    
    @Column(unique = true, nullable=false)
    private String nom;
    
    @Column(nullable=false)
    private String codePostal;
    
    @Column(nullable=false)
    private String telephone;
    
    @Column(nullable=false)
    private String email;
    
    public Magasin() {
    }

    public Magasin(String nom) {
        this.nom = nom;
    }

    public Magasin( String nom, String codePostal,  String email, String telephone   ) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.telephone = telephone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    } 
    
    
    public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof Magasin ) ) {
            return false;
        }
        Magasin other = ( Magasin ) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Magasin[ id=" + id + " ] " + nom;
    }
}
