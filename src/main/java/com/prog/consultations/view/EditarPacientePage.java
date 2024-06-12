package com.prog.consultations.view;

import com.prog.consultations.controller.PacienteController;
import com.prog.consultations.domain.Paciente;
import com.prog.consultations.exception.ResourceNotFoundException;

import javax.swing.*;
import java.awt.*;

public class EditarPacientePage extends JFrame {
    private JTextField TFNome;
    private JTextField TFEmail;
    private JTextField TFIdade;
    private JTextField TFTelefone;
    private JButton editarButton;
    private JButton deletarButton;
    private JPanel JPEditPac;

    public EditarPacientePage(PacienteController pacienteController, String email){
        Paciente paciente = pacienteController.buscarPacientePorEmail(email).get();
        TFNome.setText(paciente.getNome());
        TFEmail.setText(paciente.getEmail());
        TFIdade.setText(String.valueOf(paciente.getIdade()));
        TFTelefone.setText(paciente.getTelefone());
        setContentPane(JPEditPac);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 450));
        pack();
        setVisible(true);

        editarButton.addActionListener(e -> {
            try{
                Paciente pacienteAtt = new Paciente(paciente.getId(), TFNome.getText(), TFEmail.getText(), Integer.parseInt(TFIdade.getText()), TFTelefone.getText());
                pacienteController.atualizarPaciente(paciente.getId(), pacienteAtt);
                JOptionPane.showMessageDialog(null, "Paciente editado com sucesso");
                dispose();
            }
            catch (ResourceNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Paciente não encontrado");
                ex.printStackTrace();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao editar paciente\n" + ex.getMessage());
                ex.printStackTrace();
            }
        });
        deletarButton.addActionListener(e -> {
            try{
                String message = JOptionPane.showInputDialog("Digite 'SIM' para confirmar a exclusão do paciente");
                if(!message.equals("SIM")){
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                    return;
                }
                pacienteController.deletarPaciente(paciente.getId());
                JOptionPane.showMessageDialog(null, "Paciente deletado com sucesso");
                dispose();
            }
            catch (ResourceNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Paciente não encontrado");
                ex.printStackTrace();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao deletar paciente");
                ex.printStackTrace();
            }
        });
    }
}
