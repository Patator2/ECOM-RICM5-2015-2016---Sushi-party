package fr.grenoble.polytech.ricm.iface;

import java.util.List;

import javax.ejb.Remote;

import fr.grenoble.polytech.ricm.entity.utilisateur.Utilisateur; 

@Remote
public interface IUtilisateurEjbRemote {

    public static final String JNDI_NAME = "ejb/UtilisateurEjb";
    
    public List<Utilisateur> listeUtilisateurs() throws Exception;
    
    public Utilisateur CreerUtilisateur(Utilisateur utilisateur) throws Exception;
    
    public Utilisateur ModifierUtilisateur(Utilisateur utilisateur) throws Exception;
    
    public void SupprimerUtilisateur(Utilisateur utilisateur) throws Exception;

    public Utilisateur getUtilisateur(String login) throws Exception;

	Utilisateur ResetPassword(Utilisateur utilisateur) throws Exception;

	Utilisateur ChangePassword(Utilisateur utilisateur) throws Exception;

	Utilisateur getUtilisateur(Long id) throws Exception;    
}
