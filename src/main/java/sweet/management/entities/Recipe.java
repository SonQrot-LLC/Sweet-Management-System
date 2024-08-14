package sweet.management.entities;

import sweet.management.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static sweet.management.services.DatabaseService.getIdValueFromDataBase;

public class Recipe {
    private static final String RECIPE_ID = "recipe_id";
    private static final String USER_EMAIL = "user_email";
    private static final String RECIPE_NAME = "recipe_name";
    private static final String INGREDIENTS_COL = "ingredients";
    private static final String INSTRUCTIONS_COL = "instructions";
    private static final String CREATED_AT = "created_at";
    private static final String ALLERGIES_COL = "allergies";
    private static final String NO_CONNECTION = "No connection";
    private int recipeId;
    private final String userEmail;
    private String recipeName;
    private String ingredients;
    private String instructions;
    private final Timestamp createdAt;
    private String allergies;

    public static final int UPDATE_RECIPE_NAME = 1;
    public static final int UPDATE_INGREDIENTS = 2;
    public static final int UPDATE_INSTRUCTIONS = 3;
    public static final int UPDATE_ALLERGIES = 4;

    // Constructor
    public Recipe(int recipeId, String userEmail, String recipeName, String ingredients, String instructions, Timestamp createdAt, String allergies) {
        this.recipeId = recipeId;
        this.userEmail = userEmail;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.createdAt = createdAt;
        this.allergies = allergies;
    }

    public Recipe(String userEmail, String recipeName, String ingredients, String instructions, String allergies) {
        try {
            this.recipeId = nextId(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DatabaseService.class.getName());
            logger.warning("Something went wrong when trying to connect to database");
        }
        this.userEmail = userEmail;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.createdAt = null;
        this.allergies = allergies;
    }

    // Getters and Setters
    public int getRecipeId() {
        return recipeId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Timestamp getCreatedAt() {return createdAt;}

    // Static Methods for Database Operations
    public static boolean createRecipe(Recipe recipe, Connection conn) throws SQLException {
        String sql = "INSERT INTO recipes (recipe_id, user_email, recipe_name, ingredients, instructions, allergies) VALUES (?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, recipe.getRecipeId());
            stmt.setString(2, recipe.getUserEmail());
            stmt.setString(3, recipe.getRecipeName());
            stmt.setString(4, recipe.getIngredients());
            stmt.setString(5, recipe.getInstructions());
            stmt.setString(6, recipe.getAllergies());
        });
    }

    public static Recipe getRecipeById(int recipeId, Connection conn) throws SQLException {
        String sql = "SELECT *" + " FROM recipes WHERE recipe_id = ?";
        if (conn == null) {
            throw new SQLException();
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Recipe(
                            rs.getInt(RECIPE_ID),
                            rs.getString(USER_EMAIL),
                            rs.getString(RECIPE_NAME),
                            rs.getString(INGREDIENTS_COL),
                            rs.getString(INSTRUCTIONS_COL),
                            rs.getTimestamp(CREATED_AT),
                            rs.getString(ALLERGIES_COL)
                    );
                }
            }
        }
        return null;
    }

    public static boolean updateRecipe(Recipe recipe, Connection conn, int updateType) throws SQLException {
        String sql;
        return switch (updateType) {
            case UPDATE_RECIPE_NAME -> {
                sql = "UPDATE recipes SET recipe_name = ? WHERE recipe_id = ?";
                yield DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getRecipeName());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            }
            case UPDATE_INGREDIENTS -> {
                sql = "UPDATE recipes SET ingredients = ? WHERE recipe_id = ?";
                yield DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getIngredients());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            }
            case UPDATE_INSTRUCTIONS -> {
                sql = "UPDATE recipes SET instructions = ? WHERE recipe_id = ?";
                yield DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getInstructions());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            }
            case UPDATE_ALLERGIES -> {
                sql = "UPDATE recipes SET allergies = ? WHERE recipe_id = ?";
                yield DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getAllergies());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            }
            default -> false;
        };
    }

    public static boolean deleteRecipe(int recipeId, Connection conn) throws SQLException {
        String sql = "DELETE FROM recipes WHERE recipe_id = ?";
        boolean status = DatabaseService.executeUpdate(sql, conn, stmt -> stmt.setInt(1, recipeId));
        Logger logger = Logger.getLogger(DatabaseService.class.getName());
        if (!status) {
            logger.warning("Recipe Not Found!");
        } else {
            logger.warning("Recipe Deleted Successfully!");
        }

        return status;
    }

    public static void resetIdCounter(Connection conn) throws SQLException {
        String sql = "ALTER TABLE recipes AUTO_INCREMENT = 1;";
        DatabaseService.executeUpdate(sql, conn, stmt -> {
        });
    }

    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = 'recipes';";
        return getIdValueFromDataBase(conn, sql);
    }



    public static List<Recipe> getRecipesByUserEmail(String userEmail, Connection conn) throws SQLException {
        String sql = "SELECT " + "* FROM recipes WHERE user_email = ?";
        List<Recipe> recipes = new ArrayList<>();

        if (conn == null) {
            throw new SQLException(NO_CONNECTION);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userEmail);
            getRecipeResultSet(recipes, stmt);
        }
        return recipes;
    }


    public static List<Recipe> searchRecipesByName(String text, Connection conn) throws SQLException {
        String sql = "SELECT" + " * FROM recipes WHERE recipe_name LIKE ?";
        List<Recipe> recipes = getRecipesList(text, conn, sql);
        if (recipes.isEmpty()) {
            Logger logger = Logger.getLogger(DatabaseService.class.getName());
            logger.warning("RECIPES NOT FOUND!");
        }
        return recipes;
    }

    public static List<Recipe> getRecipesWithoutAllergies(String allergy, Connection conn) throws SQLException {
        String sql = "SELECT *" + " FROM recipes WHERE allergies NOT LIKE ?";
        return getRecipesList(allergy, conn, sql);
    }

    private static List<Recipe> getRecipesList(String allergy, Connection conn, String sql) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();

        if (conn == null) {
            throw new SQLException(NO_CONNECTION);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + allergy + "%"); // Using the NOT LIKE pattern to exclude recipes with the specified allergy
            getRecipeResultSet(recipes, stmt);
        }
        return recipes;
    }

    private static void getRecipeResultSet(List<Recipe> recipes, PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Recipe recipe = new Recipe(
                        rs.getInt(RECIPE_ID),
                        rs.getString(USER_EMAIL),
                        rs.getString(RECIPE_NAME),
                        rs.getString(INGREDIENTS_COL),
                        rs.getString(INSTRUCTIONS_COL),
                        rs.getTimestamp(CREATED_AT),
                        rs.getString(ALLERGIES_COL)
                );
                recipes.add(recipe);
            }
        }
    }

    public static List<Recipe> getAllRecipes(Connection conn) throws SQLException {
        String sql = "SELECT *" + " FROM recipes";
        List<Recipe> recipes = new ArrayList<>();

        if (conn == null) {
            throw new SQLException(NO_CONNECTION);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            getRecipeResultSet(recipes, stmt);
        }
        return recipes;
    }


}
