package de.kitsunealex.projectx.recipe;

public class RecipeException extends RuntimeException {

    public RecipeException(String message, Object... params) {
        super(String.format(message, params));
    }

}
