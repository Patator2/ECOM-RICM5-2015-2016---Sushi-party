package fr.grenoble.polytech.ricm.web;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.Properties;
import javax.naming.NamingException;
import javax.naming.InitialContext;

import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;
import fr.grenoble.polytech.ricm.entity.Categorie;
import fr.grenoble.polytech.ricm.entity.Produit;

@Path("Catalogue")
public class CatalogueResource {
    
    @Context
    private UriInfo uriInfo;
    
    private Properties env;
    private javax.naming.InitialContext ctx;
    
    public CatalogueResource() {
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
    @Produces(MediaType.APPLICATION_XML)
    public Response getCategorieAsXml(@QueryParam("id") Long id) {
   	Categorie categorie = null;
        try {
           
        } catch (Exception e) {
            
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        
        //logger.info("Project name : " + (project != null ? project.getName() : "Pas de projet"));
        if (categorie != null) {
            return Response.ok(categorie,
                    MediaType.APPLICATION_XML).build();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }   
    }
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorieAsJson(@QueryParam("id") Long id) {
        //logger.info("In getProjectAsJson for id : " + id);
        Categorie categorie = null;
        try {
            //project = entityManager.find(Project.class, id);
        } catch (Exception e) {
            //logger.error("Exception catchee " + ExceptionUtil.displayException(e));
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        //logger.info("Project name : " + (project != null ? project.getName() : "Pas de projet"));

        if (categorie != null) {
            return Response.ok(categorie,
                    MediaType.APPLICATION_JSON).build();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all/")
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
