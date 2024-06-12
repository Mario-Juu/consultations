package com.prog.consultations.view;

import com.prog.consultations.controller.ConsultaController;
import com.prog.consultations.controller.FuncionarioController;
import com.prog.consultations.controller.MedicoController;
import com.prog.consultations.controller.PacienteController;
import com.prog.consultations.domain.Consulta;
import com.prog.consultations.exception.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;


@Component
public class InitPage {
    private JPanel InitPanel;
    private JTextField EmailText;
    private JTable TableConsultas;
    private JButton imprimirConsultasButton;
    private JScrollPane JSPane;
    private JComboBox CBOpcao;

    @Autowired
    private PacienteController pacienteController;

    @Autowired
    private MedicoController medicoController;

    @Autowired
    private ConsultaController consultaController;

    @Autowired
    private FuncionarioController funcionarioController;

    public InitPage() {
    }

    @PostConstruct
    public void init() {
        JFrame frame = new JFrame("InitPage");
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuCadastro = new JMenu("Cadastrar");
        menuBar.add(menuCadastro);

        JMenuItem criarMedico = new JMenuItem("Criar Médico");
        menuCadastro.add(criarMedico);
        criarMedico.addActionListener(e -> {
            new RegistrarMedicoPage(medicoController);
        });

        JMenuItem criarPaciente = new JMenuItem("Criar Paciente");
        menuCadastro.add(criarPaciente);
        criarPaciente.addActionListener(e -> {
            new RegistrarPacientePage(pacienteController);
        });

        JMenuItem criarFuncionario = new JMenuItem("Criar Funcionário");
        menuCadastro.add(criarFuncionario);
        criarFuncionario.addActionListener(e -> {
            new RegistrarFuncionarioPage(funcionarioController);
        });

        JMenuItem criarConsulta = new JMenuItem("Criar Consulta");
        menuCadastro.add(criarConsulta);
        criarConsulta.addActionListener(e -> {
            new RegistrarConsultaPage(consultaController, medicoController, pacienteController);
        });

        JMenu menuVisualizar = new JMenu("Visualizar");
        menuBar.add(menuVisualizar);

        JMenuItem visualizarMedico = new JMenuItem("Visualizar Médico");
        menuVisualizar.add(visualizarMedico);
        visualizarMedico.addActionListener(e -> {
            new VisualizarMedicoPage(medicoController);
        });

        JMenuItem visualizarFuncionario = new JMenuItem("Visualizar Funcionário");
        menuVisualizar.add(visualizarFuncionario);
        visualizarFuncionario.addActionListener(e -> {
            new VisualizarFuncionarioPage(funcionarioController);
        });

        JMenuItem visualizarPaciente = new JMenuItem("Visualizar Paciente");
        menuVisualizar.add(visualizarPaciente);
        visualizarPaciente.addActionListener(e -> {
            new VisualizarPacientePage(pacienteController);
        });
        CBOpcao.addItem("Paciente");
        CBOpcao.addItem("Médico");

        TableConsultas.setVisible(false);
        frame.setContentPane(InitPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 450));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        imprimirConsultasButton.addActionListener(e -> {
            try{
            String email = EmailText.getText();
            List<Consulta> consultas = null;
            if(Objects.equals(CBOpcao.getSelectedItem(), "Médico"))
                consultas = consultaController.listarConsultasPorMedico(medicoController.buscarMedicoPorEmail(email).getId());
            else if(Objects.equals(CBOpcao.getSelectedItem(), "Paciente"))
                consultas = consultaController.listarConsultasPorPaciente(pacienteController.buscarPacientePorEmail(email).get().getId());

                if(consultas == null || consultas.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não foi possível encontrar consultas associadas a esse e-mail.");
                return;
            }
            String[][] data = new String[consultas.size()][5];
            for (int i = 0; i < consultas.size(); i++) {
                data[i][0] = consultas.get(i).getData().toString();
                data[i][1] = consultas.get(i).getHora();
                data[i][2] = consultas.get(i).getMedico().getNome();
                data[i][3] = consultas.get(i).getPaciente().getNome();
                data[i][4] = consultas.get(i).getRealizada() ? "Sim" : "Não";
            }
            TableConsultas.setModel(new DefaultTableModel(
                    data,
                    new String[]{ "Data", "Hora", "Médico", "Paciente", "Realizada"}
            ){
                public boolean isCellEditable(int rowIndex, int columnIndex){
                    return false;
                }

            });
            TableConsultas.setFillsViewportHeight(true);
            TableConsultas.setPreferredScrollableViewportSize(new Dimension(450, 400));
            TableConsultas.setVisible(true);
            TableConsultas.revalidate();
            TableConsultas.repaint();
            JSPane.revalidate();
            JSPane.repaint();
            } catch (ResourceNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Não foi possível encontrar consultas associadas a esse e-mail.");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao buscar consultas.");
                ex.printStackTrace();
            }

        });
    }
}
