package fr.grenoble.polytech.ricm.entity.utilisateur;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@NamedQueries( {
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT a FROM Utilisateur a" ),
    @NamedQuery(name = "Utilisateur.findById", query = "SELECT a FROM Utilisateur a WHERE a.id = :id" ),
    @NamedQuery(name = "Utilisateur.findByNomComplet", query = "SELECT a FROM Utilisateur a WHERE a.nomComplet = :NomComplet" ),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT a FROM Utilisateur a WHERE a.login = :Login")
})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = -1447051578332788777L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(nullable=false)
    private Long id;
        
    @Column(unique = true, nullable=false, length=17)
    private String login;
        
    @Column
    private String password;
    
    @Column(unique = true)
    private String nomComplet;
        
    @Column(unique = true)
    private String email;
    
    @ManyToOne
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(String login, String password, String nomComplet, String email, Role role) {
        this.login = login;
        this.password = password;
        this.nomComplet = nomComplet;
        this.email = email;
        this.role = role;
    }

    public Utilisateur(Long id, String login, String password, String nomComplet, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nomComplet = nomComplet;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }        

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        if ( !( object instanceof Utilisateur ) ) {
            return false;
        }
        Utilisateur other = ( Utilisateur ) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur[ id=" + id + " ] " + login;
    }
}
