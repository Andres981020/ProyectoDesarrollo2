/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Indicador;
import modelo.Objetivo;
import persistencia.ObjetivoJpaController;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;

public class objetivoLogica {
    private ObjetivoJpaController objetivo = new ObjetivoJpaController();
    
    public objetivoLogica(){
    }
    
    //Funciones relacionadas con los objetivos
    
    public List<Objetivo> objetivosPerspectivaCliente(){
        List<Objetivo> objetivos = objetivo.findObjetivoEntities();
        ArrayList<Objetivo> objetivosPerspectiva = new ArrayList<Objetivo>();
        for(Objetivo o: objetivos){
            if(o.getPerspectiva().equals("Perspectiva cliente")){
                objetivosPerspectiva.add(o);
            }
        }
        return objetivosPerspectiva;
    }
    
    public List<Objetivo> objetivosPerspectivaFinanciera(){
        List<Objetivo> objetivos = objetivo.findObjetivoEntities();
        ArrayList<Objetivo> objetivosPerspectiva = new ArrayList<Objetivo>();
        for(Objetivo o: objetivos){
            if(o.getPerspectiva().equals("Perspectiva financiera")){
                objetivosPerspectiva.add(o);
            }
        }
        return objetivosPerspectiva;
    }
    
    public List<Objetivo> objetivosPerspectivaCrecimiento(){
        List<Objetivo> objetivos = objetivo.findObjetivoEntities();
        ArrayList<Objetivo> objetivosPerspectiva = new ArrayList<Objetivo>();
        for(Objetivo o: objetivos){
            if(o.getPerspectiva().equals("Perspectiva crecimiento y aprendizaje")){
                objetivosPerspectiva.add(o);
            }
        }
        return objetivosPerspectiva;
    }
    
    public List<Objetivo> objetivosPerspectivaProcesos(){
        List<Objetivo> objetivos = objetivo.findObjetivoEntities();
        ArrayList<Objetivo> objetivosPerspectiva = new ArrayList<Objetivo>();
        for(Objetivo o: objetivos){
            if(o.getPerspectiva().equals("Perspectiva procesos internos")){
                objetivosPerspectiva.add(o);
            }
        }
        return objetivosPerspectiva;
    }
    
    public void crearObjetivo(Objetivo o) throws Exception{
        if(o == null){
            throw new Exception("El objetivo no contiene informacion");
        }
        Objetivo ob = objetivo.findObjetivo(o.getCodigoObjetivo());
        if(ob == null){
            objetivo.create(o);
        }else{
            throw new Exception("Objetivo ya registrado en la base de datos");
        }
    }
    
    public void eliminarObjetivo(String u) throws IllegalOrphanException{
        try {
            objetivo.destroy(u);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(usuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Objetivo buscarObjetivo(String codigo){
        return objetivo.findObjetivo(codigo);
    }
    
    public Objetivo buscarObjetivoDescripcion(String descripcion, List<Objetivo> objetivos){
        Objetivo obj = new Objetivo(); 
        for(Objetivo o: objetivos){
            if(o.getDescripcionObjetivo().equals(descripcion)){
                obj = o;
            }
        }
        return obj;
    }
    
    public static void main(String args[]) throws Exception {
        List<Objetivo> objetivos;
        objetivoLogica ob = new objetivoLogica();
        objetivos = ob.objetivosPerspectivaCliente();
        
        for(Objetivo o: objetivos){
            List<Indicador> indicadores;
            indicadores = o.getIndicadorList();
            for(Indicador i: indicadores){
                System.out.println(i.getDescripcionIndicador());
            }
        }
     }
    
}
