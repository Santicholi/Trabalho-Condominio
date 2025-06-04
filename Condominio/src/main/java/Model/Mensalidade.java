package Model;

import java.sql.Date;


public class Mensalidade {
    private int codigo_mensalidade;
    private int residencia;
    private String mesReferencia;
    private double valor;
    private Date dataVencimento;
    private Date dataPagamento;


    
    
    // Getters e Setters
    public int getCodigo_mensalidade() {
        return codigo_mensalidade;
    }

    public void setCodigo_mensalidade(int codigo_mensalidade) {
        this.codigo_mensalidade = codigo_mensalidade;
    }

    public int getResidencia() {
        return residencia;
    }

    public void setResidencia(int residencia) {
        this.residencia = residencia;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}


