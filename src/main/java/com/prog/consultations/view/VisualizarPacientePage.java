package com.prog.consultations.view;

import com.prog.consultations.controller.PacienteController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizarPacientePage extends JFrame{
    private JTextField TFEmail;
    private JButton visualizarButton;
    private JPanel JPVisPac;

    public VisualizarPacientePage(PacienteController pacienteController) {
        setContentPane(JPVisPac);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        pack();
        setVisible(true);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pacienteController.buscarPacientePorEmail(TFEmail.getText());
                    EditarPacientePage editarPacientePage = new EditarPacientePage(pacienteController, TFEmail.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao visualizar paciente");
                    ex.printStackTrace();
                }
            }
        });
    }
}
