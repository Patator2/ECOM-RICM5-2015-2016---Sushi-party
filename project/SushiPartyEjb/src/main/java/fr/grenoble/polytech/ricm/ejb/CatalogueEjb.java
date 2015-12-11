package fr.grenoble.polytech.ricm.ejb;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.grenoble.polytech.ricm.entity.catalogue.Categorie;
import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.entity.panier.Magasin;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

@SuppressWarnings("unchecked")
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
    public List<Magasin> listeMagasins() throws Exception {
    	return (List<Magasin>) em.createNamedQuery("Magasin.findAll").getResultList();
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
    
    public Object findEntityByLabel(String entityClassName, String labelField, String labelValue) throws Exception {
    	return em.createNamedQuery(entityClassName+".findBy"+labelField).setParameter(labelField, labelValue).getSingleResult();
    }


    public List<?> listeEntities(String entityClassName, String labelField, String labelValue) throws Exception {
    	if (labelField.trim().length()==0)
    		return em.createNamedQuery(entityClassName+".findAll").getResultList();
    	else
    		return em.createNamedQuery(entityClassName+".findBy"+labelField).setParameter(labelField, labelValue).getResultList();
    }

    @Override	
    public void cleanupTables() throws Exception {
    	// @DenyAll //@RolesAllowed({"Admin"})
        //em.createNativeQuery( "SET FOREIGN_KEY_CHECKS = 0; TRUNCATE CATEGORIE; SET FOREIGN_KEY_CHECKS = 1;" ).executeUpdate();
    }
}
