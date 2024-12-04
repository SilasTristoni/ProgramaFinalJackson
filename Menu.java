import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/JacksonFinal";
        final String user = "root";
        final String password = "";
        try (Scanner scanner = new Scanner(System.in)) {
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
                System.out.println("8. Alterar Cadastro de local");
                System.out.println("9. Excluir Cadastro de local");
                System.out.println("10. Alterar Organizador");
                System.out.println("11. Alterar Participante");
                System.out.println("12. Alterar Evento");
                System.out.println("13. Excluir Organizador");
                System.out.println("14. Excluir Participante");
                System.out.println("15. Excluir Evento");
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
                                        + rs.getString("descricaoEvento") + " - Data do evento: "
                                        + rs.getDate("dataEvento")
                                        + " - Id do organizador: " + rs.getInt("idOrganizadorEvento")
                                        + " - Id do local: "
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
                        System.out.println("\nEnviando notificações para o evento com ID: " + idEventoParaNotificar);
                        System.out.println("Enviando email para o organizador...");
                        System.out.println("Enviando mensagens para os participantes do evento...");
                        break;
                    case 8:
                        // Alterar Local
                        System.out.print("Informe o ID do Local que deseja alterar: ");
                        int idLocalAlterar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nova descrição do Local: ");
                        String novaDescricaoLocal = scanner.nextLine();
                        System.out.print("Nova capacidade (vagas): ");
                        int novaCapacidade = scanner.nextInt();

                        try (Connection con = DriverManager.getConnection(url, user, password);
                                PreparedStatement stmt = con.prepareStatement(
                                        "UPDATE Locais SET descricaoLocal = ?, capacidade = ? WHERE idLocal = ?")) {
                            stmt.setString(1, novaDescricaoLocal);
                            stmt.setInt(2, novaCapacidade);
                            stmt.setInt(3, idLocalAlterar);
                            int rowsUpdated = stmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Local atualizado com sucesso!");
                            } else {
                                System.out.println("Nenhum local encontrado com o ID informado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao atualizar local: " + e.getMessage());
                        }
                        break;
                    case 9:
                        // Excluir Local
                        System.out.print("Informe o ID do Local que deseja excluir: ");
                        int idLocalExcluir = scanner.nextInt();

                        try (Connection con = DriverManager.getConnection(url, user, password);
                                PreparedStatement stmt = con.prepareStatement("DELETE FROM Locais WHERE idLocal = ?")) {
                            stmt.setInt(1, idLocalExcluir);
                            int rowsDeleted = stmt.executeUpdate();
                            if (rowsDeleted > 0) {
                                System.out.println("Local excluído com sucesso!");
                            } else {
                                System.out.println("Nenhum local encontrado com o ID informado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao excluir local: " + e.getMessage());
                        }
                        break;

                    case 10:
                        // Alterar Organizador
                        System.out.print("Informe o ID do Organizador que deseja alterar: ");
                        int idOrganizadorAlterar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo nome do Organizador: ");
                        String novoNomeOrganizador = scanner.nextLine();
                        System.out.print("Novo e-mail do Organizador: ");
                        String novoEmailOrganizador = scanner.nextLine();

                        try (Connection con = DriverManager.getConnection(url, user, password);
                                PreparedStatement stmt = con.prepareStatement(
                                        "UPDATE Organizador SET nomeOrganizador = ?, emailOrganizador = ? WHERE idOrganizador = ?")) {
                            stmt.setString(1, novoNomeOrganizador);
                            stmt.setString(2, novoEmailOrganizador);
                            stmt.setInt(3, idOrganizadorAlterar);
                            int rowsUpdated = stmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Organizador atualizado com sucesso!");
                            } else {
                                System.out.println("Nenhum organizador encontrado com o ID informado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao atualizar organizador: " + e.getMessage());
                        }
                        break;
                    case 11:
                        // Alterar Participante
                        System.out.print("Informe o ID do Participante que deseja alterar: ");
                        int idParticipanteAlterar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo nome do Participante: ");
                        String novoNomeParticipante = scanner.nextLine();
                        System.out.print("Novo telefone do Participante: ");
                        String novoTelefoneParticipante = scanner.nextLine();

                        try (Connection con = DriverManager.getConnection(url, user, password);
                                PreparedStatement stmt = con.prepareStatement(
                                        "UPDATE Participante SET nomeParticipante = ?, telefoneParticipante = ? WHERE idParticipante = ?")) {
                            stmt.setString(1, novoNomeParticipante);
                            stmt.setString(2, novoTelefoneParticipante);
                            stmt.setInt(3, idParticipanteAlterar);
                            int rowsUpdated = stmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Participante atualizado com sucesso!");
                            } else {
                                System.out.println("Nenhum participante encontrado com o ID informado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao atualizar participante: " + e.getMessage());
                        }
                        break;
                    case 12:
                        // Alterar Evento
                        System.out.print("Informe o ID do Evento que deseja alterar: ");
                        int idEventoAlterar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo nome do Evento: ");
                        String novoNomeEvento = scanner.nextLine();
                        System.out.print("Nova descrição do Evento: ");
                        String novaDescricaoEvento = scanner.nextLine();
                        System.out.print("Nova data do Evento (dd/MM/yyyy): ");
                        String novaDataEvento = scanner.nextLine();
                        System.out.print("Novo ID do Local do Evento: ");
                        int novoIdLocalEvento = scanner.nextInt();
                        System.out.print("Novo ID do Organizador do Evento: ");
                        int novoIdOrganizadorEvento = scanner.nextInt();

                        SimpleDateFormat inputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat mysqlFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = inputFormat1.parse(novaDataEvento);
                            String novaDataEventoFormatada = mysqlFormat1.format(date);

                            try (Connection con = DriverManager.getConnection(url, user, password);
                                    PreparedStatement stmt = con.prepareStatement(
                                            "UPDATE Evento SET nomeEvento = ?, descricaoEvento = ?, dataEvento = ?, idLocalEvento = ?, idOrganizadorEvento = ? WHERE idEvento = ?")) {
                                stmt.setString(1, novoNomeEvento);
                                stmt.setString(2, novaDescricaoEvento);
                                stmt.setString(3, novaDataEventoFormatada);
                                stmt.setInt(4, novoIdLocalEvento);
                                stmt.setInt(5, novoIdOrganizadorEvento);
                                stmt.setInt(6, idEventoAlterar);
                                int rowsUpdated = stmt.executeUpdate();
                                if (rowsUpdated > 0) {
                                    System.out.println("Evento atualizado com sucesso!");
                                } else {
                                    System.out.println("Nenhum evento encontrado com o ID informado.");
                                }
                            }
                        } catch (ParseException e) {
                            System.out.println("Erro ao converter a data: " + e.getMessage());
                        } catch (SQLException e) {
                            System.out.println("Erro ao atualizar evento: " + e.getMessage());
                        }
                        break;
                    case 13:
                        // Excluir Organizador
                        System.out.print("Informe o ID do Organizador que deseja excluir: ");
                        int idOrganizadorExcluir = scanner.nextInt();
                    
                        try (Connection con = DriverManager.getConnection(url, user, password);
                                PreparedStatement stmt = con.prepareStatement("DELETE FROM Organizador WHERE idOrganizador = ?")) {
                            stmt.setInt(1, idOrganizadorExcluir);
                            int rowsDeleted = stmt.executeUpdate();
                            if (rowsDeleted > 0) {
                                System.out.println("Organizador excluído com sucesso!");
                            } else {
                                System.out.println("Nenhum organizador encontrado com o ID informado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao excluir organizador: " + e.getMessage());
                        }
                        break;
                    case 14:
                        // Excluir Participante
                        System.out.print("Informe o ID do Participante que deseja excluir: ");
                        int idParticipanteExcluir = scanner.nextInt();
                    
                        try (Connection con = DriverManager.getConnection(url, user, password);
                                PreparedStatement stmt = con.prepareStatement("DELETE FROM Participante WHERE idParticipante = ?")) {
                            stmt.setInt(1, idParticipanteExcluir);
                            int rowsDeleted = stmt.executeUpdate();
                            if (rowsDeleted > 0) {
                                System.out.println("Participante excluído com sucesso!");
                            } else {
                                System.out.println("Nenhum participante encontrado com o ID informado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao excluir participante: " + e.getMessage());
                        }
                        break;
                    case 15:
                        // Excluir Evento
                        System.out.print("Informe o ID do Evento que deseja excluir: ");
                        int idEventoExcluir = scanner.nextInt();
                    
                        try (Connection con = DriverManager.getConnection(url, user, password);
                                PreparedStatement stmt = con.prepareStatement("DELETE FROM Evento WHERE idEvento = ?")) {
                            stmt.setInt(1, idEventoExcluir);
                            int rowsDeleted = stmt.executeUpdate();
                            if (rowsDeleted > 0) {
                                System.out.println("Evento excluído com sucesso!");
                            } else {
                                System.out.println("Nenhum evento encontrado com o ID informado.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Erro ao excluir evento: " + e.getMessage());
                        }
                        break;
                    
                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                    
                }
            } while (opcao != 0);
        }
    }
}
