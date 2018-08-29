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

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichObject = null;
        Sandwich sandwich = new Sandwich();
        try {

            sandwichObject = new JSONObject(json);

            JSONObject name = sandwichObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            sandwich.setMainName(mainName);

            String placeOfOrigin = sandwichObject.getString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(placeOfOrigin);

            String description = sandwichObject.getString("description");
            sandwich.setDescription(description);

            String image = sandwichObject.getString("image");
            sandwich.setImage(image);

            JSONArray alsoKnownNamesArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownNames = getListFromJsonArray(alsoKnownNamesArray);
            sandwich.setAlsoKnownAs(alsoKnownNames);

            JSONArray ingredientsArray = sandwichObject.getJSONArray("ingredients");
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
