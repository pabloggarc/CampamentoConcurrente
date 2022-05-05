package parte1;

import javax.swing.UIManager;
import parte2.Cliente;

public class Interfaz extends javax.swing.JFrame {
    
    private Pausa pausa; 
    private Registro registro; 

    public Interfaz(Pausa pausa, Registro registro) {
        this.pausa=pausa; 
        this.registro=registro; 
        initComponents();
        
        //Look de Windows 10
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        }
        catch(Exception e){
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        //Centrar JFrame
        this.setLocationRelativeTo(null); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPuertaB = new javax.swing.JLabel();
        labelPuertaA = new javax.swing.JLabel();
        zonaTextoPuertaA = new javax.swing.JScrollPane();
        textoPuertaA = new javax.swing.JTextPane();
        zonaTextoPuertaB = new javax.swing.JScrollPane();
        textoPuertaB = new javax.swing.JTextPane();
        labelSoga = new javax.swing.JLabel();
        zonaTextoSoga = new javax.swing.JScrollPane();
        textoSoga = new javax.swing.JTextPane();
        labelSogaMonitor = new javax.swing.JLabel();
        zonaTextoSogaMonitor = new javax.swing.JScrollPane();
        textoSogaMonitor = new javax.swing.JTextPane();
        labelSogaEquipoA = new javax.swing.JLabel();
        zonaTextoSogaEquipoA = new javax.swing.JScrollPane();
        textoSogaEquipoA = new javax.swing.JTextPane();
        labelSogaEquipoB = new javax.swing.JLabel();
        zonaTextoSogaEquipoB = new javax.swing.JScrollPane();
        textoSogaEquipoB = new javax.swing.JTextPane();
        zonaTextoSogaEquipoGanadores = new javax.swing.JScrollPane();
        textoSogaEquipoGanadores = new javax.swing.JTextPane();
        labelSogaEquipoGanadores = new javax.swing.JLabel();
        labelZonaComun = new javax.swing.JLabel();
        labelZonaDescansoCampistas = new javax.swing.JLabel();
        zonaTextoZonaDescansoCampistas = new javax.swing.JScrollPane();
        textoZonaDescansoCampistas = new javax.swing.JTextPane();
        labelZonaDescansoMonitores = new javax.swing.JLabel();
        zonaTextoZonaDescansoMonitores = new javax.swing.JScrollPane();
        textoZonaDescansoMonitores = new javax.swing.JTextPane();
        labelTirolina = new javax.swing.JLabel();
        labelTirolinaCampistaPrep = new javax.swing.JLabel();
        zonaTextoTirolinaMonitor = new javax.swing.JScrollPane();
        textoTirolinaMonitor = new javax.swing.JTextPane();
        zonaTextoTirolina = new javax.swing.JScrollPane();
        textoTirolina = new javax.swing.JTextPane();
        labelTirolinaMonitor = new javax.swing.JLabel();
        zonaTextoTirolinaCampistaPrep = new javax.swing.JScrollPane();
        textoTirolinaCampistaPrep = new javax.swing.JTextPane();
        labelTirolinaCampista = new javax.swing.JLabel();
        zonaTextoTirolinaCampista = new javax.swing.JScrollPane();
        textoTirolinaCampista = new javax.swing.JTextPane();
        labelMerendero = new javax.swing.JLabel();
        zonaTextoMerendero = new javax.swing.JScrollPane();
        textoMerendero = new javax.swing.JTextPane();
        labelMerenderoMonitores = new javax.swing.JLabel();
        zonaTextoMerenderoMonitores = new javax.swing.JScrollPane();
        textoMerenderoMonitores = new javax.swing.JTextPane();
        labelMerenderoBandejasSucias = new javax.swing.JLabel();
        labelMerenderoBandejasListas = new javax.swing.JLabel();
        zonaTextoMerenderoSucias = new javax.swing.JScrollPane();
        textoMerenderoSucias = new javax.swing.JTextPane();
        zonaTextoMerenderoLimpias = new javax.swing.JScrollPane();
        textoMerenderoLimpias = new javax.swing.JTextPane();
        botonFinalizar = new javax.swing.JToggleButton();
        botonPararContinuar = new javax.swing.JToggleButton();
        zonaTextoEntradaAMonitor = new javax.swing.JScrollPane();
        textoEntradaAMonitor = new javax.swing.JTextPane();
        zonaTextoEntradaBMonitor = new javax.swing.JScrollPane();
        textoEntradaBMonitor = new javax.swing.JTextPane();
        zonaTextoMerenderoEspera = new javax.swing.JScrollPane();
        textoMerenderoEspera = new javax.swing.JTextPane();
        botonConsultarEstadisticas = new javax.swing.JButton();
        labelTituloMerendero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Campamento Programación Avanzada");
        setMinimumSize(new java.awt.Dimension(1280, 810));
        setResizable(false);
        getContentPane().setLayout(null);

        labelPuertaB.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        labelPuertaB.setText("ENTRADA B");
        getContentPane().add(labelPuertaB);
        labelPuertaB.setBounds(900, 30, 140, 30);

        labelPuertaA.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        labelPuertaA.setText("ENTRADA A");
        getContentPane().add(labelPuertaA);
        labelPuertaA.setBounds(270, 30, 150, 30);

        textoPuertaA.setEditable(false);
        zonaTextoPuertaA.setViewportView(textoPuertaA);

        getContentPane().add(zonaTextoPuertaA);
        zonaTextoPuertaA.setBounds(30, 80, 600, 40);

        textoPuertaB.setEditable(false);
        zonaTextoPuertaB.setViewportView(textoPuertaB);

        getContentPane().add(zonaTextoPuertaB);
        zonaTextoPuertaB.setBounds(660, 80, 580, 40);

        labelSoga.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        labelSoga.setText("SOGA");
        getContentPane().add(labelSoga);
        labelSoga.setBounds(300, 150, 110, 30);

        textoSoga.setEditable(false);
        zonaTextoSoga.setViewportView(textoSoga);

        getContentPane().add(zonaTextoSoga);
        zonaTextoSoga.setBounds(30, 230, 600, 40);

        labelSogaMonitor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelSogaMonitor.setText("Monitor responsable");
        getContentPane().add(labelSogaMonitor);
        labelSogaMonitor.setBounds(30, 190, 140, 30);

        textoSogaMonitor.setEditable(false);
        zonaTextoSogaMonitor.setViewportView(textoSogaMonitor);

        getContentPane().add(zonaTextoSogaMonitor);
        zonaTextoSogaMonitor.setBounds(170, 190, 90, 30);

        labelSogaEquipoA.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelSogaEquipoA.setText("Equipo A");
        getContentPane().add(labelSogaEquipoA);
        labelSogaEquipoA.setBounds(30, 280, 70, 20);

        textoSogaEquipoA.setEditable(false);
        zonaTextoSogaEquipoA.setViewportView(textoSogaEquipoA);

        getContentPane().add(zonaTextoSogaEquipoA);
        zonaTextoSogaEquipoA.setBounds(30, 310, 600, 40);

        labelSogaEquipoB.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelSogaEquipoB.setText("Equipo B");
        getContentPane().add(labelSogaEquipoB);
        labelSogaEquipoB.setBounds(30, 360, 70, 20);

        textoSogaEquipoB.setEditable(false);
        zonaTextoSogaEquipoB.setViewportView(textoSogaEquipoB);

        getContentPane().add(zonaTextoSogaEquipoB);
        zonaTextoSogaEquipoB.setBounds(30, 390, 600, 40);

        textoSogaEquipoGanadores.setEditable(false);
        zonaTextoSogaEquipoGanadores.setViewportView(textoSogaEquipoGanadores);

        getContentPane().add(zonaTextoSogaEquipoGanadores);
        zonaTextoSogaEquipoGanadores.setBounds(30, 480, 600, 40);

        labelSogaEquipoGanadores.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelSogaEquipoGanadores.setText("Últimos ganadores");
        getContentPane().add(labelSogaEquipoGanadores);
        labelSogaEquipoGanadores.setBounds(30, 450, 120, 20);

        labelZonaComun.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        labelZonaComun.setText("ZONA COMÚN");
        getContentPane().add(labelZonaComun);
        labelZonaComun.setBounds(260, 550, 160, 30);

        labelZonaDescansoCampistas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelZonaDescansoCampistas.setText("Niños");
        getContentPane().add(labelZonaDescansoCampistas);
        labelZonaDescansoCampistas.setBounds(30, 580, 120, 20);

        textoZonaDescansoCampistas.setEditable(false);
        zonaTextoZonaDescansoCampistas.setViewportView(textoZonaDescansoCampistas);

        getContentPane().add(zonaTextoZonaDescansoCampistas);
        zonaTextoZonaDescansoCampistas.setBounds(30, 610, 600, 40);

        labelZonaDescansoMonitores.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelZonaDescansoMonitores.setText("Monitores");
        getContentPane().add(labelZonaDescansoMonitores);
        labelZonaDescansoMonitores.setBounds(30, 670, 120, 20);

        textoZonaDescansoMonitores.setEditable(false);
        zonaTextoZonaDescansoMonitores.setViewportView(textoZonaDescansoMonitores);

        getContentPane().add(zonaTextoZonaDescansoMonitores);
        zonaTextoZonaDescansoMonitores.setBounds(30, 700, 600, 40);

        labelTirolina.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        labelTirolina.setText("TIROLINA");
        getContentPane().add(labelTirolina);
        labelTirolina.setBounds(910, 160, 110, 30);

        labelTirolinaCampistaPrep.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelTirolinaCampistaPrep.setText("En preparación");
        getContentPane().add(labelTirolinaCampistaPrep);
        labelTirolinaCampistaPrep.setBounds(870, 310, 140, 30);

        textoTirolinaMonitor.setEditable(false);
        zonaTextoTirolinaMonitor.setViewportView(textoTirolinaMonitor);

        getContentPane().add(zonaTextoTirolinaMonitor);
        zonaTextoTirolinaMonitor.setBounds(800, 310, 60, 30);

        textoTirolina.setEditable(false);
        zonaTextoTirolina.setViewportView(textoTirolina);

        getContentPane().add(zonaTextoTirolina);
        zonaTextoTirolina.setBounds(660, 230, 580, 40);

        labelTirolinaMonitor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelTirolinaMonitor.setText("Monitor responsable");
        getContentPane().add(labelTirolinaMonitor);
        labelTirolinaMonitor.setBounds(660, 310, 140, 30);

        textoTirolinaCampistaPrep.setEditable(false);
        zonaTextoTirolinaCampistaPrep.setViewportView(textoTirolinaCampistaPrep);

        getContentPane().add(zonaTextoTirolinaCampistaPrep);
        zonaTextoTirolinaCampistaPrep.setBounds(980, 310, 80, 30);

        labelTirolinaCampista.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelTirolinaCampista.setText("Tirándose");
        getContentPane().add(labelTirolinaCampista);
        labelTirolinaCampista.setBounds(1070, 310, 140, 30);

        textoTirolinaCampista.setEditable(false);
        zonaTextoTirolinaCampista.setViewportView(textoTirolinaCampista);

        getContentPane().add(zonaTextoTirolinaCampista);
        zonaTextoTirolinaCampista.setBounds(1150, 310, 90, 30);

        labelMerendero.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        labelMerendero.setText("MERENDERO");
        getContentPane().add(labelMerendero);
        labelMerendero.setBounds(890, 390, 150, 30);

        textoMerendero.setEditable(false);
        zonaTextoMerendero.setViewportView(textoMerendero);

        getContentPane().add(zonaTextoMerendero);
        zonaTextoMerendero.setBounds(670, 540, 580, 40);

        labelMerenderoMonitores.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelMerenderoMonitores.setText("Monitores");
        getContentPane().add(labelMerenderoMonitores);
        labelMerenderoMonitores.setBounds(670, 610, 160, 30);

        textoMerenderoMonitores.setEditable(false);
        zonaTextoMerenderoMonitores.setViewportView(textoMerenderoMonitores);

        getContentPane().add(zonaTextoMerenderoMonitores);
        zonaTextoMerenderoMonitores.setBounds(750, 610, 150, 30);

        labelMerenderoBandejasSucias.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelMerenderoBandejasSucias.setText("Bandejas sucias");
        getContentPane().add(labelMerenderoBandejasSucias);
        labelMerenderoBandejasSucias.setBounds(920, 610, 110, 30);

        labelMerenderoBandejasListas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelMerenderoBandejasListas.setText("Bandejas listas");
        getContentPane().add(labelMerenderoBandejasListas);
        labelMerenderoBandejasListas.setBounds(1100, 610, 100, 30);

        textoMerenderoSucias.setEditable(false);
        zonaTextoMerenderoSucias.setViewportView(textoMerenderoSucias);

        getContentPane().add(zonaTextoMerenderoSucias);
        zonaTextoMerenderoSucias.setBounds(1040, 610, 40, 30);

        textoMerenderoLimpias.setEditable(false);
        zonaTextoMerenderoLimpias.setViewportView(textoMerenderoLimpias);

        getContentPane().add(zonaTextoMerenderoLimpias);
        zonaTextoMerenderoLimpias.setBounds(1210, 610, 40, 30);

        botonFinalizar.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonFinalizar.setText("Finalizar");
        botonFinalizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFinalizarActionPerformed(evt);
            }
        });
        getContentPane().add(botonFinalizar);
        botonFinalizar.setBounds(670, 680, 150, 60);

        botonPararContinuar.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonPararContinuar.setText("Parar/Continuar");
        botonPararContinuar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonPararContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPararContinuarActionPerformed(evt);
            }
        });
        getContentPane().add(botonPararContinuar);
        botonPararContinuar.setBounds(870, 680, 190, 60);

        textoEntradaAMonitor.setEditable(false);
        zonaTextoEntradaAMonitor.setViewportView(textoEntradaAMonitor);

        getContentPane().add(zonaTextoEntradaAMonitor);
        zonaTextoEntradaAMonitor.setBounds(30, 40, 90, 30);

        textoEntradaBMonitor.setEditable(false);
        zonaTextoEntradaBMonitor.setViewportView(textoEntradaBMonitor);

        getContentPane().add(zonaTextoEntradaBMonitor);
        zonaTextoEntradaBMonitor.setBounds(660, 40, 90, 30);

        textoMerenderoEspera.setEditable(false);
        zonaTextoMerenderoEspera.setViewportView(textoMerenderoEspera);

        getContentPane().add(zonaTextoMerenderoEspera);
        zonaTextoMerenderoEspera.setBounds(670, 480, 580, 40);

        botonConsultarEstadisticas.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        botonConsultarEstadisticas.setText("Estadísticas");
        botonConsultarEstadisticas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonConsultarEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarEstadisticasActionPerformed(evt);
            }
        });
        getContentPane().add(botonConsultarEstadisticas);
        botonConsultarEstadisticas.setBounds(1100, 680, 150, 60);

        labelTituloMerendero.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelTituloMerendero.setText("Esperando comidas/comiendo");
        getContentPane().add(labelTituloMerendero);
        labelTituloMerendero.setBounds(670, 446, 200, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFinalizarActionPerformed
        registro.cerrarRegistro();
        System.exit(0); 
    }//GEN-LAST:event_botonFinalizarActionPerformed

    private void botonPararContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPararContinuarActionPerformed
        if(botonPararContinuar.isSelected()){
            pausa.pausar();
        }
        else{
            pausa.continuar();
        }
    }//GEN-LAST:event_botonPararContinuarActionPerformed

    private void botonConsultarEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarEstadisticasActionPerformed
        Cliente interfazEstadisticas=new Cliente(registro); 
        interfazEstadisticas.setVisible(true);
    }//GEN-LAST:event_botonConsultarEstadisticasActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConsultarEstadisticas;
    private javax.swing.JToggleButton botonFinalizar;
    private javax.swing.JToggleButton botonPararContinuar;
    private javax.swing.JLabel labelMerendero;
    private javax.swing.JLabel labelMerenderoBandejasListas;
    private javax.swing.JLabel labelMerenderoBandejasSucias;
    private javax.swing.JLabel labelMerenderoMonitores;
    private javax.swing.JLabel labelPuertaA;
    private javax.swing.JLabel labelPuertaB;
    private javax.swing.JLabel labelSoga;
    private javax.swing.JLabel labelSogaEquipoA;
    private javax.swing.JLabel labelSogaEquipoB;
    private javax.swing.JLabel labelSogaEquipoGanadores;
    private javax.swing.JLabel labelSogaMonitor;
    private javax.swing.JLabel labelTirolina;
    private javax.swing.JLabel labelTirolinaCampista;
    private javax.swing.JLabel labelTirolinaCampistaPrep;
    private javax.swing.JLabel labelTirolinaMonitor;
    private javax.swing.JLabel labelTituloMerendero;
    private javax.swing.JLabel labelZonaComun;
    private javax.swing.JLabel labelZonaDescansoCampistas;
    private javax.swing.JLabel labelZonaDescansoMonitores;
    private javax.swing.JTextPane textoEntradaAMonitor;
    private javax.swing.JTextPane textoEntradaBMonitor;
    private javax.swing.JTextPane textoMerendero;
    private javax.swing.JTextPane textoMerenderoEspera;
    private javax.swing.JTextPane textoMerenderoLimpias;
    private javax.swing.JTextPane textoMerenderoMonitores;
    private javax.swing.JTextPane textoMerenderoSucias;
    private javax.swing.JTextPane textoPuertaA;
    private javax.swing.JTextPane textoPuertaB;
    private javax.swing.JTextPane textoSoga;
    private javax.swing.JTextPane textoSogaEquipoA;
    private javax.swing.JTextPane textoSogaEquipoB;
    private javax.swing.JTextPane textoSogaEquipoGanadores;
    private javax.swing.JTextPane textoSogaMonitor;
    private javax.swing.JTextPane textoTirolina;
    private javax.swing.JTextPane textoTirolinaCampista;
    private javax.swing.JTextPane textoTirolinaCampistaPrep;
    private javax.swing.JTextPane textoTirolinaMonitor;
    private javax.swing.JTextPane textoZonaDescansoCampistas;
    private javax.swing.JTextPane textoZonaDescansoMonitores;
    private javax.swing.JScrollPane zonaTextoEntradaAMonitor;
    private javax.swing.JScrollPane zonaTextoEntradaBMonitor;
    private javax.swing.JScrollPane zonaTextoMerendero;
    private javax.swing.JScrollPane zonaTextoMerenderoEspera;
    private javax.swing.JScrollPane zonaTextoMerenderoLimpias;
    private javax.swing.JScrollPane zonaTextoMerenderoMonitores;
    private javax.swing.JScrollPane zonaTextoMerenderoSucias;
    private javax.swing.JScrollPane zonaTextoPuertaA;
    private javax.swing.JScrollPane zonaTextoPuertaB;
    private javax.swing.JScrollPane zonaTextoSoga;
    private javax.swing.JScrollPane zonaTextoSogaEquipoA;
    private javax.swing.JScrollPane zonaTextoSogaEquipoB;
    private javax.swing.JScrollPane zonaTextoSogaEquipoGanadores;
    private javax.swing.JScrollPane zonaTextoSogaMonitor;
    private javax.swing.JScrollPane zonaTextoTirolina;
    private javax.swing.JScrollPane zonaTextoTirolinaCampista;
    private javax.swing.JScrollPane zonaTextoTirolinaCampistaPrep;
    private javax.swing.JScrollPane zonaTextoTirolinaMonitor;
    private javax.swing.JScrollPane zonaTextoZonaDescansoCampistas;
    private javax.swing.JScrollPane zonaTextoZonaDescansoMonitores;
    // End of variables declaration//GEN-END:variables
    
    public synchronized void setTextoEntradaA(String t){
        textoPuertaA.setText(t);
    }
    
    public synchronized void setTextoEntradaB(String t){
        textoPuertaB.setText(t);
    }
    
    public synchronized void setTextoSogaMonitor(String t){
        textoSogaMonitor.setText(t);
    }
    
    public synchronized void setTextoSoga(String t){
        textoSoga.setText(t);
    }
    
    public synchronized void setTextoSogaEquipoA(String t){
        textoSogaEquipoA.setText(t);
    }
    
    public synchronized void setTextoSogaEquipoB(String t){
        textoSogaEquipoB.setText(t);
    }
    
    public synchronized void setTextoSogaGanadores(String t){
        textoSogaEquipoGanadores.setText(t);
    }
    
    public synchronized void setTextoZonaDescansoCampistas(String t){
        textoZonaDescansoCampistas.setText(t);
    }
    
    public synchronized void setTextoZonaDescansoMonitores(String t){
        textoZonaDescansoMonitores.setText(t);
    }
    
    public synchronized void setTextoTirolina(String t){
        textoTirolina.setText(t);
    }
    
    public synchronized void setTextoTirolinaMonitor(String t){
        textoTirolinaMonitor.setText(t);
    }
    
    public synchronized void setTextoTirolinaCampistaPreparacion(String t){
        textoTirolinaCampistaPrep.setText(t);
    }
    
    public synchronized void setTextoTirolinaTirandose(String t){
        textoTirolinaCampista.setText(t);
    }
    
    public synchronized void setTextoMerendero(String t){
        textoMerendero.setText(t);
    }
    
    public synchronized void setTextoMerenderoMonitores(String t){
        textoMerenderoMonitores.setText(t);
    }
    
    public synchronized void setTextoMerenderoBandejasSucias(String t){
        textoMerenderoSucias.setText(t);
    }
    
    public synchronized void setTextoMerenderoBandejasListas(String t){
        textoMerenderoLimpias.setText(t); 
    }
    
    public synchronized void setTextoEntradaAMonitores(String t){
        textoEntradaAMonitor.setText(t);
    }
    
    public synchronized void setTextoEntradaBMonitores(String t){
        textoEntradaBMonitor.setText(t);
    }
    
    public synchronized void setTextoMerenderoEspera(String t){
        textoMerenderoEspera.setText(t);
    }
    
    public void comprobarPausa(){
        pausa.pasar();
    }

}
