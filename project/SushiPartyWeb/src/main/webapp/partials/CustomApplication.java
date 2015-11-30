package fr.grenoble.polytech.ricm.web.filter;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
 

 
public class CustomApplication extends ResourceConfig
{
    public CustomApplication()
    {
        packages("fr.grenoble.polytech.ricm.web");
        register(LoggingFilter.class);

 
        //Register Auth Filter here
        register(AuthentificationFilter.class);
    }
}