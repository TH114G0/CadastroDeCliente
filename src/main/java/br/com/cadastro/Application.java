package br.com.cadastro;


import br.com.cadastro.model.User;
import br.com.cadastro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private final UserService userService;

    public Application(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("ESCOLHA UMA OPÇÃO");
            System.out.println("0. Finalizar aplicação.");
            System.out.println("1. Cadastrar usuário.");
            System.out.println("2. Listar todos os usuários.");
            System.out.println("3. Procurar usuário por ID.");
            System.out.println("4. Excluir usuário por ID.");
            int option = 0;
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.err.println(ex.getMessage());
                scanner.nextLine();
                continue;
            }
            switch (option) {
                case 0:
                    System.out.println("Aplicação finalizada!");
                    return;
                case 1 :
                    System.out.print("Informe o nome do usuário: ");
                    String name = scanner.nextLine();
                    System.out.print("Informe o e-mail do usuário: ");
                    String email = scanner.nextLine();
                    if(userService.registerUsers(name,email)) {
                        System.out.println("Usuário cadastrado com sucesso!\n");
                    }else {
                        System.out.println("Houve algum erro ao cadastrar o usuário!\n");
                    }
                    break;
                case 2:
                    System.out.println(">>>>>>>>>>>>>LISTA DE USUÁRIOS<<<<<<<<<<<<<");
                    List<User> userList = userService.usersList();
                    if (userList.isEmpty()) {
                        System.out.println("Lista vázia!");
                    }else {
                        for (User user : userList) {
                            System.out.println(user);
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println(">>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<");
                    System.out.print("Informe o ID do usuário: ");
                    long id = scanner.nextLong();
                    Optional<User> user = userService.findById(id);
                    System.out.println(user.toString());
                    break;
                case 4:
                    System.out.println(">>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<");
                    System.out.print("Informe o ID do usuário: ");
                    id = scanner.nextLong();
                    if(userService.deleteById(id)) {
                        System.out.println("Usuário deletado com sucesso!\n");
                    }else {
                        System.out.println("Houve algum erro ao deletar o usuário!\n");
                    }
                    break;
                default:
                    System.out.println("Opção invalida!");
            }
        }
    }
}