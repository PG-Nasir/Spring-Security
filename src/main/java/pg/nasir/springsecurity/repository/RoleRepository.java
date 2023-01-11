package pg.nasir.springsecurity.repository;

/**
 * Created by Yasin Mert on 25.02.2017.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pg.nasir.springsecurity.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
  @Query("SELECT r FROM Role r WHERE r.role = ?1")
  Role findByRole(String role);
  void deleteById(int id);
}
