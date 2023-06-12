/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author pedro
 */
public class Conexao {
       public Connection getConexao(){
        try{
            //tentar estabelecer a conexão
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projetoestoque?serverTimezone=UTC",
                    "root",//usuario do mysql
                    ""//senha do mysql
            );
            Statement stmt = conn.createStatement();
            return conn;
        } catch (Exception e){
            //se deu erro na hora de conectar
            System.out.println("Erro ao conectar" + e.getMessage());
            return null;
        }
    }
}

