/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

/**
 *
 * @author Gabriella
 */
public class RelatorioService {
    private Connection conn;

    public RelatorioService() {
        try {
            // Configuração da conexão com o banco de dados
            String url = "jdbc:postgresql://localhost:5432/pontoDeVenda";
            String user = "postgres";
            String password = "perceu";
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioGeral() {
    try {
        // Compilar o relatório
        String sourceFileName = "caminho/para/o/arquivo/PontoVenda.jrxml";
        JasperCompileManager.compileReportToFile(sourceFileName);

        // Preencher o relatório
        JasperPrint printer = JasperFillManager.fillReport("caminho/para/o/arquivo/PontoVenda.jasper", new HashMap<>(), conn);

        // Exibir o relatório
        JasperViewer viewer = new JasperViewer(printer, false);
        viewer.setVisible(true);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public void gerarRelatorioDetalhado(int clienteId) {

    }
}
