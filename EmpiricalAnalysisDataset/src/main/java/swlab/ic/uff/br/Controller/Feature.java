/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlab.ic.uff.br.Controller;

/**
 *
 * @author angelo
 */
public class Feature {
    
    private int id;
    private String name;
    private Double frquen;
    
    public Feature(){
        
    }

    /**
     * @return the name
     */
    public int getId() {
        return id;
    }

    /**
     * @param name the name to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the frquen
     */
    public Double getFrquen() {
        return frquen;
    }

    /**
     * @param frquen the frquen to set
     */
    public void setFrquen(Double frquen) {
        this.frquen = frquen;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
