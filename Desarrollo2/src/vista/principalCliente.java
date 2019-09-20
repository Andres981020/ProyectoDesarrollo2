/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.indicadorLogica;
import logica.iniciativaLogica;
import logica.metaLogica;
import logica.objetivoLogica;
import logica.usuarioLogica;
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
public class principalCliente extends javax.swing.JFrame {

    objetivoLogica objetivoL = new objetivoLogica();
    iniciativaLogica iniciativaL = new iniciativaLogica();
    indicadorLogica indicadorL = new indicadorLogica();
    metaLogica metaL = new metaLogica();
    
    int filaSeleccionada = 0;

    public principalCliente() {
        super("Interfaz principal");
        initComponents();
        
        DefaultTableModel tabla1 = new DefaultTableModel();
        tabla1.addColumn("Descripcion del objetivo");
        tabla1.addColumn("Indicador del objetivo");
        
        DefaultTableModel tabla2 = new DefaultTableModel();
        tabla2.addColumn("Descripcion del objetivo");
        tabla2.addColumn("Indicador del objetivo");
        
        DefaultTableModel tabla3 = new DefaultTableModel();
        tabla3.addColumn("Descripcion del objetivo");
        tabla3.addColumn("Indicador del objetivo");
        
        DefaultTableModel tabla4 = new DefaultTableModel();
        tabla4.addColumn("Descripcion del objetivo");
        tabla4.addColumn("Indicadores del objetivo");
        
        List<Objetivo> objetivosC = objetivoL.objetivosPerspectivaCliente();
        List<Objetivo> objetivosF = objetivoL.objetivosPerspectivaFinanciera();
        List<Objetivo> objetivosCA = objetivoL.objetivosPerspectivaCrecimiento();
        List<Objetivo> objetivosP = objetivoL.objetivosPerspectivaProcesos();
        
        String[] datos1 = new String[2];
        String[] datos2 = new String[2];
        String[] datos3 = new String[2];
        String[] datos4 = new String[2];
        
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
        
        perspectivaC.setModel(tabla1);
        perspectivaF.setModel(tabla2);
        perspectivaCrecimiento.setModel(tabla3);
        perspectivaPI.setModel(tabla4);
        
        perspectivaC.setEnabled(true);
        perspectivaF.setEnabled(false);
        perspectivaCrecimiento.setEnabled(false);
        perspectivaPI.setEnabled(false);
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
        perspectivaC = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        perspectivaF = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        perspectivaCrecimiento = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        perspectivaPI = new javax.swing.JTable();
        agregarO = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        siguiente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        perspectivaC.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
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
        perspectivaC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perspectivaCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(perspectivaC);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 530, 230));

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
        jScrollPane2.setViewportView(perspectivaF);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, 480, 230));

        perspectivaCrecimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(perspectivaCrecimiento);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 510, 230));

        perspectivaPI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(perspectivaPI);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 400, 490, 230));

        agregarO.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        agregarO.setText("Agregar objetivo");
        agregarO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarOActionPerformed(evt);
            }
        });
        getContentPane().add(agregarO, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, -1, -1));

        eliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eliminar.setText("Eliminar objetivo");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 170, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Perspectiva cliente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 150, 30));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Perspectiva financiera");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 360, 70));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Perspectiva crecimiento y aprendizaje");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 290, 30));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Perspectiva procesos internos");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 330, 30));

        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });
        getContentPane().add(siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 30, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/morado.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 630, 330));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/azul.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 330, 620, 350));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/verde.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 630, 320));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/rojo.jpg"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 630, 320));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarOActionPerformed
        defObjetivo definir = new defObjetivo();
        definir.setVisible(true);
        dispose();
    }//GEN-LAST:event_agregarOActionPerformed

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
        principalCliente prin = new principalCliente();
        prin.setVisible(true);
        dispose();
    }//GEN-LAST:event_eliminarActionPerformed

    private void perspectivaCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perspectivaCMouseClicked
        filaSeleccionada = perspectivaC.getSelectedRow();
    }//GEN-LAST:event_perspectivaCMouseClicked

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        secundariaCliente prin = new secundariaCliente();
        prin.setVisible(true);
        dispose();
    }//GEN-LAST:event_siguienteActionPerformed

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
            java.util.logging.Logger.getLogger(principalCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principalCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principalCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principalCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principalCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarO;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable perspectivaC;
    private javax.swing.JTable perspectivaCrecimiento;
    private javax.swing.JTable perspectivaF;
    private javax.swing.JTable perspectivaPI;
    private javax.swing.JButton siguiente;
    // End of variables declaration//GEN-END:variables
}
