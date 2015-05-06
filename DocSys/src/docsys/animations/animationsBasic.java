/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.animations;

import docsys.DocSys;
import docsys.RequiredFunctions;
import docsys.home.homePanel;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Ninad
 */
public class animationsBasic {
    
    public static Pane currentPane;
    
    public static void changePanel(Pane from, Pane to)
    {
        FadeTransition ft = new FadeTransition(Duration.millis(500), from);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                DocSys.root.setCenter(to);
                FadeTransition ft2 = new FadeTransition(Duration.millis(500), to);
        ft2.setFromValue(0);
        ft2.setToValue(1.0);
        ft2.play();
        
            }
            
        });
        
        currentPane = to;
        
    }
    
    public static void changePanel(Pane from, Pane to, Node n)
    {
        FadeTransition ft = new FadeTransition(Duration.millis(500), from);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                DocSys.root.setCenter(to);
                FadeTransition ft2 = new FadeTransition(Duration.millis(500), to);
        ft2.setFromValue(0);
        ft2.setToValue(1.0);
        ft2.play();
        ft2.setOnFinished(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {
                        RequiredFunctions.changeFocusTo(n);
                    }
            
        });
           
            }
            
        });
        
        currentPane = to;
        
    }
    
    public static void homePaneonPress(Pane p)
    {
        ScaleTransition s = new ScaleTransition(Duration.millis(200), p);
        s.setFromX(1.0);
        s.setFromY(1.0);
        s.setToX(0.9);
        s.setToY(0.9);
        s.play();
    }
    
    public static void homePaneonRelease(Pane p,Pane to)
    {
        ScaleTransition s = new ScaleTransition(Duration.millis(200), p);
        s.setFromX(0.9);
        s.setFromY(0.9);
        s.setToX(1.0);
        s.setToY(1.0);
        s.play();
        if(p.isHover())
            changePanel(homePanel.homePanel,to);
    }
    
    public static void homePaneonClick(Pane p, Pane to)
    {
        ScaleTransition s = new ScaleTransition(Duration.millis(75), p);
        s.setFromX(1.0);
        s.setFromY(1.0);
        s.setToX(0.9);
        s.setToY(0.9);
        ScaleTransition s1 = new ScaleTransition(Duration.millis(75), p);
        s1.setFromX(0.9);
        s1.setFromY(0.9);
        s1.setToX(1.0);
        s1.setToY(1.0);
        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(s,s1);
        sq.play();
            changePanel(homePanel.homePanel,to);
    }
    
    
    
}
