
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroClientes {
    private List<Cliente> clientes;
    private Scanner scanner;

    public CadastroClientes() {
        this.clientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Cliente cliente = new Cliente(nome, telefone, endereco);
        clientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i));
        }
    }

    public void buscarCliente() {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        System.out.print("Digite o nome do cliente: ");
        String nomeBusca = scanner.nextLine();

        boolean encontrado = false;
        for (Cliente cliente : clientes) {
            if (cliente.getNome().toLowerCase().contains(nomeBusca.toLowerCase())) {
                System.out.println("Cliente encontrado: " + cliente);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void editarCliente() {
        System.out.println("\n--- EDITAR CLIENTE ---");
        listarClientes();

        if (clientes.isEmpty()) {
            return;
        }

        System.out.print("Digite o número do cliente para editar: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;

            if (indice >= 0 && indice < clientes.size()) {
                Cliente cliente = clientes.get(indice);

                System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
                String nome = scanner.nextLine();
                if (!nome.trim().isEmpty()) {
                    cliente.setNome(nome);
                }

                System.out.print("Novo telefone (atual: " + cliente.getTelefone() + "): ");
                String telefone = scanner.nextLine();
                if (!telefone.trim().isEmpty()) {
                    cliente.setTelefone(telefone);
                }

                System.out.print("Novo endereço (atual: " + cliente.getEndereco() + "): ");
                String endereco = scanner.nextLine();
                if (!endereco.trim().isEmpty()) {
                    cliente.setEndereco(endereco);
                }

                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Número inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
        }
    }

    public void removerCliente() {
        System.out.println("\n--- REMOVER CLIENTE ---");
        listarClientes();

        if (clientes.isEmpty()) {
            return;
        }

        System.out.print("Digite o número do cliente para remover: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;

            if (indice >= 0 && indice < clientes.size()) {
                Cliente clienteRemovido = clientes.remove(indice);
                System.out.println("Cliente " + clienteRemovido.getNome() + " removido com sucesso!");
            } else {
                System.out.println("Número inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
        }
    }

    public void exibirMenu() {
        int opcao = 0;

        do {
            System.out.println("\n--- SISTEMA DE CADASTRO DE CLIENTES ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente");
            System.out.println("4 - Editar Cliente");
            System.out.println("5 - Remover Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        buscarCliente();
                        break;
                    case 4:
                        editarCliente();
                        break;
                    case 5:
                        removerCliente();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
