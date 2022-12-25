package com.example.cargame;

import java.util.ArrayList;

public class SaveData {
    private static ArrayList<Save> save;

    public SaveData() { };

    public static ArrayList<Save> getSave() {
        save = new ArrayList<>();

        save.add(new Save().setName("sapir").setScore(55).setLat(200_00).setLon(560_000));

            return save;
        }


    public static ArrayList <Save> saves = new ArrayList<>();



    public static ArrayList<Save> getSAveArr (){
        return saves;
    }


}
