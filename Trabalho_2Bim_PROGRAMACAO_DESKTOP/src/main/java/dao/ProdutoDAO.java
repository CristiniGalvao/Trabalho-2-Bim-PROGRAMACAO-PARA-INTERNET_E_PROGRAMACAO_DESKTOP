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
import modelo.Produto;

/**
 *
 * @author Gabriella
 */
public class ProdutoDAO extends GenericDAO<Produto>{
  private Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    protected Produto construirObjeto(ResultSet rs) {
        Produto produto = null;
        try {
            produto = new Produto();
            produto.setId(rs.getInt("ID"));
            produto.setDescricao(rs.getString("DESCRICAO"));
            produto.setCategoria(rs.getString("CATEGORIA"));
            produto.setValor(rs.getBigDecimal("VALOR"));
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produto;
    }

    public boolean salvar(Produto produto) {
        String sql = "INSERT INTO public.\"Produto\"(\"ID\", \"DESCRICAO\", \"CATEGORIA\", \"VALOR\") VALUES (?, ?, ?, ?);";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, produto.getId());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getCategoria());
            ps.setBigDecimal(4, produto.getValor());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean atualizar(Produto produto) {
        String sql = "UPDATE public.\"Produto\" SET \"DESCRICAO\" = ?, \"CATEGORIA\" = ?, \"VALOR\" = ? WHERE \"ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, produto.getDescricao());
            ps.setString(2, produto.getCategoria());
            ps.setBigDecimal(3, produto.getValor());
            ps.setInt(4, produto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Produto buscarPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM public.\"Produto\" WHERE \"ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                produto = construirObjeto(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produto;
    }

    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM public.\"Produto\";";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto produto = construirObjeto(rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM public.\"Produto\" WHERE \"ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
