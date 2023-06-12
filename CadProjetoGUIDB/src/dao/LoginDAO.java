/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author pedro
 */
public class LoginDAO {
    private Conexao conexao;
    private Connection conn;
    
    public LoginDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();        
    }
    
    public boolean login(String nameuser, String password) {
        String sql = "SELECT * FROM user WHERE nameuser=? and password=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nameuser);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            //Primeiramente, posiciona o ResultSet na primeira posicao
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
}
