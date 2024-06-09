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
import modelo.ItemVenda;
import modelo.Produto;

/**
 *
 * @author Gabriella
 */
public class ItemVendaDAO extends GenericDAO<ItemVenda>{
   private Connection conn;

    public ItemVendaDAO(Connection conn) {
        this.conn = conn;
    }

    protected ItemVenda construirObjeto(ResultSet rs) {
        ItemVenda itemVenda = null;
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);
            Produto produto = produtoDAO.buscarPorId(rs.getInt("PRODUTO_ID"));

            itemVenda = new ItemVenda(
                rs.getInt("QUANTIDADE"),
                rs.getBigDecimal("VALOR_TOTAL_ITEM")
            );
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemVenda;
    }

    public boolean salvar(ItemVenda itemVenda) {
        String sql = "INSERT INTO public.\"ItemVenda\"(\"VENDA_ID\", \"PRODUTO_ID\", \"QUANTIDADE\", \"VALOR_TOTAL_ITEM\") VALUES (?, ?, ?, ?);";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemVenda.getVendaId());
            ps.setInt(2, itemVenda.getProduto().getId());
            ps.setInt(3, itemVenda.getQuantidade());
            ps.setBigDecimal(4, itemVenda.getValorTotalItem());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean atualizar(ItemVenda itemVenda) {
        String sql = "UPDATE public.\"ItemVenda\" SET \"PRODUTO_ID\" = ?, \"QUANTIDADE\" = ?, \"VALOR_TOTAL_ITEM\" = ? WHERE \"VENDA_ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemVenda.getProduto().getId());
            ps.setInt(2, itemVenda.getQuantidade());
            ps.setBigDecimal(3, itemVenda.getValorTotalItem());
            ps.setInt(4, itemVenda.getVendaId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<ItemVenda> buscarPorVendaId(int vendaId) {
        List<ItemVenda> itensVenda = new ArrayList<>();
        String sql = "SELECT * FROM public.\"ItemVenda\" WHERE \"VENDA_ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vendaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemVenda itemVenda = construirObjeto(rs);
                itensVenda.add(itemVenda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itensVenda;
    }

    public boolean deletarPorVendaId(int vendaId) {
        String sql = "DELETE FROM public.\"ItemVenda\" WHERE \"VENDA_ID\" = ?;";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vendaId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
