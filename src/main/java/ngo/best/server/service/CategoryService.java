package ngo.best.server.service;

import ngo.best.server.model.dto.CategoryDTO;
import ngo.best.server.model.entity.Category;
import ngo.best.server.repository.CategoryRepository;
import ngo.best.server.utils.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ioana
 */

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() { return categoryRepository.findAll(); }

    public Category save(CategoryDTO categoryDTO) {
        Category category = DTOConverter.convertCategoryDTOToCategory(categoryDTO);
        return categoryRepository.save(category);
    }

    public Category delete(Long categoryId) {
        final Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()) {
            categoryRepository.delete(category.get());
            return category.get();
        }
        return null;
    }
}
