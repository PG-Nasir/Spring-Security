package pg.nasir.springsecurity.repository;

/**
 * Created by Yasin Mert on 25.02.2017.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pg.nasir.springsecurity.model.Role;
import pg.nasir.springsecurity.model.User;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
	@Query("SELECT u FROM User u WHERE u.name = ?1")
	User findByName(String name);
	@Query("SELECT r FROM Role r WHERE r.role = ?1")
	List<User> findByRole(Role role);

	void deleteById(int id);

}
