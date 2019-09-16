/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import modelo.Meta;
import persistencia.MetaJpaController;

/**
 *
 * @author Usuario
 */
public class metaLogica {
    private MetaJpaController meta = new MetaJpaController();

    public metaLogica(){
    }
    
    public void crearMeta(Meta m) throws Exception{
        if(m == null){
            throw new Exception("La meta  no contiene informacion");
        }
        Meta me = meta.findMeta(m.getCodigoMeta());
        if(me == null){
            meta.create(m);
        }else{
            throw new Exception("Meta ya registrado en la base de datos");
        }
    }
}
