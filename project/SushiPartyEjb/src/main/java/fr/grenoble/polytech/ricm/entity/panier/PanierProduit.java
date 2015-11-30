package fr.grenoble.polytech.ricm.entity.panier;

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

import fr.grenoble.polytech.ricm.entity.catalogue.Produit;


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
public class PanierProduit implements Serializable{

    private static final long serialVersionUID = 1L;

    /****		Déclaration des attributs de la classe		****/
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @ManyToOne
    private Produit produit;
	
    @Column(precision=10, scale = 0, nullable = false)
    private Long quantite;

    @Column(precision=10, scale = 3, nullable = false)
    private Double prixTotal;
    

    public PanierProduit() {
        super();
    }
    
    public PanierProduit(Produit produit, Long quantite, Double prixTotal) {
		super();
		this.produit = produit;
		this.quantite = quantite;
		this.prixTotal = prixTotal;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof PanierProduit ) ) {
            return false;
        }
        PanierProduit other = ( PanierProduit ) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit[ id=" + id + " ] " + produit.getDesignation();
    }
	
}
