/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Temporal1;
import java.util.List;
import negocio.jpa.Temporal1JpaController;
import negocio.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author MiPC
 */
public class temporalcontrol {
private Temporal1JpaController controller;
public temporalcontrol(){
    controller = new Temporal1JpaController();
}

public List<Temporal1> listartemporal(){
     return controller.findTemporal1Entities();
}
public void adicionar(Temporal1 obj) throws PreexistingEntityException, Exception {
    controller.create(obj);
}
public void eliminar (Integer id)throws PreexistingEntityException, Exception{
     controller.destroy(id);
}

}
