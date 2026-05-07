package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getList() {
        try {
            List<Recipe> recipeList = (List<Recipe>) this.recipeRepository.findAll();
            return recipeList;
        } catch (Exception e) {

        }
        return new ArrayList<Recipe>();
    }

}
