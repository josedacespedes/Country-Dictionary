package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @authors José Alvarado Céspedes B80304   
 *                 Juan Diego Coto Ramírez B82444
 *                  Aplicación de Países del Mundo
 */

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        interfaceMenu iM = new interfaceMenu();

        Scene scene = new Scene(iM.getVBox(),800, 500);

        primaryStage.setTitle("Países del Mundo");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Images/world.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        
//        //Pruebas
//        LogicCountry ic = new LogicCountry();
//        System.out.println(ic.orderArray()[0].getCount());
//        System.out.println(ic.orderArray()[0].getContinent());
//        System.out.println(ic.orderArray()[0].getDescription());
//        System.out.println(ic.orderArray()[0].getFlag());
//        System.out.println(ic.orderArray()[0].getCapital());
//        System.out.println(ic.orderArray()[0].getIdiom());
//        System.out.println(ic.orderArray()[0].getPopulation());

      launch(args);
    }
    
}
