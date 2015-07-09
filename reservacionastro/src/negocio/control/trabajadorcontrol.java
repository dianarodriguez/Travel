/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Trabajador;
import negocio.exceptions.PreexistingEntityException;
import negocio.jpa.TrabajadorJpaController;


/**
 *
 * @author wiliam
 */
public class trabajadorcontrol {
TrabajadorJpaController controller= new TrabajadorJpaController();
 public void adicionar(Trabajador obj) throws PreexistingEntityException, Exception{
        controller.create(obj);
 }
  public void actualizar(Trabajador obj) throws PreexistingEntityException, Exception{
        controller.edit(obj);
    }
    public void eliminar(String id) throws PreexistingEntityException, Exception{
        controller.destroy(id);
}

    public void buscar(String id) throws PreexistingEntityException, Exception{
        controller.findTrabajador(id);
    }
    public void canttrabajadores() throws PreexistingEntityException, Exception{
        controller.getTrabajadorCount();
}
public void listar1() throws PreexistingEntityException, Exception{
        controller.findTrabajadorEntities();
}
}
