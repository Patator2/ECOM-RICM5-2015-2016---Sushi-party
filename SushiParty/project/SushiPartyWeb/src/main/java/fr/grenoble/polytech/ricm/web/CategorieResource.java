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

import fr.grenoble.polytech.ricm.entity.Categorie;
import fr.grenoble.polytech.ricm.entity.Produit;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

@Path("categorie")
public class CategorieResource {
 	@Context
    private UriInfo uriInfo;
    
    private Properties env;
    private javax.naming.InitialContext ctx;
    
    public CategorieResource() {
        env = new Properties();
        env.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory" );
        env.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl" );
        env.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming" );
        env.setProperty("org.omg.CORBA.ORBInitialHost", "localhost" );
        env.setProperty("org.omg.CORBA.ORBInitialPort", "3700" );
        env.setProperty(javax.naming.InitialContext.PROVIDER_URL, "iiop://127.0.0.1:3700" );  
        try {
        ctx = new InitialContext(env);
        } catch (NamingException ex) {
        	throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    } 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Categorie> getCategories() {
        //logger.info("In getProjects");
        List<Categorie> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list = catalogue.listeCategories();
        	list.add(0, new Categorie(new Long(10),"Tout afficher"));
            //list = entityManager.createNamedQuery(Project.PROJECT_FIND_ALL_PROJECTS).getResultList();
            //logger.info("Nombre de projet : " + (list != null ? list.size() : "0"));
        } catch (Exception e) {
            //logger.error("Exception catchee " + ExceptionUtil.displayException(e));
        }

        return list;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public List<Produit> getProduits(@PathParam("id") Long id) {
        //logger.info("In getProjects");
        List<Produit> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list = catalogue.listeProduitsCaterorie(id);
            //list = entityManager.createNamedQuery(Project.PROJECT_FIND_ALL_PROJECTS).getResultList();
            //logger.info("Nombre de projet : " + (list != null ? list.size() : "0"));
        } catch (Exception e) {
            //logger.error("Exception catchee " + ExceptionUtil.displayException(e));
        }

        return list;
    }
}
