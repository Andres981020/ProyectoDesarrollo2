/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import modelo.Iniciativa;
import persistencia.IniciativaJpaController;

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
    
}
