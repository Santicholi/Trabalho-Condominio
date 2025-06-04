/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Morador;
import Model.MoradorDAO;
import Model.Residencia;
import Model.ResidenciaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ResidenciaController {
    
    ResidenciaDAO dao = new ResidenciaDAO();
    
    public int obterProximoId() {
        try {
            ResidenciaDAO dao = new ResidenciaDAO();
            return dao.buscarProximoId();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar próximo ID: " + e.getMessage());
            return 0;
        }
    }
    
    public boolean ResidenciaExiste(int numero) {
        try {
            return dao.buscarPorNumero(numero) != null;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean atualizarResidencia(String numero, String rua, String cep, String proprietario) {  
    try {
            if (rua.isEmpty() || cep.isEmpty()) {
                throw new Exception("Rua e CEP são obrigatórios!");
            }

            int num = Integer.parseInt(numero);
            int propId = 0;

            if (!proprietario.isEmpty()) {
                propId = Integer.parseInt(proprietario);

                // Verifica se o proprietário existe somente se foi informado
                MoradorDAO moradorDAO = new MoradorDAO();
                Morador morador = moradorDAO.buscarPorId(propId);
                if (morador == null) {
                    throw new Exception("Proprietário com ID " + propId + " não existe!");
                }
            }

            Residencia residencia = new Residencia(num, rua, cep, propId);
            dao.atualizar(residencia);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar residência: " + e.getMessage());
            return false;
        }
    }
    
    public boolean salvarResidencia(String numero, String rua, String cep, String proprietario) {
    try {
        // Validação obrigatória apenas para rua e cep
        if (rua.isEmpty() || cep.isEmpty()) {
            throw new Exception("Rua e CEP são obrigatórios!");
        }

        int num = Integer.parseInt(numero);
        int propId = 0;

        if (!proprietario.isEmpty()) {
            propId = Integer.parseInt(proprietario);

            // Verifica se o proprietário existe somente se foi informado
            MoradorDAO moradorDAO = new MoradorDAO();
            Morador morador = moradorDAO.buscarPorId(propId);
            if (morador == null) {
                throw new Exception("Proprietário com ID " + propId + " não existe!");
            }
        }

        Residencia residencia = new Residencia(num, rua, cep, propId);
        dao.salvar(residencia);
        return true;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar residência: " + e.getMessage());
        return false;
    }
}

    public boolean excluirResidencia(int numero) {
            try {
                dao.deletar(numero);
                return true;
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao salvar residencia", e);
            }
        }
    
    
    public ArrayList<Residencia> buscarResidencias(String numero, String rua) {
    try {
        if (!numero.isEmpty()) {
            int codigo = Integer.parseInt(numero);
            Residencia residencia = dao.buscarPorNumero(codigo);
            ArrayList<Residencia> lista = new ArrayList<>();
            if (residencia != null) {
                lista.add(residencia);
            }
            return lista;
        } else if (!rua.isEmpty()) {
            return dao.buscarPorRua(rua);
        } else {
            return dao.listarTodas();
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao salvar morador", e);

    }
}
    
    public ArrayList<Residencia> listarTodasResidencias() {
    try {
        return dao.listarTodas();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar moradores: " + e.getMessage(), e);
    }
}
    
    public Residencia buscarPorNumero(int numeroCasa) throws SQLException {
    ResidenciaDAO dao = new ResidenciaDAO();
    return dao.buscarPorNumero(numeroCasa);
}
    }

