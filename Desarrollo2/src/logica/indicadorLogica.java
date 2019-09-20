/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Indicador;
import modelo.Objetivo;
import persistencia.IndicadorJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class indicadorLogica {
    private IndicadorJpaController indicador = new IndicadorJpaController();
    
    public indicadorLogica(){
    }
    
    public void crearIndicador(Indicador i) throws Exception{
        if(i == null){
            throw new Exception("El indicador no contiene informacion");
        }
        Indicador ind = indicador.findIndicador(i.getCodigoIndicador());
        if(ind == null){
            indicador.create(i);
        }else{
            throw new Exception("Indicador ya registrado en la base de datos");
        }
    }
    
    public void eliminarIndicador(int i) throws NonexistentEntityException{
            indicador.destroy(i);
    }
    
    public Indicador buscarIndicadorDescripcion(String descripcion, List<Objetivo> objetivos){
        Indicador i = new Indicador(); 
        List<Indicador> indicadores;
        for(Objetivo o: objetivos){
            indicadores = o.getIndicadorList();
            for(Indicador ind: indicadores){
                if(ind.getDescripcionIndicador().equals(descripcion)){
                 i = ind;
                }
            }
        }
        return i;
    }
    
    public void editarIndicador(Indicador ind) throws Exception{
        indicador.edit(ind);
    }
}
