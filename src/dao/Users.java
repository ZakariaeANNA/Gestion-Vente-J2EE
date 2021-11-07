package dao;
// Generated Jun 22, 2021 1:56:43 PM by Hibernate Tools 5.1.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "users", catalog = "g_vente")
public class Users implements java.io.Serializable {

	private Integer codeUser;
	private String login;
	private String pass;

	public Users() {
	}

	public Users(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codeUser", unique = true, nullable = false)
	public Integer getCodeUser() {
		return this.codeUser;
	}

	public void setCodeUser(Integer codeUser) {
		this.codeUser = codeUser;
	}

	@Column(name = "login", nullable = false, length = 20)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "pass", nullable = false, length = 20)
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
