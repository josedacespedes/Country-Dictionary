
package main;


public class InfoCountry {
    
   private String name, continent, description,flag,capital,idiom,population;
   private int count;

    public InfoCountry(String name, String continent, String description, String flag, String capital, String idiom, String population, int count) {
        this.name = name;
        this.continent = continent;
        this.description = description;
        this.flag = flag;
        this.capital = capital;
        this.idiom = idiom;
        this.population = population;
        this.count = count;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getIdiom() {
        return idiom;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
   
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
    
    
    
    
}
