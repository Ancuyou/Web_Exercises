package vn.iotstar.utils;

import vn.iotstar.entities.Category;
import vn.iotstar.entities.User;
import vn.iotstar.models.CategoryDTO;

public class CategoryMapper {
    // Entity -> DTO
    public static CategoryDTO toDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setCategoryName(entity.getCategoryName());
        dto.setImages(entity.getImages());

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }
        return dto;
    }

    // DTO -> Entity
    public static Category toEntity(CategoryDTO dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setCategoryName(dto.getCategoryName());
        entity.setImages(dto.getImages());
        // Tung bi loi do khong set userID trong khi entity yeu cau khong duoc nullable
        return entity;
    }
}
