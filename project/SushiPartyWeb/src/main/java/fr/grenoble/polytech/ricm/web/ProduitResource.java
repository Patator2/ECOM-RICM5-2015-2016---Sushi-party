package fr.grenoble.polytech.ricm.web;

import java.util.List;
import java.util.Properties;

import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

@Path("produit")
@RolesAllowed({"Manager"})
public class ProduitResource {
 	@Context
    private UriInfo uriInfo;
    
    private Properties env;
    private javax.naming.InitialContext ctx;
    
    public ProduitResource() {
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
