package com.prog.consultations.view;

import com.prog.consultations.controller.PacienteController;
import com.prog.consultations.domain.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisualizarTodosPacientesPage extends JFrame {
    private JPanel JPViewAll;
    private JTable table1;
    private JScrollPane JSPane;

    public VisualizarTodosPacientesPage(PacienteController pacienteController) {
        add(JPViewAll);
        List<Paciente> pacientes = pacienteController.listarPacientes();
        String[][] data = new String[pacientes.size()][5];
        for (int i = 0; i < pacientes.size(); i++) {
            data[i][0] = pacientes.get(i).getNome();
            data[i][1] = pacientes.get(i).getEmail();
            data[i][2] = pacientes.get(i).getTelefone();
            data[i][3] = String.valueOf(pacientes.get(i).getIdade());
            data[i][4] = String.valueOf(pacientes.get(i).getId());
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{ "Nome", "Email", "Telefone", "Idade", "ID"}
        ){
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }

        });
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(450, 400));
        table1.setVisible(true);
        setSize(800, 600);
        setTitle("Visualizar todos os pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
