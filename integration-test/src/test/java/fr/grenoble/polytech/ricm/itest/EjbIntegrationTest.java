package fr.grenoble.polytech.ricm.itest;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.grenoble.polytech.ricm.entity.catalogue.Categorie;
import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.entity.utilisateur.Role;
import fr.grenoble.polytech.ricm.entity.utilisateur.Utilisateur;
import fr.grenoble.polytech.ricm.iface.ICatalogueEjbRemote;

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
        //env.put(Context.SECURITY_PRINCIPAL, "emfotsing"); 
        //env.put(Context.SECURITY_CREDENTIALS, "JordanDavid03");
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

   /* @Test
    public void testCreationCategorie() throws Exception {
        ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME);  	         

        assertTrue(catalogue.saveObject(new Categorie("contents1")).hashCode() != 0);
        
	Categorie categorie1  = catalogue.findCategorieByDesignation("contents1");
        assertEquals("contents1", categorie1.getDesignation());
        
    }*/

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
        
        categorie1  = catalogue.findCategorieByDesignation("California Rolls");
        assertTrue(catalogue.saveObject(new Produit("California Saumon avocat", "L'onctuosité de l'avocat associé au saumon fait du california rolls Saumon Avocat un met savoureux qui ravira vos papilles.", new Long(6), 4.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("California Chicken caesar", "Le California rolls Chicken Caesar est un réel plaisir tant pour vos yeux que pour vos papilles. Alliant le croustillant et la douceur du cœur de laitue, laissez-vous séduire par le Chicken Caesar.", new Long(6), 6.50, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("California Pacific", "Le California rolls Pacific est l'alliance parfaite entre fondant et piquant, son délicieux tartare de saumon parfaitement relevé à la sauce spicy réveillera vos papilles.", new Long(6), 5.50, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("California Thon cuit avocat", "Le california rolls Thon Cuit Avocat est une alternative au california rolls Thon Avocat, tout autant savoureux mais plus onctueux.", new Long(6), 4.90, categorie1, "")).hashCode() != 0);
        
        categorie1  = catalogue.findCategorieByDesignation("Maki");
        assertTrue(catalogue.saveObject(new Produit("Maki saumon", "Le Maki Saumon est à l'image du concept, simple, frais et de qualité. Succombez à cette bouchée de fraîcheur.", new Long(6), 4.50, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Maki Cheese avocat", "Alliant le fondant du cheese frais à celui de l'avocat, le Maki Cheese Avocat, ravira vos papilles.", new Long(6), 3.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Maki Cheese concombre", "Alliant le fondant du cheese frais au croquant des lamelles de concombre, le Maki Cheese Concombre rafraîchira vos papilles.", new Long(6), 3.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Maki thon", "Le Maki Thon est à l'image du concept, simple, frais et de qualité. Succombez à cette bouchée de fraîcheur.", new Long(6), 4.90, categorie1, "")).hashCode() != 0);
        
        categorie1  = catalogue.findCategorieByDesignation("Temaki");
        assertTrue(catalogue.saveObject(new Produit("Temaki saumon avocat", "Le Temaki Saumon Avocat allie la fraîcheur du saumon à l'onctuosité de l'avocat, cette association ravira vos papilles.", new Long(1), 4.50, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Temaki saumon spicy", "Le Temaki Saumon Spicy allie la fraîcheur du saumon au croquant du concombre et électrisera vos papilles grâce à sa sauce épicée.", new Long(1), 4.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Temaki thon avocat", "Le Temaki Thon Avocat allie la fraîcheur du thon à l'onctuosité de l'avocat, cette association ravira vos papilles.", new Long(1), 4.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Temaki California", "Le Temaki California allie la chair de crabe à l'avocat et fait de ce Temaki le plus onctueux avec sa mayonnaise, pour votre plus grand plaisir.", new Long(1), 4.90, categorie1, "")).hashCode() != 0);
        
        categorie1  = catalogue.findCategorieByDesignation("Desserts");
        assertTrue(catalogue.saveObject(new Produit("Mangue Fraîche", "Terminez votre dégustation de sushi et maki par une note de fraîcheur avec la salade de mangue fraîche. (selon saison)", new Long(1), 4.50, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Mochi Coco", "Coeur glacé enrobé d'une pâte à base de Lait de Coco", new Long(2), 3.90, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Cheesecake", "Laissez-vous tenter par un délicieux cheesecake pour terminer votre dégustation de sushi et maki sur une note sucrée.", new Long(1), 3.50, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Moelleux au chocolat", "Laissez-vous tenter par un délicieux mi-cuit chocolat caramel pour terminer votre dégustation de sushi et maki sur une note sucrée. (à réchauffer avant dégustation)", new Long(1), 3.90, categorie1, "")).hashCode() != 0);

        categorie1  = catalogue.findCategorieByDesignation("Boissons");
        assertTrue(catalogue.saveObject(new Produit("Evian 50cl", "", new Long(1), 2.20, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Badoit 50cl", "", new Long(2), 2.20, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Coca Cola 33cl", "", new Long(1), 2.20, categorie1, "")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Produit("Coca Cola 33cl light", "", new Long(1), 2.20, categorie1, "")).hashCode() != 0);
    }   
    
    
    @Test
    public void testInitRoles() throws Exception {
        ICatalogueEjbRemote catalogue = ( ICatalogueEjbRemote ) ctx.lookup( ICatalogueEjbRemote.JNDI_NAME);
        assertTrue(catalogue.saveObject(new Role("Client")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Role("Manager")).hashCode() != 0);
        assertTrue(catalogue.saveObject(new Role("Admin")).hashCode() != 0);
        
        Role role  = (Role) catalogue.findEntityByLabel("Role","Libelle", "Admin");        
        assertTrue(catalogue.saveObject(new Utilisateur("emfotsing","e50ef0b1d5de59fe7c5a186ead3d226288a9ea12639e30d1d81b554d5b22271d","Eric Michel FOTSING","eric.michel.fotsing@gmail.com", role)).hashCode() != 0);
    } 
        
}
