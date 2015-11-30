package fr.grenoble.polytech.ricm.iface;

import java.util.List;

import javax.ejb.Local; 

@Local
public interface ICommonEjbLocal {

    public static final String JNDI_NAME = "ejb/CommonEjb";	
    
    public Object saveObject(Object entity) throws Exception;
    
    public List<?> listeEntities(String entityClassName, String labelField, String labelValue) throws Exception;
 
    public void cleanupTables() throws Exception;
}
