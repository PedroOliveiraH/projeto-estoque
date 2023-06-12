/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Estoque;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class EstoqueDAO {

    private Conexao conexao;
    private Connection conn;

    public EstoqueDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Estoque estoque) {
        String sql = "INSERT INTO estoque(nome, genero, valor, lote) VALUES"
                + "(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, estoque.getNome());
            stmt.setString(2, estoque.getGenero());
            stmt.setDouble(3, estoque.getValor());
            stmt.setInt(4, estoque.getLote());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir dados do produto: " + e.getMessage());
        }
    }

    public Estoque getEstoque(int id) {
        String sql = "SELECT * FROM estoque WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Estoque estoque = new Estoque();
            //Primeiramente, posiciona o ResultSet na primeira posicao
            while (rs.next()) {
                estoque.setId(id);
                estoque.setNome(rs.getString("nome"));
                estoque.setGenero(rs.getString("genero"));
                estoque.setValor(rs.getDouble("valor"));
                estoque.setLote(rs.getInt("lote"));
                return estoque;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void editar(Estoque estoque) {
        String sql = "UPDATE estoque SET nome=?, genero=?, valor=?, lote=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, estoque.getNome());
            stmt.setString(2, estoque.getGenero());
            stmt.setDouble(3, estoque.getValor());
            stmt.setInt(4, estoque.getLote());
            stmt.setInt(5, estoque.getId());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao editar dados do produto: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao excluir o produto: " + e.getMessage());
        }
    }

    public List<Estoque> getEstoque(String nome, String genero) {
        String sql = "SELECT * FROM estoque WHERE nome LIKE ?";
        if (genero!="sem filtro") {
            sql += " AND genero = ?";
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            if (genero!="sem filtro") {
                stmt.setString(2, genero);
            }
            ResultSet rs = stmt.executeQuery();
            List<Estoque> listaEstoque = new ArrayList<>();
            //pecorre o "rs" e salva as informações dentro de uma vez "Curso"
            //e depois salva essa var denro da lista
            while (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setId(rs.getInt("id"));
                estoque.setNome(rs.getString("nome"));
                estoque.setGenero(rs.getString("genero"));
                estoque.setValor(rs.getDouble("valor"));
                estoque.setLote(rs.getInt("lote"));
                listaEstoque.add(estoque);
            }
            return listaEstoque;
        } catch (Exception e) {
            return null;
        }
    }
}
