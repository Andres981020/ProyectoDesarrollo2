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
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class prinUsuario extends javax.swing.JFrame {
    
    objetivoLogica objetivoL = new objetivoLogica();
    iniciativaLogica iniciativaL = new iniciativaLogica();
    indicadorLogica indicadorL = new indicadorLogica();
    metaLogica metaL = new metaLogica();

    int filaSeleccionada = 0;
    
    public prinUsuario() {
        super("Objetivos de perspectivas");
        initComponents();
        

        
        DefaultTableModel tabla1 = new DefaultTableModel();
        tabla1.addColumn("Descripcion del objetivo");
        tabla1.addColumn("Indicadores");

        
        DefaultTableModel tabla2 = new DefaultTableModel();
        tabla2.addColumn("Descripcion del objetivo");
        tabla2.addColumn("Indicadores");

        
        DefaultTableModel tabla3 = new DefaultTableModel();
        tabla3.addColumn("Descripcion del objetivo");
        tabla3.addColumn("Indicadores");

        
        DefaultTableModel tabla4 = new DefaultTableModel();
        tabla4.addColumn("Descripcion del objetivo");
        tabla4.addColumn("Indicadores");

        
        List<Objetivo> objetivosC = objetivoL.objetivosPerspectivaCliente();
        List<Objetivo> objetivosF = objetivoL.objetivosPerspectivaFinanciera();
        List<Objetivo> objetivosCA = objetivoL.objetivosPerspectivaCrecimiento();
        List<Objetivo> objetivosP = objetivoL.objetivosPerspectivaProcesos();
        
        String[] datos1 = new String[2];
        String[] datos2 = new String[2];
        String[] datos3 = new String[2];
        String[] datos4 = new String[2];
        
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
            List<Indicador> indicadores;
            indicadores = o.getIndicadorList();
            datos1[0] = o.getDescripcionObjetivo();
            for(Indicador i: indicadores){
                datos1[0] = o.getDescripcionObjetivo();
                datos1[1] = i.getDescripcionIndicador();
                tabla1.addRow(datos1);
            }
        }
        
        for(Objetivo o: objetivosF){
            List<Indicador> indicadores;
            indicadores = o.getIndicadorList();
            datos2[0] = o.getDescripcionObjetivo();
            for(Indicador i: indicadores){
                datos2[0] = o.getDescripcionObjetivo();
                datos2[1] = i.getDescripcionIndicador();
                tabla2.addRow(datos2);
            }
        }
        
        for(Objetivo o: objetivosCA){
            List<Indicador> indicadores;
            indicadores = o.getIndicadorList();
            datos3[0] = o.getDescripcionObjetivo();
            for(Indicador i: indicadores){
                datos3[0] = o.getDescripcionObjetivo();
                datos3[1] = i.getDescripcionIndicador();
                tabla3.addRow(datos3);
            }
        }
        
        for(Objetivo o: objetivosP){
            List<Indicador> indicadores;
            indicadores = o.getIndicadorList();
            datos4[0] = o.getDescripcionObjetivo();
            for(Indicador i: indicadores){
                datos4[0] = o.getDescripcionObjetivo();
                datos4[1] = i.getDescripcionIndicador();
                tabla4.addRow(datos4);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        agregarPF = new javax.swing.JButton();
        agregarP = new javax.swing.JButton();
        agregarCA = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        eliminarO = new javax.swing.JButton();
        eliminarO1 = new javax.swing.JButton();
        eliminar1 = new javax.swing.JButton();
        siguiente2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
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
        agregarPC.setText("Agregar objetivo");
        agregarPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPCActionPerformed(evt);
            }
        });
        getContentPane().add(agregarPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, -1, -1));

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

        agregarPF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarPF.setText("Agregar objetivo");
        agregarPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPFActionPerformed(evt);
            }
        });
        getContentPane().add(agregarPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, -1, -1));

        agregarP.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarP.setText("Agregar objetivo");
        agregarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPActionPerformed(evt);
            }
        });
        getContentPane().add(agregarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 630, -1, -1));

        agregarCA.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarCA.setText("Agregar objetivo");
        agregarCA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCAActionPerformed(evt);
            }
        });
        getContentPane().add(agregarCA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 630, -1, -1));

        eliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eliminar.setText("Eliminar objetivo");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 170, -1));

        eliminarO.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eliminarO.setText("Eliminar objetivo");
        eliminarO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarOActionPerformed(evt);
            }
        });
        getContentPane().add(eliminarO, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 300, 170, -1));

        eliminarO1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eliminarO1.setText("Eliminar objetivo");
        eliminarO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarO1ActionPerformed(evt);
            }
        });
        getContentPane().add(eliminarO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, 170, -1));

        eliminar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eliminar1.setText("Eliminar objetivo");
        eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar1ActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 630, 170, -1));

        siguiente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguiente2ActionPerformed(evt);
            }
        });
        getContentPane().add(siguiente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 30, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/rojo.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 630, 320));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/verde.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 630, 320));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/morado.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 630, 330));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/azul.jpg"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 620, 350));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPCActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCliente();
        if(objetivos.size() >= 10){
           JOptionPane.showMessageDialog(this, "No se permite la creacion de mas objetivos"); 
           System.exit(0);
        }
        defObjetivoAdmin definir = new defObjetivoAdmin();
        definir.setVisible(true);
        dispose();
    }//GEN-LAST:event_agregarPCActionPerformed

    private void perspectivaCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaCMouseClicked
        filaSeleccionada = perspectivaC.getSelectedRow();
    }//GEN-LAST:event_perspectivaCMouseClicked

    private void agregarPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPFActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaFinanciera();
        if(objetivos.size() >= 10){
           JOptionPane.showMessageDialog(this, "No se permite la creacion de mas objetivos"); 
           System.exit(0);
        }
        defObjetivoAdmin definir = new defObjetivoAdmin();
        definir.setVisible(true);
        dispose();
    }//GEN-LAST:event_agregarPFActionPerformed

    private void agregarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaProcesos();
        if(objetivos.size() >= 10){
           JOptionPane.showMessageDialog(this, "No se permite la creacion de mas objetivos"); 
           System.exit(0);
        }
        defObjetivoAdmin definir = new defObjetivoAdmin();
        definir.setVisible(true);
        dispose();
    }//GEN-LAST:event_agregarPActionPerformed

    private void agregarCAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCAActionPerformed
        List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCrecimiento();
        if(objetivos.size() >= 10){
           JOptionPane.showMessageDialog(this, "No se permite la creacion de mas objetivos"); 
           System.exit(0);
        }
        defObjetivoAdmin definir = new defObjetivoAdmin();
        definir.setVisible(true);
        dispose();
    }//GEN-LAST:event_agregarCAActionPerformed

    private void perspectivaFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaFMouseClicked
        filaSeleccionada = perspectivaC.getSelectedRow();
    }//GEN-LAST:event_perspectivaFMouseClicked

    private void perspectivaCAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaCAMouseClicked
        filaSeleccionada = perspectivaC.getSelectedRow();
    }//GEN-LAST:event_perspectivaCAMouseClicked

    private void perspectivaPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaPMouseClicked
        filaSeleccionada = perspectivaC.getSelectedRow();
    }//GEN-LAST:event_perspectivaPMouseClicked

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        try {
            Objetivo obj = new Objetivo();
            List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCliente();
            obj = objetivos.get(filaSeleccionada);
            List<Indicador> indicadores = obj.getIndicadorList();
            List<Iniciativa> iniciativas = obj.getIniciativaList();
            List<Meta> metas = obj.getMetaList();

            for(Iniciativa i: iniciativas){
                try {
                    iniciativaL.eliminarIniciativa(i.getCodigoIniciativa());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Indicador in: indicadores){
                try {
                    indicadorL.eliminarIndicador(in.getCodigoIndicador());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Meta m: metas){
                try {
                    metaL.eliminarMeta(m.getCodigoMeta());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            objetivoL.eliminarObjetivo(obj.getCodigoObjetivo());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(principalCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null,"Eliminacion completada");
        prinUsuario prin = new prinUsuario();
        prin.setVisible(true);
        dispose();
    }//GEN-LAST:event_eliminarActionPerformed

    private void eliminarOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarOActionPerformed
        try {
            Objetivo obj = new Objetivo();
            List<Objetivo> objetivos = objetivoL.objetivosPerspectivaFinanciera();
            obj = objetivos.get(filaSeleccionada);
            List<Indicador> indicadores = obj.getIndicadorList();
            List<Iniciativa> iniciativas = obj.getIniciativaList();
            List<Meta> metas = obj.getMetaList();

            for(Iniciativa i: iniciativas){
                try {
                    iniciativaL.eliminarIniciativa(i.getCodigoIniciativa());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalFinanciera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Indicador in: indicadores){
                try {
                    indicadorL.eliminarIndicador(in.getCodigoIndicador());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalFinanciera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Meta m: metas){
                try {
                    metaL.eliminarMeta(m.getCodigoMeta());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalFinanciera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            objetivoL.eliminarObjetivo(obj.getCodigoObjetivo());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(principalFinanciera.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null,"Eliminacion completada");
        prinUsuario prin = new prinUsuario();
        prin.setVisible(true);
        dispose();
    }//GEN-LAST:event_eliminarOActionPerformed

    private void eliminarO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarO1ActionPerformed
        try {
            Objetivo obj = new Objetivo();
            List<Objetivo> objetivos = objetivoL.objetivosPerspectivaCrecimiento();
            obj = objetivos.get(filaSeleccionada);
            List<Indicador> indicadores = obj.getIndicadorList();
            List<Iniciativa> iniciativas = obj.getIniciativaList();
            List<Meta> metas = obj.getMetaList();

            for(Iniciativa i: iniciativas){
                try {
                    iniciativaL.eliminarIniciativa(i.getCodigoIniciativa());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Indicador in: indicadores){
                try {
                    indicadorL.eliminarIndicador(in.getCodigoIndicador());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Meta m: metas){
                try {
                    metaL.eliminarMeta(m.getCodigoMeta());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            objetivoL.eliminarObjetivo(obj.getCodigoObjetivo());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(principalCrecimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null,"Eliminacion completada");
        prinUsuario prin = new prinUsuario();
        prin.setVisible(true);
        dispose();
    }//GEN-LAST:event_eliminarO1ActionPerformed

    private void eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar1ActionPerformed
        try {
            Objetivo obj = new Objetivo();
            List<Objetivo> objetivos = objetivoL.objetivosPerspectivaProcesos();
            obj = objetivos.get(filaSeleccionada);
            List<Indicador> indicadores = obj.getIndicadorList();
            List<Iniciativa> iniciativas = obj.getIniciativaList();
            List<Meta> metas = obj.getMetaList();

            for(Iniciativa i: iniciativas){
                try {
                    iniciativaL.eliminarIniciativa(i.getCodigoIniciativa());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalProcesos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Indicador in: indicadores){
                try {
                    indicadorL.eliminarIndicador(in.getCodigoIndicador());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalProcesos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for(Meta m: metas){
                try {
                    metaL.eliminarMeta(m.getCodigoMeta());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(principalProcesos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            objetivoL.eliminarObjetivo(obj.getCodigoObjetivo());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(principalProcesos.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null,"Eliminacion completada");
        prinUsuario prin = new prinUsuario();
        prin.setVisible(true);
        dispose();
    }//GEN-LAST:event_eliminar1ActionPerformed

    private void siguiente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguiente2ActionPerformed
        principalUsuario prin = new principalUsuario();
        prin.setVisible(true);
        dispose();
    }//GEN-LAST:event_siguiente2ActionPerformed

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
            java.util.logging.Logger.getLogger(prinUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prinUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prinUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prinUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prinUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarCA;
    private javax.swing.JButton agregarP;
    private javax.swing.JButton agregarPC;
    private javax.swing.JButton agregarPF;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminar1;
    private javax.swing.JButton eliminarO;
    private javax.swing.JButton eliminarO1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable perspectivaC;
    private javax.swing.JTable perspectivaCA;
    private javax.swing.JTable perspectivaF;
    private javax.swing.JTable perspectivaP;
    private javax.swing.JButton siguiente2;
    // End of variables declaration//GEN-END:variables
}
