package ngo.best.server.controller;

import ngo.best.server.model.dto.CategoryDTO;
import ngo.best.server.model.dto.RoleDTO;
import ngo.best.server.model.entity.Category;
import ngo.best.server.model.entity.Role;
import ngo.best.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Ioana
 */

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<Role> saveRole(@RequestBody RoleDTO roleDTO) {
        Role savedRole = roleService.save(roleDTO);

        if (savedRole == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedRole.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(savedRole);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<Object> deleteRole(@PathVariable("id") Long roleId) {
        roleService.delete(roleId);
        return ResponseEntity.noContent().build();
    }
}
