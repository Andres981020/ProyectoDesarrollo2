/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
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
    
    public usuarioLogica(){
    }

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
    
     public static void main(String args[]) throws Exception {
        List<Usuario> usuarios;
        usuarioLogica us = new usuarioLogica();
        usuarios = us.consultar();    
     }
}
