package fr.grenoble.polytech.ricm.ejb;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

import fr.grenoble.polytech.ricm.entity.Categorie;
import fr.grenoble.polytech.ricm.entity.Produit;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

@SuppressWarnings("unchecked")
@Path("CatalogueEjb")
@Stateless(name = "CatalogueEjb", mappedName = "ejb/CatalogueEjb" )
public class CatalogueEjb implements ICatalogueEjbRemote {
    
    @PersistenceContext
    private EntityManager em;

	/**
     * Default constructor. 
     */
    public CatalogueEjb() {
        
    }
    
	@Override
    public List<Categorie> listeCategories() throws Exception {
		return (List<Categorie>) em.createNamedQuery("Categorie.findAll").getResultList();
    }

    @Override
    public List<Produit> listeProduits() throws Exception {
    	return (List<Produit>) em.createNamedQuery("Produit.findAll").getResultList();
    }

    @Override
    public List<Produit> listeProduitsCaterorie(String designationCategorie) throws Exception {
    	return (List<Produit>) em.createNamedQuery("Produit.findByCategorieDesignation").setParameter("designationCategorie", designationCategorie).getResultList();
    }

    @Override
    public List<Produit> listeProduitsCaterorie(Long idCategorie) throws Exception {
    	return (List<Produit>) em.createNamedQuery("Produit.findByCategorieId").setParameter("idCategorie", idCategorie).getResultList();
    }
    
    @Override
    public Produit CreerProduit(Produit produit) throws Exception {
    	em.persist(produit);
    	return produit;
    }

    @Override
    public Produit ModifierProduit(Produit produit) throws Exception {
	em.merge(produit);
    	return produit;
    }

    @Override
    public void SupprimerProduit(Produit produit) throws Exception {
	produit = em.find(Produit.class, produit.getId());
	em.remove(produit);
    }

    @Override
    public Produit getProduit(String reference) throws Exception {
        return (Produit) em.createNamedQuery("Produit.findByReference").setParameter("reference", reference).getSingleResult();	
    }
    
    @Override	
    public Object saveObject(Object entity) throws Exception {
        em.persist(entity);
        return entity;
    }

    @Override
    public Categorie findCategorieByDesignation(String designation) throws Exception {
        return (Categorie) em.createNamedQuery("Categorie.findByDesignation").setParameter("designation", designation).getSingleResult();
    }	

    @Override	
    public void cleanupTables() throws Exception {
        //em.createNativeQuery( "SET FOREIGN_KEY_CHECKS = 0; TRUNCATE CATEGORIE; SET FOREIGN_KEY_CHECKS = 1;" ).executeUpdate();
    }
}
