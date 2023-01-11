package pg.nasir.springsecurity.model;

/**
 * Created by Yasin Mert on 25.02.2017.
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="role")
	private String role;

	public Role() { }

	public Role(String role) {
		this.role = role;
	}

	public Role(Integer id, String role) {
		this.id = id;
		this.role = role;
	}

	public Role(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return this.role;
	}

}
