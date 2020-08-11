package ngo.best.server.controller;

import ngo.best.server.model.dto.CategoryDTO;
import ngo.best.server.model.entity.Category;
import ngo.best.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<Category> saveCategory(@RequestBody CategoryDTO categoryDTO) {

        Category savedCategory = categoryService.save(categoryDTO);

        if (savedCategory == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedCategory.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(savedCategory);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGEMENT')")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long categoryId) {

        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

}
