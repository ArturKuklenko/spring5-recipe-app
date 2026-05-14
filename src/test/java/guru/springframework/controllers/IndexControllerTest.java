package guru.springframework.controllers;

import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testGetIndexPage() throws Exception {
        String indexPage = indexController.getIndexPage(model);
        assertEquals("index", indexPage);
        verify(model, times(1)).addAttribute(eq("recipes"), anyObject());
        verify(recipeService, times(1)).getRecipes();
    }
}