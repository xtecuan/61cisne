/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.formasgeometricas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author javaee
 */
public class FormasGeometricas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        double base = 0;
        double altura = 0;
        double radio =0;
        Triangulo triangulo;
        Circulo  circulo;
        Rectangulo rectangulo;

        System.out.println("Forma Geometrica a Calcular el Area: 1 Triangulo, 2 Circulo 3 Rectangulo 0 Salir ");

        String temp = dataIn.readLine();
        int eleccion = Integer.parseInt(temp);
        
        switch (eleccion) {
            
            case  1:
                System.out.println("Digite la base del triangulo");
                base = Integer.parseInt(dataIn.readLine());
                System.out.println("Digite la altura del triangulo");
                altura = Integer.parseInt(dataIn.readLine());                    
                triangulo = new Triangulo(base,altura);
                System.out.println("El area del Triangulo es: "+triangulo.calcularArea());
                break;
                
           case  2:
                System.out.println("Digite la ratio del Circulo");
                radio = Integer.parseInt(dataIn.readLine());
                circulo = new Circulo(radio);
                System.out.println("El area del Circulo es: "+circulo.calcularArea());
                break;
           case  3:
                System.out.println("Digite la base del Rectangulo");
                base = Integer.parseInt(dataIn.readLine());
                System.out.println("Digite la altura del Rectangulo");
                altura = Integer.parseInt(dataIn.readLine());                    
                rectangulo = new Rectangulo(base, altura);
                System.out.println("El area del Rectangulo es: "+rectangulo.calcularArea());
                break;
          default:
              System.out.println("No eligio ninguna opción");
               
        }
        
  
    }
}
