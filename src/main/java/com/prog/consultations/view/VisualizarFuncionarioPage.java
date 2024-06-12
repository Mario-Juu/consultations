package com.prog.consultations.view;

import com.prog.consultations.controller.FuncionarioController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizarFuncionarioPage extends JFrame {
    private JTextField TFEmail;
    private JButton visualizarButton;
    private JPanel JPVisFunc;
    private JButton visualizarTodosButton;

    public VisualizarFuncionarioPage(FuncionarioController funcionarioController) {
        setContentPane(JPVisFunc);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    funcionarioController.buscarFuncionarioPorEmail(TFEmail.getText());
                    EditarFuncionarioPage editarFuncionarioPage = new EditarFuncionarioPage(funcionarioController, TFEmail.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao visualizar médico");
                    ex.printStackTrace();
                }
            }
        });
        visualizarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizarTodosFuncionariosPage visualizarTodosFuncionariosPage = new VisualizarTodosFuncionariosPage(funcionarioController);
            }
        });
    }
}
