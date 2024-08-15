package sweet.management;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.management.entities.Recipe;
import sweet.management.entities.User;
import sweet.management.services.DatabaseService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RecipeManagementStepDefinition {
    UserAuthService userAuthService;
    User loggedInUser;
    Boolean added;
    Recipe recipeToAdd;
    List <Recipe> recipesList;
    boolean found;
    boolean deleted;
    boolean updated;
    Recipe testRecipe;

    public RecipeManagementStepDefinition() throws SQLException {
        userAuthService = new UserAuthService();
        userAuthService.login("feedbacktest@gmail.com","321", DatabaseService.getConnection(true));
        loggedInUser = userAuthService.getLoggedInUser();
        try {
            Recipe.resetIdCounter(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Given("that the user is logged in and user is Store Owner or Beneficiary User")
    public void thatTheUserIsLoggedInAndUserIsStoreOwnerOrBeneficiaryUser() {
       assertTrue(loggedInUser.isBeneficiaryUser() || loggedInUser.isStoreOwner());
    }

    @When("The user adds recipe with name {string}, ingredients {string}, instruction {string} and allergies {string}")
    public void theUserAddsRecipeWithNameIngredientsInstructionAndAllergies(String name, String ingredients, String instructions, String allergies) {
        recipeToAdd= new Recipe(loggedInUser.getEmail(),name,ingredients,instructions,allergies);
        try {
           added =  Recipe.createRecipe(recipeToAdd,DatabaseService.getConnection(true));
           Recipe.deleteRecipe(Recipe.nextId(DatabaseService.getConnection(true))-1,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("The recipe is added successfully")
    public void theRecipeIsAddedSuccessfully() {
        assertTrue(added);
        assertNotNull(recipeToAdd);
        System.out.println("Recipe id: " + recipeToAdd.getRecipeId());
        System.out.println("Recipe name: " + recipeToAdd.getRecipeName());
        System.out.println("Recipe ingredients: " + recipeToAdd.getIngredients());
        System.out.println("Recipe instructions: " + recipeToAdd.getInstructions());
        System.out.println("Recipe allergies: " + recipeToAdd.getAllergies());
        System.out.println("Created at: " + recipeToAdd.getCreatedAt());

    }


    @When("The user searches for product with name {string}")
    public void theUserSearchesForProductWithName(String name) {
        try {
            recipesList = Recipe.searchRecipesByName(name,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Then("the recipe should appear")
    public void theRecipeShouldAppear() {
        found = !recipesList.isEmpty();
        printFoundRecipes();
        assertTrue(found);
    }

    @Then("A message should appear to notify the user")
    public void aMessageShouldAppearToNotifyTheUser() {
        found = !recipesList.isEmpty();
        assertFalse(found);
    }

    @When("the user filters the results to exclude recipes with {string} food allergy")
    public void theUserFiltersTheResultsToExcludeRecipesWithFoodAllergy(String allergy) {
        try {
            recipesList = Recipe.getRecipesWithoutAllergies(allergy,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Then("matching recipes should not be shown")
    public void matchingRecipesShouldNotBeShown() {
        found = !recipesList.isEmpty();
        printFoundRecipes();
        assertTrue(found);
        System.out.println("matching recipes should not be shown");
    }

    @When("The user searches for recipes he shared")
    public void theUserSearchesForRecipesHeShared() {
        try {
            recipesList = Recipe.getRecipesByUserEmail(loggedInUser.getEmail(),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Then("The recipes should appear")
    public void theRecipesShouldAppear() {
        found = !recipesList.isEmpty();
        printFoundRecipes();
        assertTrue(found);
        System.out.println("The recipes should appear");
    }

    public void printFoundRecipes(){
        if(found){
            for (Recipe recipe : recipesList) {
                System.out.println(recipe.getRecipeName());
                System.out.println(recipe.getUserEmail());
            }
        }
    }

    @Given("That the admin is logged in")
    public void thatTheAdminIsLoggedIn() throws SQLException {
      assertTrue(adminLogin().isAdmin());
    }

    @When("The admin Deletes recipe with ID {string}")
    public void theAdminDeletesRecipeWithID(String id) {
        if (id.equals("7")) {
        addTestRecipe(Integer.parseInt(id));
        }
        try {
            deleted = Recipe.deleteRecipe(Integer.parseInt(id),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Then("The recipe will be deleted")
    public void theRecipeWillBeDeleted() {
        assertTrue(deleted);
    }

    @When("the admin updates recipe with ID {string}")
    public void theAdminUpdatesRecipeWithID(String id) {
        try {
            testRecipe = Recipe.getRecipeById(Integer.parseInt(id),DatabaseService.getConnection(true));
        } catch (SQLException e) {
            fail();
        }

    }
    @When("sets the name to {string}")
    public void setsTheNameTo(String name) {
        testRecipe.setRecipeName(name);
        System.out.println(testRecipe.getRecipeId());
        updated = updateRecipe(Recipe.UPDATE_RECIPE_NAME);
    }
    @When("sets the ingredients to {string}")
    public void setsTheIngredientsTo(String ingredients) {
        testRecipe.setIngredients(ingredients);
        updated=  updateRecipe(Recipe.UPDATE_INGREDIENTS);
    }
    @When("sets the instructions to {string}")
    public void setsTheInstructionsTo(String instructions) {
        testRecipe.setInstructions(instructions);
        updated = updateRecipe(Recipe.UPDATE_INSTRUCTIONS);
    }
    @When("sets the allergies to {string}")
    public void setsTheAllergiesTo(String allergies) {
        testRecipe.setAllergies(allergies);
        updated = updateRecipe(Recipe.UPDATE_ALLERGIES);
    }
    @Then("the recipe will be updated")
    public void theRecipeWillBeUpdated() {
       assertTrue(updated);
       updated = false;
    }


    @Then("The admin will be notified that product doesn't exist")
    public void theAdminWillBeNotifiedThatProductDoesnTExist() {
        assertFalse(deleted);
    }




    public boolean updateRecipe(int updateType) {
        try {
            return Recipe.updateRecipe(testRecipe,DatabaseService.getConnection(true),updateType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User adminLogin() throws SQLException{
        UserAuthService adminAuthService = new UserAuthService();
        adminAuthService.login("admin@gmail.com","123", DatabaseService.getConnection(true));
        return adminAuthService.getLoggedInUser();

    }

    public void addTestRecipe(int id){
        Recipe tempRecipe = new Recipe(id,"feedbacktest@gmail.com","test","test","test",null,"test");
        try {
            Recipe.createRecipe(tempRecipe,DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @When("The user tries to get all recipes")
    public void theUserTriesToGetAllRecipes() {
        try {
            recipesList = Recipe.getAllRecipes(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            fail("Could not get all recipes");
        }
    }

    @Then("The recipes should be shown successfully")
    public void theRecipesShouldBeShownSuccessfully() {
        assertNotNull(recipesList);
    }
}
