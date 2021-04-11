package com.example.InformationTheoryProject;

import Entities.SymbolProbability;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ProjectConsole {



    public static double getProbability(Character uniqueSymbol,String wholeText){
        double counter = 0;

        for(int i = 0; i<wholeText.length();i++){
            if(uniqueSymbol==wholeText.charAt(i)){
                counter++;
            }
        }

        return counter/wholeText.length();
    }

    public static ArrayList<Character> getUniqueChars(String data){

        Set<Character> mySet = new HashSet<Character>();
        for(int i=0;i<data.length();i++){
            mySet.add(data.charAt(i));
        }
        ArrayList<Character> myArr = new ArrayList<>(mySet);
        Collections.sort(myArr);

        return myArr;
    }

    public static void main(String[] args){
        String data="";
        System.out.println(System.getProperty("user.dir"));
        try {
            File myObj = new File(System.getProperty("user.dir")+"/src/main/java/files/Text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ArrayList<Character> uniqueChars = getUniqueChars(data);
        ArrayList<SymbolProbability> table = new ArrayList<>();

        for(int i = 0; i < uniqueChars.size(); i++){
            double probability = getProbability(uniqueChars.get(i),data);
            SymbolProbability sp = new SymbolProbability(uniqueChars.get(i), probability);
            table.add(sp);
        }

        for(SymbolProbability sp: table){
            System.out.println(sp.getSymbol()+"   "+sp.getProbability());
        }
    }
}
