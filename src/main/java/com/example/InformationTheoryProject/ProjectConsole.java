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

    public static void grouping(SymbolProbability sp, ArrayList<SymbolProbability> table){
        double sum = 0;
        double sum1 = 0;
        ArrayList<SymbolProbability> g1 = new ArrayList<>();
        ArrayList<SymbolProbability> g2 = new ArrayList<>();
        g1.add(sp);

        for(int i = 0;i < table.size(); i++){
            if(table.get(i).equals(sp)){
                continue;
            }
            g2.add(table.get(i));
        }

        groupingRecur(g1,g2);

    }

    public static void groupingRecur(ArrayList<SymbolProbability> g1, ArrayList<SymbolProbability> g2){

        double sum1 = 0;
        double sum2 = 0;
        ArrayList<SymbolProbability> g1new = new ArrayList<>();
        ArrayList<SymbolProbability> g2new = new ArrayList<>();

        for(int i=0;i<g1.size();i++){
            sum1 += g1.get(i).getProbability();
        }

        for(int i=0;i<g2.size();i++){
            sum2 += g2.get(i).getProbability();
        }
        System.out.println(Math.round(sum1*100.0)/100.0);
        System.out.println(Math.round(sum2*100.0)/100.0);

        if((Math.round(sum1*100.0)/100.0) == (Math.round(sum2*100.0)/100.0)){
            System.out.println("ef");
            for(int i=0;i<g1.size();i++){
                g1.get(i).setCode(g1.get(i).getCode()+"1");
            }
            for(int i=0;i<g2.size();i++){
                g2.get(i).setCode(g2.get(i).getCode()+"0");
            }
        }

        else {
            g1.add(g2.get(0));
            g2.remove(0);
            if(g2.size()==1){
                g2.get(0).setCode(g2.get(0).getCode()+"0");
            }
            else {
                groupingRecur(g1, g2);
            }
        }
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

        Collections.sort(table);
//        for(SymbolProbability sp: table){
//            System.out.println(sp.getSymbol()+"   "+sp.getProbability());
//        }

        grouping(table.get(0),table);

        for(SymbolProbability sp: table){
            System.out.println(sp.getSymbol()+"   "+sp.getProbability()+"  "+sp.getCode());
        }
    }
}
