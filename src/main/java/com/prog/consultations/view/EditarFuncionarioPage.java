package com.prog.consultations.view;

import com.prog.consultations.controller.FuncionarioController;
import com.prog.consultations.domain.Funcionario;
import com.prog.consultations.exception.ResourceNotFoundException;

import javax.swing.*;
import java.awt.*;

public class EditarFuncionarioPage extends JFrame{
    private JPanel JPEditFun;
    private JTextField TFNome;
    private JTextField TFEmail;
    private JTextField TFIdade;
    private JTextField TFTelefone;
    private JTextField TFAnoEntrada;
    private JTextField TFCargo;
    private JTextField TFSindicato;
    private JButton editarButton;
    private JButton deletarButton;

    public EditarFuncionarioPage(FuncionarioController funcionarioController, String email){
        Funcionario funcionario = funcionarioController.buscarFuncionarioPorEmail(email);
        TFNome.setText(funcionario.getNome());
        TFEmail.setText(funcionario.getEmail());
        TFIdade.setText(String.valueOf(funcionario.getIdade()));
        TFTelefone.setText(funcionario.getTelefone());
        TFCargo.setText(funcionario.getCargo());
        TFSindicato.setText(funcionario.getSindicato());
        TFAnoEntrada.setText(String.valueOf(funcionario.getAnoEntrada()));
        setContentPane(JPEditFun);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 450));
        pack();
        setVisible(true);

        editarButton.addActionListener(e -> {
            try{
                Funcionario funcionarioAtt = new Funcionario(funcionario.getId(), TFNome.getText(), TFEmail.getText(), Integer.parseInt(TFIdade.getText()), TFTelefone.getText(), Integer.parseInt(TFAnoEntrada.getText()), TFCargo.getText(), TFSindicato.getText());
                funcionarioController.atualizarFuncionario(funcionario.getId(), funcionarioAtt);
                JOptionPane.showMessageDialog(null, "Funcionário editado com sucesso");
                dispose();
            }
            catch (ResourceNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
                ex.printStackTrace();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao editar funcionário\n" + ex.getMessage());
                ex.printStackTrace();
            }
        });
        deletarButton.addActionListener(e -> {
            try{
                String message = JOptionPane.showInputDialog("Digite 'SIM' para confirmar a exclusão do funcionário");
                if(!message.equals("SIM")){
                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                    return;
                }
                funcionarioController.deletarFuncionario(funcionario.getId());
                JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso");
                dispose();
            }
            catch (ResourceNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
                ex.printStackTrace();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao deletar funcionário");
                ex.printStackTrace();
            }
        });
    }
}
