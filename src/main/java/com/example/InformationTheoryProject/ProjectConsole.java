package com.example.InformationTheoryProject;

import Entities.BinarySearchTree;
import Entities.SymbolProbability;
import Entities.SymbolProbability1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProjectConsole {

    public static ArrayList<SymbolProbability> table = new ArrayList<>();

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

    public static String getText(){
        String data="";
        try {
            File myObj = new File(System.getProperty("user.dir")+"/src/main/java/files/Text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }

    public static ArrayList<SymbolProbability> part1(){


        ArrayList<Character> uniqueChars = getUniqueChars(getText());


        for(int i = 0; i < uniqueChars.size(); i++){
            double probability = getProbability(uniqueChars.get(i),getText());
            SymbolProbability sp = new SymbolProbability(String.valueOf(uniqueChars.get(i)), probability);
            table.add(sp);
        }

        Collections.sort(table);



        for(SymbolProbability sp: table){
            System.out.println(sp.getSymbol()+"   "+sp.getProbability()+"  "+sp.getCode());
        }

        return table;
    }

    public static ArrayList<String> part2(){
        ArrayList<SymbolProbability> table = part1();
        ArrayList<SymbolProbability1> table1 = new ArrayList<>();
        for(int i=0;i<table.size();i++){
            SymbolProbability sp = table.get(i);
            table1.add(new SymbolProbability1(sp.getSymbol(),sp.getProbability()));
        }

        BinarySearchTree tree = new BinarySearchTree();
        SymbolProbability1 root = new SymbolProbability1();
        root.setSymbol("root");
        root.setProbability(3.0);
        root.setPriority(0.6);
        tree.insert(root);


        for(int i=0; i<table1.size();i++){

            table1.get(i).setPriority(i);
            tree.insert(table1.get(i));
            if(i==table1.size()-2){
                table1.get(i+1).setPriority(i+1.1);
                tree.insert(table1.get(i+1));
                break;
            }
            SymbolProbability1 newSp1 = new SymbolProbability1();
            double probSum = 0;
            String symbSum = "";

            for(int j=i+1;j<table.size();j++){
                symbSum+=table.get(j).getSymbol();
                probSum+=table.get(j).getProbability();
            }
            newSp1.setSymbol(symbSum);
            newSp1.setProbability(probSum);

            newSp1.setPriority(i+1.1);

            tree.insert(newSp1);
        }
        tree.print();

        for (int i=0;i<table1.size();i++){
            System.out.println(tree.find(table1.get(i)).getCode());
            table.get(i).setCode(tree.find(table1.get(i)).getCode());
        }

        String text = getText();

        ArrayList<String> encoded = new ArrayList<>();
        for(int i=0;i<text.length();i++){
            for(int j=0;j<table.size();j++){
                if(table.get(j).getSymbol().equals(String.valueOf(text.charAt(i)))){
                    encoded.add(table.get(j).getCode());
                    break;
                }
            }
        }
        return encoded;
    }

    public static String part3(){
        ArrayList<String> encoded = part2();
        String decoded = "";
        for(int i=0;i<encoded.size();i++){
            for(int j=0;j<table.size();j++){
                if(encoded.get(i).equals(table.get(j).getCode())){
                    decoded+=table.get(j).getSymbol();
                    break;
                }
            }
        }
        return decoded;
    }

    public static void main(String[] args){

        System.out.println(part3());
    }
}
