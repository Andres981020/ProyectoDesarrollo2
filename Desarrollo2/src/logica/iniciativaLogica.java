/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Iniciativa;
import modelo.Objetivo;
import persistencia.IniciativaJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class iniciativaLogica {
    private IniciativaJpaController iniciativa = new IniciativaJpaController();
    
    public iniciativaLogica(){
    }
    
    public void crearIniciativa(Iniciativa i) throws Exception{
        if(i == null){
            throw new Exception("La iniciativa no contiene informacion");
        }
        Iniciativa in = iniciativa.findIniciativa(i.getCodigoIniciativa());
        if(in == null){
            iniciativa.create(i);
        }else{
            throw new Exception("Iniciativa ya registrado en la base de datos");
        }
    }
    
    public void eliminarIniciativa(int i) throws NonexistentEntityException{
        iniciativa.destroy(i);
    }
    
    public Iniciativa buscarIniciativaDescripcion(String descripcion, List<Objetivo> objetivos){
        Iniciativa i = new Iniciativa(); 
        List<Iniciativa> iniciativas;
        for(Objetivo o: objetivos){
            iniciativas = o.getIniciativaList();
            for(Iniciativa ini: iniciativas){
                if(ini.getDescripcionIniciativa().equals(descripcion)){
                    i = ini;
                }
            }
        }
        return i;
    }
    
    public void editarIniciativa(Iniciativa i) throws Exception{
        iniciativa.edit(i);
    }
}
