/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Usuario;
import persistencia.ObjetivoJpaController;
import persistencia.UsuarioJpaController;

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
    
     public static void main(String args[]) throws Exception {
        List<Usuario> usuarios;
        usuarioLogica us = new usuarioLogica();
        usuarios = us.consultar();    
     }
}
