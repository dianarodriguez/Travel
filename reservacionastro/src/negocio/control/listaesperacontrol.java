/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.control;

import datos.Listaespera;
import java.util.List;
import negocio.exceptions.PreexistingEntityException;
import negocio.jpa.ListaesperaJpaController;

/**
 *
 * @author MiPC
 */
public class listaesperacontrol {

public listaesperacontrol(){

}
ListaesperaJpaController controller= new ListaesperaJpaController();

public void adicionar(Listaespera obj)throws PreexistingEntityException, Exception {
    controller.create(obj);
}
public List<Listaespera> listaespera ()throws PreexistingEntityException, Exception {
   return controller.findListaesperaEntities();
}
}
