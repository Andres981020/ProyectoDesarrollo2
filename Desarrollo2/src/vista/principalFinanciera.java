/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.objetivoLogica;
import logica.usuarioLogica;
import modelo.Objetivo;

/**
 *
 * @author Usuario
 */
public class principalFinanciera extends javax.swing.JFrame {

    objetivoLogica objetivoL = new objetivoLogica();
    
    public principalFinanciera() {
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
        tabla4.addColumn("Indicador del objetivo");
        
        List<Objetivo> objetivosC = objetivoL.objetivosPerspectivaCliente();
        List<Objetivo> objetivosF = objetivoL.objetivosPerspectivaFinanciera();
        List<Objetivo> objetivosCA = objetivoL.objetivosPerspectivaCrecimiento();
        List<Objetivo> objetivosP = objetivoL.objetivosPerspectivaProcesos();
        
        String[] datos1 = new String[2];
        String[] datos2 = new String[2];
        String[] datos3 = new String[2];
        String[] datos4 = new String[2];
        
        for(Objetivo o: objetivosC){
            datos1[0] = o.getDescripcionObjetivo();
            datos1[1] = o.getIndicadorList().toString();
        }
        
        for(Objetivo o: objetivosF){
            datos2[0] = o.getDescripcionObjetivo();
            datos2[1] = o.getIndicadorList().toString();
        }
        
        for(Objetivo o: objetivosCA){
            datos3[0] = o.getDescripcionObjetivo();
            datos3[1] = o.getIndicadorList().toString();
        }
        
        for(Objetivo o: objetivosP){
            datos4[0] = o.getDescripcionObjetivo();
            datos4[1] = o.getIndicadorList().toString();
        }
        
        perspectivaC.setModel(tabla1);
        perspectivaF.setModel(tabla2);
        perspectivaCrecimiento.setModel(tabla3);
        perspectivaPI.setModel(tabla4);
        
        perspectivaC.setEnabled(false);
        perspectivaF.setEnabled(true);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        perspectivaC = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        perspectivaF = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        perspectivaCrecimiento = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        perspectivaPI = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Visualizacion de objetivos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 280, 42));

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
        jScrollPane1.setViewportView(perspectivaC);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 190));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, -1, 190));

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

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, 200));

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

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, -1, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(principalFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principalFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principalFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principalFinanciera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principalFinanciera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable perspectivaC;
    private javax.swing.JTable perspectivaCrecimiento;
    private javax.swing.JTable perspectivaF;
    private javax.swing.JTable perspectivaPI;
    // End of variables declaration//GEN-END:variables
}
