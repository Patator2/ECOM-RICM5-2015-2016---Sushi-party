package fr.grenoble.polytech.ricm.entity.panier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import fr.grenoble.polytech.ricm.entity.utilisateur.Utilisateur;


@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "Panier.findAll", query = "SELECT a FROM Panier a" ),
    @NamedQuery(name = "Panier.findById", query = "SELECT a FROM Panier a WHERE a.id = :Id" ),        
    @NamedQuery(name = "Panier.findByUtilisateurLogin", query = "SELECT a FROM Panier a WHERE a.client.login = :UtilisateurLogin"),
    @NamedQuery(name = "Panier.findByUtilisateurId", query = "SELECT a FROM Panier a WHERE a.client.id = :UtilisateurId")
})
public class Panier implements Serializable{

    private static final long serialVersionUID = 1L;

    /****		Déclaration des attributs de la classe		****/
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
	
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)  
    private Date dateCreation;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)  
    private Date dateValidation;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)  
    private Date dateLivraison;
    
    @Column(precision=10, scale = 3, nullable = false)  
    private Double montant;
           
    @ManyToOne
    private Utilisateur client;
    
    @ManyToOne
    private ModeReglement modeReglement;	

    @ManyToOne
    private ModeLivraison modelivraison;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<PanierProduit> produits = new ArrayList<PanierProduit>();
    
    
    @ManyToOne
    private Magasin magasin;
    
    public Panier() {
        super();
    }

    public Panier(Long id, Date dateCreation, Date dateValidation, Date dateLivraison, Double montant,
			Utilisateur client, Magasin magasin, ModeReglement modeReglement, ModeLivraison modelivraison,
			List<PanierProduit> produits) {
		super();
		this.id = id;
		this.dateCreation = dateCreation;
		this.dateValidation = dateValidation;
		this.dateLivraison = dateLivraison;
		this.montant = montant;
		this.client = client;
		this.magasin = magasin;
		this.modeReglement = modeReglement;
		this.modelivraison = modelivraison;
		this.produits = produits;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Utilisateur getClient() {
		return client;
	}

	public void setClient(Utilisateur client) {
		this.client = client;
	}

	public ModeReglement getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(ModeReglement modeReglement) {
		this.modeReglement = modeReglement;
	}

	public ModeLivraison getModelivraison() {
		return modelivraison;
	}

	public void setModelivraison(ModeLivraison modelivraison) {
		this.modelivraison = modelivraison;
	}	

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public List<PanierProduit> getProduits() {
		return produits;
	}

	public void setProduits(List<PanierProduit> produits) {
		this.produits = produits;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof Panier ) ) {
            return false;
        }
        Panier other = ( Panier ) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Panier[ id=" + id + " ] " + client.getNomComplet();
    }
	
}
