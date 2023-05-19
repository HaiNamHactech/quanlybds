package com.bkap.convert;

import com.bkap.dto.CategoryDto;
import com.bkap.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConvert {

    @Autowired
    private ModelMapper modelMapper;
    public CategoryDto convertToDto(Category categoryEntity) {
        CategoryDto categoryDto = modelMapper.map(categoryEntity,CategoryDto.class);
        if(categoryEntity.getRooms() != null){
            categoryDto.setTotalRoom(categoryEntity.getRooms().size());
        }
        return categoryDto;
    }

    public Category convertToEntity(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        return category;
    }

    public Category convertToEntity(CategoryDto categoryDto, Category categoryOld ) {
        Category categoryNew = modelMapper.map(categoryDto,Category.class);
        categoryOld.setName(categoryNew.getName());
        categoryOld.setDescription(categoryNew.getDescription());
        categoryOld.setSlug(categoryNew.getSlug());
        categoryOld.setTitle(categoryNew.getTitle());
        categoryOld.setStatus(categoryNew.getStatus());
        categoryOld.setParentId(categoryNew.getParentId());
        categoryOld.setType(categoryNew.getType());
        categoryOld.setShowMenu(categoryNew.getShowMenu());
        categoryOld.setShowInAdmin(categoryNew.getShowInAdmin());
        return categoryOld;
    }
}
