package fr.grenoble.polytech.ricm.itest;

import fr.grenoble.polytech.ricm.entity.Categorie;
import fr.grenoble.polytech.ricm.entity.Produit;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EjbIntegrationTest {

    private Properties env;
    private Context ctx;

    public EjbIntegrationTest() {
        env = new Properties();
        env.setProperty( "java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory" );
        env.setProperty( "java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl" );
        env.setProperty( "java.naming.factory.url.pkgs", "com.sun.enterprise.naming" );
        env.setProperty( "org.omg.CORBA.ORBInitialHost", "localhost" );
        env.setProperty( "org.omg.CORBA.ORBInitialPort", "3700" );
        env.setProperty( Context.PROVIDER_URL, "iiop://127.0.0.1:3700" );
    }

    @Before
    public void setUp() throws Exception {
        ctx = new InitialContext(env);	
    }

    @After
    public void tearDown() throws Exception {
        ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME );
        catalogue.cleanupTables();
    }

    @Test
    public void testCreationCategorie() throws Exception {
        ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME);  	         

        assertTrue(catalogue.saveObject(new Categorie("contents1")).hashCode() != 0);
        
	Categorie categorie1  = catalogue.findCategorieByDesignation("contents1");
        assertEquals("contents1", categorie1.getDesignation());
        
    }

    @Test
    public void testInitCategories() throws Exception {
        ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME);
        assertTrue(catalogue.saveObject(new Categorie("Lunch Boxes")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Categorie("Signature Rolls")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Categorie("Spring Rolls")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Categorie("California Rolls")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Categorie("Maki")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Categorie("Temaki")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Categorie("Desserts")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Categorie("Boissons")).hashCode() != 0);
    }
    
    @Test
    public void testInitProduits() throws Exception {
    	ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME);
    	Categorie categorie1  = catalogue.findCategorieByDesignation("Lunch Boxes");
        assertTrue(catalogue.saveObject(new Produit("Lunch Box Du Mois By Marie Luv Pink", "Salade de Choux, Edamame, 4 Sashimi Saumon, 6 Spring Concombre Cheese, 3 California Ebi Tempura, 3 Salmon Roll", new Long(16), 14.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Lunch box A", "Salade de Choux, Edamame, 3 maki salmon roll, 3 maki concombre, 3 spring roll saumon avocat, 3 california saumo avocat", new Long(12), 11.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Lunch box B", "Salade de Choux, Edamame, 6 California Saumon Avocat, 5 Sushi Saumon", new Long(12), 11.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Lunch box C", "Salade de Choux, Edamame, 3 California Surimi Avocat, 3 Spring Avocat Cheese, 3 Maki Saumon, 3 Sushi Saumon", new Long(12), 11.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Lunch box D", "Salade de Choux, Edamame, 6 California Saumon Avocat, 3 Spring Saumon Avocat, 3 Maki Saumon, 3 Sushi Saumon", new Long(14), 11.90, categorie1, "")).hashCode() != 0);

        categorie1  = catalogue.findCategorieByDesignation("Signature Rolls");
        assertTrue(catalogue.saveObject(new Produit("Rock n Roll", "Dessus : Saumon Laqué, Chips, Piment, Sauce Teriyaki. À L’Intérieur : Thon, Asperge, Avocat, Masago, Sauce Épicée", new Long(8), 9.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Fire Roll", "Dessus : Thon, Sauce Épicée. À L’Intérieur : Avocat, Crevette Tempura, Sauce Mayonnaise Ponzu.", new Long(8), 9.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Rainbow Roll", "Dessus : Saumon, Thon, Avocat, Piment. À L’Intérieur : Chair De Crabe, Avocat, Sauce Mayonnaise Ponzu.", new Long(8), 11.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Dragon Roll", "Dessus : Avocat, Oeufs De Saumon, Sauce Mayonnaise Teriyaki, Piment. À L’Intérieur : Ceviche Daurade.", new Long(8), 11.90, categorie1, "")).hashCode() != 0);
        
        categorie1  = catalogue.findCategorieByDesignation("Spring Rolls");
        assertTrue(catalogue.saveObject(new Produit("Spring Rolls Saumon Avocat", "Le Spring rolls Saumon est une vraie bouchée de plaisir avec la menthe et la coriandre accentuant le goût du saumon et de l'avocat.", new Long(6), 4.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Spring Rolls Avocat cheese", "Succombez à l'onctuosité du Spring rolls Avocat Cheese et faites de votre dégustation un moment de plaisir.", new Long(6), 4.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Spring Rolls Fried chicken Avocat", "Laissez vous séduire par l'onctuosité de l'avocat et le croustillant du poulet frit avec le Spring rolls Fried Chicken Avocat.", new Long(6), 6.50, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Spring Rolls Concombre cheese", "Alliant la fraîcheur du cheese et le croquant du concombre, le Spring rolls Concombre Cheese ravira vos papilles.", new Long(6), 6.50, categorie1, "")).hashCode() != 0);
    }
    
}
