package com.prog.consultations.view;

import com.prog.consultations.controller.MedicoController;
import com.prog.consultations.domain.Medico;
import com.prog.consultations.exception.EmailUsedException;

import javax.swing.*;
import java.awt.*;

public class RegistrarMedicoPage extends JFrame {
    private JTextField TextEmail;
    private JTextField TextNome;
    private JTextField TextIdade;
    private JTextField TextTelefone;
    private JTextField TextAnoEntrada;
    private JTextField TextCRM;
    private JTextField TextEspecialidade;
    private JTextField TextClinica;
    private JButton registrarButton;
    private JPanel JPRegMed;

    public RegistrarMedicoPage(MedicoController medicoController) {
        setContentPane(JPRegMed);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 450));
        pack();
        setVisible(true);
        registrarButton.addActionListener(e -> {
            try{
            medicoController.criarMedico(new Medico(TextNome.getText(), TextEmail.getText(), Integer.parseInt(TextIdade.getText()), TextTelefone.getText(), Integer.parseInt(TextAnoEntrada.getText()), TextCRM.getText(), TextEspecialidade.getText(), TextClinica.getText()));
            JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso");
            dispose();
            } catch (EmailUsedException ex){
                JOptionPane.showMessageDialog(null, "Email já cadastrado");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar médico\n" + ex.getMessage());
                ex.printStackTrace();
        }
    });
    }
}
