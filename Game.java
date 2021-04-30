// Student Name: Diego Santosuosso
// This lab was done individually

package com.example.capsgame;

import java.util.List;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;


public class Game {
    private CountryDB db;


    public Game()
    {
        this.db = new CountryDB();
    }

    public String qa()
    {
        List<String> capitals = db.getCapitals();
        int n = capitals.size();
        int index = (int) (n * Math.random());
        String c = capitals.get(index);
        Map<String, Country> data = db.getData();
//        System.out.println(data.size());
        Country ref = data.get(c);
//        System.out.println(ref.toString());

        String ques;
        if(Math.random() < 0.5)
        {
            ques =  String.format("What is the capital of %s?\n%s", ref.getName(), ref.getCapital());

        }
        else
        {
            ques = String.format("%s is the capital of ?\n%s", ref.getCapital(), ref.getName());

        }
        return  ques;

    }

}
