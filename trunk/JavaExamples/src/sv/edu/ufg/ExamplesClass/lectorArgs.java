/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ufg.ExamplesClass;

import java.io.FileWriter;

/**
 *
 * @author jaimerojas
 */
public class lectorArgs  {
    public static void main(String[] args)throws Exception  {
         FileWriter fw = new FileWriter("c:/file.dat");
        if (args.length>0)
        {
            System.out.println("Hay "+args.length+" argumentos"); 
            int i; 
            for (i=0;i<args.length;i++) 
            { 
              System.out.println("Parámetro"+i+":"+args[i]); 
                      fw.write(args[i] + "\n");
            }
        }
      fw.close();                                      
    }
}
