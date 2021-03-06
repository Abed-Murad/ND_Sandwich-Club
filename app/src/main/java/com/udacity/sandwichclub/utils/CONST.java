package com.udacity.sandwichclub.utils;

import java.util.ArrayList;

public class CONST {
    private static ArrayList<String> sandwichesImageUrls = new ArrayList<>();
    public static String IMAGE_MEET = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG";
    public static String IMAGE_BOSNA = "https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg";
    public static String IMAGE_CHIVITO = "https://upload.wikimedia.org/wikipedia/commons/4/48/Chivito1.jpg";
    public static String IMAGE_CLUB_SANDWICH = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Club_sandwich.png/800px-Club_sandwich.png";
    public static String IMAGE_GUA_BAO = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Steamed_Sandwich%2Ctaken_by_LeoAlmighty.jpg/600px-Steamed_Sandwich%2Ctaken_by_LeoAlmighty.jpg";
    public static String IMAGE_MEDIANOCHE = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Sandwich_de_Medianoche.jpg/800px-Sandwich_de_Medianoche.jpg";
    public static String IMAGE_PLJESKAVICE = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Pljeskavica_%286883073017%29.jpg/800px-Pljeskavica_%286883073017%29.jpg";
    public static String IMAGE_ROUJIAMO = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Roujiamo.jpg/800px-Roujiamo.jpg";
    public static String IMAGE_SHAWARMA = "https://d2814mmsvlryp1.cloudfront.net/wp-content/uploads/Chicken-Shawarma-Stuffed-Pita.jpg";
    public static String IMAGE_VADA_PAV = "https://upload.wikimedia.org/wikipedia/commons/1/15/Vada_Paav-The_Mumbai_Burger.jpg";

    private static boolean mIsFirstTime = true;

    public static String getSandwichIamgeUrl(int postition) {

        if (mIsFirstTime) {
            sandwichesImageUrls.add(IMAGE_MEET);
            sandwichesImageUrls.add(IMAGE_BOSNA);
            sandwichesImageUrls.add(IMAGE_CHIVITO);
            sandwichesImageUrls.add(IMAGE_CLUB_SANDWICH);
            sandwichesImageUrls.add(IMAGE_GUA_BAO);
            sandwichesImageUrls.add(IMAGE_MEDIANOCHE);
            sandwichesImageUrls.add(IMAGE_PLJESKAVICE);
            sandwichesImageUrls.add(IMAGE_ROUJIAMO);
            sandwichesImageUrls.add(IMAGE_SHAWARMA);
            sandwichesImageUrls.add(IMAGE_VADA_PAV);
            mIsFirstTime = false;
        }


        return sandwichesImageUrls.get(postition);
    }
}
