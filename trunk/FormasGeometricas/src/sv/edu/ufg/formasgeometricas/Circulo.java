/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.formasgeometricas;
import java.math.*;

/**
 *
 * @author javaee
 */
public class Circulo {
    private static double pi = 3.141516;
    private double radio;

    public Circulo() {
    }

    public Circulo(double radio) {
        this.radio = radio;
    }
    
    public double calcularArea(){
        return pi*(Math.pow(radio, 2));
    }

    @Override
    public String toString() {
        return "Circulo{" + "radio=" + radio + '}';
    }
    
    
}
