package aplication.main;

import aplication.repository.InMemoryAcaoDAO;
import aplication.repository.InMemoryCarteiraDAO;
import domain.entities.notificarClientes.Publisher;
import domain.entities.notificarClientes.Administrador;
import domain.entities.acao.*;
import domain.entities.aplicacao.Aplicacao;
import domain.entities.carteira.*;
import domain.utils.EntityAlreadyExist;
import domain.utils.EntityNotFoundException;

import java.util.Scanner;

public class Principal {
    private static Publisher publisher;

    private static CreateAcaoUseCase createAcaoUseCase;
    private static RemoveAcaoUseCase removeAcaoUseCase;
    private static UpdateAcaoUseCase updateAcaoUseCase;
    private static ListAcaoUseCase listAcaoUseCase;
    private static FindOneAcaoUseCase findOneAcaoUseCase;

    private static CreateCarteiraUserCase createCarteiraUserCase;
    private static RemoveCarteiraUseCase removeCarteiraUseCase;
    private static UpdateCarteiraUseCase updateCarteiraUseCase;
    private static ListCarteiraUseCase listCarteiraUseCase;
    private static GetUmaCarteiraUseCase getUmaCarteiraUseCase;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        configurarInjecaoDeDependencia();

        while (true){
            int opcao = coletarOpcaoUsuario();
            switch (opcao){
                case 1:
                    inserirAcao();
                    break;
                case 2:
                    inserirCarteira();
                    break;
                case 3:
                    removerAcao();
                    break;
                case 4:
                    removerCarteira();
                    break;
                case 5:
                    atualizarAcao();
                    break;
                case 6:
                    atualizarCarteira();
                    break;
                case 7:
                    listarTodasAsCarteiras();
                    break;
                case 8:
                    obterCarteira();
                    break;
                case 9:
                    listarTodasAcoes();
                    break;
                case 0:
                    sair();
                    return;
                default:
                    continue;
            }
        }
    }

    private static void listarTodasAcoes() {
        System.out.println(listAcaoUseCase.listAcoes());
        System.out.println("\n");
    }

    private static void obterCarteira() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try {
            Carteira carteira = getUmaCarteiraUseCase.getCarteira(cpf);
            System.out.printf(carteira.toString());
            System.out.println("\n");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarTodasAsCarteiras() {
        System.out.println(listCarteiraUseCase.listCarteira());
        System.out.println("\n");
    }

    private static void atualizarCarteira() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Carteira carteira = new Carteira(cpf);

        while(true){
            System.out.println("Deseja adicionar uma ação na carteira [S,N] ?");
            String desejo = scanner.nextLine();

            if(desejo.toUpperCase().equals("N")){
                break;
            }

            System.out.println("Ação: ");
            String nomeAcao = scanner.nextLine();
            System.out.println("Quantidade: ");
            double quantidadeAcao = Double.valueOf(scanner.nextLine());

            try{
                Acao acao = findOneAcaoUseCase.findOne(nomeAcao);
                Aplicacao aplicacao = new Aplicacao(quantidadeAcao, acao);
                carteira.addAplicacao(aplicacao);
                System.out.println("Ação inserida com sucesso!\n");
            } catch (EntityNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
        updateCarteiraUseCase.update(carteira);
        System.out.println("Carteira atualizada!\n");
    }

    private static void atualizarAcao() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Valor: ");
        Double valor = Double.valueOf(scanner.nextLine());

        Acao acao = new Acao(nome, codigo, valor);
        try {
            updateAcaoUseCase.update(acao);
            System.out.println("Atualizado com sucesso!\n");
        }catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private static void removerCarteira() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try{
            removeCarteiraUseCase.remove(cpf);
            System.out.println("Carteira removida com sucesso!\n");
        } catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private static void removerAcao() {
        System.out.print("Código Ação: ");
        String codigoAcao = scanner.nextLine();

        try {
            removeAcaoUseCase.remove(codigoAcao);
            System.out.println("Ação removida com sucesso!\n");
        } catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private static void inserirCarteira() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Carteira carteira = new Carteira(cpf);

        while(true){
            System.out.println("Deseja adicionar uma ação na carteira [S,N] ?");
            String desejo = scanner.nextLine();

            if(desejo.toUpperCase().equals("N")){
                break;
            }

            System.out.println("Ação: ");
            String nomeAcao = scanner.nextLine();
            System.out.println("Quantidade: ");
            double quantidadeAcao = Double.valueOf(scanner.nextLine());

            try{
                Acao acao = findOneAcaoUseCase.findOne(nomeAcao);
                Aplicacao aplicacao = new Aplicacao(quantidadeAcao, acao);
                carteira.addAplicacao(aplicacao);
                System.out.println("Ação inserida com sucesso!\n");
            } catch (EntityNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
        createCarteiraUserCase.create(carteira);
        System.out.println("Carteira cadastrada!\n");
    }

    private static void inserirAcao() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Valor: ");
        Double valor = Double.valueOf(scanner.nextLine());

        Acao acao = new Acao(nome, codigo, valor);
        try {
            createAcaoUseCase.insert(acao);
            System.out.println("Cadastrado com sucesso!\n");
        }catch (EntityNotFoundException | EntityAlreadyExist e){
            System.out.println(e.getMessage());
        }
    }

    private static void sair() {
        System.out.println("Saindo..");
    }

    private static int coletarOpcaoUsuario() {
        System.out.println("1 - Adicionar Ação");
        System.out.println("2 - Adicionar Carteira");
        System.out.println("3 - Remover Ação");
        System.out.println("4 - Remover Carteira");
        System.out.println("5 - Atualizar Ação");
        System.out.println("6 - Atualizar Carteira");
        System.out.println("7 - Listar todas Carteira");
        System.out.println("8 - Obter uma Carteira");
        System.out.println("9 - Listar todas Ações");
        System.out.println("0 - Sair");
        System.out.println("> ");

        Integer opcao = Integer.valueOf(scanner.nextLine());
        return opcao;
    }

    private static void configurarInjecaoDeDependencia() {
        AcaoDAO acaoDAO = new InMemoryAcaoDAO();
        publisher = new Administrador();
        createAcaoUseCase = new CreateAcaoUseCase(acaoDAO, publisher);
        removeAcaoUseCase = new RemoveAcaoUseCase(acaoDAO, publisher);
        updateAcaoUseCase = new UpdateAcaoUseCase(acaoDAO, publisher);
        listAcaoUseCase = new ListAcaoUseCase(acaoDAO);
        findOneAcaoUseCase = new FindOneAcaoUseCase(acaoDAO);

        CarteiraDAO carteiraDAO = new InMemoryCarteiraDAO();
        createCarteiraUserCase = new CreateCarteiraUserCase(carteiraDAO, publisher);
        removeCarteiraUseCase = new RemoveCarteiraUseCase(carteiraDAO);
        updateCarteiraUseCase = new UpdateCarteiraUseCase(carteiraDAO);
        listCarteiraUseCase = new ListCarteiraUseCase(carteiraDAO);
        getUmaCarteiraUseCase = new GetUmaCarteiraUseCase(carteiraDAO);
    }
}
