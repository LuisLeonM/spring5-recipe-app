package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    IndexController indexController;

    @Mock
    Model model;

    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        String viewName = indexController.getIndexPage(model);

        //se verifica que regrese el template correcto
        assertEquals(viewName, "index");

        //se verifica que se llame la funcion getRecipes de la interface
        verify(recipeService, times(1)).getRecipes();

        //se verifica que exista la variable recipes en el modelo
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

    }
}