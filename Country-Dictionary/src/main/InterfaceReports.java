/*
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

/**
 */
public class InterfaceReports {
    
    LogicCountry lC= new LogicCountry();
    
    //Busca el nombre del país
    public VBox getCountryList() {
        
        VBox vB_searchNameCountry = new VBox();
        VBox vB_imageView = new VBox();
        vB_searchNameCountry.setSpacing(10);

        //Arreglo para usar en Combobox
        InfoCountry temArray[] = lC.orderArray();
        
        Label lB_report = new Label("Ver país");
        lB_report.setFont(new Font("Arial", 30)); //Tamaño y estilo de la letra
        lB_report.setTextFill(Color.DARKGOLDENROD);
        
        ComboBox cB_seeCountry = new ComboBox();
        cB_seeCountry.setValue("Elegir país");
        
        //Agrega paises a ComboBox
        for(int i=0; i<temArray.length; i++)
            cB_seeCountry.getItems().add(temArray[i].getName());
        
        //Label que muestra msj de aplicaion vacia
        Label lB_appCleaned = new Label("Aplicación vacía");
        lB_appCleaned.setVisible(false);
        lB_appCleaned.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 18px;");
        
        //Mensaje si app esta vacia 
        BufferedReader br = lC.getBufferedReader();
        try{
        if (br.readLine()==null) 
           lB_appCleaned.setVisible(true);
        
            br.close();//cierra el buffer
        }catch(IOException ioe){
            JOptionPane.showMessageDialog(null, "Problemas con archivo");
        }//End catch
            

        //Creacion de tableView
        TableView<InfoCountry> tV_countries = new TableView<>();
        tV_countries.setPrefHeight(200);
        tV_countries.setStyle("-fx-font-size:100%");
          
        //Crea las columnas 
        TableColumn tC_NameCountry = new TableColumn("País");
        tC_NameCountry.setMinWidth(50);
        tC_NameCountry.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn tC_NameContinent = new TableColumn("Continente");
        tC_NameContinent.setMinWidth(50);
        tC_NameContinent.setCellValueFactory(new PropertyValueFactory("continent")); 
        
        TableColumn tC_Description = new TableColumn("Descripción");
        tC_Description.setMinWidth(50);
        tC_Description.setCellValueFactory(new PropertyValueFactory("description"));        
        
        TableColumn tC_Flag = new TableColumn("Bandera");
        tC_Flag.setMinWidth(50);
        tC_Flag.setCellValueFactory(new PropertyValueFactory("flag"));
        
        TableColumn tC_Capital = new TableColumn("Capital");
        tC_Capital.setMinWidth(50);
        tC_Capital.setCellValueFactory(new PropertyValueFactory("capital")); 
        
        TableColumn tC_Idiom = new TableColumn("Idioma");
        tC_Idiom.setMinWidth(50);
        tC_Idiom.setCellValueFactory(new PropertyValueFactory("idiom"));

        TableColumn tC_Population = new TableColumn("Población");
        tC_Population.setMinWidth(50);
        tC_Population.setCellValueFactory(new PropertyValueFactory("population"));        
        
        Button bTN_Exit = new Button("Cerrar");
        bTN_Exit.setOnAction((event) -> {
            vB_searchNameCountry.getChildren().clear();
        }); 
        
        //Accion de ComboBox 
        cB_seeCountry.setOnAction((event) -> {

            //Limpia de Vbox la imagen y  luego remueve el Vbox de imagen del Vbox principal
            vB_imageView.getChildren().clear();
            vB_searchNameCountry.getChildren().remove(vB_imageView);

            ArrayList aL = new ArrayList();

            //For que busca coincidencia de datos para traer los datos al tableView
            for (int i = 0; i < temArray.length; i++) {

                if (cB_seeCountry.getValue().toString().equals(temArray[i].getName())) {
                    
                    //Agrega datos a arrayList
                    aL.add(temArray[i]);
                    
                    //Trae ruta de la imagen y la muestra en imagen
                    ImageView iV_Fla = new ImageView(new Image("file:" + temArray[i].getFlag()));
                    iV_Fla.setFitHeight(115);
                    iV_Fla.setFitWidth(180);

                    //Agrega imagen a vbox de imagen y luego agrega dicho vbox a vbox principal
                    vB_imageView.getChildren().addAll(iV_Fla);
                    
                     //Acomodo
                    vB_imageView.setTranslateX(295);
                    vB_imageView.setTranslateY(-40);
                    vB_searchNameCountry.getChildren().add(vB_imageView);
                    break;
                    
                }//End if
            }//End for
            
            //Crea observableList con el arrayList generado
            ObservableList<InfoCountry> oL_dataCountries = FXCollections.observableArrayList(aL);
            //Le pasa al TableView el ObservableList
            tV_countries.setItems(oL_dataCountries);

        });//End Accion 

        //Se agregan las columnas del TableView
        tV_countries.getColumns().addAll(tC_NameCountry,tC_NameContinent,tC_Description,tC_Flag,tC_Capital,tC_Idiom,tC_Population);
        tV_countries.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Se agregan todos los atributos 
        vB_searchNameCountry.getChildren().addAll(lB_report,cB_seeCountry ,tV_countries,bTN_Exit,lB_appCleaned);
        //Acomodo
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(160);
        lB_report.setTranslateX(345);
        lB_appCleaned.setTranslateX(315);
        
        return vB_searchNameCountry;
    }//end serachNameCountry
    

