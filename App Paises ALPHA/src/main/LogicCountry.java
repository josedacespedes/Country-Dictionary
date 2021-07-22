
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;


public class LogicCountry {

    private String message;   

    public void getInsertCountry(InfoCountry c, InfoCountry cArray[]) {

        int gate = 0;
        String wordToInsert;
        String wordInserted;
            
        File f = new File("paises.txt");
      
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintStream pS = new PrintStream(fos);

            //Si archivo exite compara entradas sino guarda una primera existe
            if (countRegistersFile() != 0) {
                for (int i = 0; i < cArray.length; i++) {
                    
                    if (c.getName().equals(cArray[i].getName())) {
                        gate = 0;
                        break;
                    } else 
                        gate = 1;
                    
                }//End for
                
            } else {
                gate = 1;
            }//End if - else
                
            if (gate == 1) {
                pS.println(c.getName() + "[]" + c.getContinent() + "[]" + c.getDescription() + "[]" + c.getFlag() + "[]"
                        + c.getCapital() + "[]" + c.getIdiom() + "[]" + c.getPopulation()+ "[]" + c.getCount());
                setmessage("Guardado exitoso");
            } else {
                setmessage("Error, país ya existe");
            }
   
            pS.close();//Cierra el ps
        }//End try
        catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Error, problemas con el archivo");
        }//End catch fnfe

    }//End getInsertCountry(InfoCountry c, InfoCountry cArray[]) 
    
    public BufferedReader getBufferedReader(){

        File f = new File("paises.txt");
        

        //En nulo temporalmente
        BufferedReader br = null;

        try {
            //Escritura
            if (!f.exists()) {
                FileOutputStream fos = new FileOutputStream(f);
                fos.close();
            }//End if

            //Lectura
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            
        }//End try
        
        catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Error, problemas con el archivo");
        }//End catch FileNotFoundException fnfe
        catch(IOException ioe){
             JOptionPane.showMessageDialog(null, "Error, problemas con el archivo");
        }

        return br;
    }//End getBufferedReader()
    
    public int countRegistersFile() {
        //Variable que contabiliza numero de registros
        int countRegisters = 0;
        //Variable que toma valor de cada linea
        String registerActual;
        
        BufferedReader br = getBufferedReader();
        
        try {
            registerActual = br.readLine();
            
            while (registerActual != null) {
                
                if (registerActual != null) {
                    countRegisters++;
                    registerActual = br.readLine();
                }
            }//End while
            br.close();//Cierra el buffer
        }//End try
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }//End IOException ioe

        return countRegisters;
    }//End countRegistersFile()

    public InfoCountry[] getArrayInfoCountry() {

        //Arreglo de objetos
        InfoCountry arrayCountry[] = new InfoCountry[countRegistersFile()];
        InterfaceCountries iCo = new InterfaceCountries();

        //Variable que mueve campos del arreglo
        int arrayIndex = 0;
        BufferedReader br = getBufferedReader();

        String ActualRegister;
        
        int ControlToken;
        
        try {
            ActualRegister = br.readLine();
            
            while(ActualRegister!= null){
                //Declaracion de variables para los atributos
                String name = "", continent = "", description = "", flag = "", capital = "", idiom = "", population = "";
                int countSearch=0;
                ControlToken = 1;
                
               //Para el token
               StringTokenizer  sT = new StringTokenizer(ActualRegister, "[]");
                
                //Ira asignando los valores que esten antes de cada token
                while(sT.hasMoreTokens()){

                    if (ControlToken == 1) {
                        name = sT.nextToken();
                    } else if (ControlToken == 2) {
                        continent = sT.nextToken();
                    }else if (ControlToken == 3) {
                        description = sT.nextToken();
                    }else if (ControlToken == 4) {
                        flag = sT.nextToken();
                    }else if (ControlToken == 5) {
                        capital = sT.nextToken();
                    }else if (ControlToken == 6) {
                        idiom = sT.nextToken();
                    }else if (ControlToken == 7) {
                        population = sT.nextToken();
                    }else if (ControlToken == 8) {
                        countSearch = Integer.parseInt(sT.nextToken());
                    }

                    ControlToken++;
                }//End while interno
                    
                //Creo un objeto
                InfoCountry iC = new InfoCountry(name, continent, description, flag, capital, idiom, population,countSearch);
                
                //Paso objeto a arreglo de objetos
                arrayCountry[arrayIndex]= iC;
                
                //Aumento del que mueve campo del arreglo
                arrayIndex++;
                ActualRegister=br.readLine();
            }//End while
            br.close();//cierra el buffer
        }//End try
        catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }        
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }//End catch ioe
        
        
        return arrayCountry;
    }//End getArrayInfoCountry()
    
    
    public InfoCountry[]  orderArray() {

        //Asignacacion de arreglo de objetos
        InfoCountry arrayOrder[] = getArrayInfoCountry();
        
        //Arreglo de objetos temporal
        InfoCountry arrayTemp[] = new InfoCountry[1];
        
        //Declaraciones
        String countryA;
        String countryB;
        String wordAccentM = "ÁáÉéÍíÓóÚúÑñ";
        String replaceAccent = "AaEeIiOoUuNn";
        char[] array;
        
        File f = new File("paises.txt");

        if (countRegistersFile()  >= 2) {
            for (int i = 1; i < arrayOrder.length; i++) {
                for (int j = 0; j < arrayOrder.length; j++) {
                    
                    countryA = arrayOrder[i].getName();
                    countryB = arrayOrder[j].getName();

                    //Quita esapcios en blanco
                    countryA=countryA.replaceAll(" ", "");
                    countryB = countryB.replaceAll(" ", "");
                    
                    //Quita tildes para comparacion
                    array = countryA.toCharArray();
                    for (int index = 0; index < array.length; index++) {
                        int pos = wordAccentM.indexOf(array[index]);
                        if (pos > -1) {
                            array[index] = replaceAccent.charAt(pos);
                        }//End if
                    }//End for
                    
                    //Convierto los arreglos a string
                    String withoutAccentA = new String(array);
                    
                    //Quita tildes para comparacion
                    array = countryB.toCharArray();
                    for (int index = 0; index < array.length; index++) {
                        int pos = wordAccentM.indexOf(array[index]);
                        if (pos > -1) {
                            array[index] = replaceAccent.charAt(pos);
                      }//End if
                    }//End for
                    
                    //Convierto los arreglo a string
                    String  withoutAccentB = new String(array);

                    //Compara si son iguales, por encima o por debajo
                    if (withoutAccentA.compareToIgnoreCase(withoutAccentB) < 0) {
                        arrayTemp[0] = arrayOrder[i];
                        arrayOrder[i] = arrayOrder[j];
                        arrayOrder[j] = arrayTemp[0];

                    }//End if
                }//End for j

            }//End for i
            
        }//End if countRegistersFi...

        try {
            FileOutputStream fos = new FileOutputStream(f);
            PrintStream pS = new PrintStream(fos);

            for (int z = 0; z < arrayOrder.length; z++) {
                pS.println(arrayOrder[z].getName() + "[]" + arrayOrder[z].getContinent() + "[]" + arrayOrder[z].getDescription() + "[]"
                        + arrayOrder[z].getFlag() + "[]" + arrayOrder[z].getCapital() + "[]" + arrayOrder[z].getIdiom() + "[]" + arrayOrder[z].getPopulation()
                        + "[]" + arrayOrder[z].getCount());
            }//End for z
            pS.close();//Cierra el ps
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Probelmas con el archivo");
        }//End try/ ctach
        
        return arrayOrder;
    }//End orderArray()
    
    public String getLogicTop(){

        InfoCountry infC[] = orderArray();
        
        //Declaro
        int max = infC[0].getCount();
        
        //Contendra pais con mas busquedas y msj resultante
        String champCountry = "",  output = "";

        for (int i = 0; i < infC.length; i++) {
            max = Math.max(max, infC[i].getCount());
        }//End for

        for (int i = 0; i < infC.length; i++) 
            if (max ==0){
                output=  "No se han realizado busquedas";
                break;
            }else if (max == infC[i].getCount()){
                output=  "El país con más busquedas es "+ infC[i].getName()+ " con " + infC[i].getCount()+ " busquedas";
                break;
            }
        return output;
    }//End getLogicTop()
    
  

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    
}//End class

