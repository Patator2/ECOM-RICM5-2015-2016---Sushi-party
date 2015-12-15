package fr.grenoble.polytech.ricm.ejb;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.grenoble.polytech.ricm.entity.utilisateur.Utilisateur;
import fr.grenoble.polytech.ricm.iface.IUtilisateurEjbRemote;
import fr.grenoble.polytech.ricm.mail.MailSender;

@SuppressWarnings("unchecked")
@Stateless(name = "UtilisateurEjb", mappedName = "ejb/UtilisateurEjb")
public class UtilisateurEjb implements IUtilisateurEjbRemote {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private MailSender mailSender;

	/**
     * Default constructor. 
     */
    public UtilisateurEjb() {
        
    }
    
	@Override
    public List<Utilisateur> listeUtilisateurs() throws Exception {
		return (List<Utilisateur>) em.createNamedQuery("Utilisateur.findAll").getResultList();
    }
    
    
    @Override
    public Utilisateur CreerUtilisateur(Utilisateur utilisateur) throws Exception {
    	em.persist(utilisateur);
    	//mailSender.sendAccountValidationMail(utilisateur.getEmail(), "http://localhost:8080/SushiPartyWeb/ressources/utilisateur/valider/"+utilisateur.getId());
    	return utilisateur;
    }
    

    @Override
    public Utilisateur ModifierUtilisateur(Utilisateur utilisateur) throws Exception {
    	em.merge(utilisateur);
    	return utilisateur;
    }


    @Override
    public Utilisateur ResetPassword(Utilisateur utilisateur) throws Exception {
    	em.merge(utilisateur);
    	return utilisateur;
    }
    

    @Override
    public Utilisateur ChangePassword(Utilisateur utilisateur) throws Exception {
    	em.merge(utilisateur);
    	return utilisateur;
    }
    

    @Override
    public void SupprimerUtilisateur(Utilisateur utilisateur) throws Exception {
    	utilisateur = em.find(Utilisateur.class, utilisateur.getId());
    	em.remove(utilisateur);
    }


    @Override
    public Utilisateur getUtilisateur(String login) throws Exception {
        return (Utilisateur) em.find(Utilisateur.class, login);	
    }

    @Override
    public Utilisateur getUtilisateur(Long id) throws Exception {
        return (Utilisateur) em.find(Utilisateur.class, id);	
    }
}
