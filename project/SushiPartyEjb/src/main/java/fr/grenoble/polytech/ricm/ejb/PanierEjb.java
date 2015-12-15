package fr.grenoble.polytech.ricm.ejb;


import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.gson.Gson;

import fr.grenoble.polytech.ricm.entity.panier.Panier;
import fr.grenoble.polytech.ricm.entity.panier.PanierProduit;
import fr.grenoble.polytech.ricm.iface.IPanierEjbRemote;
import fr.grenoble.polytech.ricm.mail.MailSender;


@SuppressWarnings("unchecked")
@Stateless(name = "PanierEjb", mappedName = "ejb/PanierEjb")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PanierEjb implements IPanierEjbRemote {
    
	
    @Inject
    private MailSender mailSender;
    
    @PersistenceContext
    private EntityManager em;

	/**
     * Default constructor. 
     */
    public PanierEjb() {
        
    }
    
    @RolesAllowed({"Admin","Manager","Client"})
	@Override
    public List<Panier> listePaniersClient(Long idClient) throws Exception {
		return (List<Panier>) em.createNamedQuery("Panier.findAll").getResultList();
    }
    
    //@RolesAllowed({"Admin","Manager","Client"})    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Panier CreerPanier(Panier panier) throws Exception {
    	for (PanierProduit lignePanier : panier.getProduits()) {
			lignePanier.getProduit().setQteStock(lignePanier.getProduit().getQteStock() - lignePanier.getQuantite());
		}
    	em.persist(panier);
    	mailSender.sendOrderValidateNotificationMail(panier.getMagasin().getEmail(), panier);
    	return panier;
    }
    
    //@RolesAllowed({"Admin","Manager","Client"})    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Panier CreerPanierShop(Panier panier) throws Exception {
    	for (PanierProduit lignePanier : panier.getProduits()) {
			lignePanier.getProduit().setQteStock(lignePanier.getProduit().getQteStock() - lignePanier.getQuantite());
		}
    	em.persist(panier);
    	mailSender.sendOrderValidateNotificationMailAtShop(panier.getMagasin().getEmail(), panier);
    	return panier;
    }
    
    @RolesAllowed({"Admin","Manager","Client"})
    @Override
    public Panier ModifierPanier(Panier panier) throws Exception {
	em.merge(panier);
    	return panier;
    }
    
    @RolesAllowed({"Admin","Manager","Client"})
    @Override
    public void SupprimerPanier(Panier panier) throws Exception {
    	panier = em.find(Panier.class, panier.getId());
    	em.remove(panier);
    }
    
    @RolesAllowed({"Admin","Manager","Client"})
    @Override
    public Panier ValiderPanier(Panier panier) throws Exception {
    	panier.setDateValidation(new Date());
    	em.merge(panier);
    	return panier;
    }   
    
    @RolesAllowed({"Admin","Manager"})
    @Override
    public Panier LivrerPanier(Panier panier) throws Exception {
    	panier.setDateLivraison(new Date());
    	em.merge(panier);
    	return panier;
    }
    
    @RolesAllowed({"Admin","Manager"})
    @Override
    public List<Panier> listePaniers() throws Exception {
		return (List<Panier>) em.createNamedQuery("Panier.findAll").getResultList();
    }    
 
    @RolesAllowed({"Admin","Manager","Client"})
    @Override
    public Panier getPanier(Long id) throws Exception {
        return (Panier) em.find(Panier.class, id);	
    }
    
    
}
