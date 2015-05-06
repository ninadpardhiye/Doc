/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys;

import javafx.application.Platform;
import javafx.scene.Node;

/**
 *
 * @author Ninad
 */
public class RequiredFunctions {

    public static void changeFocusTo(Node n)
    {
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                n.requestFocus();
            }
            
        });
    }
    
    public static boolean isAlpha(String name)
    {
        char[] chars = name.toCharArray();
        for(char c:chars)
        {
            if(!Character.isLetter(c))
            {
                return false;
            }
        }
        return true;
                
    }
    
    public static boolean isNumber(String name)
    {
        char[] chars = name.toCharArray();
        for(char c:chars)
        {
            if(!Character.isDigit(c))
            {
                return false;
            }
        }
        return true;
                
    }
    
}

