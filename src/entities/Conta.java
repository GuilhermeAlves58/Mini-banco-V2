package entities;

import entities.exceptions.ContaException;

import java.util.List;

public class Conta {
    private Integer id;
    private String nomeDoTitular;
    private Double saldo;

    public Conta(Integer id, String nomeDoTitular, Double saldo) {
        this.id = id;
        this.nomeDoTitular = nomeDoTitular;
        this.saldo = saldo;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public void setNomeDoTitular(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Conta conta = (Conta) o;
        return id.equals(conta.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Conta: " + id + " Titular: " + nomeDoTitular + " Saldo: " + saldo;
    }

    public Double depositar(double quantia){
        if (quantia == 0){
            throw new ContaException("É necessário ser um valor acima de 0");
        }
        return saldo += quantia;
    }

    public Double sacar(double quantia){
        if (quantia > saldo){
            throw new ContaException("Não é possível sacar mais do que o saldo");
        }
        if (quantia == 0){
            throw new ContaException("É necessário ser um valor acima de 0");
        }
        return saldo -= quantia;
    }

    public void transferir(Integer id, Double quantia,List<Conta> contas){
        if (quantia == 0){
            throw new ContaException("É necessário ser um valor acima de 0");
        }
        for (Conta conta : contas){
            if (id.equals(conta.getId())){
                if (saldo < quantia){
                   throw new ContaException("Não é possível transferir uma quantidade maior do que o saldo");
                }
                conta.saldo += quantia;
                this.saldo -= quantia;
                return;
            }
        }
        throw new ContaException("Não foi possível achar a conta");
    }

    public void printarSaldo(List<Conta> saldoDeContas){
        for (Conta conta : saldoDeContas) {
            System.out.println(conta.toString());
        }
    }
}

