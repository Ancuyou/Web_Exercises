package vn.iotstar.services;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.CategoryDaoImpl;
import vn.iotstar.models.CategoryDTO;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao cateDao = new CategoryDaoImpl();

    @Override
    public void insert(CategoryDTO categoryDTO) {
        cateDao.create(categoryDTO); // Giờ DAO nhận thẳng DTO
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        cateDao.update(categoryDTO);
    }

    @Override
    public void delete(int id) {
        cateDao.delete(id);
    }

    @Override
    public CategoryDTO findById(int id) {
        return cateDao.findById(id);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return cateDao.findAll();
    }

    @Override
    public List<CategoryDTO> findByUserId(int userId) {
        return cateDao.findByUserId(userId);
    }
}
