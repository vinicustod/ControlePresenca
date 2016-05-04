/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author viniciuscustodio
 */
public class FileManager {

    public static boolean updateInFile(String fileName, String toCheck, String toSave){
        
        return true;
    }
    
    public static boolean saveInFile(String fileName, String toSave) {
        return true;
    }
    
    public static boolean checkInFile(String fileName, String toCheck){
        File file = new File(fileName); 
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                String line = sc.nextLine();
                if(toCheck.equals(line))
                    return true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean deleteFromFile(String fileName, String toDelete){
        return true;
    }
}
