/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.formasgeometricas;

/**
 *
 * @author javaee
 */
public class Triangulo {
    private  double base ;
    private  double altura ;

    public Triangulo() {
    }

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }
    
    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    
   public double calcularArea() {
       double areaForma = 0;
       areaForma = base*altura/2;               
       return areaForma;
   }

    @Override
    public String toString() {
        return "Triangulo{" + "base=" + base + ", altura=" + altura + '}';
    }
   
   
    
}
