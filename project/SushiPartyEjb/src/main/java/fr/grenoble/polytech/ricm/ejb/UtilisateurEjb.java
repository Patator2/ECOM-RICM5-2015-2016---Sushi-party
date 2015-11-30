package fr.grenoble.polytech.ricm.ejb;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.grenoble.polytech.ricm.entity.utilisateur.Utilisateur;
import fr.grenoble.polytech.ricm.iface.IUtilisateurEjbRemote;

@SuppressWarnings("unchecked")
@Stateless(name = "UtilisateurEjb", mappedName = "ejb/UtilisateurEjb")
public class UtilisateurEjb implements IUtilisateurEjbRemote {
    
    @PersistenceContext
    private EntityManager em;

	/**
     * Default constructor. 
     */
    public UtilisateurEjb() {
        
    }
    @RolesAllowed({"Admin"})
	@Override
    public List<Utilisateur> listeUtilisateurs() throws Exception {
		return (List<Utilisateur>) em.createNamedQuery("Utilisateur.findAll").getResultList();
    }
    
    @RolesAllowed({"Admin","Client"})
    @Override
    public Utilisateur CreerUtilisateur(Utilisateur utilisateur) throws Exception {
    	em.persist(utilisateur);
    	return utilisateur;
    }
    
    @RolesAllowed({"Admin","Manager", "Client"})
    @Override
    public Utilisateur ModifierUtilisateur(Utilisateur utilisateur) throws Exception {
    	em.merge(utilisateur);
    	return utilisateur;
    }

    @RolesAllowed({"Admin"})
    @Override
    public Utilisateur ResetPassword(Utilisateur utilisateur) throws Exception {
    	em.merge(utilisateur);
    	return utilisateur;
    }
    
    @RolesAllowed({"Admin","Manager", "Client"})
    @Override
    public Utilisateur ChangePassword(Utilisateur utilisateur) throws Exception {
    	em.merge(utilisateur);
    	return utilisateur;
    }
    
    @RolesAllowed({"Admin"})
    @Override
    public void SupprimerUtilisateur(Utilisateur utilisateur) throws Exception {
    	utilisateur = em.find(Utilisateur.class, utilisateur.getId());
    	em.remove(utilisateur);
    }

    @RolesAllowed({"Admin","Manager", "Client"})
    @Override
    public Utilisateur getUtilisateur(String login) throws Exception {
        return (Utilisateur) em.find(Utilisateur.class, login);	
    }
}
