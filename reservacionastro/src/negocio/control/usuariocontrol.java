/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Usuario;
import java.util.List;
import negocio.exceptions.PreexistingEntityException;
import negocio.jpa.UsuarioJpaController;

/**
 *
 * @author wiliam
 */
public class usuariocontrol {
UsuarioJpaController controller= new UsuarioJpaController();
 public void adicionar(Usuario obj) throws PreexistingEntityException, Exception{
        controller.create(obj);
 }
  public void actualizar(Usuario obj) throws PreexistingEntityException, Exception{
        controller.edit(obj);
    }
    public void eliminar(String id) throws PreexistingEntityException, Exception{
        controller.destroy(id);
}

    public void buscar(String id) throws PreexistingEntityException, Exception{
        controller.findUsuario(id);
    }
    public void cantusuario() throws PreexistingEntityException, Exception{
        controller.getUsuarioCount();
}
public List<Usuario> listar1() throws PreexistingEntityException, Exception{
      return  controller.findUsuarioEntities();
}
}
