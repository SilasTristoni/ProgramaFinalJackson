import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/JacksonFinal";
        final String user = "root";
        final String password = "";
        int opcao;
        do {
            System.out.println("\n---- MENU PRINCIPAL ----");
            System.out.println("1. Cadastrar Local");
            System.out.println("2. Cadastrar Organizador");
            System.out.println("3. Cadastrar Participante");
            System.out.println("4. Cadastrar Evento");
            System.out.println("5. Listar Eventos");
            System.out.println("6. Vincular Participante a Evento");
            System.out.println("7. Enviar notificações");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    // Cadastro de Local
                    System.out.print("Descrição do Local: ");
                    String descricaoLocal = scanner.nextLine();
                    System.out.print("Capacidade (vagas): ");
                    int capacidade = scanner.nextInt();
                    try (Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement()) {
                        String sql = "INSERT INTO Locais (descricaoLocal, capacidade) VALUES ('" + descricaoLocal
                                + "', '" + capacidade + "')";
                        int result = stm.executeUpdate(sql);
                        if (result > 0) {
                            System.out.println("Local cadastrado com sucesso!");
                        } else {
                            System.out.println("Falha na execução");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao cadastrar local: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Cadastro de Organizador
                    System.out.print("Nome do Organizador: ");
                    String nomeOrganizador = scanner.nextLine();
                    System.out.print("E-mail do Organizador: ");
                    String emailOrganizador = scanner.nextLine();
                    try (Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement()) {
                        String sql = "INSERT INTO Organizador (nomeOrganizador, emailOrganizador) VALUES ('"
                                + nomeOrganizador + "', '" + emailOrganizador + "')";
                        int result = stm.executeUpdate(sql);
                        if (result > 0) {
                            System.out.println("Organizador cadastrado com sucesso!");
                        } else {
                            System.out.println("Falha na execução");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao cadastrar o organizador: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Cadastro de Participante
                    System.out.print("Nome do Participante: ");
                    String nomeParticipante = scanner.nextLine();
                    System.out.print("Telefone do Participante: ");
                    String telefoneParticipante = scanner.nextLine();
                    try (Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement()) {
                        String sql = "INSERT INTO Participante (nomeParticipante, telefoneParticipante) VALUES ('"
                                + nomeParticipante + "', '" + telefoneParticipante + "')";
                        int result = stm.executeUpdate(sql);
                        if (result > 0) {
                            System.out.println("Participante cadastrado com sucesso!");
                        } else {
                            System.out.println("Falha na execução");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao cadastrar participante: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Cadastro de Evento
                    System.out.print("Nome do Evento: ");
                    String nomeEvento = scanner.nextLine();
                    System.out.print("Descrição do Evento: ");
                    String descricaoEvento = scanner.nextLine();
                    System.out.print("Data do Evento: ");
                    String dataEvento = scanner.nextLine();
                    System.out.print("ID do Local para o Evento: ");
                    int idLocalEvento = scanner.nextInt();
                    System.out.print("ID do Organizador do Evento: ");
                    int idOrganizadorEvento = scanner.nextInt();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dataEventoFormatada;

                    try {
                        Date date = inputFormat.parse(dataEvento);
                        dataEventoFormatada = mysqlFormat.format(date);

                        try (Connection con = DriverManager.getConnection(url, user, password);
                                Statement stm = con.createStatement()) {
                            String sql = "INSERT INTO Evento (nomeEvento, descricaoEvento, dataEvento, idLocalEvento, idOrganizadorEvento) VALUES ('"
                                    + nomeEvento + "', '" + descricaoEvento + "', '" + dataEventoFormatada + "', '"
                                    + idLocalEvento
                                    + "', '" + idOrganizadorEvento + "')";
                            int result = stm.executeUpdate(sql);
                            if (result > 0) {
                                System.out.println("Evento cadastrado com sucesso!");
                            } else {
                                System.out.println("Falha na execução");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao cadastrar evento: " + e.getMessage());
                        }

                    } catch (ParseException e) {
                        System.out.println("Erro ao converter a data: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\nListar Eventos:");
                    try (Connection con = DriverManager.getConnection(url, user, password);
                            Statement stm = con.createStatement();
                            ResultSet rs = stm.executeQuery("SELECT * FROM Evento")) {
                        while (rs.next()) {
                            System.out.println("- ID: " + rs.getInt("idEvento") + " - Nome do Evento: "
                                    + rs.getString("nomeEvento") + " - Descrição do evento: "
                                    + rs.getString("descricaoEvento") + " - Data do evento: " + rs.getDate("dataEvento")
                                    + " - Id do organizador: " + rs.getInt("idOrganizadorEvento") + " - Id do local: "
                                    + rs.getInt("idLocalEvento"));
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao listar eventos: " + e.getMessage());
                    }
                    break;

                case 6:
                    // Vincular Participante a Evento
                    System.out.print("Informe o ID do Participante: ");
                    int idParticipanteEvento = scanner.nextInt();
                    System.out.print("Informe o ID do Evento: ");
                    int idEventoParticipante = scanner.nextInt();

                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        String insertQuery = "INSERT INTO EventoParticipante (idParticipante, idEvento) VALUES (?, ?)";
                        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
                            stmt.setInt(1, idParticipanteEvento);
                            stmt.setInt(2, idEventoParticipante);
                            int rowsAffected = stmt.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Participante vinculado ao evento com sucesso!");
                            }
                        }
                    } catch (SQLException e) {
                        System.err.println("Erro ao vincular participante ao evento: " + e.getMessage());
                    }
                    break;
                case 7:
                    // Enviar Notificações
                    System.out.print("Informe o ID do Evento para enviar notificações: ");
                    int idEventoParaNotificar = scanner.nextInt();
                    scanner.nextLine();

                    // Exemplo de mensagem de notificação
                    System.out.println("\nEnviando notificações para o evento com ID: " + idEventoParaNotificar);

                    // Envio de notificação para o organizador
                    System.out.println("Enviando email para o organizador...");

                    // Envio de notificações para os participantes
                    System.out.println("Enviando mensagens para os participantes do evento...");

                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
