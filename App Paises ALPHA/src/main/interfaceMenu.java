
package main;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;


public class interfaceMenu {
    //Instancias
    InterfaceAboutCredits iA = new InterfaceAboutCredits();
    InterfaceCountries iC = new InterfaceCountries();
    InterfaceReports iR = new InterfaceReports();
    
    public VBox getVBox() {

        VBox vB_Main = new VBox();
        VBox vB_Windows = new VBox();
        
        vB_Windows.setPadding(new Insets(5, 5, 5, 5));
        vB_Main.setPadding(new Insets(5, 5, 5, 5));
        
        //Imagen de fondo
        vB_Main.setStyle("-fx-background-image: url(globo4.jpg);"
                        +"-fx-background-position: left top,center;"
                        +"-fx-background-repeat: no-repeat;"
                        +"-fx-background-size: cover,auto;");        
        
        //Barra de menu
        MenuBar mB_Menu = new MenuBar();

        //**** Menús ****
        Menu m_System = new Menu("Sistema");
        Menu m_Countries = new Menu("Paises");
        Menu m_Upkeep = new Menu("Mantenimiento");
        Menu m_Reports = new Menu("Reportes");

        //SubMenu System
        MenuItem mI_About = new MenuItem("Acerca de", new ImageView(new Image("Images/info1.png")));

        //Accion acerca de..
        mI_About.setOnAction((event) -> {
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iA.getAbout());
        });

        MenuItem mI_Credits = new MenuItem("Créditos", new ImageView(new Image("Images/credits.png")));
        
        //Accion de creditos
        mI_Credits.setOnAction((event) -> {
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iA.getCredits());
        });

        MenuItem mI_Exit = new MenuItem("Salir", new ImageView(new Image("Images/quit.png")));

        mI_Exit.setOnAction((event) -> Platform.exit());
        
        //Agrega todo a menu sistema
        m_System.getItems().addAll(mI_About, mI_Credits, mI_Exit);
        
        //HotKeys
        mI_Exit.setAccelerator(KeyCombination.keyCombination("CTRL+E"));
        mI_Credits.setAccelerator(KeyCombination.keyCombination("CTRL+P"));
        mI_About.setAccelerator(KeyCombination.keyCombination("CTRL+I"));

        //SubMenu de pais
        MenuItem mI_Search = new MenuItem("Buscar", new ImageView(new Image("Images/search.png")));
        
        mI_Search.setOnAction((event) -> {
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iC.searchNameCountry());
        });        

        MenuItem mI_Add = new MenuItem("Agregar", new ImageView(new Image("Images/add.png")));
        
        mI_Add.setOnAction((event) -> {
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iC.getAddCountry());
        });

        MenuItem mI_Modify = new MenuItem("Modificar", new ImageView(new Image("Images/modify.png")));
        
        mI_Modify.setOnAction((event) -> {
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iC.modifyCountry());
        });         

        MenuItem mI_Delete = new MenuItem("Eliminar", new ImageView(new Image("Images/delete.png")));
        
        mI_Delete.setOnAction((event) -> {
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iC.deleteCountry());
        });        

        m_Countries.getItems().addAll(mI_Add,mI_Search,mI_Modify,mI_Delete);

        //HotKeysPaises
        mI_Search.setAccelerator(KeyCombination.keyCombination("CTRL+S"));
        mI_Add.setAccelerator(KeyCombination.keyCombination("CTRL+A"));
        mI_Modify.setAccelerator(KeyCombination.keyCombination("CTRL+M"));
        mI_Delete.setAccelerator(KeyCombination.keyCombination("CTRL+D"));  

        //SubMenu de mantenimiento
        MenuItem mI_EmptyApplication = new MenuItem("Vaciar aplicación", new ImageView(new Image("Images/empty.png")));
        
        //Accion de mantenimiento
        mI_EmptyApplication.setOnAction((event) -> {         
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iC.emptyApplication());
        });

        m_Upkeep.getItems().addAll(mI_EmptyApplication);

        //HotKeysMantenimiento
        mI_EmptyApplication.setAccelerator(KeyCombination.keyCombination("CTRL+B"));

        //SubMenu de reportes
        MenuItem mI_LookCountry = new MenuItem("Ver país", new ImageView(new Image("Images/country.png")));
        
        //Accion de ver pais
        mI_LookCountry.setOnAction((event) -> {         
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iR.getCountryList());
        });        

        MenuItem mI_List = new MenuItem("Listado de países por continente", new ImageView(new Image("Images/list.png")));
        
        mI_List.setOnAction((event) -> {

            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iR.getContinentList());
        });  

        MenuItem mI_CountrySearch = new MenuItem("País que más ha sido buscado", new ImageView(new Image("Images/more.png")));
        
        mI_CountrySearch.setOnAction((event) -> {
            vB_Windows.getChildren().clear();
            vB_Windows.getChildren().addAll(iR.getTopSearch());
        });

        m_Reports.getItems().addAll(mI_LookCountry, mI_List, mI_CountrySearch);

        //HotKeysReporte
        mI_LookCountry.setAccelerator(KeyCombination.keyCombination("CTRL+F"));
        mI_List.setAccelerator(KeyCombination.keyCombination("CTRL+L"));
        mI_CountrySearch.setAccelerator(KeyCombination.keyCombination("CTRL+T"));

        mB_Menu.getMenus().addAll(m_System, m_Countries, m_Upkeep, m_Reports);

        vB_Main.getChildren().addAll(mB_Menu,vB_Windows);
        
        
    return vB_Main;   
        
    }//end VBox
    
}//end class
