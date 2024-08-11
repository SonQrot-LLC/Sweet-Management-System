package sweet.management.entities;

import sweet.management.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private int recipeId;
    private String userEmail;
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
            e.printStackTrace();
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    // Static Methods for Database Operations
    public static boolean createRecipe(Recipe recipe, Connection conn) throws SQLException {
        String sql = "INSERT INTO recipes (user_email, recipe_name, ingredients, instructions, created_at, allergies) VALUES (?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, recipe.getUserEmail());
            stmt.setString(2, recipe.getRecipeName());
            stmt.setString(3, recipe.getIngredients());
            stmt.setString(4, recipe.getInstructions());
            stmt.setTimestamp(5, recipe.getCreatedAt());
            stmt.setString(6, recipe.getAllergies());
        });
    }

    public static Recipe getRecipeById(int recipeId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM recipes WHERE recipe_id = ?";
        if (conn == null) {
            throw new SQLException();
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Recipe(
                            rs.getInt("recipe_id"),
                            rs.getString("user_email"),
                            rs.getString("recipe_name"),
                            rs.getString("ingredients"),
                            rs.getString("instructions"),
                            rs.getTimestamp("created_at"),
                            rs.getString("allergies")
                    );
                }
            }
        }
        return null;
    }

    public static boolean updateRecipe(Recipe recipe, Connection conn, int updateType) throws SQLException {
        String sql = null;
        switch (updateType) {
            case UPDATE_RECIPE_NAME:
                sql = "UPDATE recipes SET recipe_name = ? WHERE recipe_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getRecipeName());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            case UPDATE_INGREDIENTS:
                sql = "UPDATE recipes SET ingredients = ? WHERE recipe_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getIngredients());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            case UPDATE_INSTRUCTIONS:
                sql = "UPDATE recipes SET instructions = ? WHERE recipe_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getInstructions());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            case UPDATE_ALLERGIES:
                sql = "UPDATE recipes SET allergies = ? WHERE recipe_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, recipe.getAllergies());
                    stmt.setInt(2, recipe.getRecipeId());
                });
            default:
                return false;
        }
    }

    public static boolean deleteRecipe(int recipeId, Connection conn) throws SQLException {
        String sql = "DELETE FROM recipes WHERE recipe_id = ?";
        boolean status = DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, recipeId);
        });
        if (!status){
            System.out.println("Recipe Not Found!");
        }
        else {
            System.out.println("Recipe Deleted Successfully!");
        }

        return status;
    }

    public static boolean resetIdCounter(Connection conn) throws SQLException {
        String sql = "ALTER TABLE recipes AUTO_INCREMENT = 1;";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {});
    }

    public  boolean setId(int id, Connection conn) throws SQLException {
        int oldId = this.recipeId;
        this.recipeId = id;
        String sql = "UPDATE recipes SET recipe_id = ? WHERE recipe_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, id);
            stmt.setInt(2, oldId);
        });
    }

    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = 'recipes';";
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "sweetmanagementsystem");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public static List<Recipe> getRecipesByUserEmail(String userEmail, Connection conn) throws SQLException {
        String sql = "SELECT * FROM recipes WHERE user_email = ?";
        List<Recipe> recipes = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Recipe recipe = new Recipe(
                            rs.getInt("recipe_id"),
                            rs.getString("user_email"),
                            rs.getString("recipe_name"),
                            rs.getString("ingredients"),
                            rs.getString("instructions"),
                            rs.getTimestamp("created_at"),
                            rs.getString("allergies")
                    );
                    recipes.add(recipe);
                }
            }
        }
        return recipes;
    }

    // New static method to update the allergies for a recipe
    public static boolean setAllergies(int recipeId, String allergies, Connection conn) throws SQLException {
        String sql = "UPDATE recipes SET allergies = ? WHERE recipe_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, allergies);
            stmt.setInt(2, recipeId);
        });
    }


    public static List<Recipe> searchRecipesByName(String text, Connection conn) throws SQLException {
        String sql = "SELECT * FROM recipes WHERE recipe_name LIKE ?";
        List<Recipe> recipes = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + text + "%"); // Using the LIKE pattern for partial matches
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Recipe recipe = new Recipe(
                            rs.getInt("recipe_id"),
                            rs.getString("user_email"),
                            rs.getString("recipe_name"),
                            rs.getString("ingredients"),
                            rs.getString("instructions"),
                            rs.getTimestamp("created_at"),
                            rs.getString("allergies")
                    );
                    recipes.add(recipe);
                }
            }
        }
        if (recipes.isEmpty()){
            System.out.println("No recipes found!");
        }
        return recipes;
    }

    public static List<Recipe> getRecipesWithoutAllergies(String allergy, Connection conn) throws SQLException {
        String sql = "SELECT * FROM recipes WHERE allergies NOT LIKE ?";
        List<Recipe> recipes = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + allergy + "%"); // Using the NOT LIKE pattern to exclude recipes with the specified allergy
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Recipe recipe = new Recipe(
                            rs.getInt("recipe_id"),
                            rs.getString("user_email"),
                            rs.getString("recipe_name"),
                            rs.getString("ingredients"),
                            rs.getString("instructions"),
                            rs.getTimestamp("created_at"),
                            rs.getString("allergies")
                    );
                    recipes.add(recipe);
                }
            }
        }
        return recipes;
    }


}
