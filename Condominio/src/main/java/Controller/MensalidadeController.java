/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Mensalidade;
import Model.MensalidadeDAO;
import Model.Residencia;
import Model.ResidenciaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MensalidadeController {

    private MensalidadeDAO dao;

        public int obterProximoId() {
        try {
            return dao.buscarProximoId();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar próximo ID: " + e.getMessage());
            return 0;
        }
    }
        
        public boolean MensalidadeExiste(int numero) {
        try {
            return dao.buscarPorId(numero) != null;
        } catch (SQLException e) {
            return false;
        }
    }
    

    public MensalidadeController() {
        dao = new MensalidadeDAO();
    }

    public void salvar(Mensalidade mensalidade) throws SQLException {
        dao.salvar(mensalidade);
    }

    public void atualizar(Mensalidade mensalidade) throws SQLException {
        dao.atualizar(mensalidade);
    }

    public boolean deletar(int codigoMensalidade) throws SQLException {
        try {
            dao.deletar(codigoMensalidade);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar residencia", e);
        }
    }

    public Mensalidade buscarPorId(int codigoMensalidade) throws SQLException {
        return dao.buscarPorId(codigoMensalidade);
    }

    public ArrayList<Mensalidade> listarTodas() throws SQLException {
        return dao.listarTodas();
    }

    public ArrayList<Mensalidade> buscarPorCasa(int numeroCasa) throws SQLException {
        return dao.buscarPorCasa(numeroCasa);
    }
    
    public ArrayList<Mensalidade> buscarMensalidades(String numero, String codigoMensalidade) {
    try {
        if (codigoMensalidade != null && !codigoMensalidade.isEmpty()) {
            int codigo = Integer.parseInt(codigoMensalidade);
            Mensalidade m = dao.buscarPorId(codigo);
            ArrayList<Mensalidade> lista = new ArrayList<>();
            if (m != null) {
                lista.add(m);
            }
            return lista;
        } else if (numero != null && !numero.isEmpty()) {
            int numeroCasa = Integer.parseInt(numero);
            return dao.buscarPorCasa(numeroCasa);
        } else {
            return dao.listarTodas();
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao buscar mensalidades", e);
    }
}
    
    
    
}
