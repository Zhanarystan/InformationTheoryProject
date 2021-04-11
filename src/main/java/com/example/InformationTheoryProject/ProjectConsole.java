package com.example.InformationTheoryProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProjectConsole {
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

        System.out.println(data);
    }
}
