package com.example.cargame;

import java.util.Comparator;

    public class SortByScore implements Comparator<Save> {

        @Override
        public int compare(Save rec1, Save rec2) {

            return rec2.getScore() - rec1.getScore();
        }

    }
