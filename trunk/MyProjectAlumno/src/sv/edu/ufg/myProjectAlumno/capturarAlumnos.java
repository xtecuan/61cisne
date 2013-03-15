/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.myProjectAlumno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import sv.edu.ufg.ejemplos.obtenerentrada.teclado.Alumno;

/**
 *
 * @author jaimerojas
 */

public class capturarAlumnos {
     private static  String noAlumnoleer="";
    
  public static void main(String[] args) {
      Alumno alumno = new Alumno();
      Alumno arrayAlumno[];
      int noAlumnosint = 0;
      int i=0;
      

    JOptionPane.showMessageDialog(null, "Bienvenido al sistema de alumnos!!!");
    String noAlumnos = JOptionPane.showInputDialog("No de Alumnos a Registrar");
    noAlumnosint = Integer.parseInt(noAlumnos);
    /*if (noAlumnosint>0)*/
    noAlumnoleer = noAlumnos;

    arrayAlumno = new Alumno[noAlumnosint];

    String opcionLectura = JOptionPane.showInputDialog("Leer por JOptionpane 1, Consola 2");

    if (Integer.parseInt(opcionLectura)==1) {
    for (i=0;i<noAlumnosint;i++){
        String carnet = JOptionPane.showInputDialog("Ingrese el carnet");
        String nombres = JOptionPane.showInputDialog("Ingrese los nombres");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos");
        String correo = JOptionPane.showInputDialog("Ingrese el correo");
        String fechanacTexto = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento dd/MM/yyyy");

         alumno.setId(i);
         alumno.setCarnet(carnet);
         alumno.setNombres(nombres);
         alumno.setApellidos(apellidos);
         alumno.setCorreo(correo);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechanac = sdf.parse(fechanacTexto);
            if(fechanac!=null){
                alumno.setFechanac(fechanac);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*alumno.imprimirInfoAlumno();
        JOptionPane.showMessageDialog(null, alumno);   */       
        arrayAlumno[i]=alumno;
        arrayAlumno[i].imprimirInfoAlumno();
    }
      /*finaliza*/ }
    else
    {   BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (i=0;i<noAlumnosint;i++){
            System.out.println("Ingrese el carnet");
            String carnet = dataIn.readLine();
            System.out.println("Ingrese los nombres");
            String nombres = dataIn.readLine();
            System.out.println("Ingrese los apellidos");
            String apellidos = dataIn.readLine();
            System.out.println("Ingrese el correo");
            String correo = dataIn.readLine();
            System.out.println("Ingrese la fecha de nacimiento dd/MM/yyyy");
            String fechanacTexto = dataIn.readLine();

            alumno.setId(i);
            alumno.setCarnet(carnet);
            alumno.setNombres(nombres);
            alumno.setApellidos(apellidos);
            alumno.setCorreo(correo);                
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechanac = sdf.parse(fechanacTexto);
                    if(fechanac!=null){
                        alumno.setFechanac(fechanac);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            arrayAlumno[i]=alumno;
            arrayAlumno[i].imprimirInfoAlumno();
            }
        } catch (IOException e) {
            System.err.println("Error de i/o al leer la terminal!!!");
        } 
    }

      
      
  }
  
 public String getnoAlumnoleer() {
        return noAlumnoleer;
    }
        
}
