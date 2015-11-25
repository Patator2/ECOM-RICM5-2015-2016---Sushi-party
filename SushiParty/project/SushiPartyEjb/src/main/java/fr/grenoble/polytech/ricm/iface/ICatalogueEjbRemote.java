package fr.grenoble.polytech.ricm.iface;

import javax.ejb.Remote;
import java.util.List;

import fr.grenoble.polytech.ricm.entity.Categorie;
import fr.grenoble.polytech.ricm.entity.Produit; 

@Remote
public interface ICatalogueEjbRemote {

    public static final String JNDI_NAME = "ejb/CatalogueEjb";
    
    public List<Categorie> listeCategories() throws Exception;
    
    public List<Produit> listeProduits() throws Exception;
    
    public List<Produit> listeProduitsCaterorie(String codeCategorie) throws Exception;
    
    public Produit CreerProduit(Produit produit) throws Exception;
    
    public Produit ModifierProduit(Produit produit) throws Exception;
    
    public void SupprimerProduit(Produit produit) throws Exception;

    public Produit getProduit(String reference) throws Exception;

    public Object saveObject(Object entity) throws Exception;

    public Categorie findCategorieByDesignation(String designation) throws Exception;	
 
    public void cleanupTables() throws Exception;
}