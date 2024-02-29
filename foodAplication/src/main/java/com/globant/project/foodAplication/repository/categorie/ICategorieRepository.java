package com.globant.project.foodAplication.repository.categorie;

import com.globant.project.foodAplication.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategorieRepository extends JpaRepository<Category, Long> {
}
