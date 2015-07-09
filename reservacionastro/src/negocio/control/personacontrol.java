/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Persona;
import negocio.exceptions.PreexistingEntityException;
import negocio.jpa.PersonaJpaController;



/**
 *
 * @author wiliam
 */
public class personacontrol {
PersonaJpaController controller= new PersonaJpaController();
 public void adicionar(Persona obj) throws PreexistingEntityException, Exception{
        controller.create(obj);
 }
  public void actualizar(Persona obj) throws PreexistingEntityException, Exception{
        controller.edit(obj);
    }
    public void eliminar(String id) throws PreexistingEntityException, Exception{
        controller.destroy(id);
}

    public void buscar(String id) throws PreexistingEntityException, Exception{
        controller.findPersona(id);
    }
    public void cantpasajes() throws PreexistingEntityException, Exception{
        controller.getPersonaCount();
}
public void listar1() throws PreexistingEntityException, Exception{
        controller.findPersonaEntities();
}
}
