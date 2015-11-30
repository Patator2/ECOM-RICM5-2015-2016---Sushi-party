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

import fr.grenoble.polytech.ricm.entity.catalogue.Categorie;
import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

@Path("common")
public class CommonRest {
    
    @Context
    private UriInfo uriInfo;
    
    private Properties env;
    private javax.naming.InitialContext ctx;
    
    public CommonRest() {
        env = new Properties();          
        try {
        ctx = new InitialContext(env);
        } catch (NamingException ex) {
        	throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }   
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categorie/")
    public List<Categorie> getCategories() {
        //logger.info("In getProjects");
        List<Categorie> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list = catalogue.listeCategories();
            //list = entityManager.createNamedQuery(Project.PROJECT_FIND_ALL_PROJECTS).getResultList();
            //logger.info("Nombre de projet : " + (list != null ? list.size() : "0"));
        } catch (Exception e) {
            //logger.error("Exception catchee " + ExceptionUtil.displayException(e));
        }

        return list;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categorie/{id}")
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
    @Path("/produit/")
    public List<Produit> getProduits() {
        List<Produit> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list = catalogue.listeProduits();
        } catch (Exception e) {
        }
        return list;
    }
    
   

}
