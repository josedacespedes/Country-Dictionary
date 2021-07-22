package main;


import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;


public class InterfaceAboutCredits {
    
    public VBox getAbout(){
        
        VBox vB_About = new VBox();
        vB_About.setSpacing(10);
        
        TextArea tA_About = new TextArea();
        tA_About.setEditable(false);
        tA_About.setText("Última actualización: 26/11/2019 \nTamaño: 25.1 MB \nVersión: 1.0\nLenguaje de desarrollo: Java");
        tA_About.setTranslateY(100);

        Button bTN_Exit = new Button("Cerrar");
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(150);

        bTN_Exit.setOnAction((event) -> {
            vB_About.getChildren().clear();
        });         
        
        
        vB_About.getChildren().addAll(tA_About,bTN_Exit);
  
        return vB_About;
    }//End getAbout()
    
    public VBox getCredits(){
        
        VBox vB_getCredits = new VBox();

        TextArea tA_Credits = new TextArea();
        tA_Credits.setEditable(false);
        tA_Credits.setText("Desarrolladores:\nJosé David Alvarado Céspedes - B80304\nJuan Diego Coto Ramírez - B82444 ");
        tA_Credits.setTranslateY(100);
        
        Button bTN_Exit = new Button("Cerrar");
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(160);

        bTN_Exit.setOnAction((event) -> {
            vB_getCredits.getChildren().clear();
        });         
        
        vB_getCredits.getChildren().addAll(tA_Credits,bTN_Exit);

        return vB_getCredits;
    }//End getCredits()
    
    
}//End class
