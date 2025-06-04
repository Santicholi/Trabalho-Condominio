package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class MensalidadeDAO {
    
        public int buscarProximoId() throws SQLException {
        int proximoId = 1;
        String sql = "SELECT MAX(codigo_mensalidade) AS max_id FROM mensalidade";

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
   
    
    public void salvar(Mensalidade mensalidade) throws SQLException {
        String sql = "INSERT INTO mensalidade (numero_casa, mes_referencia, valor, data_vencimento, data_pagamento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mensalidade.getResidencia());
            stmt.setString(2, mensalidade.getMesReferencia());
            stmt.setDouble(3, mensalidade.getValor());
            stmt.setDate(4, mensalidade.getDataVencimento());

            if (mensalidade.getDataPagamento() != null) {
                stmt.setDate(5, mensalidade.getDataPagamento());
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.executeUpdate();
        }
    }

    public ArrayList<Mensalidade> listarTodas() throws SQLException {
        ArrayList<Mensalidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM mensalidade ORDER BY data_vencimento DESC";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mensalidade m = new Mensalidade();
                m.setCodigo_mensalidade(rs.getInt("codigo_mensalidade"));
                m.setResidencia(rs.getInt("numero_casa"));
                m.setMesReferencia(rs.getString("mes_referencia"));
                m.setValor(rs.getDouble("valor"));
                m.setDataVencimento(rs.getDate("data_vencimento"));
                m.setDataPagamento(rs.getDate("data_pagamento"));
                lista.add(m);
            }
        }

        return lista;
    }

    public void atualizar(Mensalidade mensalidade) throws SQLException {
        String sql = "UPDATE mensalidade SET numero_casa = ?, mes_referencia = ?, valor = ?, data_vencimento = ?, data_pagamento = ? WHERE codigo_mensalidade = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mensalidade.getResidencia());
            stmt.setString(2, mensalidade.getMesReferencia());
            stmt.setDouble(3, mensalidade.getValor());
            stmt.setDate(4, mensalidade.getDataVencimento());

            if (mensalidade.getDataPagamento() != null) {
                stmt.setDate(5, mensalidade.getDataPagamento());
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.setInt(6, mensalidade.getCodigo_mensalidade());
            stmt.executeUpdate();
        }
    }

    public void deletar(int codigo) throws SQLException {
        String sql = "DELETE FROM mensalidade WHERE codigo_mensalidade = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }

    public Mensalidade buscarPorId(int codigo) throws SQLException {
        String sql = "SELECT * FROM mensalidade WHERE codigo_mensalidade = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Mensalidade m = new Mensalidade();
                m.setCodigo_mensalidade(rs.getInt("codigo_mensalidade"));
                m.setResidencia(rs.getInt("numero_casa"));
                m.setMesReferencia(rs.getString("mes_referencia"));
                m.setValor(rs.getDouble("valor"));
                m.setDataVencimento(rs.getDate("data_vencimento"));
                m.setDataPagamento(rs.getDate("data_pagamento"));
                return m;
            }
        }

        return null;
    }

    public ArrayList<Mensalidade> buscarPorCasa(int numeroCasa) throws SQLException {
        ArrayList<Mensalidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM mensalidade WHERE numero_casa = ? ORDER BY data_vencimento DESC";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroCasa);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Mensalidade m = new Mensalidade();
                m.setCodigo_mensalidade(rs.getInt("codigo_mensalidade"));
                m.setResidencia(rs.getInt("numero_casa"));
                m.setMesReferencia(rs.getString("mes_referencia"));
                m.setValor(rs.getDouble("valor"));
                m.setDataVencimento(rs.getDate("data_vencimento"));
                m.setDataPagamento(rs.getDate("data_pagamento"));
                lista.add(m);
            }
        }

        return lista;
    }
    
    
    
    
}
