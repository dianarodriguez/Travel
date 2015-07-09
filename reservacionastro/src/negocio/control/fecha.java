/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;
import negocio.exceptions.PreexistingEntityException;
/**
 *
 * @author MiPC
 */
public class fecha {

public String diaSalida(String arreglo)throws PreexistingEntityException, Exception{

    char ds;
    char ds2;
    ds=arreglo.charAt(8);
    ds2=arreglo.charAt(9);
    int dia=0;
    String d1="";
        String d2="";
         String d3="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
    d3=d1+d2;

return d3;
}

public String AnoSalida(String arreglo)throws PreexistingEntityException, Exception{

    char ds;
    char ds2;
    char ds3 ;
    char ds4;
    ds=arreglo.charAt(25);
    ds2=arreglo.charAt(26);
     ds3=arreglo.charAt(27);
     ds4=arreglo.charAt(28);
       String d1="";
        String d2="";
         String d3="";
         String d4="";
         String ano="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
d3=String.valueOf(ds3);
d4=String.valueOf(ds4);
    ano=d1+d2+d3+d4;

return ano;
}
public String HoraSalida(String arreglo)throws PreexistingEntityException, Exception{

    char ds;
    char ds2;
    ds=arreglo.charAt(11);
    ds2=arreglo.charAt(12);

    String d1="";
        String d2="";
         String hora="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
    hora=d1+d2;

return hora;
}
public String MinSalida(String arreglo)throws PreexistingEntityException, Exception{

    char ds;
    char ds2;
    ds=arreglo.charAt(14);
    ds2=arreglo.charAt(15);

    String d1="";
        String d2="";
         String min="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
    min=d1+d2;

return min;
}

public String MesSalida(String arreglo)throws PreexistingEntityException, Exception{

    char ds;
    char ds2;
     char ds3;
    ds=arreglo.charAt(4);
    ds2=arreglo.charAt(5);
        ds3=arreglo.charAt(6);
    String d1="";
        String d2="";
          String d3="";
         String mes="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
d3=String.valueOf(ds3);
    mes=d1+d2+d3;

return mes;
}
public String HoraDestino(String arreglo)throws PreexistingEntityException, Exception{
     char ds;
    char ds2;

    ds=arreglo.charAt(0);
    ds2=arreglo.charAt(1);

    String d1="";
        String d2="";

         String hora="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
   hora=d1+d2;

return hora;
}


public String MinDestino(String arreglo)throws PreexistingEntityException, Exception{
     char ds;
    char ds2;

    ds=arreglo.charAt(3);
    ds2=arreglo.charAt(4);

    String d1="";
        String d2="";

         String min="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
   min=d1+d2;

return min;
}
public String MesdeSalida(String arreglo)throws PreexistingEntityException, Exception{

    char ds;
    char ds2;
     char ds3;
    ds=arreglo.charAt(4);
    ds2=arreglo.charAt(5);
        ds3=arreglo.charAt(6);
    String d1="";
        String d2="";
          String d3="";
         String mes="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
d3=String.valueOf(ds3);
    mes=d1+d2+d3;

return mes;
}

public String ci(String array){
    char d1;
    char d2;
    char d3;
     char d4;
      char d5;
       char d6;
        char d7;
         char d8;
          char d9;
           char d10;
            char d11;
            String ci="";
            d1=array.charAt(0);
             d2=array.charAt(1);
              d3=array.charAt(2);
               d4=array.charAt(3);
                d5=array.charAt(4);
                 d6=array.charAt(5);
                  d7=array.charAt(6);
                   d8=array.charAt(7);
                    d9=array.charAt(8);
                     d10=array.charAt(9);
                      d11=array.charAt(10);
                      String s1="";
                      String s2="";
                      String s3="";
                      String s4="";
                      String s5="";
                      String s6="";
                      String s7="";
                      String s8="";
                      String s9="";
                      String s10="";
                      String s11="";
                      s1=String.valueOf(d1);
                       s2=String.valueOf(d2);
                        s3=String.valueOf(d3);
                         s4=String.valueOf(d4);
                          s5=String.valueOf(d5);
                           s6=String.valueOf(d6);
                            s7=String.valueOf(d7);
                             s8=String.valueOf(d8);
                              s9=String.valueOf(d9);
                               s10=String.valueOf(d10);
                                s11=String.valueOf(d11);
                                ci=s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11;
return ci;
}
/*public String MesSalida(String arreglo){

    char ds;
    char ds2;
    char ds3;
    ds=arreglo.charAt(4);
    ds2=arreglo.charAt(5);
    ds3=arreglo.charAt(6);
String s ="S";
String j="J";
    String d1="";
        String d2="";
         String d3="";
         String mes="";
d1=String.valueOf(ds);
d2=String.valueOf(ds2);
d3=String.valueOf(ds3);

//if (d1.equals("S") && d2.equals("e")&& d3.equals("p") ){

if (d1.equals(s)){
mes="09";
    }
 else{

if (d1.equals(j))
    mes="01";

//if (d1.equals("F"))
// mes="02";
 }
return mes;

}*/
}