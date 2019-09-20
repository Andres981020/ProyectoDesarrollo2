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
public class principalUsuario extends javax.swing.JFrame {
    
    objetivoLogica objetivoL = new objetivoLogica();
    iniciativaLogica iniciativaL = new iniciativaLogica();
    indicadorLogica indicadorL = new indicadorLogica();
    metaLogica metaL = new metaLogica();
    
    int fila;
    int columna;

    int filaSeleccionada = 0;
    
    public principalUsuario() {
        super("Objetivos de perspectivas");
        initComponents();
        
        int pos = 0;
        
        DefaultTableModel tabla1 = new DefaultTableModel();
        tabla1.addColumn("Descripcion del objetivo");
        tabla1.addColumn("Indicadores");
        tabla1.addColumn("Iniciativas");
        tabla1.addColumn("Metas");
        
        DefaultTableModel tabla2 = new DefaultTableModel();
        tabla2.addColumn("Descripcion del objetivo");
        tabla2.addColumn("Indicadores");
        tabla2.addColumn("Iniciativas");
        tabla2.addColumn("Metas");
        
        DefaultTableModel tabla3 = new DefaultTableModel();
        tabla3.addColumn("Descripcion del objetivo");
        tabla3.addColumn("Indicadores");
        tabla3.addColumn("Iniciativas");
        tabla3.addColumn("Metas");
        
        DefaultTableModel tabla4 = new DefaultTableModel();
        tabla4.addColumn("Descripcion del objetivo");
        tabla4.addColumn("Indicadores");
        tabla4.addColumn("Iniciativas");
        tabla4.addColumn("Metas");
        
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
        
        perspectivaC.setEnabled(true);
        perspectivaF.setEnabled(true);
        perspectivaCA.setEnabled(true);
        perspectivaP.setEnabled(true);
        
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
        agregarPC = new javax.swing.JButton();
        modificarPC = new javax.swing.JButton();
        tipo1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        modificarPF = new javax.swing.JButton();
        agregarPF = new javax.swing.JButton();
        tipo2 = new javax.swing.JComboBox<>();
        agregarP = new javax.swing.JButton();
        modificarP = new javax.swing.JButton();
        tipo4 = new javax.swing.JComboBox<>();
        agregarCA = new javax.swing.JButton();
        modificarCA = new javax.swing.JButton();
        tipo3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

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
        perspectivaCA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perspectivaCAMouseClicked(evt);
            }
        });
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
        perspectivaP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perspectivaPMouseClicked(evt);
            }
        });
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
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        perspectivaC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perspectivaCMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(perspectivaC);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 580, 240));

        agregarPC.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarPC.setText("Agregar");
        agregarPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPCActionPerformed(evt);
            }
        });
        getContentPane().add(agregarPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        modificarPC.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modificarPC.setText("Modificar");
        modificarPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarPCActionPerformed(evt);
            }
        });
        getContentPane().add(modificarPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        tipo1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Iniciativa", "Indicador", "Meta" }));
        tipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo1ActionPerformed(evt);
            }
        });
        getContentPane().add(tipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 130, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Perspectiva procesos internos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, 330, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Perspectiva cliente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 150, 30));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Perspectiva financiera");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 330, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Perspectiva crecimiento y aprendizaje");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 290, 30));

        modificarPF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modificarPF.setText("Modificar");
        modificarPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarPFActionPerformed(evt);
            }
        });
        getContentPane().add(modificarPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, -1, -1));

        agregarPF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarPF.setText("Agregar");
        agregarPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPFActionPerformed(evt);
            }
        });
        getContentPane().add(agregarPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, -1, -1));

        tipo2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Iniciativa", "Indicador", "Meta" }));
        tipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo2ActionPerformed(evt);
            }
        });
        getContentPane().add(tipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 300, 130, -1));

        agregarP.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarP.setText("Agregar");
        agregarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPActionPerformed(evt);
            }
        });
        getContentPane().add(agregarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 630, -1, -1));

        modificarP.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modificarP.setText("Modificar");
        modificarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarPActionPerformed(evt);
            }
        });
        getContentPane().add(modificarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 630, -1, -1));

        tipo4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tipo4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Iniciativa", "Indicador", "Meta" }));
        tipo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo4ActionPerformed(evt);
            }
        });
        getContentPane().add(tipo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 630, 130, -1));

        agregarCA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarCA.setText("Agregar");
        agregarCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCAActionPerformed(evt);
            }
        });
        getContentPane().add(agregarCA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 630, -1, -1));

        modificarCA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        modificarCA.setText("Modificar");
        modificarCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarCAActionPerformed(evt);
            }
        });
        getContentPane().add(modificarCA, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 630, -1, -1));

        tipo3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tipo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Iniciativa", "Indicador", "Meta" }));
        tipo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo3ActionPerformed(evt);
            }
        });
        getContentPane().add(tipo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 630, 130, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/morado.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 630, 330));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/azul.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 620, 350));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/rojo.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 630, 320));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/verde.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 620, 320));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPCActionPerformed
        String objetivo;
        Date objDate = new Date();
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCliente();
        objetivo = (String)perspectivaC.getValueAt(fila, 0);
        Objetivo obj = objetivoL.buscarObjetivoDescripcion(objetivo, objetivos);
        if(tipo1.getSelectedItem().toString().equals("Iniciativa")){
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
                Logger.getLogger(principalUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Registro de iniciativa satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        
        if(tipo1.getSelectedItem().toString().equals("Indicador")){
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
                Logger.getLogger(principalUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Registro de indicador satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();         
        }
        
        if(tipo1.getSelectedItem().toString().equals("Meta")){
            try {
                Meta m = new Meta();
                String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la meta",
                        "Agregar meta", JOptionPane.INFORMATION_MESSAGE);
                if(descripcionMet.equals("")){
                    
                }
                m.setCodigoMeta(0);
                m.setDescripcionMeta(descripcionMet);
                m.setFechaMeta(objDate);
                m.setMetaObjetivo(obj);
                metaL.crearMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(principalUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Registro de meta satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_agregarPCActionPerformed

    private void tipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo1ActionPerformed
    }//GEN-LAST:event_tipo1ActionPerformed

    private void perspectivaCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaCMouseClicked
        fila = perspectivaC.getSelectedRow();
        columna = perspectivaC.getSelectedColumn();
        System.out.println(columna);
    }//GEN-LAST:event_perspectivaCMouseClicked

    private void modificarPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarPCActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCliente();
        if(columna == 0){
            String descripcion = (String)perspectivaC.getValueAt(fila, columna);
            Objetivo obj = objetivoL.buscarObjetivoDescripcion(descripcion, objetivos);
            
            String descripcionObj = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del objetivo",
                        "Modificar Objetivo", JOptionPane.INFORMATION_MESSAGE);
            
            obj.setDescripcionObjetivo(descripcionObj);
            
            try {
                objetivoL.editarObjetivo(obj);
            } catch (Exception ex) {
                Logger.getLogger(principalUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion del objetivo satisfactorio");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 1){
            String descripcion = (String)perspectivaC.getValueAt(fila, columna);
            Indicador ind = indicadorL.buscarIndicadorDescripcion(descripcion, objetivos);
            
            String descripcionInd = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del indicador",
                        "Modificar indicador", JOptionPane.INFORMATION_MESSAGE);
            
            ind.setDescripcionIndicador(descripcionInd);
            
            try {
                indicadorL.editarIndicador(ind);
            } catch (Exception ex) {
                Logger.getLogger(principalUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de indicador satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 2){
            String descripcion = (String)perspectivaC.getValueAt(fila, columna);
            Iniciativa ini = iniciativaL.buscarIniciativaDescripcion(descripcion, objetivos);
            
            String descripcionIni = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la iniciativa",
                        "Modificar iniciativa", JOptionPane.INFORMATION_MESSAGE);
            
            ini.setDescripcionIniciativa(descripcionIni);
            
            try {
                iniciativaL.editarIniciativa(ini);
            } catch (Exception ex) {
                Logger.getLogger(principalUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } 
            JOptionPane.showMessageDialog(null,"Modificacion de iniciativa satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        
        if(columna == 3){
            String descripcion = (String)perspectivaC.getValueAt(fila, columna);
            Meta m = metaL.buscarMetaDescripcion(descripcion, objetivos);
            
            String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la meta",
                        "Modificar meta", JOptionPane.INFORMATION_MESSAGE);
            
            m.setDescripcionMeta(descripcionMet);
            
            try {
                metaL.editarMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(principalUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de meta satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_modificarPCActionPerformed

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
            principalUsuario prin = new principalUsuario();
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
            principalUsuario prin = new principalUsuario();
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
            principalUsuario prin = new principalUsuario();
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
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_modificarPFActionPerformed

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
            
        }
        
        if(tipo2.getSelectedItem().toString().equals("Meta")){
            try {
                Meta m = new Meta();
                String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la meta",
                        "Agregar meta", JOptionPane.INFORMATION_MESSAGE);
                if(descripcionMet.equals("")){
                    
                }
                m.setCodigoMeta(0);
                m.setDescripcionMeta(descripcionMet);
                m.setFechaMeta(objDate);
                m.setMetaObjetivo(obj);
                metaL.crearMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(secundariaFinanciera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_agregarPFActionPerformed

    private void tipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo2ActionPerformed

    private void agregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPActionPerformed
        String objetivo;
        Date objDate = new Date();
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaProcesos();
        objetivo = (String)perspectivaP.getValueAt(fila, 0);
        Objetivo obj = objetivoL.buscarObjetivoDescripcion(objetivo, objetivos);
        if(tipo4.getSelectedItem().toString().equals("Iniciativa")){
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
                Logger.getLogger(secundariaProcesos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(tipo4.getSelectedItem().toString().equals("Indicador")){
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
                Logger.getLogger(secundariaProcesos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(tipo4.getSelectedItem().toString().equals("Meta")){
            try {
                Meta m = new Meta();
                String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la meta",
                        "Agregar meta", JOptionPane.INFORMATION_MESSAGE);
                if(descripcionMet.equals("")){
                    
                }
                m.setCodigoMeta(0);
                m.setDescripcionMeta(descripcionMet);
                m.setFechaMeta(objDate);
                m.setMetaObjetivo(obj);
                metaL.crearMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(secundariaProcesos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_agregarPActionPerformed

    private void modificarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarPActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaProcesos();
        if(columna == 0){
            String descripcion = (String)perspectivaP.getValueAt(fila, columna);
            Objetivo obj = objetivoL.buscarObjetivoDescripcion(descripcion, objetivos);
            
            String descripcionObj = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del objetivo",
                        "Modificar Objetivo", JOptionPane.INFORMATION_MESSAGE);
            
            obj.setDescripcionObjetivo(descripcionObj);
            
            try {
                objetivoL.editarObjetivo(obj);
            } catch (Exception ex) {
                Logger.getLogger(secundariaProcesos.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de objetivo satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 1){
            String descripcion = (String)perspectivaP.getValueAt(fila, columna);
            Indicador ind = indicadorL.buscarIndicadorDescripcion(descripcion, objetivos);
            
            String descripcionInd = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del indicador",
                        "Modificar indicador", JOptionPane.INFORMATION_MESSAGE);
            
            ind.setDescripcionIndicador(descripcionInd);
            
            try {
                indicadorL.editarIndicador(ind);
            } catch (Exception ex) {
                Logger.getLogger(secundariaProcesos.class.getName()).log(Level.SEVERE, null, ex);
            } 
            JOptionPane.showMessageDialog(null,"Modificacion de indicador satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 2){
            String descripcion = (String)perspectivaP.getValueAt(fila, columna);
            Iniciativa ini = iniciativaL.buscarIniciativaDescripcion(descripcion, objetivos);
            
            String descripcionIni = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la iniciativa",
                        "Modificar iniciativa", JOptionPane.INFORMATION_MESSAGE);
            
            ini.setDescripcionIniciativa(descripcionIni);
            
            try {
                iniciativaL.editarIniciativa(ini);
            } catch (Exception ex) {
                Logger.getLogger(secundariaProcesos.class.getName()).log(Level.SEVERE, null, ex);
            } 
            JOptionPane.showMessageDialog(null,"Modificacion de iniciativa satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        
        if(columna == 3){
            String descripcion = (String)perspectivaP.getValueAt(fila, columna);
            Meta m = metaL.buscarMetaDescripcion(descripcion, objetivos);
            
            String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la meta",
                        "Modificar meta", JOptionPane.INFORMATION_MESSAGE);
            
            m.setDescripcionMeta(descripcionMet);
            
            try {
                metaL.editarMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(secundariaProcesos.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de meta satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_modificarPActionPerformed

    private void tipo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo4ActionPerformed

    private void agregarCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCAActionPerformed
        String objetivo;
        Date objDate = new Date();
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCrecimiento();
        objetivo = (String)perspectivaCA.getValueAt(fila, 0);
        Objetivo obj = objetivoL.buscarObjetivoDescripcion(objetivo, objetivos);
        if(tipo3.getSelectedItem().toString().equals("Iniciativa")){
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
                Logger.getLogger(secundariaCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(tipo3.getSelectedItem().toString().equals("Indicador")){
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
                Logger.getLogger(secundariaCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(tipo3.getSelectedItem().toString().equals("Meta")){
            try {
                Meta m = new Meta();
                String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la meta",
                        "Agregar meta", JOptionPane.INFORMATION_MESSAGE);
                if(descripcionMet.equals("")){
                    
                }
                m.setCodigoMeta(0);
                m.setDescripcionMeta(descripcionMet);
                m.setFechaMeta(objDate);
                m.setMetaObjetivo(obj);
                metaL.crearMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(secundariaCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_agregarCAActionPerformed

    private void modificarCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarCAActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCrecimiento();
        if(columna == 0){
            String descripcion = (String)perspectivaCA.getValueAt(fila, columna);
            Objetivo obj = objetivoL.buscarObjetivoDescripcion(descripcion, objetivos);
            
            String descripcionObj = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del objetivo",
                        "Modificar Objetivo", JOptionPane.INFORMATION_MESSAGE);
            
            obj.setDescripcionObjetivo(descripcionObj);
            
            try {
                objetivoL.editarObjetivo(obj);
            } catch (Exception ex) {
                Logger.getLogger(secundariaCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de objetivo satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 1){
            String descripcion = (String)perspectivaCA.getValueAt(fila, columna);
            Indicador ind = indicadorL.buscarIndicadorDescripcion(descripcion, objetivos);
            
            String descripcionInd = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion del indicador",
                        "Modificar indicador", JOptionPane.INFORMATION_MESSAGE);
            
            ind.setDescripcionIndicador(descripcionInd);
            
            try {
                indicadorL.editarIndicador(ind);
            } catch (Exception ex) {
                Logger.getLogger(secundariaCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de indicador satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        if(columna == 2){
            String descripcion = (String)perspectivaCA.getValueAt(fila, columna);
            Iniciativa ini = iniciativaL.buscarIniciativaDescripcion(descripcion, objetivos);
            
            String descripcionIni = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la iniciativa",
                        "Modificar iniciativa", JOptionPane.INFORMATION_MESSAGE);
            
            ini.setDescripcionIniciativa(descripcionIni);
            
            try {
                iniciativaL.editarIniciativa(ini);
            } catch (Exception ex) {
                Logger.getLogger(secundariaCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
            } 
            JOptionPane.showMessageDialog(null,"Modificacion de iniciativa satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
        
        if(columna == 3){
            String descripcion = (String)perspectivaCA.getValueAt(fila, columna);
            Meta m = metaL.buscarMetaDescripcion(descripcion, objetivos);
            
            String descripcionMet = JOptionPane.showInputDialog(null, "Ingrese la nueva descripcion de la meta",
                        "Modificar meta", JOptionPane.INFORMATION_MESSAGE);
            
            m.setDescripcionMeta(descripcionMet);
            
            try {
                metaL.editarMeta(m);
            } catch (Exception ex) {
                Logger.getLogger(secundariaCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null,"Modificacion de meta satisfactoria");
            principalUsuario prin = new principalUsuario();
            prin.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_modificarCAActionPerformed

    private void tipo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo3ActionPerformed

    private void perspectivaFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaFMouseClicked
        fila = perspectivaF.getSelectedRow();
        columna = perspectivaF.getSelectedColumn();
        System.out.println(columna);
    }//GEN-LAST:event_perspectivaFMouseClicked

    private void perspectivaCAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaCAMouseClicked
        fila = perspectivaCA.getSelectedRow();
        columna = perspectivaCA.getSelectedColumn();
        System.out.println(columna);
    }//GEN-LAST:event_perspectivaCAMouseClicked

    private void perspectivaPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaPMouseClicked
        fila = perspectivaP.getSelectedRow();
        columna = perspectivaP.getSelectedColumn();
        System.out.println(columna);
    }//GEN-LAST:event_perspectivaPMouseClicked

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
            java.util.logging.Logger.getLogger(principalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principalUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarCA;
    private javax.swing.JButton agregarP;
    private javax.swing.JButton agregarPC;
    private javax.swing.JButton agregarPF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton modificarCA;
    private javax.swing.JButton modificarP;
    private javax.swing.JButton modificarPC;
    private javax.swing.JButton modificarPF;
    private javax.swing.JTable perspectivaC;
    private javax.swing.JTable perspectivaCA;
    private javax.swing.JTable perspectivaF;
    private javax.swing.JTable perspectivaP;
    private javax.swing.JComboBox<String> tipo1;
    private javax.swing.JComboBox<String> tipo2;
    private javax.swing.JComboBox<String> tipo3;
    private javax.swing.JComboBox<String> tipo4;
    // End of variables declaration//GEN-END:variables
}
