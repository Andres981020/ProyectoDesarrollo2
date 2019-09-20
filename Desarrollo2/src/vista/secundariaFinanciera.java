/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.indicadorLogica;
import logica.iniciativaLogica;
import logica.metaLogica;
import logica.objetivoLogica;
import modelo.Indicador;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class secundariaFinanciera extends javax.swing.JFrame {
    
    objetivoLogica objetivoL = new objetivoLogica();
    iniciativaLogica iniciativaL = new iniciativaLogica();
    indicadorLogica indicadorL = new indicadorLogica();
    metaLogica metaL = new metaLogica();

    int fila;
    int columna;
    
    public secundariaFinanciera() {
        super("Objetivos de perspectivas");
        initComponents();
        
        int pos; 
        
        DefaultTableModel tabla1 = new DefaultTableModel();
        tabla1.addColumn("Descripcion del objetivo");
        tabla1.addColumn("Indicador");
        tabla1.addColumn("Iniciativa");
        tabla1.addColumn("Meta");
        
        DefaultTableModel tabla2 = new DefaultTableModel();
        tabla2.addColumn("Descripcion del objetivo");
        tabla2.addColumn("Indicador");
        tabla2.addColumn("Iniciativa");
        tabla2.addColumn("Meta");
        
        DefaultTableModel tabla3 = new DefaultTableModel();
        tabla3.addColumn("Descripcion del objetivo");
        tabla3.addColumn("Indicador");
        tabla3.addColumn("Iniciativa");
        tabla3.addColumn("Meta");
        
        DefaultTableModel tabla4 = new DefaultTableModel();
        tabla4.addColumn("Descripcion del objetivo");
        tabla4.addColumn("Indicador");
        tabla4.addColumn("Iniciativa");
        tabla4.addColumn("Meta");
        
        List<Objetivo> objetivosC = objetivoL.objetivosPerspectivaCliente();
        List<Objetivo> objetivosF = objetivoL.objetivosPerspectivaFinanciera();
        List<Objetivo> objetivosCA = objetivoL.objetivosPerspectivaCrecimiento();
        List<Objetivo> objetivosP = objetivoL.objetivosPerspectivaProcesos();
        
        String[] datos1 = new String[4];
        String[] datos2 = new String[4];
        String[] datos3 = new String[4];
        String[] datos4 = new String[4];
        
        perspectivaC.setModel(tabla1);
        perspectivaF.setModel(tabla2);
        perspectivaCA.setModel(tabla3);
        perspectivaP.setModel(tabla4);
        
        perspectivaC.setEnabled(false);
        perspectivaF.setEnabled(true);
        perspectivaCA.setEnabled(false);
        perspectivaP.setEnabled(false);
        
        //Tabla perspectiva Cliente
        for(Objetivo o: objetivosC){
            pos = 0;
            
            List<Indicador> indicadores;
            List<Iniciativa> iniciativas;
            List<Meta> metas;
            
            indicadores = o.getIndicadorList();
            iniciativas = o.getIniciativaList();
            metas = o.getMetaList();
            
            datos1[0] = o.getDescripcionObjetivo();
           
            if(o.getIndicadorList().size() > o.getIniciativaList().size() && o.getIndicadorList().size() > o.getMetaList().size()){
                for(Indicador i: indicadores){

                    if(pos >= o.getMetaList().size() || pos >= o.getIniciativaList().size()){
                        datos1[0] = o.getDescripcionObjetivo();
                        datos1[1] = i.getDescripcionIndicador();
                        datos1[2] = "";
                        datos1[3] = "";
                        tabla1.addRow(datos1);
                    }
                    else{
                        datos1[0] = o.getDescripcionObjetivo();
                        datos1[1] = i.getDescripcionIndicador();
                        datos1[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos1[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla1.addRow(datos1);
                    }    
                    pos++;
                }
            }
            if(o.getIniciativaList().size() >= o.getIndicadorList().size() && o.getIniciativaList().size() >= o.getMetaList().size()){
                for(Iniciativa in: iniciativas){
                    if(pos >= o.getIndicadorList().size() || pos >= o.getMetaList().size()){
                        datos1[0] = o.getDescripcionObjetivo();
                        datos1[1] = "";
                        datos1[2] = in.getDescripcionIniciativa();
                        datos1[3] = "";
                        tabla1.addRow(datos1);
                    }

                    else{
                        datos1[0] = o.getDescripcionObjetivo();
                        datos1[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos1[2] = in.getDescripcionIniciativa();
                        datos1[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla1.addRow(datos1);
                    }    
                    pos++;
                }
            }
            
            if(o.getMetaList().size() > o.getIndicadorList().size() && o.getMetaList().size() > o.getIniciativaList().size()){
                for(Meta m: metas){
                    if(pos >= o.getIniciativaList().size() || pos >= o.getIndicadorList().size()){
                        datos1[0] = o.getDescripcionObjetivo();
                        datos1[1] = "";
                        datos1[2] = "";
                        datos1[3] = m.getDescripcionMeta();
                        tabla1.addRow(datos1);
                        }
                    else{
                        datos1[0] = o.getDescripcionObjetivo();
                        datos1[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos1[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos1[3] = m.getDescripcionMeta();
                        tabla1.addRow(datos1);
                        }    
                    pos++;
                }
            }
        }
        
        //Perspectiva Financiera
        for(Objetivo o: objetivosF){
            pos = 0;
            
            List<Indicador> indicadores;
            List<Iniciativa> iniciativas;
            List<Meta> metas;
            
            indicadores = o.getIndicadorList();
            iniciativas = o.getIniciativaList();
            metas = o.getMetaList();
            
            datos2[0] = o.getDescripcionObjetivo();
           
            if(o.getIndicadorList().size() > o.getIniciativaList().size() && o.getIndicadorList().size() > o.getMetaList().size()){
                for(Indicador i: indicadores){

                    if(pos >= o.getMetaList().size() || pos >= o.getIniciativaList().size()){
                        datos2[0] = o.getDescripcionObjetivo();
                        datos2[1] = i.getDescripcionIndicador();
                        datos2[2] = "";
                        datos2[3] = "";
                        tabla2.addRow(datos2);
                    }
                    else{
                        datos2[0] = o.getDescripcionObjetivo();
                        datos2[1] = i.getDescripcionIndicador();
                        datos2[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos2[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla2.addRow(datos2);
                    }    
                    pos++;
                }
            }
            if(o.getIniciativaList().size() >= o.getIndicadorList().size() && o.getIniciativaList().size() >= o.getMetaList().size()){
                for(Iniciativa in: iniciativas){
                    if(pos >= o.getIndicadorList().size() || pos >= o.getMetaList().size()){
                        datos2[0] = o.getDescripcionObjetivo();
                        datos2[1] = "";
                        datos2[2] = in.getDescripcionIniciativa();
                        datos2[3] = "";
                        tabla2.addRow(datos2);
                    }

                    else{
                        datos2[0] = o.getDescripcionObjetivo();
                        datos2[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos2[2] = in.getDescripcionIniciativa();
                        datos2[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla2.addRow(datos2);
                    }    
                    pos++;
                }
            }
            
            if(o.getMetaList().size() > o.getIndicadorList().size() && o.getMetaList().size() > o.getIniciativaList().size()){
                for(Meta m: metas){
                    if(pos >= o.getIniciativaList().size() || pos >= o.getIndicadorList().size()){
                        datos2[0] = o.getDescripcionObjetivo();
                        datos2[1] = "";
                        datos2[2] = "";
                        datos2[3] = m.getDescripcionMeta();
                        tabla2.addRow(datos2);
                        }
                    else{
                        datos2[0] = o.getDescripcionObjetivo();
                        datos2[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos2[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos2[3] = m.getDescripcionMeta();
                        tabla2.addRow(datos2);
                        }    
                    pos++;
                }
            }
        }
        //Perspectiva crecimiento y aprendizaje
        for(Objetivo o: objetivosCA){
            pos = 0;
            
            List<Indicador> indicadores;
            List<Iniciativa> iniciativas;
            List<Meta> metas;
            
            indicadores = o.getIndicadorList();
            iniciativas = o.getIniciativaList();
            metas = o.getMetaList();
            
            datos3[0] = o.getDescripcionObjetivo();
           
            if(o.getIndicadorList().size() > o.getIniciativaList().size() && o.getIndicadorList().size() > o.getMetaList().size()){
                for(Indicador i: indicadores){

                    if(pos >= o.getMetaList().size() || pos >= o.getIniciativaList().size()){
                        datos3[0] = o.getDescripcionObjetivo();
                        datos3[1] = i.getDescripcionIndicador();
                        datos3[2] = "";
                        datos3[3] = "";
                        tabla3.addRow(datos3);
                    }
                    else{
                        datos3[0] = o.getDescripcionObjetivo();
                        datos3[1] = i.getDescripcionIndicador();
                        datos3[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos3[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla3.addRow(datos3);
                    }    
                    pos++;
                }
            }
            if(o.getIniciativaList().size() >= o.getIndicadorList().size() && o.getIniciativaList().size() >= o.getMetaList().size()){
                for(Iniciativa in: iniciativas){
                    if(pos >= o.getIndicadorList().size() || pos >= o.getMetaList().size()){
                        datos3[0] = o.getDescripcionObjetivo();
                        datos3[1] = "";
                        datos3[2] = in.getDescripcionIniciativa();
                        datos3[3] = "";
                        tabla3.addRow(datos3);
                    }

                    else{
                        datos3[0] = o.getDescripcionObjetivo();
                        datos3[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos3[2] = in.getDescripcionIniciativa();
                        datos3[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla3.addRow(datos3);
                    }    
                    pos++;
                }
            }
            
            if(o.getMetaList().size() > o.getIndicadorList().size() && o.getMetaList().size() > o.getIniciativaList().size()){
                for(Meta m: metas){
                    if(pos >= o.getIniciativaList().size() || pos >= o.getIndicadorList().size()){
                        datos3[0] = o.getDescripcionObjetivo();
                        datos3[1] = "";
                        datos3[2] = "";
                        datos3[3] = m.getDescripcionMeta();
                        tabla3.addRow(datos3);
                        }
                    else{
                        datos3[0] = o.getDescripcionObjetivo();
                        datos3[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos3[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos3[3] = m.getDescripcionMeta();
                        tabla3.addRow(datos3);
                        }    
                    pos++;
                }
            }
        }
        //Perspectiva Procesos internos
        for(Objetivo o: objetivosP){
            pos = 0;
            
            List<Indicador> indicadores;
            List<Iniciativa> iniciativas;
            List<Meta> metas;
            
            indicadores = o.getIndicadorList();
            iniciativas = o.getIniciativaList();
            metas = o.getMetaList();
            
            datos4[0] = o.getDescripcionObjetivo();
           
            if(o.getIndicadorList().size() > o.getIniciativaList().size() && o.getIndicadorList().size() > o.getMetaList().size()){
                for(Indicador i: indicadores){

                    if(pos >= o.getMetaList().size() || pos >= o.getIniciativaList().size()){
                        datos4[0] = o.getDescripcionObjetivo();
                        datos4[1] = i.getDescripcionIndicador();
                        datos4[2] = "";
                        datos4[3] = "";
                        tabla4.addRow(datos4);
                    }
                    else{
                        datos4[0] = o.getDescripcionObjetivo();
                        datos4[1] = i.getDescripcionIndicador();
                        datos4[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos4[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla4.addRow(datos4);
                    }    
                    pos++;
                }
            }
            if(o.getIniciativaList().size() >= o.getIndicadorList().size() && o.getIniciativaList().size() >= o.getMetaList().size()){
                for(Iniciativa in: iniciativas){
                    if(pos >= o.getIndicadorList().size() || pos >= o.getMetaList().size()){
                        datos4[0] = o.getDescripcionObjetivo();
                        datos4[1] = "";
                        datos4[2] = in.getDescripcionIniciativa();
                        datos4[3] = "";
                        tabla4.addRow(datos4);
                    }

                    else{
                        datos4[0] = o.getDescripcionObjetivo();
                        datos4[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos4[2] = in.getDescripcionIniciativa();
                        datos4[3] = o.getMetaList().get(pos).getDescripcionMeta();
                        tabla4.addRow(datos4);
                    }    
                    pos++;
                }
            }
            
            if(o.getMetaList().size() > o.getIndicadorList().size() && o.getMetaList().size() > o.getIniciativaList().size()){
                for(Meta m: metas){
                    if(pos >= o.getIniciativaList().size() || pos >= o.getIndicadorList().size()){
                        datos4[0] = o.getDescripcionObjetivo();
                        datos4[1] = "";
                        datos4[2] = "";
                        datos4[3] = m.getDescripcionMeta();
                        tabla4.addRow(datos4);
                        }
                    else{
                        datos4[0] = o.getDescripcionObjetivo();
                        datos4[1] = o.getIndicadorList().get(pos).getDescripcionIndicador();
                        datos4[2] = o.getIniciativaList().get(pos).getDescripcionIniciativa();
                        datos4[3] = m.getDescripcionMeta();
                        tabla4.addRow(datos4);
                        }    
                    pos++;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        perspectivaCA = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        perspectivaP = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        perspectivaF = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        perspectivaC = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        agregarPF = new javax.swing.JButton();
        modificarPF = new javax.swing.JButton();
        eliminarPF = new javax.swing.JButton();
        tipo2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        perspectivaCA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(perspectivaCA);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 580, 250));

        perspectivaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(perspectivaP);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 580, 250));

        perspectivaF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        perspectivaF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perspectivaFMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(perspectivaF);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 580, 240));

        perspectivaC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(perspectivaC);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 580, 240));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Perspectiva procesos internos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, 330, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Perspectiva cliente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 150, 30));

        agregarPF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarPF.setText("Agregar");
        agregarPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPFActionPerformed(evt);
            }
        });
        getContentPane().add(agregarPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, -1, -1));

        modificarPF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modificarPF.setText("Modificar");
        modificarPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarPFActionPerformed(evt);
            }
        });
        getContentPane().add(modificarPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 300, -1, -1));

        eliminarPF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eliminarPF.setText("Eliminar");
        eliminarPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPFActionPerformed(evt);
            }
        });
        getContentPane().add(eliminarPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 300, -1, -1));

        tipo2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Iniciativa", "Indicador", "Meta" }));
        tipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo2ActionPerformed(evt);
            }
        });
        getContentPane().add(tipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 300, 130, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Perspectiva financiera");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 330, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Perspectiva crecimiento y aprendizaje");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 290, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/verde.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 630, 320));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/azul.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 620, 350));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/morado.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 340, 630, 330));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/rojo.jpg"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 20, 630, 320));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -5, 1260, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPFActionPerformed
        String objetivo;
        Date objDate = new Date();
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaFinanciera();
        objetivo = (String)perspectivaF.getValueAt(fila, 0);
        Objetivo obj = objetivoL.buscarObjetivoDescripcion(objetivo, objetivos);
        if(tipo2.getSelectedItem().toString().equals("Iniciativa")){
            try {
                Iniciativa ini = new Iniciativa();
                String descripcionIni = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la iniciativa",
                        "Agregar iniciativa", JOptionPane.INFORMATION_MESSAGE);
                ini.setCodigoIniciativa(0);
                ini.setDescripcionIniciativa(descripcionIni);
                ini.setFechaIniciativa(objDate);
                ini.setIniciativaObjetivo(obj);
                iniciativaL.crearIniciativa(ini);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Registro de iniciativa satisfactorio");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
        
        if(tipo2.getSelectedItem().toString().equals("Indicador")){
            try {
                Indicador i = new Indicador();
                String descripcionInd = JOptionPane.showInputDialog(null, "Ingrese la descripcion del indicador",
                        "Agregar indicador", JOptionPane.INFORMATION_MESSAGE);
                i.setCodigoIndicador(0);
                i.setDescripcionIndicador(descripcionInd);
                i.setFechaIndicador(objDate);
                i.setIndicadorObjetivo(obj);
                indicadorL.crearIndicador(i);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Registro de indicador satisfactorio");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
        
        if(tipo2.getSelectedItem().toString().equals("Meta")){
            try {
                Meta m = new Meta();
                String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la meta",
                        "Agregar meta", JOptionPane.INFORMATION_MESSAGE);
                m.setCodigoMeta(0);
                m.setDescripcionMeta(descripcionMet);
                m.setFechaMeta(objDate);
                m.setMetaObjetivo(obj);
                metaL.crearMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Registro de meta satisfactorio");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_agregarPFActionPerformed

    private void eliminarPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPFActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaFinanciera();
        if(columna == 1){
            try {
                String descripcion = (String)perspectivaF.getValueAt(fila, columna);
                Indicador indicador = new Indicador();
                indicador = indicadorL.buscarIndicadorDescripcion(descripcion, objetivos);
                indicadorL.eliminarIndicador(indicador.getCodigoIndicador());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Eliminacion de indicador satisfactoria");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
        else if(columna == 2){
            try {
                String descripcion = (String)perspectivaF.getValueAt(fila, columna);
                Iniciativa iniciativa = new Iniciativa();
                iniciativa = iniciativaL.buscarIniciativaDescripcion(descripcion, objetivos);
                iniciativaL.eliminarIniciativa(iniciativa.getCodigoIniciativa());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Eliminacion de iniciativa satisfactoria");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
        else if(columna == 3){
            try {
                String descripcion = (String)perspectivaF.getValueAt(fila, columna);
                Meta meta = new Meta();
                meta = metaL.buscarMetaDescripcion(descripcion, objetivos);
                metaL.eliminarMeta(meta.getCodigoMeta());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Eliminacion de meta satisfactoria");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_eliminarPFActionPerformed

    private void tipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo2ActionPerformed
    }//GEN-LAST:event_tipo2ActionPerformed

    private void modificarPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarPFActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaFinanciera();
        if(columna == 0){
            String descripcion = (String)perspectivaF.getValueAt(fila, columna);
            Objetivo obj = objetivoL.buscarObjetivoDescripcion(descripcion, objetivos);
            
            String descripcionObj = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del objetivo",
                        "Modificar Objetivo", JOptionPane.INFORMATION_MESSAGE);
            
            obj.setDescripcionObjetivo(descripcionObj);
            
            try {
                objetivoL.editarObjetivo(obj);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de objetivo satisfactoria");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 1){
            String descripcion = (String)perspectivaF.getValueAt(fila, columna);
            Indicador ind = indicadorL.buscarIndicadorDescripcion(descripcion, objetivos);
            
            String descripcionInd = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del indicador",
                        "Modificar indicador", JOptionPane.INFORMATION_MESSAGE);
            
            ind.setDescripcionIndicador(descripcionInd);
            
            try {
                indicadorL.editarIndicador(ind);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            } 
            JOptionPane.showMessageDialog(null,"Modificacion de indicador satisfactoria");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 2){
            String descripcion = (String)perspectivaF.getValueAt(fila, columna);
            Iniciativa ini = iniciativaL.buscarIniciativaDescripcion(descripcion, objetivos);
            
            String descripcionIni = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la iniciativa",
                        "Modificar iniciativa", JOptionPane.INFORMATION_MESSAGE);
            
            ini.setDescripcionIniciativa(descripcionIni);
            
            try {
                iniciativaL.editarIniciativa(ini);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de iniciativa satisfactoria");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
        
        if(columna == 3){
            String descripcion = (String)perspectivaF.getValueAt(fila, columna);
            Meta m = metaL.buscarMetaDescripcion(descripcion, objetivos);
            
            String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la meta",
                        "Modificar meta", JOptionPane.INFORMATION_MESSAGE);
            
            m.setDescripcionMeta(descripcionMet);
            
            try {
                metaL.editarMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de meta satisfactoria");
            secundariaFinanciera prin = new secundariaFinanciera();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_modificarPFActionPerformed

    private void perspectivaFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaFMouseClicked
        fila = perspectivaF.getSelectedRow();
        columna = perspectivaF.getSelectedColumn();
        System.out.println(columna);
    }//GEN-LAST:event_perspectivaFMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(secundariaFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(secundariaFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(secundariaFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(secundariaFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new secundariaFinanciera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarPF;
    private javax.swing.JButton eliminarPF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton modificarPF;
    private javax.swing.JTable perspectivaC;
    private javax.swing.JTable perspectivaCA;
    private javax.swing.JTable perspectivaF;
    private javax.swing.JTable perspectivaP;
    private javax.swing.JComboBox<String> tipo2;
    // End of variables declaration//GEN-END:variables
}
