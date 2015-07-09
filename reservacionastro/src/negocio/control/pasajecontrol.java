/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.control;

import datos.Pasaje;
import datos.Viaje;
import java.util.List;

import negocio.jpa.PasajeJpaController;
import negocio.jpa.exceptions.PreexistingEntityException;
import org.eclipse.persistence.internal.jpa.parsing.LengthNode;

/**
 *
 * @author wiliam
 */
public class pasajecontrol {

    public pasajecontrol() {
    }
    PasajeJpaController controller = new PasajeJpaController();

    public void adicionar(Pasaje obj) throws PreexistingEntityException, Exception {
        controller.create(obj);
    }

    public void actualizar(Pasaje obj) throws PreexistingEntityException, Exception {
        controller.edit(obj);
    }

    public void eliminar(Integer id) throws PreexistingEntityException, Exception {
        controller.destroy(id);
    }

    public void buscar(Integer id) throws PreexistingEntityException, Exception {
        controller.findPasaje(id);
    }

    public void cantpasajes() throws PreexistingEntityException, Exception {
        controller.getPasajeCount();
    }

    public List<Pasaje> listar1() throws PreexistingEntityException, Exception {
        return controller.findPasajeEntities();
    }

    public int cantPasajes(String ruta, String dia, String mes, String year, String hora, String min) throws Exception {
        viajecontrol v = new viajecontrol();

        int cont = 0;
        for (int j = 0; j < listar1().size(); j++) {
            Pasaje object = listar1().get(j);
            if (object.getViaje().getNroviaje() == v.buscarid(ruta, dia, mes, year, hora, min)) {
                cont++;
            }

        }

        return cont;
    }

    /*public Pasaje[] pasajesdisponibles(String ruta,String dia,String mes,String year,String hora,String min) throws PreexistingEntityException, PreexistingEntityException, Exception{
    Pasaje[] listPdis;
    viajecontrol v=new viajecontrol();
    Viaje viaje=new Viaje();
    String var="si";
    int j=0;
    listPdis= new Pasaje[viaje.getPasajeList().size()];
    for (int i = 0; i < viaje.getPasajeList().size(); i++) {
    Pasaje object = listar1().get(i);
    if ((object.getDestino().equals(ruta) && object.getDiasalida().equals(dia) &&object.getMessalida().equals(mes)&&object.getYearsalida().equals(year)&&object.getHorasalida().equals(hora)&&object.getMinutosalida().equals(min))&& object.getDisponibilidad().equals(var)){
    listPdis[j]=object;
    j++;
    }
    }
    return listPdis;


    }*/
    public int cantPasajesD(int idv) throws PreexistingEntityException, PreexistingEntityException, Exception {
       

        String var = "si";
        int cont = 0;
        for (int i = 0; i < listar1().size(); i++) {
            Pasaje object = listar1().get(i);

           // if (object.getNrovia() == v.buscar(idv)) {
            if (object.getViaje().getNroviaje() == idv) {
                if (object.getDisponibilidad().equals(var)) {
                    cont++;
                }
            }
        }
        return cont;
    }

public int nroasiento(int id) throws PreexistingEntityException, Exception {



    String var="si";
    int nroa = 0;
        for (int i = 0; i < listar1().size(); i++) {
            Pasaje object = listar1().get(i);
        if(object.getViaje().getNroviaje()==id){
               if( object.getDisponibilidad().equals(var)){
                     nroa=object.getNroasiento();break;
               }
              
            }
        }
    return nroa;
}
}

