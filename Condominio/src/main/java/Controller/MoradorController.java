package Controller;

import Model.MoradorDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Morador;
import java.awt.List;
import java.util.ArrayList;

public class MoradorController {
    
    MoradorDAO dao = new MoradorDAO();
    
        public int obterProximoId() {
        try {
            MoradorDAO dao = new MoradorDAO();
            return dao.buscarProximoId();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar próximo ID: " + e.getMessage());
            return 0;
        }
    }
    
    public boolean moradorExiste(int id) {
    try {
        return dao.buscarPorId(id) != null;
    } catch (SQLException e) {
        return false;
    }
    }
    
    public boolean atualizarMorador(String id, String nome, String idade, String rg, String cpf, String moraEm) {
    try {
        Morador morador = new Morador(
            Integer.parseInt(id),
            nome,
            Integer.parseInt(idade),
            rg,
            cpf,
            Integer.parseInt(moraEm)
        );
        dao.atualizar(morador);
        return true;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar morador: " + e.getMessage());
        return false;
    }
}
    
    public boolean salvarMorador(String id, String nome, String cpf, String rg, String idade, String moraEm) {
        try {

            Morador morador = new Morador(
                Integer.parseInt(id),
                nome,
                Integer.parseInt(idade),
                rg,
                cpf,
                Integer.parseInt(moraEm)
            );

            dao.salvar(morador);
            return true;


        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar morador", e);            
        }
    
    } 
    
    public boolean excluirMorador(int id) {
    try {
        dao.deletar(id);
        return true;
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao salvar morador", e);
    }
}
    
public ArrayList<Morador> buscarMoradores(String id, String nome) {
    try {
        if (!id.isEmpty()) {
            int codigo = Integer.parseInt(id);
            Morador morador = dao.buscarPorId(codigo);
            ArrayList<Morador> lista = new ArrayList<>();
            if (morador != null) {
                lista.add(morador);
            }
            return lista;
        } else if (!nome.isEmpty()) {
            return dao.buscarPorNome(nome);
        } else {
            return dao.listarTodos();
        }
    } catch (Exception e) {
        throw new RuntimeException("Erro ao salvar morador", e);

    }
}

public ArrayList<Morador> listarTodosMoradores() {
    try {
        return dao.listarTodos();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar moradores: " + e.getMessage(), e);
    }
}


}

