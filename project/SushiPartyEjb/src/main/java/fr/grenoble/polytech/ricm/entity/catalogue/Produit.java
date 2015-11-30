package fr.grenoble.polytech.ricm.entity.catalogue;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT a FROM Produit a" ),
    @NamedQuery(name = "Produit.findById", query = "SELECT a FROM Produit a WHERE a.id = :id" ),
    @NamedQuery(name = "Produit.findByDesignation", query = "SELECT a FROM Produit a WHERE a.designation = :designation" ),
    @NamedQuery(name = "Produit.findByDescription", query = "SELECT a FROM Produit a WHERE a.description = :description"),
    @NamedQuery(name = "Produit.findByCategorieDesignation", query = "SELECT a FROM Produit a WHERE a.categorie.designation = :designationCategorie"),
    @NamedQuery(name = "Produit.findByCategorieId", query = "SELECT a FROM Produit a WHERE a.categorie.id = :idCategorie")
})
public class Produit implements Serializable{

    private static final long serialVersionUID = 1L;

    /****		Déclaration des attributs de la classe		****/
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
	
    @Column(unique = true, length = 50, nullable = false)  
    private String designation;
    
    @Column(length = 255, nullable = false)
    private String description;

    @Column(precision=10, scale = 3, nullable = false)
    private Double prixVente;
    
    @Column(precision=10, scale = 0, nullable = false)
    private Long qteStock;

    @Column(length = 30, nullable = true)
    private String imagePath;	
    
    @ManyToOne
    private Categorie categorie;	

    public Produit() {
        super();
    }

    public Produit(String designation, String description, Long qteStock,
		  Double prixVente, Categorie categorie, String imagePath) {
	super();
	this.designation = designation;
	this.description = description;
	this.prixVente = prixVente;
	this.qteStock = qteStock;	
	this.categorie = categorie;
	this.imagePath = imagePath;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public String getDesignation() {
	return designation;
    }

    public void setDesignation(String designation) {
	this.designation = designation;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
	
    public Double getPrixVente() {
	return prixVente;
    }

    public void setPrixVente(Double prixVente) {
	this.prixVente = prixVente;
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
    }	
	
    public Categorie getCategorie() {
	return categorie;
    }

    public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
    }	

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof Produit ) ) {
            return false;
        }
        Produit other = ( Produit ) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit[ id=" + id + " ] " + designation;
    }
	
}
