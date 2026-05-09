package guru.springframework.service;

import guru.springframework.domain.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> getList();
}
