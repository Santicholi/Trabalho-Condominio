/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.ConexaoDAO;
import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class MoradorDAO {
    
        public int buscarProximoId() throws SQLException {
        int proximoId = 1;
        String sql = "SELECT MAX(codigo_morador) AS max_id FROM morador";

        try (Connection conn = ConexaoDAO.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                proximoId = rs.getInt("max_id") + 1;
            }
        rs.close();
        stmt.close();
        conn.close();
        }
        return proximoId;
}
    
        public void salvar(Morador morador) throws SQLException {
        String sql = "INSERT INTO morador (nome, idade,rg,cpf,moraEm) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, morador.getNome());
            stmt.setString(2, morador.getCpf());
            stmt.setString(3, morador.getRg());
            stmt.setInt(4, morador.getIdade());
            stmt.setInt(5, morador.getMoraEm());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
    }
        
        
        
        public ArrayList<Morador> listarTodos() throws SQLException {
        ArrayList<Morador> moradores = new ArrayList<>();
        String sql = "SELECT * FROM morador ORDER BY nome";
        
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                moradores.add(new Morador(
                    rs.getInt("codigo_morador"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("rg"),
                    rs.getString("cpf"),
                    rs.getInt("moraEm")
                ));
            }
        }
        return moradores;
    }

    public void atualizar(Morador morador) throws SQLException {
        String sql = "UPDATE morador SET nome = ?, idade = ?, rg = ?, cpf = ?, moraEm = ? WHERE codigo_morador = ?";
        
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, morador.getNome());
            stmt.setInt(2, morador.getIdade());
            stmt.setString(3, morador.getRg());
            stmt.setString(4, morador.getCpf());
            stmt.setInt(5, morador.getMoraEm());
            stmt.setInt(6, morador.getCodigo_morador());
            stmt.executeUpdate();
        }
    }

    public void deletar(int codigo) throws SQLException {
        String sql = "DELETE FROM morador WHERE codigo_morador = ?";
        
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }
    
    public Morador buscarPorId(int id) throws SQLException {
    Connection conn = ConexaoDAO.getConnection();
    String sql = "SELECT * FROM morador WHERE codigo_morador = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        return new Morador(
            rs.getInt("codigo_morador"),
            rs.getString("nome"),
            rs.getInt("idade"),
            rs.getString("rg"),
            rs.getString("cpf"),
            rs.getInt("moraEm")
        );
    }
    return null;
}

public ArrayList<Morador> buscarPorNome(String nome) throws SQLException {
    Connection conn = ConexaoDAO.getConnection();
    String sql = "SELECT * FROM morador WHERE nome LIKE ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, "%" + nome + "%");
    ResultSet rs = stmt.executeQuery();

    ArrayList<Morador> lista = new ArrayList<>();

    while (rs.next()) {
        Morador morador = new Morador(
            rs.getInt("codigo_morador"),
            rs.getString("nome"),
            rs.getInt("idade"),
            rs.getString("rg"),
            rs.getString("cpf"),
            rs.getInt("moraEm")
        );
        lista.add(morador);
    }

    return lista;
}
}


