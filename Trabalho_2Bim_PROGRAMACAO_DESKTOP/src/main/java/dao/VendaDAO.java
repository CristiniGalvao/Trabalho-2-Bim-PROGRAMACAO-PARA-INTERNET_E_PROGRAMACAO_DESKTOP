/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.ItemVenda;
import modelo.Venda;

/**
 *
 * @author Gabriella
 */
public class VendaDAO extends GenericDAO<Venda>{
    private Connection conn;

    public VendaDAO(Connection conn) {
        this.conn = conn;
    }

    public VendaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Venda construirObjeto(ResultSet rs) {
        Venda venda = null;

        try {
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            Cliente cliente = clienteDAO.construirObjeto(rs);
            List<ItemVenda> itensVenda = new ArrayList<>();

            venda = new Venda(
                cliente,
                itensVenda,
                rs.getInt("ID"),
                rs.getBigDecimal("TOTAL_VENDA"),
                rs.getString("OBSERVACOES")
            );
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return venda;
    }

    @Override
    public boolean salvar(Venda venda) {
        String sql = "INSERT INTO public.\"Venda\"(\"ID\", \"CLIENTE_ID\", \"TOTAL_VENDA\", \"OBSERVACOES\") VALUES (?, ?, ?, ?);";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, venda.getId());
            ps.setInt(2, venda.getCliente().getId());
            ps.setBigDecimal(3, venda.getTotalVenda());
            ps.setString(4, venda.getObservacoes());
            ps.executeUpdate();
            // Save itemsVenda here with a loop
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean atualizar(Venda venda) {
        String sql = "UPDATE public.\"Venda\" SET \"CLIENTE_ID\" = ?, \"TOTAL_VENDA\" = ?, \"OBSERVACOES\" = ? WHERE \"ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, venda.getCliente().getId());
            ps.setBigDecimal(2, venda.getTotalVenda());
            ps.setString(3, venda.getObservacoes());
            ps.setInt(4, venda.getId());
            ps.executeUpdate();
            // Update itemsVenda here with a loop
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public List<Venda> buscarTodos() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM public.\"Venda\";";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Venda venda = construirObjeto(rs);
                vendas.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendas;
    }
}
