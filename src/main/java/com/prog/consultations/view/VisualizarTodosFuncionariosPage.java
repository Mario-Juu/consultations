package com.prog.consultations.view;

import com.prog.consultations.controller.FuncionarioController;
import com.prog.consultations.domain.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisualizarTodosFuncionariosPage extends JFrame {
    private JPanel JPViewAll;
    private JTable table1;
    private JScrollPane JSPane;

    public VisualizarTodosFuncionariosPage(FuncionarioController funcionarioController) {
        add(JPViewAll);
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        String[][] data = new String[funcionarios.size()][8];
        for (int i = 0; i < funcionarios.size(); i++) {
            data[i][0] = funcionarios.get(i).getNome();
            data[i][1] = funcionarios.get(i).getEmail();
            data[i][2] = funcionarios.get(i).getTelefone();
            data[i][3] = String.valueOf(funcionarios.get(i).getIdade());
            data[i][4] = String.valueOf(funcionarios.get(i).getId());
            data[i][5] = funcionarios.get(i).getCargo();
            data[i][6] = funcionarios.get(i).getSindicato();
            data[i][7] = String.valueOf(funcionarios.get(i).getAnoEntrada());
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{ "Nome", "Email", "Telefone", "Idade", "ID", "Cargo", "Sindicato", "Ano de entrada"}
        ){
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }

        });
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(450, 400));
        table1.setVisible(true);
        setSize(800, 600);
        setTitle("Visualizar todos os funcionários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
