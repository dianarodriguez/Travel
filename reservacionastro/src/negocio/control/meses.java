/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

/**
 *
 * @author MiPC
 */
public class meses {

    private String mes[]= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
 
    public meses(){
    
    }

    public String getMes(String m){
    int x =0;
        for(int i=0;i<12;i++){
        if(mes[i].equalsIgnoreCase(m)){
            x=i+1;
        break;}
        }
    return String.valueOf(x);
    }
}
