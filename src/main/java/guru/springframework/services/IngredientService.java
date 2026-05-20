package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Recipe;

/**
 * Created by jt on 6/27/17.
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    Recipe removeIngredientFromRecipe(Recipe recipe, Long ingredientId);
}
