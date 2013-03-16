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
import org.xtecuan.mir.facade.AlumnosFacade;

/**
 *
 * @author jaimerojas
 */

public class capturarAlumnos {
     private static  String noAlumnoleer="";
    
  public static void main(String[] args) {
      Alumno alumno        = new Alumno();
      Alumno arrayAlumno   [];
      int noAlumnosint     = 0;
      int i                = 0;
      String carnet        = "";   
      String nombres       = "";
      String apellidos     = "";
      String correo        = "";
      String fechanacTexto = "";

    JOptionPane.showMessageDialog(null, "Bienvenido al sistema de alumnos!!!");
    String noAlumnos = JOptionPane.showInputDialog("No de Alumnos a Registrar");
    noAlumnosint = Integer.parseInt(noAlumnos);
    /*if (noAlumnosint>0)*/
    noAlumnoleer = noAlumnos;

    arrayAlumno = new Alumno[noAlumnosint];

    String opcionLectura = JOptionPane.showInputDialog("Leer por JOptionpane 1, Consola 2");

    if (Integer.parseInt(opcionLectura)==1) {
    for (i=0;i<noAlumnosint;i++){
        carnet = JOptionPane.showInputDialog("Ingrese el carnet");
        nombres = JOptionPane.showInputDialog("Ingrese los nombres");
        apellidos = JOptionPane.showInputDialog("Ingrese los apellidos");
        correo = JOptionPane.showInputDialog("Ingrese el correo");
        fechanacTexto = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento dd/MM/yyyy");

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
        
        arrayAlumno[i]=alumno; }
    
        /* guardar alumnos en base de datos*/
        for (i=0;i<noAlumnosint;i++){
           arrayAlumno[i].imprimirInfoAlumno();
         }
        AlumnosFacade.guardarAlumnos(arrayAlumno);
        /*finaliza*/ }
    else
    {   BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (i=0;i<noAlumnosint;i++){
            System.out.println("Ingrese el carnet");
            carnet = dataIn.readLine();
            System.out.println("Ingrese los nombres");
            nombres = dataIn.readLine();
            System.out.println("Ingrese los apellidos");
            apellidos = dataIn.readLine();
            System.out.println("Ingrese el correo");
            correo = dataIn.readLine();
            System.out.println("Ingrese la fecha de nacimiento dd/MM/yyyy");
            fechanacTexto = dataIn.readLine();

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
    
            }
        } catch (IOException e) {
            System.err.println("Error de i/o al leer la terminal!!!");
        } 
        
         for (i=0;i<noAlumnosint;i++){
           arrayAlumno[i].imprimirInfoAlumno();
         }
       AlumnosFacade.guardarAlumnos(arrayAlumno);
    }

      
      
  }
  
 public String getnoAlumnoleer() {
        return noAlumnoleer;
    }
        
}
