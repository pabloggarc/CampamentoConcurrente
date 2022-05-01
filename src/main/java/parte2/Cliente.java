package parte2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import principal.plcampamento.Registro;

public class Cliente extends javax.swing.JFrame {
    
    private Registro registro; 
    private Socket socket; 
    private DataInputStream entrada; 
    private DataOutputStream salida; 

    public Cliente(Registro registro){
        this.registro=registro; 
        
        initComponents();
        
        //Look de Windows 10
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        }
        catch(Exception e){
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        
        //Centrar JFrame
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelEstadisticas = new javax.swing.JLabel();
        labelTirolina = new javax.swing.JLabel();
        labelEsperandoTirolina = new javax.swing.JLabel();
        labelCampistasLanzados = new javax.swing.JLabel();
        textoEsperandoTirolina = new javax.swing.JTextField();
        textoCampistasLanzados = new javax.swing.JTextField();
        botonEsperandoTirolina = new javax.swing.JButton();
        botonCampistasLanzados = new javax.swing.JButton();
        labelMerendero = new javax.swing.JLabel();
        labelCampistasMerendando = new javax.swing.JLabel();
        labelBandejasSucias = new javax.swing.JLabel();
        labelBandejasLimpias = new javax.swing.JLabel();
        textoCampistasMerendando = new javax.swing.JTextField();
        botonCampistasMerendando = new javax.swing.JButton();
        textoBandejasSucias = new javax.swing.JTextField();
        botonBandejasSucias = new javax.swing.JButton();
        textoBandejasLimpias = new javax.swing.JTextField();
        botonBandejasLimpias = new javax.swing.JButton();
        labelSoga = new javax.swing.JLabel();
        labelEsperandoSoga = new javax.swing.JLabel();
        textoEsperandoSoga = new javax.swing.JTextField();
        botonEsperandoSoga = new javax.swing.JButton();
        labelActividades = new javax.swing.JLabel();
        labelCampistaEstadistica = new javax.swing.JLabel();
        textoCampistaEstadistica = new javax.swing.JTextField();
        botonCampistaEstadistica = new javax.swing.JButton();
        labelActividadesCampista = new javax.swing.JLabel();
        textoActividadesCampista = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estadísticas Campamento");
        setMinimumSize(new java.awt.Dimension(823, 491));
        setPreferredSize(new java.awt.Dimension(823, 491));
        setResizable(false);
        getContentPane().setLayout(null);

        labelEstadisticas.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelEstadisticas.setText("ESTADÍSTICAS");
        getContentPane().add(labelEstadisticas);
        labelEstadisticas.setBounds(320, 20, 190, 50);

        labelTirolina.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelTirolina.setText("TIROLINA");
        getContentPane().add(labelTirolina);
        labelTirolina.setBounds(30, 120, 90, 20);

        labelEsperandoTirolina.setText("Esperando");
        getContentPane().add(labelEsperandoTirolina);
        labelEsperandoTirolina.setBounds(30, 160, 80, 20);

        labelCampistasLanzados.setText("Niños lanzados");
        getContentPane().add(labelCampistasLanzados);
        labelCampistasLanzados.setBounds(30, 200, 90, 20);

        textoEsperandoTirolina.setEditable(false);
        getContentPane().add(textoEsperandoTirolina);
        textoEsperandoTirolina.setBounds(130, 160, 100, 24);

        textoCampistasLanzados.setEditable(false);
        getContentPane().add(textoCampistasLanzados);
        textoCampistasLanzados.setBounds(130, 200, 100, 24);

        botonEsperandoTirolina.setText("Consultar");
        botonEsperandoTirolina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEsperandoTirolinaActionPerformed(evt);
            }
        });
        getContentPane().add(botonEsperandoTirolina);
        botonEsperandoTirolina.setBounds(250, 160, 80, 24);

        botonCampistasLanzados.setText("Consultar");
        botonCampistasLanzados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCampistasLanzadosActionPerformed(evt);
            }
        });
        getContentPane().add(botonCampistasLanzados);
        botonCampistasLanzados.setBounds(250, 200, 80, 24);

        labelMerendero.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelMerendero.setText("MERENDERO");
        getContentPane().add(labelMerendero);
        labelMerendero.setBounds(30, 260, 130, 20);

        labelCampistasMerendando.setText("Niños merendando");
        getContentPane().add(labelCampistasMerendando);
        labelCampistasMerendando.setBounds(30, 300, 110, 20);

        labelBandejasSucias.setText("Bandejas sucias");
        getContentPane().add(labelBandejasSucias);
        labelBandejasSucias.setBounds(30, 340, 100, 20);

        labelBandejasLimpias.setText("Bandejas limpias");
        getContentPane().add(labelBandejasLimpias);
        labelBandejasLimpias.setBounds(30, 380, 110, 16);

        textoCampistasMerendando.setEditable(false);
        getContentPane().add(textoCampistasMerendando);
        textoCampistasMerendando.setBounds(150, 300, 80, 24);

        botonCampistasMerendando.setText("Consultar");
        botonCampistasMerendando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCampistasMerendandoActionPerformed(evt);
            }
        });
        getContentPane().add(botonCampistasMerendando);
        botonCampistasMerendando.setBounds(250, 300, 80, 24);

        textoBandejasSucias.setEditable(false);
        getContentPane().add(textoBandejasSucias);
        textoBandejasSucias.setBounds(140, 340, 90, 24);

        botonBandejasSucias.setText("Consultar");
        botonBandejasSucias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBandejasSuciasActionPerformed(evt);
            }
        });
        getContentPane().add(botonBandejasSucias);
        botonBandejasSucias.setBounds(250, 340, 80, 24);

        textoBandejasLimpias.setEditable(false);
        getContentPane().add(textoBandejasLimpias);
        textoBandejasLimpias.setBounds(140, 380, 90, 24);

        botonBandejasLimpias.setText("Consultar");
        botonBandejasLimpias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBandejasLimpiasActionPerformed(evt);
            }
        });
        getContentPane().add(botonBandejasLimpias);
        botonBandejasLimpias.setBounds(250, 380, 80, 24);

        labelSoga.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelSoga.setText("SOGA");
        getContentPane().add(labelSoga);
        labelSoga.setBounds(410, 120, 90, 20);

        labelEsperandoSoga.setText("Esperando para jugar");
        getContentPane().add(labelEsperandoSoga);
        labelEsperandoSoga.setBounds(410, 150, 130, 20);

        textoEsperandoSoga.setEditable(false);
        getContentPane().add(textoEsperandoSoga);
        textoEsperandoSoga.setBounds(550, 150, 100, 24);

        botonEsperandoSoga.setText("Consultar");
        botonEsperandoSoga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEsperandoSogaActionPerformed(evt);
            }
        });
        getContentPane().add(botonEsperandoSoga);
        botonEsperandoSoga.setBounds(670, 150, 80, 24);

        labelActividades.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelActividades.setText("ACTIVIDADES");
        getContentPane().add(labelActividades);
        labelActividades.setBounds(410, 200, 130, 20);

        labelCampistaEstadistica.setText("ID niño:");
        getContentPane().add(labelCampistaEstadistica);
        labelCampistaEstadistica.setBounds(410, 240, 50, 20);
        getContentPane().add(textoCampistaEstadistica);
        textoCampistaEstadistica.setBounds(470, 240, 100, 24);

        botonCampistaEstadistica.setText("Consultar");
        botonCampistaEstadistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCampistaEstadisticaActionPerformed(evt);
            }
        });
        getContentPane().add(botonCampistaEstadistica);
        botonCampistaEstadistica.setBounds(590, 240, 80, 24);

        labelActividadesCampista.setText("Actividades completadas");
        getContentPane().add(labelActividadesCampista);
        labelActividadesCampista.setBounds(480, 290, 150, 16);

        textoActividadesCampista.setEditable(false);
        getContentPane().add(textoActividadesCampista);
        textoActividadesCampista.setBounds(480, 320, 120, 24);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEsperandoTirolinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEsperandoTirolinaActionPerformed
        textoEsperandoTirolina.setText(String.valueOf(consultar(0, 0)));
    }//GEN-LAST:event_botonEsperandoTirolinaActionPerformed

    private void botonCampistasLanzadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCampistasLanzadosActionPerformed
        textoCampistasLanzados.setText(String.valueOf(consultar(1, 0)));
    }//GEN-LAST:event_botonCampistasLanzadosActionPerformed

    private void botonCampistasMerendandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCampistasMerendandoActionPerformed
        textoCampistasMerendando.setText(String.valueOf(consultar(3, 0)));
    }//GEN-LAST:event_botonCampistasMerendandoActionPerformed

    private void botonBandejasSuciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBandejasSuciasActionPerformed
        textoBandejasSucias.setText(String.valueOf(consultar(4, 0)));
    }//GEN-LAST:event_botonBandejasSuciasActionPerformed

    private void botonBandejasLimpiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBandejasLimpiasActionPerformed
        textoBandejasLimpias.setText(String.valueOf(consultar(5, 0)));
    }//GEN-LAST:event_botonBandejasLimpiasActionPerformed

    private void botonEsperandoSogaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEsperandoSogaActionPerformed
        textoEsperandoSoga.setText(String.valueOf(consultar(2, 0)));
    }//GEN-LAST:event_botonEsperandoSogaActionPerformed

    private void botonCampistaEstadisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCampistaEstadisticaActionPerformed
        try{
            String posibleID=textoCampistaEstadistica.getText(); 
            if(posibleID.charAt(0)=='N' && Integer.parseInt(posibleID.substring(1))>=1 && Integer.parseInt(posibleID.substring(1))<=20000){
                textoActividadesCampista.setText(String.valueOf(consultar(Integer.parseInt(posibleID.substring(1)), 1)));
            }
            else{
                mostrarErrorIDInvalido(); 
            }
        }
        catch(Exception e){
            mostrarErrorIDInvalido(); 
        }
    }//GEN-LAST:event_botonCampistaEstadisticaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBandejasLimpias;
    private javax.swing.JButton botonBandejasSucias;
    private javax.swing.JButton botonCampistaEstadistica;
    private javax.swing.JButton botonCampistasLanzados;
    private javax.swing.JButton botonCampistasMerendando;
    private javax.swing.JButton botonEsperandoSoga;
    private javax.swing.JButton botonEsperandoTirolina;
    private javax.swing.JLabel labelActividades;
    private javax.swing.JLabel labelActividadesCampista;
    private javax.swing.JLabel labelBandejasLimpias;
    private javax.swing.JLabel labelBandejasSucias;
    private javax.swing.JLabel labelCampistaEstadistica;
    private javax.swing.JLabel labelCampistasLanzados;
    private javax.swing.JLabel labelCampistasMerendando;
    private javax.swing.JLabel labelEsperandoSoga;
    private javax.swing.JLabel labelEsperandoTirolina;
    private javax.swing.JLabel labelEstadisticas;
    private javax.swing.JLabel labelMerendero;
    private javax.swing.JLabel labelSoga;
    private javax.swing.JLabel labelTirolina;
    private javax.swing.JTextField textoActividadesCampista;
    private javax.swing.JTextField textoBandejasLimpias;
    private javax.swing.JTextField textoBandejasSucias;
    private javax.swing.JTextField textoCampistaEstadistica;
    private javax.swing.JTextField textoCampistasLanzados;
    private javax.swing.JTextField textoCampistasMerendando;
    private javax.swing.JTextField textoEsperandoSoga;
    private javax.swing.JTextField textoEsperandoTirolina;
    // End of variables declaration//GEN-END:variables

    public int consultar(int c, int n){
        int consulta=0; 
        try{
            socket=new Socket("localhost", 5000); 
            entrada=new DataInputStream(socket.getInputStream()); 
            salida=new DataOutputStream(socket.getOutputStream());
            
            salida.writeUTF(String.valueOf(n)+String.valueOf(c));
            consulta=entrada.readInt();
            cerrarConexion(); 
        }
        catch(IOException ioe){
            registro.escribir("Error al enviar petición");
        }
        return consulta; 
    }
    
    public void cerrarConexion(){
        try{
            entrada.close(); 
            salida.close(); 
            socket.close(); 
        }
        catch(IOException ioe){
            registro.escribir("Error al cerrar conexión");
        }
    }
    
    public void mostrarErrorIDInvalido(){
        JOptionPane.showMessageDialog(null, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
