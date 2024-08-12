/*
Proyecto Sistemas Operativos
Alumno: Alejandro Chacon Garita
II Cuatrimestre 2024

Cardenas, D. (2022). Algoritmo del banquero. https://youtu.be/-wrjpTBcuhs?si=xrJdkFeduJXmPTG- 
Elvira, J. (2020). Sistemas Operativos, Interbloqueo 4 Algoritmo del banquero. Mtro. José Luis Elvira. https://youtu.be/kbXjKKaMUAI?si=6EwVwn-9QHZ7qso_
Gagne, G., Galvin, P., & Silberschatz, A. (2006). Fundamentos de Sistemas Operativos. McGraw-Hill.
Tanenbaum, A. (2009). Sistemas Operativos Modernos. Pearson.
*/
package Interfaz;

import AlgoritmoBanquero.Algoritmo;

public class Menu extends javax.swing.JFrame {   
    public Menu() {
        //Se inician los componentes de la interfaz
        initComponents();
        //Se muestran las instrucciones en el txtArea
        instrucciones();
    }
    
    //Método para imprimir mensajes en el txtArea
    private void mostrarMensaje(String mensaje){
        txtArea.append(mensaje);
    } 
    
    
    //Método que muestra las instrucciones
    private void instrucciones(){
        txtArea.setText("");
        mostrarMensaje("Proyecto 1Sistemas Operativos Alejandro Chacon\n");
        mostrarMensaje("\nBienvenido al Simulador de Algoritmo del Banquero\n");
        mostrarMensaje("\nInstrucciones para Iniciar el Algoritmo:\n");
        
        mostrarMensaje("\n-----Con el botón Definir Matrices-----\n");
        mostrarMensaje("1 Se deberán definir la cantidad de Recursos y Procesos\n");
        mostrarMensaje("2 Se realizará la asignación de los recursos en las Matrices\n");
        
        mostrarMensaje("\n-----Con el botón Solicitar Recursos-----\n");
        mostrarMensaje("3 Se podrá solicitar los recursos necesarios para un Proceso\n");
        mostrarMensaje("4 Los recursos que alcanzan la cantidad necesitada se liberan automáticamente\n");
        mostrarMensaje("5 Con este botón se comprobará el Adecuado Funcionamiento del Algoritmo\n");
        
        mostrarMensaje("\n-----Con el botón Mostrar Estados-----\n");
        mostrarMensaje("6. Puede observar los Estados de las Matrices\n");
        
        mostrarMensaje("\n-----Con el botón Instrucciones-----\n");
        mostrarMensaje("7.Puede Observar las instrucciones las veces que desee\n");
        mostrarMensaje("8. Luego puede repetir los pasos para seguir trabajando con el Algoritmo\n");
        
        mostrarMensaje("-----------------Notas-------------------\n");
        mostrarMensaje("1.Los sistemas tienen limites por lo que: \n");
        mostrarMensaje("2.Los recursos Máximos por proceso no podrán ser mayores a los Iniciales\n");
        mostrarMensaje("3.Los recursos asignados no podrán ser mayores a los máximos requeridos\n");
        mostrarMensaje("Si se identifica alguno de los casos anteriores deberá asignar nuevamente\n");
        mostrarMensaje("Ya que un sistema real no permitiría ninguno de los escenarios anteriores\n");
        
        mostrarMensaje("\n-----------Acerca del Algoritmo del Banquero:-----------\n");
        mostrarMensaje("""                      
                       La idea del Algoritmo del Banquero es permitir la asociación de Recursos
                       sin entrar en un Estado Inseguro. Así como el banco reparte crédito a sus clientes,
                       el computador debería repartir recursos a sus procesos, para esto debe asegurarse
                       de que la solicitud puede satisfacer la cantidad de recursos necesarios.""");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        panelOpciones = new javax.swing.JPanel();
        btnSolicitar = new javax.swing.JButton();
        btnObservar = new javax.swing.JButton();
        btnInstrucciones = new javax.swing.JButton();
        btnDefinir = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AlgoritmoDelBanquero");
        setResizable(false);

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        panelOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        btnSolicitar.setText("2.Solicitar Recursos");
        btnSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarActionPerformed(evt);
            }
        });

        btnObservar.setText("3.Mostrar Estados");
        btnObservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObservarActionPerformed(evt);
            }
        });

        btnInstrucciones.setText("4.Instrucciones");
        btnInstrucciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstruccionesActionPerformed(evt);
            }
        });

        btnDefinir.setText("1.DefinirMatrices");
        btnDefinir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefinirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnObservar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDefinir, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSolicitar)
                    .addComponent(btnDefinir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnObservar)
                    .addComponent(btnInstrucciones))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTitulo.setFont(new java.awt.Font("Oswald", 0, 18)); // NOI18N
        lblTitulo.setText("Algoritmo Del Banquero");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Métodos para las acciones de los botones
    
    private void btnObservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObservarActionPerformed
        // TODO add your handling code here:
        txtArea.setText("");
        txtArea.append(Algoritmo.mostrarMatrices());
    }//GEN-LAST:event_btnObservarActionPerformed

    private void btnSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarActionPerformed
        // TODO add your handling code here:
        Algoritmo.iniciarSolicitud();
        txtArea.setText("");
        txtArea.append(Algoritmo.mostrarMatrices());
    }//GEN-LAST:event_btnSolicitarActionPerformed

    private void btnInstruccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstruccionesActionPerformed
        // TODO add your handling code here:
        instrucciones();
    }//GEN-LAST:event_btnInstruccionesActionPerformed

    private void btnDefinirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefinirActionPerformed
        // TODO add your handling code here:
        Algoritmo.inicializarMatrices();
        Algoritmo.definirMatrices();
        txtArea.setText("");
        txtArea.append(Algoritmo.mostrarMatrices());
    }//GEN-LAST:event_btnDefinirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDefinir;
    private javax.swing.JButton btnInstrucciones;
    private javax.swing.JButton btnObservar;
    private javax.swing.JButton btnSolicitar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