        public VBox getContinentList() {
        
        VBox vB_searchNameContinent = new VBox();
        vB_searchNameContinent.setSpacing(10);

        
        //Arreglo para usar en Combobox
        InfoCountry tempArray[] = lC.orderArray();

        
        Label lB_report = new Label("Ver país por continente");
        lB_report.setTextFill(Color.DARKGOLDENROD); 
        lB_report.setFont(new Font("Arial", 30)); //Tamaño y estilo de la letra
        lB_report.setTranslateX(240);
        
        ComboBox cB_seeContinent = new ComboBox();
        cB_seeContinent.setTranslateX(100);
        cB_seeContinent.setTranslateY(100);
        cB_seeContinent.setValue("Elegir continente");
        
        //Agrega paises a ComboBox
        cB_seeContinent.getItems().addAll("África","América","Antártida","Asia","Europa ","Oceanía");
        
        //Label que muestra msj de aplicaion vacia
        Label lB_appCleaned = new Label("Aplicación vacía");
        lB_appCleaned.setVisible(false);
        lB_appCleaned.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 18px;");
        lB_appCleaned.setTranslateX(315);
        
        //Mensaje si app esta vacia 
        BufferedReader br = lC.getBufferedReader();
            try {
                if (br.readLine() == null) {
                    lB_appCleaned.setVisible(true);
                }//End if
                br.close();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Problemas con archivo");
            }//End catch
            
        //Creacion de tableView
        TableView<InfoCountry> tV_countries = new TableView<>();
        tV_countries.setTranslateX(250);
        tV_countries.setMaxSize(300, 200);
        tV_countries.setStyle("-fx-font-size:100%");
          
        //Crea las columnas 
        TableColumn tC_NameCountry = new TableColumn("País");
        tC_NameCountry.setMinWidth(50);
        tC_NameCountry.setCellValueFactory(new PropertyValueFactory("name"));
        
        Button bTN_Exit = new Button("Cerrar");
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(100);
        bTN_Exit.setOnAction((event) -> {
            vB_searchNameContinent.getChildren().clear();
        }); 
        
        //Accion de ComboBox 
        cB_seeContinent.setOnAction((event) -> {
            ArrayList aLConti = new ArrayList();

            //For que busca coincidencia de datos para traer los datos al tableView
            for (int i = 0; i < tempArray.length; i++) 
                if(cB_seeContinent.getValue().toString(). equals(tempArray[i].getContinent()))
                    aLConti.add(tempArray[i]);
            
            ObservableList<InfoCountry> oL_dataContinent = FXCollections.observableArrayList(aLConti);
            
            tV_countries.setItems(oL_dataContinent);
            
        });//End Accion 

        //Se agregan las columnas del TableView
        tV_countries.getColumns().addAll(tC_NameCountry);
        tV_countries.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Se agregan todos los atributos 
        vB_searchNameContinent.getChildren().addAll(lB_report,cB_seeContinent ,tV_countries, bTN_Exit,lB_appCleaned);
        
        return vB_searchNameContinent;
    }//end serachNameCountry
        
        
        public VBox getTopSearch(){
            
            VBox vB_topSearch = new VBox();
            
            Label lB_topCountry = new Label();
            lB_topCountry.setFont(new Font("Arial", 20));
            lB_topCountry.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 20px;");
            lB_topCountry.setTranslateX(190);
            lB_topCountry.setTranslateY(180);
            
            //Label que muestra msj de aplicaion vacia
            Label lB_appCleaned = new Label("Aplicación vacía");
            lB_appCleaned.setVisible(false);
            lB_appCleaned.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 18px;");
            lB_appCleaned.setTranslateX(330);
            lB_appCleaned.setTranslateY(180);
            
            //Mensaje si app esta vacia 
            BufferedReader br = lC.getBufferedReader();
            try {
                if (br.readLine() == null) {
                    lB_appCleaned.setVisible(true);
                }else{
                    lB_topCountry.setText(lC.getLogicTop());
                }
                br.close();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Problemas con archivo");
            }//End catch
            
            Button bTN_Exit = new Button("Cerrar");
            bTN_Exit.setTranslateX(360);
            bTN_Exit.setTranslateY(200);
            
            //Accion de salir
            bTN_Exit.setOnAction((event) -> {
                vB_topSearch.getChildren().clear();
            });
            

            vB_topSearch.getChildren().addAll(lB_appCleaned,lB_topCountry, bTN_Exit);
            return vB_topSearch;
        }//End getTopSearch()
        
        
        

}//End class
