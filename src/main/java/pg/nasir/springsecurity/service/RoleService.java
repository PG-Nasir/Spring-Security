package pg.nasir.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.nasir.springsecurity.model.Role;
import pg.nasir.springsecurity.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("roleService")
public class RoleService {

	
	@Autowired
    private RoleRepository roleRepository;
	
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> findAll(){
		List<Role> roles = new ArrayList<>();
		roles = roleRepository.findAll();
		return roles;
	}
	
	public Optional<Role> findRole(int id){
		return roleRepository.findById(id);
	}



	public void save(Role role){
		roleRepository.save(role);
	}
	
	public void delete(int id){
		roleRepository.deleteById(id);

	}

	public Role findRole(String role) {
		// TODO Auto-generated method stub
		return roleRepository.findByRole(role);
	}

}
