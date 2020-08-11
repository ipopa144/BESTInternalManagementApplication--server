package ngo.best.server.service;

import ngo.best.server.model.dto.CategoryDTO;
import ngo.best.server.model.dto.RoleDTO;
import ngo.best.server.model.entity.Category;
import ngo.best.server.model.entity.Role;
import ngo.best.server.repository.CategoryRepository;
import ngo.best.server.repository.RoleRepository;
import ngo.best.server.utils.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ioana
 */

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() { return roleRepository.findAll(); }

    public Role save(RoleDTO roleDTO) {
        Role role = DTOConverter.convertRoleDTOToRole(roleDTO);
        return roleRepository.save(role);
    }

    public Role delete(Long roleId) {
        final Optional<Role> role = roleRepository.findById(roleId);
        if(role.isPresent()) {
            roleRepository.delete(role.get());
            return role.get();
        }
        return null;
    }
}
