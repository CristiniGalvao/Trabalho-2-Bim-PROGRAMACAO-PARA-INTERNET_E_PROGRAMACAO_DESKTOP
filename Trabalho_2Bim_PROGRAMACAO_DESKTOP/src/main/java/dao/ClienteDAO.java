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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import service.ApiService;

/**
 *
 * @author Gabriella
 */
public class ClienteDAO extends GenericDAO<Cliente> {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public ClienteDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Cliente construirObjeto(ResultSet rs) {
        Cliente cliente = null;

        try {
            cliente = new Cliente();
            cliente.setId(rs.getInt("ID"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setEmail(rs.getString("EMAIL"));
            cliente.setTelefone(rs.getString("TELEFONE"));
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

    @Override
    public boolean salvar(Cliente cliente) {
        String sql = "INSERT INTO public.\"Cliente\"(\"ID\", \"NOME\", \"EMAIL\", \"TELEFONE\") VALUES (?, ?, ?, ?);";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE public.\"Cliente\" SET \"NOME\" = ?, \"EMAIL\" = ?, \"TELEFONE\" = ? WHERE \"ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setInt(4, cliente.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
   
    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM public.\"Cliente\";";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = construirObjeto(rs);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;
    }
}
