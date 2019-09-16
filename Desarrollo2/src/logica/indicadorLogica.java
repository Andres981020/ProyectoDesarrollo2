/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import modelo.Indicador;
import persistencia.IndicadorJpaController;

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
    
}
