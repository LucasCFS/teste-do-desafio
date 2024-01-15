import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciamentoClientes {
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("==== Menu ====");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Depositar Dinheiro");
            System.out.println("6. Sacar Dinheiro");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    removerCliente();
                    break;
                case 5:
                    depositarDinheiro();
                    break;
                case 6:
                    sacarDinheiro();
                    break;
            }
        } while (opcao != 7);
    }

    private static void adicionarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("CNPJ: ");
        String cnpj = scanner.next();
        System.out.print("Endereço: ");
        String endereco = scanner.next();

        Map<String, Double> contas = new HashMap<>();

        System.out.print("Número de Contas (separadas por vírgula): ");
        scanner.nextLine(); // Limpar o buffer
        String contasInput = scanner.nextLine();
        String[] contasArray = contasInput.split(",");
        for (String conta : contasArray) {
            contas.put(conta.trim(), 0.0);
        }

        Cliente cliente = new Cliente(nome, cpf, cnpj, endereco, contas);
        clientes.add(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.nome);
                System.out.println("CPF: " + cliente.cpf);
                System.out.println("CNPJ: " + cliente.cnpj);
                System.out.println("Endereço: " + cliente.endereco);
                System.out.println("Contas: " + cliente.contas);
                System.out.println("----------------------");
            }
        }
    }

    private static void atualizarCliente() {
        System.out.print("Digite o CPF do cliente a ser atualizado: ");
        String cpf = scanner.next();

        Cliente clienteExistente = encontrarClientePorCPF(cpf);

        if (clienteExistente != null) {
            System.out.print("Novo endereço: ");
            String novoEndereco = scanner.next();
            clienteExistente.endereco = novoEndereco;
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void removerCliente() {
        System.out.print("Digite o CPF do cliente a ser removido: ");
        String cpf = scanner.next();

        Cliente clienteExistente = encontrarClientePorCPF(cpf);

        if (clienteExistente != null) {
            clientes.remove(clienteExistente);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void depositarDinheiro() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.next();
        Cliente clienteExistente = encontrarClientePorCPF(cpf);

        if (clienteExistente != null) {
            System.out.print("Digite o número da conta para depósito: ");
            String conta = scanner.next();
            if (clienteExistente.contas.containsKey(conta)) {
                System.out.print("Digite o valor a ser depositado: ");
                double valor = scanner.nextDouble();

                double saldoAtual = clienteExistente.contas.get(conta);
                clienteExistente.contas.put(conta, saldoAtual + valor);
                System.out.println("Depósito realizado com sucesso!");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void sacarDinheiro() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.next();
        Cliente clienteExistente = encontrarClientePorCPF(cpf);

        if (clienteExistente != null) {
            System.out.print("Digite o número da conta para saque: ");
            String conta = scanner.next();
            if (clienteExistente.contas.containsKey(conta)) {
                System.out.print("Digite o valor a ser sacado: ");
                double valor = scanner.nextDouble();

                double saldoAtual = clienteExistente.contas.get(conta);
                if (saldoAtual >= valor) {
                    clienteExistente.contas.put(conta, saldoAtual - valor);
                    System.out.println("Saque realizado com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static Cliente encontrarClientePorCPF(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.cpf.equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}