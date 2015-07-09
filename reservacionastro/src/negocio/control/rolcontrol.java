/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Rol;
import java.util.List;
import negocio.exceptions.PreexistingEntityException;
import negocio.jpa.RolJpaController;



/**
 *
 * @author wiliam
 */
public class rolcontrol {
RolJpaController controller= new RolJpaController();
 public void adicionar(Rol obj) throws PreexistingEntityException, Exception{
        controller.create(obj);
 }
  public void actualizar(Rol obj) throws PreexistingEntityException, Exception{
        controller.edit(obj);
    }
    public void eliminar(Integer id) throws PreexistingEntityException, Exception{
        controller.destroy(id);
}

    public void buscar(Integer id) throws PreexistingEntityException, Exception{
        controller.findRol(id);
    }
    public void cantusuario() throws PreexistingEntityException, Exception{
        controller.getRolCount();
}
public List<Rol> listar1() throws PreexistingEntityException, Exception{
       return controller.findRolEntities();
}
}
