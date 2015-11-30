package fr.grenoble.polytech.ricm.web;

import java.net.URI;
import java.util.List;
import java.util.Properties;

import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import fr.grenoble.polytech.ricm.entity.catalogue.Categorie;
import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

@Path("categorie")
@RolesAllowed({"Manager"})
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
        List<Categorie> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list = catalogue.listeCategories();
        	list.add(0, new Categorie(new Long(10),"Tout afficher"));
        } catch (Exception e) {            
        }
        return list;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public List<Produit> getProduits(@PathParam("id") Long id) {
        List<Produit> list = null;       
        try {
        	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        	list = catalogue.listeProduitsCaterorie(id);            
        } catch (Exception e) {
        }
        return list;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insertCategorie(Categorie categorie) {
        //logger.info("In insertCategorie");
        try {
            //entityManager.persist(project);
            //l//ogger.info("projectName=[" + project.getName() + "] id=" + project.getProjectId());
            //logger.info("startDate=" + (project.getStartDate() != null ? project.getStartDate() : "Pas de date"));

            URI bookUri = uriInfo.getAbsolutePathBuilder().path(categorie.getId().toString()).build();
            return Response.created(bookUri).build();
        } catch (Exception e) {
            //logger.error("Exception catchee " + ExceptionUtil.displayException(e));
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateCategorie(Categorie project) {
        //logger.info("In updateProject");
        try {
            //entityManager.merge(project);
            //logger.info("projectName=[" + project.getName() + "] id=" + project.getProjectId());
        } catch (Exception e) {
            //logger.error("Exception catchee " + ExceptionUtil.displayException(e));
        }
    }

    @DELETE
    @Consumes("text/xml")
    public void deleteCategorie(@QueryParam("id") Long id) {
        //logger.info("In deleteProject for id " + id);
        try {
            //Project project = entityManager.find(Project.class, id);
            //logger.info("projectName=[" + project.getName() + "] id=" + project.getProjectId());
            //entityManager.remove(project);
        } catch (Exception e) {
            //logger.error("Exception catchee " + ExceptionUtil.displayException(e));
        }
    }
}
