package com.prog.consultations.view;

import com.prog.consultations.controller.FuncionarioController;
import com.prog.consultations.domain.Funcionario;

import javax.swing.*;
import java.awt.*;

public class RegistrarFuncionarioPage extends JFrame {
    private JPanel JPRegFunc;
    private JTextField TFNome;
    private JTextField TFEmail;
    private JTextField TFIdade;
    private JTextField TFTelefone;
    private JTextField TFAnoEntrada;
    private JTextField TFCargo;
    private JTextField TFSindicato;
    private JButton registrarButton;

    public RegistrarFuncionarioPage(FuncionarioController funcionarioController) {
        setContentPane(JPRegFunc);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 450));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        registrarButton.addActionListener(e -> {
            try{
                funcionarioController.criarFuncionario(new Funcionario(TFNome.getText(), TFEmail.getText(), Integer.parseInt(TFIdade.getText()), TFTelefone.getText(), Integer.parseInt(TFAnoEntrada.getText()), TFCargo.getText(), TFSindicato.getText()));
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso");
                dispose();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário\n" + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
