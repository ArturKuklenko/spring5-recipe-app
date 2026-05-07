package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jt on 7/25/18.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Value("classpath:images/Guacamole.webp")
    private Resource guacamoleImageResource;

    @Value("classpath:images/Simply-Recipes-Chicken-Tacos.webp")
    private Resource chickenTacosImageResource;

    private Category american;
    private Category italian;
    private Category mexican;
    private Category fastFood;

    private UnitOfMeasure teaspoon;
    private UnitOfMeasure tablespoon;
    private UnitOfMeasure cup;
    private UnitOfMeasure pinch;
    private UnitOfMeasure ounce;
    private UnitOfMeasure pound;
    private UnitOfMeasure piece;
    private UnitOfMeasure slice;

    private Byte[] guacamoleBytes;
    private Byte[] chickenTacosBytes;

    public DataLoader(CategoryRepository categoryRepository,
                      RecipeRepository recipeRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean imagesBytesGot = getImagesBytes();
        boolean categoriesGot = getCategories();
        boolean unitsOfMeasureGot = getUnitsOfMeasure();
        if(!imagesBytesGot){
            System.out.println("Didn't got image bytes.");
            return;
        }
        if(!categoriesGot){
            System.out.println("Didn't got categories.");
            return;
        }
        if(!unitsOfMeasureGot){
            System.out.println("Didn't got units of measure.");
            return;
        }

        loadGuacamoleRecipe();
        loadEasyGroundChickenTacosRecipe();
    }

    private boolean getImagesBytes() {
        try {
            InputStream guacamoleInputStream = guacamoleImageResource.getInputStream();
            InputStream chickenTacosInputStream = chickenTacosImageResource.getInputStream();

            byte[] guacamoleBytes0 = IOUtils.toByteArray(guacamoleInputStream);
            byte[] chickenTacosBytes0 = IOUtils.toByteArray(chickenTacosInputStream);

            guacamoleBytes = ArrayUtils.toObject(guacamoleBytes0);
            chickenTacosBytes = ArrayUtils.toObject(chickenTacosBytes0);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean getCategories() {
        Optional<Category> americanOptional = categoryRepository.findByDescription("American");
        Optional<Category> italianOptional = categoryRepository.findByDescription("Italian");
        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
        Optional<Category> fastFoodOptional = categoryRepository.findByDescription("Fast Food");

        american = americanOptional.get();
        italian = italianOptional.get();
        mexican = mexicanOptional.get();
        fastFood = fastFoodOptional.get();

        if(mexican == null){
            return false;
        }

        return true;
    }

    private boolean getUnitsOfMeasure() {
        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> poundOptional = unitOfMeasureRepository.findByDescription("Pound");
        Optional<UnitOfMeasure> pieceOptional = unitOfMeasureRepository.findByDescription("Piece");
        Optional<UnitOfMeasure> sliceOptional = unitOfMeasureRepository.findByDescription("Slice");

        teaspoon = teaspoonOptional.get();
        tablespoon = tablespoonOptional.get();
        cup = cupOptional.get();
        pinch = pinchOptional.get();
        ounce = ounceOptional.get();
        pound = poundOptional.get();
        piece = pieceOptional.get();
        slice = sliceOptional.get();

        if(pound == null || slice == null){
            return false;
        }
        return true;
    }

    public void loadGuacamoleRecipe() {
        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        Ingredient ripeAvocadosIngredient0 = new Ingredient();
        ripeAvocadosIngredient0.setDescription("Ripe avocados");
        ripeAvocadosIngredient0.setAmount(new BigDecimal(2));
        ripeAvocadosIngredient0.setUom(piece);

        Ingredient kosherSaltIngredient0 = new Ingredient();
        kosherSaltIngredient0.setDescription("Kosher salt");
        kosherSaltIngredient0.setAmount(new BigDecimal(0.25F));
        kosherSaltIngredient0.setUom(teaspoon);

        Ingredient freshLimeIngredient0 = new Ingredient();
        freshLimeIngredient0.setDescription("Fresh lime");
        freshLimeIngredient0.setAmount(new BigDecimal(1));
        freshLimeIngredient0.setUom(tablespoon);

        Ingredient mincedRedOnionIngredient0 = new Ingredient();
        mincedRedOnionIngredient0.setDescription("Minced red onion");
        mincedRedOnionIngredient0.setAmount(new BigDecimal(2));
        mincedRedOnionIngredient0.setUom(tablespoon);

        Ingredient serranoIngredient0 = new Ingredient();
        serranoIngredient0.setDescription("Serrano (or jalapeño) chilis, stems and seeds removed, minced");
        serranoIngredient0.setAmount(new BigDecimal(2));
        serranoIngredient0.setUom(piece);

        Ingredient cilantroIngredient0 = new Ingredient();
        cilantroIngredient0.setDescription("Cilantro (leaves and tender stems), finely chopped");
        cilantroIngredient0.setAmount(new BigDecimal(2));
        cilantroIngredient0.setUom(tablespoon);

        Ingredient pepperIngredient0 = new Ingredient();
        pepperIngredient0.setDescription("Freshly ground black pepper");
        pepperIngredient0.setAmount(new BigDecimal(2));
        pepperIngredient0.setUom(pinch);

        Ingredient tomatoIngredient0 = new Ingredient();
        tomatoIngredient0.setDescription("Ripe tomato, chopped (optional)");
        tomatoIngredient0.setAmount(new BigDecimal(0.5));
        tomatoIngredient0.setUom(piece);

        Ingredient redRadishIngredient0 = new Ingredient();
        redRadishIngredient0.setDescription("Red radish or jicama");
        redRadishIngredient0.setAmount(new BigDecimal(1));
        redRadishIngredient0.setUom(piece);

        Ingredient tortillaChipsIngredient0 = new Ingredient();
        tortillaChipsIngredient0.setDescription("Tortilla chips");
        tortillaChipsIngredient0.setAmount(new BigDecimal(1));
        tortillaChipsIngredient0.setUom(piece);

        guacamoleIngredients.add(ripeAvocadosIngredient0);
        guacamoleIngredients.add(kosherSaltIngredient0);
        guacamoleIngredients.add(freshLimeIngredient0);
        guacamoleIngredients.add(mincedRedOnionIngredient0);
        guacamoleIngredients.add(serranoIngredient0);
        guacamoleIngredients.add(cilantroIngredient0);
        guacamoleIngredients.add(pepperIngredient0);
        guacamoleIngredients.add(tomatoIngredient0);
        guacamoleIngredients.add(redRadishIngredient0);
        guacamoleIngredients.add(tortillaChipsIngredient0);

        Recipe guacamole = new Recipe();
        guacamole.setDescription("The Best Guacamole");
        guacamole.setPrepTime(600);
        guacamole.setCookTime(600);
        guacamole.setServings(2);
        //guacamole.setSource();
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        //guacamole.setDirections();

        guacamole.setIngredients(guacamoleIngredients);
        guacamole.setImage(guacamoleBytes);
        //guacamole.setCategories();

        recipeRepository.save(guacamole);
        System.out.println("The Best Guacamole recipe loaded.");
    }

    public void loadEasyGroundChickenTacosRecipe() {
        Set<Ingredient> easyGroundChickenTacosIngredients = new HashSet<>();
        Ingredient neutralOilIngredient0 = new Ingredient();
        neutralOilIngredient0.setDescription("Neutral oil");
        neutralOilIngredient0.setAmount(new BigDecimal(1));
        neutralOilIngredient0.setUom(tablespoon);

        Ingredient groundChickenIngredient0 = new Ingredient();
        groundChickenIngredient0.setDescription("Ground chicken");
        groundChickenIngredient0.setAmount(new BigDecimal(1));
        groundChickenIngredient0.setUom(pound);

        Ingredient tacoSeasoningIngredient0 = new Ingredient();
        tacoSeasoningIngredient0.setDescription("Taco seasoning");
        tacoSeasoningIngredient0.setAmount(new BigDecimal(2));
        tacoSeasoningIngredient0.setUom(tablespoon);

        Ingredient blackBeansIngredient0 = new Ingredient();
        blackBeansIngredient0.setDescription("Black beans");
        blackBeansIngredient0.setAmount(new BigDecimal(15));
        blackBeansIngredient0.setUom(ounce);

        Ingredient dicedTomatoesIngredient0 = new Ingredient();
        dicedTomatoesIngredient0.setDescription("Diced tomatoes");
        dicedTomatoesIngredient0.setAmount(new BigDecimal(14.5F));
        dicedTomatoesIngredient0.setUom(ounce);

        Ingredient limeJuiceIngredient0 = new Ingredient();
        limeJuiceIngredient0.setDescription("Lime juice");
        limeJuiceIngredient0.setAmount(new BigDecimal(2));
        limeJuiceIngredient0.setUom(tablespoon);

        Ingredient tortillasIngredient0 = new Ingredient();
        tortillasIngredient0.setDescription("Tortillas");
        tortillasIngredient0.setAmount(new BigDecimal(2));
        tortillasIngredient0.setUom(piece);

        easyGroundChickenTacosIngredients.add(neutralOilIngredient0);
        easyGroundChickenTacosIngredients.add(groundChickenIngredient0);
        easyGroundChickenTacosIngredients.add(tacoSeasoningIngredient0);
        easyGroundChickenTacosIngredients.add(blackBeansIngredient0);
        easyGroundChickenTacosIngredients.add(dicedTomatoesIngredient0);
        easyGroundChickenTacosIngredients.add(limeJuiceIngredient0);
        easyGroundChickenTacosIngredients.add(tortillasIngredient0);

        Recipe easyGroundChickenTacos = new Recipe();
        easyGroundChickenTacos.setDescription("Easy Ground Chicken Tacos");
        easyGroundChickenTacos.setPrepTime(300);
        easyGroundChickenTacos.setCookTime(900);
        easyGroundChickenTacos.setServings(4);
        //easyGroundChickenTacos.setSource();
        easyGroundChickenTacos.setUrl("https://www.simplyrecipes.com/ground-chicken-taco-recipe-11912516");
        //easyGroundChickenTacos.setDirections();

        easyGroundChickenTacos.setIngredients(easyGroundChickenTacosIngredients);
        easyGroundChickenTacos.setImage(chickenTacosBytes);

        easyGroundChickenTacosIngredients.forEach(i->i.setRecipe(easyGroundChickenTacos));

        recipeRepository.save(easyGroundChickenTacos);

        System.out.println("Easy ground chicken tacos recipe loaded.");
    }

}
