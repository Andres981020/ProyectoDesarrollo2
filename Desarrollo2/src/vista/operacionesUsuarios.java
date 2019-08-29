/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.usuarioLogica;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class operacionesUsuarios extends javax.swing.JFrame {

    usuarioLogica usuarioL = new usuarioLogica();

    public operacionesUsuarios() {
        super("Operaciones sobre ususarios");
        initComponents();

        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Cedula");
        tabla.addColumn("Nombres");
        tabla.addColumn("Apellidos");
        tabla.addColumn("Clave");
        tabla.addColumn("Tipo usuario");
        tabla.addColumn("Telefono");
        tabla.addColumn("Direccion");
        tabla.addColumn("Correo electronico");

        List<Usuario> usuarios = usuarioL.consultar();
        String[] datos = new String[8];

        for (Usuario u : usuarios) {
            datos[0] = u.getCedula();
            datos[1] = u.getNombres();
            datos[2] = u.getApellidos();
            datos[3] = u.getClave();
            datos[4] = u.getTipoUsuario();
            datos[5] = u.getTelefono();
            datos[6] = u.getDireccion();
            datos[7] = u.getCorreoElectronico();
            tabla.addRow(datos);
        }
        tablaUsuarios.setModel(tabla);
        tablaUsuarios.setEnabled(false);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        habilitar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaUsuarios.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ));
        jScrollPane1.setViewportView(tablaUsuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1310, 390));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Bienvenido a la interfaz de operaciones sobre usuarios");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 1330, 500));

        modificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        modificar.setText("Modificar usuario");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        getContentPane().add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, -1, -1));

        eliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        eliminar.setText("Eliminar usuario");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 530, -1, -1));

        habilitar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        habilitar.setText("Habilitar tabla");
        habilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                habilitarActionPerformed(evt);
            }
        });
        getContentPane().add(habilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        /*List<Usuario> usuarios = usuarioL.consultar();
        Usuario usuario = new Usuario();
        usuario = usuarios.get(1);
        System.out.print(usuario.getNombres());*/
        System.out.print(tablaUsuarios.getSelectedRow());

    }//GEN-LAST:event_modificarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarActionPerformed

    private void habilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_habilitarActionPerformed
        tablaUsuarios.setEnabled(true);
        JOptionPane.showMessageDialog(this, "Cambie los datos que desee realizar de los usuarios que desee, "
                + "posteriormente presione modificar");
    }//GEN-LAST:event_habilitarActionPerformed

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
            java.util.logging.Logger.getLogger(operacionesUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(operacionesUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(operacionesUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(operacionesUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new operacionesUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eliminar;
    private javax.swing.JButton habilitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificar;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}