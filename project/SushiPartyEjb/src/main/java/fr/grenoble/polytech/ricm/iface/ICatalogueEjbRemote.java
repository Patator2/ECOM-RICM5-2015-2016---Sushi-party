package fr.grenoble.polytech.ricm.iface;

import javax.ejb.Remote;

import fr.grenoble.polytech.ricm.entity.catalogue.Categorie;
import fr.grenoble.polytech.ricm.entity.catalogue.Produit;

import java.util.List; 

@Remote
public interface ICatalogueEjbRemote {

    public static final String JNDI_NAME = "ejb/CatalogueEjb";
    
    public List<Categorie> listeCategories() throws Exception;
    
    public List<Produit> listeProduits() throws Exception;
    
    public List<Produit> listeProduitsCaterorie(Long idCategorie) throws Exception;
    
    public List<Produit> listeProduitsCaterorie(String designationCategorie) throws Exception;
    
    public Produit CreerProduit(Produit produit) throws Exception;
    
    public Produit ModifierProduit(Produit produit) throws Exception;
    
    public void SupprimerProduit(Produit produit) throws Exception;

    public Produit getProduit(String reference) throws Exception;

    public Object saveObject(Object entity) throws Exception;

    public Categorie findCategorieByDesignation(String designation) throws Exception;	
    
    public List<?> listeEntities(String entityClassName, String labelField, String labelValue) throws Exception;
    
    public Object findEntityByLabel(String entityClassName, String labelField, String labelValue) throws Exception;
 
    public void cleanupTables() throws Exception;
}
