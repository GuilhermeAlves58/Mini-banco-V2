package application;

import entities.Conta;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Conta> contas = new ArrayList<>();
        Conta conta;
        while (true) {
            System.out.println("Entre os dados da conta");

            System.out.print("Id da conta: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome do titular: ");
            String nome = scanner.nextLine();
            System.out.print("Saldo da conta: ");
            double saldo = scanner.nextDouble();

            conta = new Conta(id, nome, saldo);

            contas.add(conta);
            System.out.println("Aperte [N] para parar adicionar contas [S]");
            if (scanner.next().toUpperCase().charAt(0) == 'N') {
                break;
            }
        }

        System.out.println("Entre qual das contas usar:  "+ contas.size() + " pessoas");
        int numConta = scanner.nextInt();
        Conta conta1 = contas.get(numConta - 1);

        System.out.println("Conta: " + conta1.getNomeDoTitular());

        System.out.println("Valor para depositar na conta: " + conta1.getId());
        conta1.depositar(scanner.nextDouble());

        System.out.println("Valor sacar na conta: " + conta1.getId());
        conta1.sacar(scanner.nextDouble());

        while (true) {
            System.out.println("Gostaria de transferir para outra conta? Se sim aperte: [S]");
            char c1 = scanner.next().toUpperCase().charAt(0);

            if (c1 == 'S') {
                System.out.println("Digite o ID da conta que deseja transferir: " + contas.size());
                int idTransferencia = scanner.nextInt();
                System.out.print("Quanto dinheiro transferir? ");
                conta1.transferir(idTransferencia, scanner.nextDouble(),contas);
            } else {
                break;
            }
        }
        System.out.println("saldo das contas: ");
        conta.printarSaldo(contas);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("contas.txt"))) {
            for (Conta c : contas) {
                bw.write(c.toString());
                bw.newLine();
            }
        }catch (IOException e){
             e.getMessage();
        }
    }
}

