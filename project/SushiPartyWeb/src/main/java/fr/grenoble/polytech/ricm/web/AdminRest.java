package fr.grenoble.polytech.ricm.web;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.entity.utilisateur.Utilisateur;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;
import fr.grenoble.polytech.ricm.iface.IUtilisateurEjbRemote;

@SuppressWarnings("unchecked")
@Path("admin")
public class AdminRest {
 	@Context
    private UriInfo uriInfo;
    
    private Properties env;
    private javax.naming.InitialContext ctx;
    
    public AdminRest() {
        env = new Properties();        
        try {
        	ctx = new InitialContext(env);
        } catch (NamingException ex) {
        	throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    } 
    
    
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/utilisateur/")
    public List<Utilisateur> getUtilisateurs() {        
        List<Utilisateur> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list =  (List<Utilisateur>) catalogue.listeEntities("Utilisateur","","");        	
        } catch (Exception e) {
        	throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return list;
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/utilisateur/{id}")
    public Utilisateur getUtilisateur(@PathParam("id") Long id) {
        Utilisateur user = null;       
        try {
        	IUtilisateurEjbRemote users = ( IUtilisateurEjbRemote ) ctx.lookup( IUtilisateurEjbRemote.JNDI_NAME );
        	user = users.getUtilisateur(id);            
        } catch (Exception e) {
        }
        return user	;
    }
}
