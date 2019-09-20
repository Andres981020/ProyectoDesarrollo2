/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Meta;
import modelo.Objetivo;
import persistencia.MetaJpaController;
import persistencia.exceptions.NonexistentEntityException;

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
    
    public void eliminarMeta(int m) throws NonexistentEntityException{
        meta.destroy(m);
    }
    
    public Meta buscarMetaDescripcion(String descripcion, List<Objetivo>objetivos){
        Meta met = new Meta(); 
        List<Meta> metas;
        for(Objetivo o: objetivos){
            metas = o.getMetaList();
            for(Meta m: metas){
                if(m.getDescripcionMeta().equals(descripcion)){
                    met = m;
                }
            }
        }
        return met;
    }
    
    public void editarMeta(Meta m) throws Exception{
        meta.edit(m);
    }
}
