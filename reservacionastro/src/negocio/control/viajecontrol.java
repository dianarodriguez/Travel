/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Pasaje;
import datos.Viaje;
import java.util.List;
import negocio.exceptions.IllegalOrphanException;
import negocio.exceptions.NonexistentEntityException;

import negocio.exceptions.PreexistingEntityException;
import negocio.jpa.ViajeJpaController;

/**
 *
 * @author wiliam
 */
public class viajecontrol {

    private ViajeJpaController controller;

    public viajecontrol() {
        controller = new ViajeJpaController();
    }
    

  

    public void adicionar(Viaje obj) throws PreexistingEntityException, Exception{
        controller.create(obj);
    }
    public void actualizar(Viaje obj) throws PreexistingEntityException, Exception{
        controller.edit(obj);
    }
    public void eliminar(Integer id)  throws PreexistingEntityException, Exception{
        controller.destroy(id);
}

    public Viaje buscar(Integer id) throws PreexistingEntityException, Exception{
       return  controller.findViaje(id);
    }
    public void cantviajes() throws PreexistingEntityException, Exception{
        controller.getViajeCount();
}
public List<Viaje> listar1() {

   return controller.findViajeEntities();
}
public int buscarid(String ruta,String dia,String mes,String year,String hora,String min) throws Exception{
    for (int i = 0; i < listar1().size(); i++) {
        Viaje object = listar1().get(i);
 if (object.getRuta().equals(ruta) && object.getDiasalida().equals(dia) && object.getMessalida().equals(mes) && object.getYearsalida().equals(year) && object.getHorasalida().equals(hora) && object.getMinutosalida().equals(min)){
    return object.getNroviaje();
    
}
}
return -1;
}
public int buscaridp(String ruta,String dia,String mes,String year,String hora,String min)throws PreexistingEntityException, Exception {
int idreporte=0;
    Viaje v = new Viaje();
    for (int i = 0; i < listar1().size(); i++) {
        v = listar1().get(i);
         if( v.getRuta().equals(ruta) && v.getDiasalida().equals(dia) &&v.getMessalida().equals(mes)&&v.getYearsalida().equals(year)&&v.getHorasalida().equals(hora)&&v.getMinutosalida().equals(min))
idreporte= v.getNroviaje();
    }
   
    return idreporte;
}
public Viaje[] buscarviaje(String ruta,String dia,String mes,String year){
    int idreporte=0;
 //   List<Viaje> lv= new List<Viaje>(1000);
    Viaje[] varray = new Viaje[100];
    int pos=0;
       Viaje v = new Viaje();

    for (int i = 0; i < listar1().size(); i++) {
        v = listar1().get(i);
         if( v.getRuta().equals(ruta) && v.getDiasalida().equals(dia) &&v.getMessalida().equals(mes)&&v.getYearsalida().equals(year)){
             idreporte= v.getNroviaje();
        varray[pos]=v;
        pos++;
         }

    }

    return varray;
}
public int nroasiento(int id) throws PreexistingEntityException, Exception {
 Viaje v = new Viaje();

 viajecontrol vc = new viajecontrol();
 int idr=id;
    String var="si";
    int nroa = 0;
        for (int i = 0; i < v.getPasajeList().size(); i++) {
            Pasaje object = v.getPasajeList().get(i);
        if(object.getViaje().getNroviaje()==id){

               if( object.getDisponibilidad().equals(var))
                nroa=object.getNroasiento();break;
            }
        }
    return nroa;
}
public void nodisponible(int id) throws PreexistingEntityException, Exception{
 Viaje v = new Viaje();

 viajecontrol vc = new viajecontrol();
 int idr=id;
    String var="no";
  
        for (int i = 0; i < v.getPasajeList().size(); i++) {
            Pasaje object = v.getPasajeList().get(i);
        if(object.getViaje()==vc.buscar(idr))
          object.setDisponibilidad(var);break;
}
}}