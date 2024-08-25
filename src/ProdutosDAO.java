/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) {
    conn = new conectaDAO().connectDB();
    String query = "INSERT INTO produto (nome, valor, status) VALUES (?, ?, ?)";
    
    try {
        prep = conn.prepareStatement(query);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        int result = prep.executeUpdate();
        
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
    } finally {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}

    
    public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    conn = new conectaDAO().connectDB();
    String query = "SELECT * FROM produto";
    
    try {
        prep = conn.prepareStatement(query);
        resultset = prep.executeQuery();
        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            lista.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    } finally {
        try {
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
    return lista;
}

    public void venderProduto(int produtoId) {
    Connection conn = null;
    PreparedStatement prep = null;
    
    try {
        conn = new conectaDAO().connectDB();
        String query = "UPDATE produto SET status = 'Vendido' WHERE id = ?";
        
        prep = conn.prepareStatement(query);
        prep.setInt(1, produtoId);
        int result = prep.executeUpdate();
        
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado ou falha ao vender.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    } finally {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}


   
public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
    conn = new conectaDAO().connectDB();
    String query = "SELECT * FROM produto WHERE status = 'Vendido'";
    
    try {
        prep = conn.prepareStatement(query);
        resultset = prep.executeQuery();
        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            listaVendidos.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    } finally {
        try {
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
        }
    }
    return listaVendidos;
}



}


