package com.udacity.sandwichclub.utils;

import com.orhanobut.logger.Logger;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public final static String KEY_NAME = "name";
    public final static String KEY_MAIN_NAME = "mainName";
    public final static String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_IMAGE = "image";
    public final static String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public final static String KEY_INGREDIENTS = "ingredients";

    /**
     * This method is an Example of manual Json Parsing, The best practice is to use
     * a Parsing Library Like <a href="https://github.com/google/gson">Gson</a>.
     *
     * @param json Json Data As A String
     * @return Json Data As A Sandwich Object
     */
    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichObject;
        Sandwich sandwich = new Sandwich();
        try {

            sandwichObject = new JSONObject(json);

            JSONObject name = sandwichObject.getJSONObject(KEY_NAME);
            String mainName = name.getString(KEY_MAIN_NAME);
            sandwich.setMainName(mainName);

            String placeOfOrigin = sandwichObject.getString(KEY_PLACE_OF_ORIGIN);
            sandwich.setPlaceOfOrigin(placeOfOrigin);

            String description = sandwichObject.getString(KEY_DESCRIPTION);
            sandwich.setDescription(description);

            String image = sandwichObject.getString(KEY_IMAGE);
            sandwich.setImage(image);

            JSONArray alsoKnownNamesArray = name.getJSONArray(KEY_ALSO_KNOWN_AS);
            List<String> alsoKnownNames = getListFromJsonArray(alsoKnownNamesArray);
            sandwich.setAlsoKnownAs(alsoKnownNames);

            JSONArray ingredientsArray = sandwichObject.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredients = getListFromJsonArray(ingredientsArray);
            sandwich.setIngredients(ingredients);

            return sandwich;

        } catch (JSONException e) {
            Logger.e("Failed to Parse Sandwich Json", e);
        }
        return null;
    }

    private static List<String> getListFromJsonArray(JSONArray jsonArray) {
        ArrayList<String> list = new ArrayList<>();

        if (jsonArray != null) {

            for (int i = 0; i < jsonArray.length(); i++) {

                try {
                    list.add(jsonArray.get(i).toString());
                } catch (JSONException e) {
                    Logger.e("Failed to Extract The Objects List From JsonArray", e);
                }

            }

        } else {
            Logger.e("JsonArray is null");
        }
        return list;
    }
}
