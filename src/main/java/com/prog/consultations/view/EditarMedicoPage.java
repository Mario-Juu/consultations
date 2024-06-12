package com.prog.consultations.view;

import com.prog.consultations.controller.MedicoController;
import com.prog.consultations.domain.Medico;
import com.prog.consultations.exception.ResourceNotFoundException;

import javax.swing.*;
import java.awt.*;

public class EditarMedicoPage extends JFrame {
    private JTextField TFNome;
    private JTextField TFEmail;
    private JTextField TFIdade;
    private JTextField TFTelefone;
    private JTextField TFAnoEntrada;
    private JTextField TFCRM;
    private JTextField TFEspecialidade;
    private JTextField TFClinica;
    private JButton editarButton;
    private JButton deletarButton;
    private JPanel JPEditMed;

    public EditarMedicoPage(MedicoController medicoController, String email){
        Medico medico = medicoController.buscarMedicoPorEmail(email);
        TFNome.setText(medico.getNome());
        TFEmail.setText(medico.getEmail());
        TFIdade.setText(String.valueOf(medico.getIdade()));
        TFTelefone.setText(medico.getTelefone());
        TFClinica.setText(medico.getClinica());
        TFEspecialidade.setText(medico.getEspecialidade());
        TFCRM.setText(medico.getCrm());
        TFAnoEntrada.setText(String.valueOf(medico.getAnoEntrada()));

        setContentPane(JPEditMed);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 450));
        pack();
        setVisible(true);

        editarButton.addActionListener(e -> {
            try{
                Medico medicoAtt = new Medico(medico.getId(), TFNome.getText(), TFEmail.getText(), Integer.parseInt(TFIdade.getText()), TFTelefone.getText(), Integer.parseInt(TFAnoEntrada.getText()), TFCRM.getText(), TFEspecialidade.getText(), TFClinica.getText());
                medicoController.atualizarMedico(medico.getId(), medicoAtt);
                JOptionPane.showMessageDialog(null, "Médico editado com sucesso");
                dispose();
            }
            catch (ResourceNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Médico não encontrado");
                ex.printStackTrace();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao editar médico\n" + ex.getMessage());
                ex.printStackTrace();
            }
        });
        deletarButton.addActionListener(e -> {
            try{
                String message = JOptionPane.showInputDialog("Digite 'SIM' para confirmar a exclusão do médico");
                if(!message.equals("SIM")){
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                    return;
                }
                medicoController.deletarMedico(medico.getId());
                JOptionPane.showMessageDialog(null, "Médico deletado com sucesso");
                dispose();
            }
            catch (ResourceNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Médico não encontrado");
                ex.printStackTrace();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao deletar médico");
                ex.printStackTrace();
            }
        });
    }
}
