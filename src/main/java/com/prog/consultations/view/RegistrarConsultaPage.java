package com.prog.consultations.view;

import com.prog.consultations.controller.ConsultaController;
import com.prog.consultations.controller.MedicoController;
import com.prog.consultations.controller.PacienteController;
import com.prog.consultations.domain.Consulta;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RegistrarConsultaPage extends JFrame{
    private JTextField TFAno;
    private JTextField TFHora;
    private JTextField TFEmailMed;
    private JTextField TFEmailPac;
    private JButton registrarButton;
    private JPanel JPRegCon;
    private JTextField TFDia;
    private JTextField TFMes;

    public RegistrarConsultaPage(ConsultaController consultaController, MedicoController medicoController, PacienteController pacienteController){
        setContentPane(JPRegCon);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 450));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        registrarButton.addActionListener(e -> {
            try{
                //Ano - 1900 pois Date adiciona 1900 no ano padrão. Mês + 1 pois Janeiro é 0
                consultaController.criarConsulta(new Consulta(new Date(Integer.parseInt(TFAno.getText()) - 1900, Integer.parseInt(TFMes.getText()) - 1, Integer.parseInt(TFDia.getText())), TFHora.getText(), medicoController.buscarMedicoPorEmail(TFEmailMed.getText()), pacienteController.buscarPacientePorEmail(TFEmailPac.getText()).get()));
                JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso");
                dispose();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta\n" + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
