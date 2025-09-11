package vn.iotstar.services;

import vn.iotstar.models.CategoryDTO;
import java.util.List;

public interface CategoryService {
    void insert(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    void delete(int id);
    CategoryDTO findById(int id);
    List<CategoryDTO> findAll();
    List<CategoryDTO> findByUserId(int userId);
}
