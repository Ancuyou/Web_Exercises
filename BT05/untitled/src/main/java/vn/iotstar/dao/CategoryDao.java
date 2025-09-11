package vn.iotstar.dao;

import vn.iotstar.models.CategoryDTO;
import java.util.List;

public interface CategoryDao {
    void create(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    void delete(int id);
    CategoryDTO findById(int id);
    List<CategoryDTO> findByUserId(int userId);
    List<CategoryDTO> findAll();
}
