/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Indicador;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;
import modelo.Usuario;
import persistencia.IndicadorJpaController;
import persistencia.IniciativaJpaController;
import persistencia.MetaJpaController;
import persistencia.ObjetivoJpaController;
import persistencia.UsuarioJpaController;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class usuarioLogica {
    
    private UsuarioJpaController usuario = new UsuarioJpaController();
    private ObjetivoJpaController objetivo = new ObjetivoJpaController();
    private IniciativaJpaController iniciativa = new IniciativaJpaController();
    private IndicadorJpaController indicador = new IndicadorJpaController();
    private MetaJpaController meta = new MetaJpaController();
    
    public usuarioLogica(){
    }
    
    //Funciones relacionadas con los usuarios
    
    public List<Usuario> consultar(){
        return usuario.findUsuarioEntities();
    }
    
    public Usuario encontrar(String a){
        return usuario.findUsuario(a);
    }
    
    public void eliminarUsuario(String u) throws IllegalOrphanException{
        try {
            usuario.destroy(u);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(usuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void crearUsuario(Usuario u) throws Exception{
        if(u == null){
            throw new Exception("El usuario no contiene informacion");
        }
        Usuario us = usuario.findUsuario(u.getCedula());
        if(us == null){
            usuario.create(u);
        }else{
            throw new Exception("Usuario ya registrado en la base de datos");
        }
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
    
     public static void main(String args[]) throws Exception {
        List<Usuario> usuarios;
        usuarioLogica us = new usuarioLogica();
        usuarios = us.consultar();    
     }
}
