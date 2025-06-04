package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class ResidenciaDAO {
    
    public int buscarProximoId() throws SQLException {
        int proximoId = 1;
        String sql = "SELECT MAX(numero_casa) AS max_id FROM residencia";

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
    
        public void salvar(Residencia residencia) throws SQLException {

        String sql = "INSERT INTO residencia (rua, cep, proprietario) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, residencia.getRua());
            stmt.setString(2, residencia.getCep());
            stmt.setInt(3, residencia.getProprietario());

            
            // Tratamento para proprietário nulo/vazio
            if (residencia.getProprietario() == 0) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, residencia.getProprietario());
            }
        
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }  
    }
        
    public ArrayList<Residencia> listarTodas() throws SQLException {
        ArrayList<Residencia> residencias = new ArrayList<>();
        String sql = "SELECT * FROM residencia ORDER BY rua";
        
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                residencias.add(new Residencia(
                    rs.getInt("numero_casa"),
                    rs.getString("rua"),
                    rs.getString("cep"),
                    rs.getInt("proprietario")
                ));
            }
        }
        return residencias;
    }
    
    public void atualizar(Residencia residencia) throws SQLException {
    
        
        String sql = "UPDATE residencia SET rua = ?, cep = ?, proprietario = ? WHERE numero_casa = ?";
        
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, residencia.getRua());
                stmt.setString(2, residencia.getCep());

                // Tratamento do proprietário
                if (residencia.getProprietario() == 0) {
                    stmt.setNull(3, Types.INTEGER); // Define como NULL no banco
                } else {
                    stmt.setInt(3, residencia.getProprietario());
                }

                stmt.setInt(4, residencia.getNumeroCasa());
                stmt.executeUpdate();
                }
    }
    
        public void deletar(int numero) throws SQLException {
        String sql = "DELETE FROM residencia WHERE numero_casa = ?";
        
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        }
    }
        
    public Residencia buscarPorNumero(int numero) throws SQLException {
    Connection conn = ConexaoDAO.getConnection();
    String sql = "SELECT * FROM residencia WHERE numero_casa = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, numero);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        return new Residencia(
            rs.getInt("numero_casa"),
            rs.getString("rua"),
            rs.getString("cep"),
            rs.getInt("proprietario")
        );
    }
    return null;
}
    
    public ArrayList<Residencia> buscarPorRua(String rua) throws SQLException {
    Connection conn = ConexaoDAO.getConnection();
    String sql = "SELECT * FROM residencia WHERE rua LIKE ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, "%" + rua + "%");
    ResultSet rs = stmt.executeQuery();

    ArrayList<Residencia> lista = new ArrayList<>();

    while (rs.next()) {
        Residencia residencia = new Residencia(
            rs.getInt("numero_casa"),
            rs.getString("rua"),
            rs.getString("cep"),
            rs.getInt("proprietario")
        );
        lista.add(residencia);
    }

    return lista;
}

    public ArrayList<Residencia> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
