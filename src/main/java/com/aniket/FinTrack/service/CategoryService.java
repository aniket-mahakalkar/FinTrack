package com.aniket.FinTrack.service;


import com.aniket.FinTrack.dto.CategoryDTO;
import com.aniket.FinTrack.entity.CategoryEntity;
import com.aniket.FinTrack.entity.ProfileEntity;
import com.aniket.FinTrack.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {


    private  final ProfileService profileService;
    private  final CategoryRepository categoryRepository;


    // helper methods

    private CategoryEntity toEntity(CategoryDTO categoryDTO, ProfileEntity profile) {

        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .icon(categoryDTO.getIcon())
                .profile(profile)
                .type(categoryDTO.getType())
                .build();


    }


    private CategoryDTO toDTO(CategoryEntity categoryEntity){

        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .profileId(categoryEntity.getProfile() != null ? categoryEntity.getProfile().getId(): null)
                .name(categoryEntity.getName())
                .icon(categoryEntity.getIcon())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .type(categoryEntity.getType())
                .build();
    }


    public CategoryDTO saveCategory(CategoryDTO categoryDTO){

        ProfileEntity profile = profileService.getCurrentProfile();

        if (categoryRepository.existsByNameAndProfileId(categoryDTO.getName(), profile.getId())){

//            throw  new ResponseStatusException(HttpStatus.CONFLICT,"Category with this name already exists");

            throw new RuntimeException("Category with this name already exists");
        }

        CategoryEntity newcategoryEntity = toEntity(categoryDTO,profile);

        categoryRepository.save(newcategoryEntity);

        return toDTO(newcategoryEntity);


    }


    public List<CategoryDTO> getCategoriesForCurrentProfile() {

        ProfileEntity profile = profileService.getCurrentProfile();
        List<CategoryEntity> categories = categoryRepository.findByProfileId(profile.getId());

        return categories.stream().map(this::toDTO).toList();


    }

    public List<CategoryDTO> getCategoriesByTypeForCurrentProfile(String type){

        ProfileEntity profile = profileService.getCurrentProfile();
        List<CategoryEntity> categories = categoryRepository.findByTypeAndProfileId(type,profile.getId());

        return categories.stream().map(this::toDTO).toList();
    }


    public  CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO){

        ProfileEntity profile = profileService.getCurrentProfile();

        CategoryEntity existingCategory = categoryRepository.findByIdAndProfileId(categoryId,profile.getId())
                .orElseThrow( () -> new RuntimeException("Category not found with id"));

        existingCategory.setName(categoryDTO.getName());
        existingCategory.setIcon(categoryDTO.getIcon());

        categoryRepository.save(existingCategory);

        return toDTO(existingCategory);
    }
}
