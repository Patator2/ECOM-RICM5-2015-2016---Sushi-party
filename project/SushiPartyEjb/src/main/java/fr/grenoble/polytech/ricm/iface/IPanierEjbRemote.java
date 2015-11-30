package fr.grenoble.polytech.ricm.iface;

import java.util.List;

import javax.ejb.Remote;

import fr.grenoble.polytech.ricm.entity.panier.Panier; 

@Remote
public interface IPanierEjbRemote {

    public static final String JNDI_NAME = "ejb/CatalogueEjb";
    
    public List<Panier> listePaniersClient(Long idClient) throws Exception;
    
    public Panier CreerPanier(Panier panier) throws Exception;
    
    public Panier getPanier(Long id) throws Exception;
    
    public Panier ModifierPanier(Panier panier) throws Exception;
    
    public List<Panier> listePaniers() throws Exception;

    public Panier ValiderPanier(Panier panier) throws Exception;
    
    public Panier LivrerPanier(Panier panier) throws Exception;
    
    public void SupprimerPanier(Panier panier) throws Exception;
}
