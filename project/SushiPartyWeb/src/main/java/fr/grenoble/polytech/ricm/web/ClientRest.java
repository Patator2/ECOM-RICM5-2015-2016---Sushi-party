package fr.grenoble.polytech.ricm.web;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.entity.panier.Panier;
import fr.grenoble.polytech.ricm.entity.utilisateur.Utilisateur;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;
import fr.grenoble.polytech.ricm.iface.IPanierEjbRemote;

@SuppressWarnings("unchecked")
@Path("client")
public class ClientRest {
 	@Context
    private UriInfo uriInfo;
    
    private Properties env;
    private javax.naming.InitialContext ctx;
    
    public ClientRest() {
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
    public List<Produit> getProduits(@PathParam("id") Long id) {
        List<Produit> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list = catalogue.listeProduitsCaterorie(id);            
        } catch (Exception e) {
        }
        return list;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/panier/{id}")
    public Panier getPanier(@PathParam("id") Long id) {
        Panier res = null;       
        try {
        	IPanierEjbRemote panierEjb = ( IPanierEjbRemote ) ctx.lookup( IPanierEjbRemote.JNDI_NAME );
        	res = panierEjb.getPanier(id);
        } catch (Exception e) {
        }
        return res;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/panier/")
    public Panier creerPanierShop(Panier panier) {    	
        try {
        	System.out.println("Objet Panier en entrée: " + new Gson().toJson(panier));
        	panier.setId(null);
        	IPanierEjbRemote panierEjb = ( IPanierEjbRemote ) ctx.lookup(IPanierEjbRemote.JNDI_NAME );
        	//Logger.getLogger(ClientRest.class.getName()).log(Level.INFO, null, "salut");
        	if(panier.getModelivraison())
        		panierEjb.CreerPanier(panier);
        	else
        		panierEjb.CreerPanierShop(panier);
        } catch (Exception e) {

        }
        return panier;
    }
}
