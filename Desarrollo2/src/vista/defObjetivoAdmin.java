/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.indicadorLogica;
import logica.iniciativaLogica;
import logica.metaLogica;
import logica.objetivoLogica;
import logica.usuarioLogica;
import modelo.Indicador;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class defObjetivoAdmin extends javax.swing.JFrame {
    
    usuarioLogica usuario = new usuarioLogica();
    objetivoLogica objetivo = new objetivoLogica();
    indicadorLogica indicador = new indicadorLogica();
    iniciativaLogica iniciativa = new iniciativaLogica();
    metaLogica meta = new metaLogica();

    /**
     * Creates new form defObjetivo
     */
    public defObjetivoAdmin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        idUsuario = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        iniciativaO = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcionO = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        indicadorO = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        metaO = new javax.swing.JTextArea();
        crearO = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        codigoO = new javax.swing.JTextField();
        perspectivaO = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Definición de los objetivos");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 250, 41));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("   Identificación usuario:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 220, 40));

        idUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        idUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(idUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 310, 40));

        iniciativaO.setColumns(20);
        iniciativaO.setRows(5);
        iniciativaO.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.setViewportView(iniciativaO);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 260, 160));

        descripcionO.setColumns(20);
        descripcionO.setRows(5);
        jScrollPane2.setViewportView(descripcionO);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 260, 160));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Descripción objetivo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 200, 41));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("                    Iniciativa");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 200, 41));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Meta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, 60, 41));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Indicador");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 100, 41));

        indicadorO.setColumns(20);
        indicadorO.setRows(5);
        jScrollPane4.setViewportView(indicadorO);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 260, 160));

        metaO.setColumns(20);
        metaO.setRows(5);
        jScrollPane1.setViewportView(metaO);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 260, 160));

        crearO.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        crearO.setText("Crear Objetivo");
        crearO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearOActionPerformed(evt);
            }
        });
        getContentPane().add(crearO, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 680, -1, 40));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("               Perspectiva:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 200, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 620, 700));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("   Codigo de objetivo:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 200, 40));

        codigoO.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        codigoO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoOActionPerformed(evt);
            }
        });
        getContentPane().add(codigoO, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 310, 40));

        perspectivaO.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        perspectivaO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perspectiva cliente", "Perspectiva financiera", "Perspectiva crecimiento y aprendizaje", "Perspectiva procesos internos" }));
        getContentPane().add(perspectivaO, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 310, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idUsuarioActionPerformed

    private void crearOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearOActionPerformed
        try {
            Date objDate = new Date();
            Usuario u = usuario.encontrar(idUsuario.getText());
            
            Objetivo obj = new Objetivo();
            Iniciativa ini = new Iniciativa();
            Indicador ind = new Indicador();
            Meta met = new Meta();
            
            // Creación de los objetivos
            obj.setCodigoObjetivo(codigoO.getText());
            obj.setFecha(objDate);
            obj.setDescripcionObjetivo(descripcionO.getText());
            obj.setCreadorObjetivo(u);
            obj.setPerspectiva(perspectivaO.getSelectedItem().toString());
            
            objetivo.crearObjetivo(obj);
            
            //Creacion de iniciativa
            ini.setCodigoIniciativa(0);
            ini.setDescripcionIniciativa(iniciativaO.getText());
            ini.setFechaIniciativa(objDate);
            ini.setIniciativaObjetivo(obj);
            
            //Creacion de indicador
            ind.setCodigoIndicador(0);
            ind.setDescripcionIndicador(indicadorO.getText());
            ind.setFechaIndicador(objDate);
            ind.setIndicadorObjetivo(obj);
            
            //Creacion de meta
            met.setCodigoMeta(0);
            met.setDescripcionMeta(metaO.getText());
            met.setFechaMeta(objDate);
            met.setMetaObjetivo(obj);
            
            iniciativa.crearIniciativa(ini);
            indicador.crearIndicador(ind);
            meta.crearMeta(met);
            
        } catch (Exception ex) {
            Logger.getLogger(defObjetivoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Creacion de objetivo satisfactoria"); 
        prinUsuario objetivos = new prinUsuario();
        objetivos.setVisible(true);
        dispose();
    }//GEN-LAST:event_crearOActionPerformed

    private void codigoOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoOActionPerformed

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
            java.util.logging.Logger.getLogger(defObjetivoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(defObjetivoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(defObjetivoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(defObjetivoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new defObjetivoAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigoO;
    private javax.swing.JButton crearO;
    private javax.swing.JTextArea descripcionO;
    private javax.swing.JTextField idUsuario;
    private javax.swing.JTextArea indicadorO;
    private javax.swing.JTextArea iniciativaO;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea metaO;
    private javax.swing.JComboBox<String> perspectivaO;
    // End of variables declaration//GEN-END:variables
}