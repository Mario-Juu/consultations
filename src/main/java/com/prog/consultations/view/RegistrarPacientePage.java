package com.prog.consultations.view;

import com.prog.consultations.controller.PacienteController;
import com.prog.consultations.domain.Paciente;
import com.prog.consultations.exception.EmailUsedException;

import javax.swing.*;
import java.awt.*;

public class RegistrarPacientePage extends JFrame {
    private JPanel JPRegPac;
    private JTextField TFNome;
    private JTextField TFEmail;
    private JTextField TFIdade;
    private JTextField TFTelefone;
    private JButton registrarButton;

    public RegistrarPacientePage(PacienteController pacienteController) {
        setContentPane(JPRegPac);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 450));
        pack();
        setVisible(true);
        registrarButton.addActionListener(e -> {
            try{
                pacienteController.criarPaciente(new Paciente(TFNome.getText(), TFEmail.getText(), Integer.parseInt(TFIdade.getText()), TFTelefone.getText()));
                JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
                dispose();
            } catch (EmailUsedException ex){
                JOptionPane.showMessageDialog(null, "Email já cadastrado");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente\n" + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
