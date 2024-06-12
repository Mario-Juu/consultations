package com.prog.consultations.view;

import com.prog.consultations.controller.MedicoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizarMedicoPage extends JFrame {
    private JPanel JPVisMed;
    private JTextField TFEmail;
    private JButton visualizarButton;
    private JButton visualizarTodosButton;

    public VisualizarMedicoPage(MedicoController medicoController) {
        setContentPane(JPVisMed);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    medicoController.buscarMedicoPorEmail(TFEmail.getText());
                    EditarMedicoPage editarMedicoPage = new EditarMedicoPage(medicoController, TFEmail.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao visualizar médico");
                    ex.printStackTrace();
                }
            }
        });
        visualizarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizarTodosMedicosPage visualizarTodosMedicosPage = new VisualizarTodosMedicosPage(medicoController);
            }
        });
    }
}
