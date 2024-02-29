package com.globant.project.foodAplication.seeds;

import com.globant.project.foodAplication.model.category.Category;
import com.globant.project.foodAplication.repository.categorie.ICategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Seeders implements ApplicationRunner {

    @Autowired
    private ICategorieRepository categorieRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
            Category category = new Category();
            category.setLabel("HAMBURGERS_AND_HOTDOGS ");
            category.setDescription("Delicious combos of burgers and hotdogs.");

            category.setLabel("CHICKEN ");
            category.setDescription("Fried chicken, wing combos, chicken tenders, and more.");

            category.setLabel("FISH ");
            category.setDescription("Fish croquettes, tilapia fillet, and other fish dishes.");

            category.setLabel("MEATS ");
            category.setDescription("Grilled meats, juicy chops, and more.");

            category.setLabel("DESSERTS ");
            category.setDescription("Cheesecake with ice cream, Lemon Pie, Cinnamon roll combo.");

            category.setLabel("VEGAN_FOOD ");
            category.setDescription("Tasty combos with vegan ingredients.");

            category.setLabel("KIDS_MEALS");
            category.setDescription("Combo meals specially designed for kids.");

            categorieRepository.save(category);
    }
}
