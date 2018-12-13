package com.udacity.sandwichclub.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Sandwich implements Parcelable {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;


    public Sandwich() {

    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getAlsoKnownAs() {
        if (alsoKnownAs.size() != 0) {
            StringBuilder text = new StringBuilder();
            String prefix = "";
            for (String serverId : alsoKnownAs) {
                text.append(prefix);
                prefix = ", ";
                text.append(serverId);
            }
            return text.toString();

        } else {
            return null;
        }
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredients() {

        if (ingredients.size() != 0) {
            StringBuilder text = new StringBuilder();
            String prefix = "⚬";
            for (String serverId : ingredients) {
                text.append(prefix);
                prefix = "\n⚬";
                text.append(serverId);
            }
            return text.toString();

        } else {
            return null;
        }
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "mainName='" + mainName + '\'' +
                ", alsoKnownAs=" + alsoKnownAs +
                ", placeOfOrigin='" + placeOfOrigin + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mainName);
        dest.writeStringList(this.alsoKnownAs);
        dest.writeString(this.placeOfOrigin);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeStringList(this.ingredients);
    }

    protected Sandwich(Parcel in) {
        this.mainName = in.readString();
        this.alsoKnownAs = in.createStringArrayList();
        this.placeOfOrigin = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.ingredients = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Sandwich> CREATOR = new Parcelable.Creator<Sandwich>() {
        @Override
        public Sandwich createFromParcel(Parcel source) {
            return new Sandwich(source);
        }

        @Override
        public Sandwich[] newArray(int size) {
            return new Sandwich[size];
        }
    };
}
