/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Pasaje;
import datos.Reservacion;
import datos.Viaje;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.exceptions.PreexistingEntityException;
import negocio.jpa.ReservacionJpaController;



/**
 *
 * @author wiliam
 */
public class reservacioncontrol {
ReservacionJpaController  controller= new ReservacionJpaController ();
public void adicionar(Reservacion obj) throws PreexistingEntityException, Exception{
        controller.create(obj);
 }
  public void actualizar(Reservacion obj) throws PreexistingEntityException, Exception{
        controller.edit(obj);
    }
    public void eliminar(Integer id) throws PreexistingEntityException, Exception{
        controller.destroy(id);
}

    public Reservacion buscar(Integer id) throws PreexistingEntityException, Exception{
       return controller.findReservacion(id);
    }
    public void cantreservaciones() throws PreexistingEntityException, Exception{
        controller.getReservacionCount();
}
   public int buscarasiento(int id)throws PreexistingEntityException, Exception{
         int nroa = 0;
            pasajecontrol pc = new pasajecontrol();
            for (int i = 0; i < pc.listar1().size(); i++) {
                Pasaje object = pc.listar1().get(i);
              if (object.getViaje().getNroviaje()==id && object.getDisponibilidad().equals("si")){
                   nroa = object.getNroasiento();break;
              }
                   
        }
             return nroa;
            }
public List<Reservacion> listar1() throws PreexistingEntityException, Exception{
     return   controller.findReservacionEntities();
}

public double ingresosMes(String mes, String year) throws PreexistingEntityException, Exception{
   double ingreso=0;
    for (int i = 0; i < listar1().size(); i++) {
        Reservacion object = listar1().get(i);
        if(object.getMessalida().equals(mes)&& object.getYearsalida().equals(year)){
            ingreso+=object.getPrecio();
        }
    }
   return ingreso;
}
/*public void cambiardisponibilidad(int id){
    String var="no";
    for (int i = 0; i < listar1().size(); i++) {
        Pasaje p= new Pasaje;
        Reservacion object = listar1().get(i);
        if(object.getNroreserv()==id)
            p.setDisponibilidad(null);

    }
}*/
}
