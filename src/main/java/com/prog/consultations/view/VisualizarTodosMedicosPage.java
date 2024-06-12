package com.prog.consultations.view;

import com.prog.consultations.controller.MedicoController;
import com.prog.consultations.domain.Medico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisualizarTodosMedicosPage extends JFrame {
    private JTable table1;
    private JPanel JPViewAll;

    public VisualizarTodosMedicosPage(MedicoController medicoController) {
        add(JPViewAll);
        List<Medico> medicos = medicoController.listarMedicos();
        String[][] data = new String[medicos.size()][9];
        for (int i = 0; i < medicos.size(); i++) {
            data[i][0] = medicos.get(i).getNome();
            data[i][1] = medicos.get(i).getCrm();
            data[i][2] = medicos.get(i).getEspecialidade();
            data[i][3] = medicos.get(i).getTelefone();
            data[i][4] = medicos.get(i).getEmail();
            data[i][5] = medicos.get(i).getClinica();
            data[i][6] = String.valueOf(medicos.get(i).getAnoEntrada());
            data[i][7] = String.valueOf(medicos.get(i).getIdade());
            data[i][8] = String.valueOf(medicos.get(i).getId());
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{ "Nome", "CRM", "Especialidade", "Telefone", "Email", "Clinica", "Ano de entrada", "Idade", "ID"}
        ){
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }

        });
        table1.setFillsViewportHeight(true);
        table1.setPreferredScrollableViewportSize(new Dimension(450, 400));
        table1.setVisible(true);
        setSize(800, 600);
        setTitle("Visualizar todos os médicos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
