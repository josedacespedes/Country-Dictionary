
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class InterfaceCountries {

    //Instancias 
    LogicCountry lC = new LogicCountry();
    private int countSearch;

    public int getCountSearch() {
        return countSearch;
    }

    public void setCountSearch(int countSearch) {
        this.countSearch = countSearch;
    }
    
    
    public AnchorPane getAddCountry(){
        Stage s_Chooser = new Stage();
        
        //Label toma mensaje de guadado/no guardado
        Label lB_message = new Label();
        lB_message.setStyle("-fx-text-fill: DARKGOLDENROD; -fx-background-color: WHITE; -fx-font-size: 18px;");
        

        AnchorPane aP_getAdding = new AnchorPane();

        Label lB_Name = new Label("País");
        lB_Name.setTextFill(Color.WHITE);
        TextField tF_Name = new TextField();
        
        //Label por si deja campo vacio
        Label lB_NameRequired = new Label("**Campo obligatorio**");
        lB_NameRequired.setVisible(false);
        lB_NameRequired.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");
        
        ComboBox cB_Continent = new ComboBox();
        cB_Continent.setValue("Continente");
        cB_Continent.getItems().addAll("África","América","Antártida","Asia","Europa ","Oceanía");
        cB_Continent.setOnAction((event) -> {
            lB_message.setVisible(false);
        });
        
        //Label por si deja campo vacio
        Label lB_ContinentRequired = new Label("**Campo obligatorio**");
        lB_ContinentRequired.setVisible(false);
        lB_ContinentRequired.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");
        
        Label lB_Description = new Label("Descripción");
        lB_Description.setTextFill(Color.WHITE); 
        TextArea tF_Description = new TextArea(" ");
        tF_Description.setPrefSize(400, 80);
        tF_Description.setWrapText(true);
        
        Button bTN_Flag = new Button("Seleccione bandera...");
        TextField tF_Flag = new TextField();
        tF_Flag.setPrefWidth(400);
        tF_Flag.setDisable(true);
        
        bTN_Flag.setOnAction((event) -> {
            //Crea fileChooser
            FileChooser fC = new FileChooser();

            //Filtro para solo imagenes
            fC.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

            //El file abre ventana
            File fileSelect = fC.showOpenDialog(s_Chooser);

            if (fileSelect != null) {
                //Guarda texto de ruta seleccionada
                tF_Flag.setText(fileSelect.getAbsolutePath());
            }//End if
            
        });//End accion de seleccionar..
    
        //Label por si deja campo vacio
        Label lB_FlagRequired = new Label("**Campo obligatorio**");
        lB_FlagRequired.setVisible(false);
        lB_FlagRequired.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");
        
        Label lB_Capital = new Label("Capital");
        lB_Capital.setTextFill(Color.WHITE);
        TextField tF_Capital = new TextField(" ");
        
        Label lB_Language = new Label("Idioma");
        lB_Language.setTextFill(Color.WHITE);
        TextField tF_Language = new TextField(" ");
        
        Label lB_Population = new Label("Población");
        lB_Population.setTextFill(Color.WHITE);
        TextField tF_Population = new TextField(" ");
        
        //Labels que válidan ingresar letras 
        Label  lB_CountryError = new Label("**Ingrese solo letras**");
        lB_CountryError.setVisible(false);
        lB_CountryError.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");

        Label  lB_CapitalError = new Label("**Ingrese solo letras**");
        lB_CapitalError.setVisible(false);
        lB_CapitalError.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");
        
        Label  lB_LanguageError = new Label("**Ingrese solo letras**");
        lB_LanguageError.setVisible(false);
        lB_LanguageError.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");        
        
        Button bTN_Add = new Button("Agregar");
      
        
        //Accion de agregar
        bTN_Add.setOnAction((event) -> {
            //Decalracion de variables
            int gateName=0;
            int gateContinent=0;
            int gateFlag=0;
            int gateValidateCapital=0;
            int gateValidateLanguage=0;
            int gateBadWord=0;
  

            //Verifica campos vacios de pais,continente y bandera
            String textName = tF_Name.getText();
            

            //Elimino espacios
            textName = textName.replaceAll(" ", ""); 
            String specialCharacter = "0123456789/*-+.<>,;:-_[]{}()¡'¿?!|°&%$@";

            //Verifico si se dejo en blanco el espacio de pais
            if (textName.length() == 0 ){ 
                lB_NameRequired.setVisible(true);
                lB_CountryError.setVisible(false);
                
            }//End if
            else {
                lB_NameRequired.setVisible(false);

                for (int i = 0; i < textName.length(); i++) {
                    for (int j = 0; j < specialCharacter.length(); j++) {
                        if (textName.charAt(i) == specialCharacter.charAt(j)) {
                            gateBadWord = 1;

                        }//End if
                    }//End for j
                    if (gateBadWord == 1) 
                        break;
                    

                }//End for i
                if (gateBadWord == 1) 
                    lB_CountryError.setVisible(true);
                 else {
                    lB_CountryError.setVisible(false);
                    gateName = 1;
                }//End else
            }//End else
            
            //Verifica que se haya seleccionado un contienente
            if (cB_Continent.getValue().equals("Continente"))
                lB_ContinentRequired.setVisible(true);
            else{
                gateContinent =1;
                lB_ContinentRequired.setVisible(false);
            }//End else
            
             //Verifico si se dejo en blanco el espacio de bandera y si no entro una imagen
            if (tF_Flag.getText().length() == 0) 
                 lB_FlagRequired.setVisible(true);
             else {
                gateFlag=1;
                lB_FlagRequired.setVisible(false);
            }//End else

            //Verifica que el en el campo de capital solo entre letras
            gateBadWord = 0;

            for (int i = 0; i < tF_Capital.getText().length(); i++) {
                for (int j = 0; j < specialCharacter.length(); j++) {
                    if (tF_Capital.getText().charAt(i) == specialCharacter.charAt(j)) {
                        gateBadWord = 1;

                    }//End if
                }//End for j
                if (gateBadWord == 1) 
                    break;
                
            }//End for i
            if (gateBadWord == 1) 
                lB_CapitalError.setVisible(true);
             else {
                lB_CapitalError.setVisible(false);
                gateValidateCapital = 1;
            }//End else
            
            //Verifica que el en el campo de idioma solo entre letras
            gateBadWord = 0;
            
            for (int i = 0; i < tF_Language.getText().length(); i++){
                for (int j = 0; j < specialCharacter.length(); j++){
                    if(tF_Language.getText().charAt(i) == specialCharacter.charAt(j)){
                        gateBadWord = 1;
            
                    }//End if
                }//End for j
                if(gateBadWord == 1)
                    break;
                
            }//End for i
            if(gateBadWord == 1)
                lB_LanguageError.setVisible(true);
            else{
                lB_LanguageError.setVisible(false);
                gateValidateLanguage =1;
            }//End else            
            
            
            //Creacion de objeto
            if(gateName ==1 && gateContinent ==1 && gateFlag ==1 && gateValidateCapital ==1 && gateValidateLanguage ==1){
                
                InfoCountry inC = new InfoCountry(tF_Name.getText(), cB_Continent.getValue().toString(), tF_Description.getText(),
                                                                    tF_Flag.getText(), tF_Capital.getText(), tF_Language.getText(), tF_Population.getText(),getCountSearch());
                lC.getInsertCountry(inC, lC.getArrayInfoCountry());
               
                //Muestra mensaje de guardado/no guardado
                lB_message.setText(lC.getmessage());
 
                //reinicio de variables
                tF_Name.clear();
                cB_Continent.setValue("Continente");
                tF_Description.setText(" ");
                tF_Flag.clear();
                tF_Capital.setText(" ");
                tF_Language.setText(" ");
                tF_Population.setText(" ");
                
                //ESte boton inicia metodo que ordena alfabeticamente
                lC.orderArray();
                lB_message.setVisible(true);
            }//End if
            
        });//End Boton Agregar

        Button bTN_Close = new Button("Cerrar");
        
        //Accion de cerrar
        bTN_Close.setOnAction((event) -> {
            aP_getAdding.getChildren().clear();
        });
        
        //Acomodo de elementos
        AnchorPane.setTopAnchor(lB_Name, 10d);
        AnchorPane.setLeftAnchor(lB_Name, 10.d);
        AnchorPane.setTopAnchor(tF_Name, 10.d);
        AnchorPane.setLeftAnchor(tF_Name, 50d);
        AnchorPane.setTopAnchor(lB_NameRequired, 40d);
        AnchorPane.setLeftAnchor(lB_NameRequired, 50d);
        AnchorPane.setTopAnchor(lB_CountryError, 40d);
        AnchorPane.setLeftAnchor(lB_CountryError, 50d);  
        
        AnchorPane.setTopAnchor(cB_Continent, 10d);
        AnchorPane.setLeftAnchor(cB_Continent, 230d);
        AnchorPane.setTopAnchor(lB_ContinentRequired, 40d);
        AnchorPane.setLeftAnchor(lB_ContinentRequired, 230d);
        
        AnchorPane.setTopAnchor(lB_Description, 70d);
        AnchorPane.setLeftAnchor(lB_Description, 10d);
        AnchorPane.setTopAnchor(tF_Description, 70d);
        AnchorPane.setLeftAnchor(tF_Description, 80d);
        
        AnchorPane.setTopAnchor(tF_Flag, 175d);
        AnchorPane.setLeftAnchor(tF_Flag, 10d);
        AnchorPane.setTopAnchor(bTN_Flag, 175d);
        AnchorPane.setLeftAnchor(bTN_Flag, 430d);
        AnchorPane.setTopAnchor(lB_FlagRequired, 180d);
        AnchorPane.setRightAnchor(lB_FlagRequired, 100d);
        
        AnchorPane.setTopAnchor(lB_Capital, 230d);
        AnchorPane.setLeftAnchor(lB_Capital, 10d);
        AnchorPane.setTopAnchor(tF_Capital, 230d);
        AnchorPane.setLeftAnchor(tF_Capital, 65d);
        AnchorPane.setTopAnchor(lB_CapitalError, 235d);
        AnchorPane.setRightAnchor(lB_CapitalError, 450d);    
        
        AnchorPane.setTopAnchor(lB_Language, 265d);
        AnchorPane.setLeftAnchor(lB_Language, 10d);
        AnchorPane.setTopAnchor(tF_Language, 265d);
        AnchorPane.setLeftAnchor(tF_Language, 65d);
        AnchorPane.setTopAnchor(lB_LanguageError, 270d);
        AnchorPane.setRightAnchor(lB_LanguageError, 450d); 
        
        AnchorPane.setTopAnchor(lB_Population, 300d);
        AnchorPane.setLeftAnchor(lB_Population, 10d);
        AnchorPane.setTopAnchor(tF_Population, 300d);
        AnchorPane.setLeftAnchor(tF_Population, 65d);
        AnchorPane.setTopAnchor(lB_message, 335d);
        AnchorPane.setLeftAnchor(lB_message, 10d);
        
        AnchorPane.setTopAnchor(bTN_Add, 350d);
        AnchorPane.setLeftAnchor(bTN_Add, 350d);
        
        AnchorPane.setTopAnchor(bTN_Close, 390d);
        AnchorPane.setLeftAnchor(bTN_Close, 357d);
        
        aP_getAdding.getChildren().addAll(lB_Name,tF_Name,cB_Continent,lB_Description,tF_Description,bTN_Flag,tF_Flag,
                                          lB_Capital,tF_Capital,lB_Language,tF_Language,lB_Population,tF_Population,
                                          bTN_Add,bTN_Close,lB_NameRequired,lB_ContinentRequired,lB_message,
                                          lB_FlagRequired,lB_CountryError,lB_CapitalError,lB_LanguageError);
        return aP_getAdding;
    }//End getAddCountry()
    
    //Busca el nombre del país
    public VBox searchNameCountry() {
        
        VBox vB_searchNameCountry = new VBox();
        VBox vB_imageView = new VBox();
        vB_searchNameCountry.setSpacing(10);

        Label lB_report = new Label("Ingrese el nombre del país que desea buscar");
        lB_report.setFont(new Font("Arial", 30)); //Tamaño y estilo de la letra
        lB_report.setTextFill(Color.DARKGOLDENROD);// Color de la letra
        TextField tF_SearchName = new TextField();
        //Tmaño y acomodo
        tF_SearchName.setMaxWidth(200);
        tF_SearchName.setTranslateX(280);

        
        Button bTN_Search =new Button("Buscar");
    
        //Label por si pais no se encuentra
        Label lB_CountrynT = new Label("País no encontrado");
        lB_CountrynT.setVisible(false);
        //Color y tamaño
        lB_CountrynT.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 20px;"); 
        
        //Arreglo para usar en BUSQUEDA
        InfoCountry temArray[] = lC.orderArray();
        
        //Label que muestra msj de aplicaion vacia
        Label lB_appCleaned = new Label("Aplicación vacía");
        lB_appCleaned.setVisible(false);
        lB_appCleaned.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 20px;");
        
        //Labels que válidan ingresar letras
        Label  lB_countryError = new Label("**Ingrese solo letras**");
        lB_countryError.setVisible(false);
        lB_countryError.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");
           
        
        //Mensaje si app esta vacia 
        BufferedReader br = lC.getBufferedReader();
        try{
            if (br.readLine() == null) {
                lB_appCleaned.setVisible(true);
                tF_SearchName.setEditable(false);
            } else {
                tF_SearchName.setEditable(true);
            }//End if - else
            
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
        
        //Accion de boton de buscar 
        bTN_Search.setOnAction((event) -> {

            //Limpia de Vbox la imagen y  luego remueve el Vbox de imagen del Vbox principal
            vB_imageView.getChildren().clear();
            vB_searchNameCountry.getChildren().remove(vB_imageView);

            ArrayList aL = new ArrayList();

            //For que busca coincidencia de datos para traer los datos al tableView
            for (int i = 0; i < temArray.length; i++) {

                if (tF_SearchName.getText().equals(temArray[i].getName())) {
                    //Quita msj de pais sin encontar
                    lB_CountrynT.setVisible(false);
                    //Agrega datos a arrayList
                    aL.add(temArray[i]);

                    //Trae ruta de la imagen y la muestra en imagen
                    ImageView iV_Fla = new ImageView(new Image("file:" + temArray[i].getFlag()));
                    iV_Fla.setFitHeight(80);
                    iV_Fla.setFitWidth(160);

                    //Agrega imagen a vbox de imagen y luego agrega dicho vbox a vbox principal
                    vB_imageView.getChildren().addAll(iV_Fla);

                    //Acomodo
                    vB_imageView.setTranslateX(305);
                    vB_imageView.setTranslateY(-100);
                    vB_searchNameCountry.getChildren().add(vB_imageView);

                    //Contabiliza busquedas por pais
                    setCountSearch(temArray[i].getCount() + 1);
                    temArray[i].setCount(getCountSearch());
                    setCountSearch(0);

                    //Agrega numero de veces que ha sido buscado un pais
                    try {
                        File f = new File("paises.txt");
                        //Para escribir
                        FileOutputStream fos = new FileOutputStream(f);
                        PrintStream pS = new PrintStream(fos);
                        for (int z = 0; z < temArray.length; z++) {

                            pS.println(temArray[z].getName() + "[]" + temArray[z].getContinent() + "[]" + temArray[z].getDescription() + "[]"
                                    + temArray[z].getFlag() + "[]" + temArray[z].getCapital() + "[]" + temArray[z].getIdiom() + "[]" + temArray[z].getPopulation()
                                    + "[]" + temArray[z].getCount());

                        }

                        br.close();
                        pS.close();
                    } catch (IOException ioe) {
                        JOptionPane.showMessageDialog(null, "Problemas con el archivo");
                    }//End catch
                    break;//sale del for

                } else {
                    lB_CountrynT.setVisible(true);//End if
                }
            }//End for

            //Crea observableList con el arrayList generado
            ObservableList<InfoCountry> oL_dataCountries = FXCollections.observableArrayList(aL);
            //Le pasa al TableView el ObservableList
            tV_countries.setItems(oL_dataCountries);
            tF_SearchName.clear();
        });//End Accion 

        //Se agregan las columnas del TableView
        tV_countries.getColumns().addAll(tC_NameCountry,tC_NameContinent,tC_Description,tC_Flag,tC_Capital,tC_Idiom,tC_Population);
        tV_countries.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Se agregan todos los atributos 
        vB_searchNameCountry.getChildren().addAll(lB_report,tF_SearchName,bTN_Search,tV_countries,bTN_Exit,lB_CountrynT,lB_appCleaned,lB_countryError);
        //Acomodo
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(120);
        lB_report.setTranslateX(100);
        lB_appCleaned.setTranslateX(315);
        lB_CountrynT.setTranslateX(315);
        bTN_Search.setTranslateY(-35);
        bTN_Search.setTranslateX(500);
        tV_countries.setTranslateY(-25);
        
        return vB_searchNameCountry;
    }//end serachNameCountry
    
    
    //Método modifica el país ingresado previamente
    public VBox modifyCountry(){
        
        VBox vB_mofifyCountry = new VBox();
        vB_mofifyCountry.setSpacing(10);
        
        //Arreglo para usar en Combobox
        InfoCountry temArray[] = lC.orderArray();

        ComboBox cB_showCountry = new ComboBox();
        cB_showCountry.setTranslateX(345);

        cB_showCountry.setValue("Elegir país");

        //Agrega paises a ComboBox
        for (int i = 0; i < temArray.length; i++) {
            cB_showCountry.getItems().add(temArray[i].getName());
        }
        
        Label lb_Modify = new Label("Escoja el país que desea modificar");
        lb_Modify.setFont(new Font("Garamond",20));
        lb_Modify.setTextFill(Color.DARKGOLDENROD); 
        lb_Modify.setTranslateX(260);
        
        ComboBox cB_Continent = new ComboBox();
        cB_Continent.setValue("Continente");//Aqui va logica
        cB_Continent.getItems().addAll("África","América","Antártida","Asia","Europa ","Oceanía");
        
        Label lB_Description = new Label("Descripción");
        lB_Description.setTextFill(Color.WHITE);
        TextArea tF_Description = new TextArea(" ");
        tF_Description.setWrapText(true);
        tF_Description.setMaxWidth(500);
        tF_Description.setMinHeight(75);
    
        Label lB_Capital = new Label("Capital");
        lB_Capital.setTextFill(Color.WHITE);
        TextField tF_Capital = new TextField(" ");
        tF_Capital.setMaxWidth(200);
    
        Label lB_Language = new Label("Idioma");
        lB_Language.setTextFill(Color.WHITE);
        lB_Language.setTranslateY(-30);
        TextField tF_Language = new TextField(" ");
        tF_Language.setMaxWidth(200);
        tF_Language.setTranslateY(-30);
        
        Label lB_Population = new Label("Población");
        lB_Population.setTextFill(Color.WHITE);
        lB_Population.setTranslateY(-60);
        TextField tF_Population = new TextField(" "); 
        tF_Population.setMaxWidth(200);
        tF_Population.setTranslateY(-60);
        
        
        Button bTN_Modify = new Button("Modificar");
        Font Garamond2 = new Font("Garamond",22);
        Font Arial = new Font("ArialBlack",22);
        bTN_Modify.setTranslateX(350);
        bTN_Modify.setTranslateY(-120);
        
        Button bTN_Exit = new Button("Cerrar");
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(-120);
        
        Label lB_Modifed = new Label("Registro Modificado");
        lB_Modifed.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 18px;");
        lB_Modifed.setVisible(false);
        lB_Modifed.setTranslateY(-60);
        
        Label lB_appCleaned = new Label("Aplicación vacía");
        lB_appCleaned.setVisible(false);
        lB_appCleaned.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 18px;");
        lB_appCleaned.setTranslateY(-30);
        
        //Labels que válidan ingresar letras 
        Label  lB_CapitalError = new Label("**Ingrese solo letras**");
        lB_CapitalError.setVisible(false);
        lB_CapitalError.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;");
        lB_CapitalError.setTranslateX(210);
        lB_CapitalError.setTranslateY(-30);
        
        Label  lB_LanguageError = new Label("**Ingrese solo letras**");
        lB_LanguageError.setVisible(false);
        lB_LanguageError.setStyle("-fx-text-fill: RED; -fx-background-color: WHITE;"); 
        lB_LanguageError.setTranslateX(210);
        lB_LanguageError.setTranslateY(-60);
        
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
        

        bTN_Exit.setOnAction((event) -> {
            vB_mofifyCountry.getChildren().clear();
        });  
      
        //Accion de combobox de paises        
        cB_showCountry.setOnAction((event) -> {
            
            for (int i = 0; i < temArray.length; i++) {

                if (cB_showCountry.getValue().toString().equals(temArray[i].getName())) {

                    cB_Continent.setValue(temArray[i].getContinent());
                    tF_Description.setText(temArray[i].getDescription());
                    tF_Capital.setText(temArray[i].getCapital());
                    tF_Language.setText(temArray[i].getIdiom());
                    tF_Population.setText(temArray[i].getPopulation());
                    
                    lB_Modifed.setVisible(false);
                    break;
                    
                }//End if
            }//End for
            
        });//End Accion
        
        //Accion de boton de modificar
        bTN_Modify.setOnAction((event) -> {
            
            //Para sobreescribir
            File f = new File("paises.txt");
            int gateBadWord;
            int gateValidateCapital=0;
            int gateValidateLanguage=0;
            String specialCharacter = "0123456789/*-+.<>,;:-_[]{}()¡'¿?!|°&%$@";
            
            gateBadWord =0;
            for (int i = 0; i < tF_Capital.getText().length(); i++) {
                for (int j = 0; j < specialCharacter.length(); j++) {
                    if (tF_Capital.getText().charAt(i) == specialCharacter.charAt(j)) {
                        gateBadWord = 1;

                    }//End if
                }//End for j
                if (gateBadWord == 1) 
                    break;

            }//End for i
            if (gateBadWord == 1) 
                lB_CapitalError.setVisible(true);
             else {
                lB_CapitalError.setVisible(false);
                gateValidateCapital = 1;
            }//End else

            //Verifica que el en el campo de idioma solo entre letras
            gateBadWord = 0;

            for (int i = 0; i < tF_Language.getText().length(); i++) {
                for (int j = 0; j < specialCharacter.length(); j++) {
                    if (tF_Language.getText().charAt(i) == specialCharacter.charAt(j)) {
                        gateBadWord = 1;

                    }//End if
                }//End for j
                if (gateBadWord == 1) 
                    break;
                

            }//End for i
            if (gateBadWord == 1) 
                lB_LanguageError.setVisible(true);
             else {
                lB_LanguageError.setVisible(false);
                gateValidateLanguage = 1;
            }//End else 

            if (gateValidateCapital == 1 && gateValidateLanguage == 1) {
                for (int i = 0; i < temArray.length; i++) {

                    if (cB_showCountry.getValue().toString().equals(temArray[i].getName())) {

                        try {
                            //Para escribir
                            FileOutputStream fos = new FileOutputStream(f);
                            PrintStream pS = new PrintStream(fos);

                            temArray[i].setContinent(cB_Continent.getValue().toString());
                            temArray[i].setDescription(tF_Description.getText());
                            temArray[i].setCapital(tF_Capital.getText());
                            temArray[i].setIdiom(tF_Language.getText());
                            temArray[i].setPopulation(tF_Population.getText());

                            for (int z = 0; z < temArray.length; z++) {

                                pS.println(temArray[z].getName() + "[]" + temArray[z].getContinent() + "[]" + temArray[z].getDescription() + "[]"
                                        + temArray[z].getFlag() + "[]" + temArray[z].getCapital() + "[]" + temArray[z].getIdiom() + "[]" + temArray[z].getPopulation()
                                        + "[]" + temArray[z].getCount());

                                lB_Modifed.setVisible(true);
                            }

                            br.close();
                            pS.close();
                        } catch (IOException ioe) {
                            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
                        }//End catch
                        break;

                    }//End if
                }//End for

                //Limpia
                tF_Description.clear();
                tF_Capital.clear();
                tF_Language.clear();
                tF_Population.clear();
                cB_Continent.setValue("Continente");
                cB_showCountry.setValue("Elegir país");
            }//End if
        });

        vB_mofifyCountry.getChildren().addAll(lb_Modify,cB_showCountry,cB_Continent,lB_Description,tF_Description,lB_Capital,tF_Capital,lB_CapitalError,lB_Language,
                                              tF_Language,lB_LanguageError,lB_Population,tF_Population,lB_appCleaned,lB_Modifed,bTN_Modify,bTN_Exit);
        
        return vB_mofifyCountry;
    }//End modifyCountry()
    
    //Método elimina el país
    public VBox deleteCountry(){
    
        VBox vB_deleteCountry = new VBox();
        
        //Arreglo para usar en Combobox
        InfoCountry teArray[] = lC.orderArray();
        
        Label lB_DEelete = new Label("Escoja el país que desea eliminar");
        lB_DEelete.setStyle("-fx-text-fill: DARKGOLDENROD; -fx-background-color: WHITE; -fx-font-size: 20px;");
        lB_DEelete.setTranslateX(240);
        lB_DEelete.setTranslateY(100);          
        
        ComboBox cb_lista =new ComboBox();
        cb_lista.setTranslateX(240);
        cb_lista.setTranslateY(150);
        cb_lista.setValue("Elegir país");

        //Agrega paises a ComboBox
        for (int i = 0; i < teArray.length; i++) {
            cb_lista.getItems().add(teArray[i].getName());
        }

        Button bTN_deleteCountry = new Button("Eliminar");
        bTN_deleteCountry.setTranslateX(440);
        bTN_deleteCountry.setTranslateY(126);        
        bTN_deleteCountry.setDisable(true);

        Button bTN_Exit = new Button("Cerrar");
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(250);   
        
        Label lB_CountryDelete = new Label("País eliminado");
        lB_CountryDelete.setStyle("-fx-text-fill: DARKGOLDENROD; -fx-background-color: WHITE; -fx-font-size: 18px;");
        lB_CountryDelete.setVisible(false);
        lB_CountryDelete.setTranslateY(150);
        lB_CountryDelete.setTranslateX(330);
        
        Label lB_appCleaned = new Label("Aplicación vacía");
        lB_appCleaned.setVisible(false);
        lB_appCleaned.setStyle("-fx-text-fill: DARKGOLDENROD; -fx-background-color: WHITE; -fx-font-size: 18px;");
        lB_appCleaned.setTranslateY(150);
        lB_appCleaned.setTranslateX(320);
        
        //Mensaje si app esta vacia 
        BufferedReader br = lC.getBufferedReader();
        try {
            if (br.readLine() == null) {
                lB_appCleaned.setVisible(true);
                lB_CountryDelete.setVisible(false);
            }//End if
            br.close();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problemas con archivo");
        }//End catch
        
        bTN_Exit.setOnAction((event) -> {
            vB_deleteCountry.getChildren().clear();
        });  
        
        //Accion de borrar
        bTN_deleteCountry.setOnAction((event) -> {
            
            //Arreglo para usar en Combobox
            InfoCountry temArray[] = lC.orderArray();
            //Para sobreescribir
            File f = new File("paises.txt");

            for (int i = 0; i < temArray.length; i++) {

                if (cb_lista.getValue().toString().equals(temArray[i].getName())) {

                    try {
                        //Para escribir
                        FileOutputStream fos = new FileOutputStream(f);
                        PrintStream pS = new PrintStream(fos);

                        for (int z = 0; z < temArray.length; z++) {

                            if (z != i) {
                                pS.println(temArray[z].getName() + "[]" + temArray[z].getContinent() + "[]" + temArray[z].getDescription() + "[]"
                                        + temArray[z].getFlag() + "[]" + temArray[z].getCapital() + "[]" + temArray[z].getIdiom() + "[]" + temArray[z].getPopulation()+
                                        "[]" + temArray[z].getCount());

                            }//End if
                                        
                        }//End for z
                        
                        //Remueve valor eliminado de combobox y apone el por default
                        cb_lista.getItems().removeAll(temArray[i].getName());
                        cb_lista.setValue("Elegir país");
                        
                        br.close();
                        pS.close();
                        
                    } catch (IOException ioe) {
                        JOptionPane.showMessageDialog(null, "Problemas con el archivo");
                    }//End catch
                    
                    break;
                }//End if
                
            }//End for

            //Actualizacion de valores
            lB_CountryDelete.setVisible(true);

            bTN_deleteCountry.setDisable(true);
        });
        
        //Accion de eleguir pais
        cb_lista.setOnAction((event) -> {
            lB_CountryDelete.setVisible(false);
            bTN_deleteCountry.setDisable(false);
        });
        

        vB_deleteCountry.getChildren().addAll(lB_DEelete,cb_lista, bTN_deleteCountry, lB_CountryDelete,bTN_Exit, lB_appCleaned);
  
        return vB_deleteCountry;
    }//end deletCountry   
    
//Método que vacía la liista de Contactos
    public VBox emptyApplication(){
    
        VBox vB_Empty = new VBox();
        File f = new File("paises.txt");

        Label lB_emptyList= new Label("¿Desea vaciar la agenda de países?");
        lB_emptyList.setFont(new Font("Arial", 25));
        lB_emptyList.setTextFill(Color.DARKGOLDENROD); 
        lB_emptyList.setTranslateX(200);
        
        
        //Elementos de elleccion de vaciar apliacacion
        Button bTN_emptyList= new Button("Vaciar lista");
        bTN_emptyList.setStyle("-fx-text-fill: White; -fx-background-color: Brown; -fx-font-size: 16px;");
        bTN_emptyList.setTranslateX(340);
        bTN_emptyList.setTranslateY(15); 
        
        Label lB_Confirm = new Label("               ¿Esta seguro que desea eliminar la lista de países?"+
                                     "\nSi vacía la lista no podrá volver a ver los países guardados anteriormente");
        lB_Confirm.setStyle("-fx-text-fill: RED;"+"-fx-background-color:WHITE;"+"-fx-font-size: 16px;");
        lB_Confirm.setTranslateX(150);
        lB_Confirm.setTranslateY(30); 
        
        Button bTN_Yes= new Button("Sí");
        bTN_Yes.setStyle("-fx-text-fill: White; -fx-background-color: Brown; -fx-font-size: 16px;");
        bTN_Yes.setTranslateX(280);
        bTN_Yes.setTranslateY(60);         
        
        Button bTN_No= new Button("No");
        bTN_No.setStyle("-fx-text-fill: White; -fx-background-color: Brown; -fx-font-size: 16px;");
        bTN_No.setTranslateX(440);
        bTN_No.setTranslateY(23);         
        
        
        Label lB_Delete = new Label("Ha eliminado la lista de paises");
        lB_Delete.setStyle("-fx-text-fill: DARKGOLDENROD;"+"-fx-background-color:WHITE;"+"-fx-font-size: 20px;");
        lB_Delete.setTranslateX(260);
        lB_Delete.setTranslateY(10);        
        
        Button bTN_Exit = new Button("Cerrar");
        bTN_Exit.setStyle("-fx-text-fill: White; -fx-background-color: Brown; -fx-font-size: 13px;");
        bTN_Exit.setTranslateX(360);
        bTN_Exit.setTranslateY(140);

        Label lB_alreadyDelete = new Label("Lista ya vaciada");
        lB_alreadyDelete.setStyle("-fx-text-fill: GREEN;"+"-fx-background-color:WHITE;"+"-fx-font-size: 20px;");
        lB_alreadyDelete.setTranslateX(325);
        lB_alreadyDelete.setTranslateY(10); 
        lB_alreadyDelete.setVisible(false);
        
        lB_Confirm.setVisible(false);
        lB_Delete.setVisible(false);

        bTN_Yes.setVisible(false);
        bTN_No.setVisible(false);

        //Accion de cerrar
        bTN_Exit.setOnAction((event) -> {
            vB_Empty.getChildren().clear();
        });
        
        //Accion de Vaciar lista
        bTN_emptyList.setOnAction((event) -> {
            lB_Delete.setVisible(false);

            //Al dar click en vaciar app si esta vacia muestra msj que ya la app esta vacia sino muestra boton si/no
            BufferedReader br = lC.getBufferedReader();
            try {
                if (br.readLine() == null) {
                    bTN_Yes.setVisible(false);
                    bTN_No.setVisible(false);
                    lB_alreadyDelete.setVisible(true);
                    lB_Confirm.setVisible(false);
                } else {
                    lB_Confirm.setVisible(true);
                    bTN_Yes.setVisible(true);
                    bTN_No.setVisible(true);
                }
                br.close();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Problemas con archivo");
            }//End catch
            
        });     
        
        //Accion de si
        bTN_Yes.setOnAction((event) -> {
            lB_Delete.setVisible(true);
            lB_Confirm.setVisible(false);
            
            //Elimina archivo
            
            f.delete();
            
            //Esconde boton si y no
            bTN_Yes.setVisible(false);
            bTN_No.setVisible(false);
        });
        
        //Accion de no
        bTN_No.setOnAction((event) -> {
            bTN_Yes.setVisible(false);
            bTN_No.setVisible(false);
            lB_Confirm.setVisible(false);
            lB_Confirm.setVisible(false);
        });
        

        vB_Empty.getChildren().addAll(lB_emptyList,bTN_emptyList,lB_Confirm,bTN_Yes,bTN_No,lB_Delete,lB_alreadyDelete,bTN_Exit);
    return vB_Empty;
    }//end emptyApplication        
}//End class


