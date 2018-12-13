package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;


public class JsonUtils {
    private final static String TAG = JsonUtils.class.getSimpleName();
    private final static String KEY_NAME = "name";
    private final static String KEY_MAIN_NAME = "mainName";
    private final static String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_IMAGE = "image";
    private final static String KEY_ALSO_KONWN_AS = "alsoKnownAs";
    private final static String KEY_INGREDIENTS = "ingredients";

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

            JSONArray alsoKnownNamesArray = name.getJSONArray(KEY_ALSO_KONWN_AS);
            List<String> alsoKnownNames = getListFromJsonArray(alsoKnownNamesArray);
            sandwich.setAlsoKnownAs(alsoKnownNames);

            JSONArray ingredientsArray = sandwichObject.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredients = getListFromJsonArray(ingredientsArray);
            sandwich.setIngredients(ingredients);

            return sandwich;

        } catch (JSONException e) {
            Log.e(TAG, "parseSandwichJson: ", e);
        }
        return null;
    }

    private static List<String> getListFromJsonArray(JSONArray alsoKnownNames) {
        ArrayList<String> list = new ArrayList<>();
        if (alsoKnownNames != null) {
            int len = alsoKnownNames.length();
            for (int i = 0; i < len; i++) {
                try {
                    list.add(alsoKnownNames.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
