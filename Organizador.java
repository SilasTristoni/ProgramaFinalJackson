import java.util.ArrayList;

public class Organizador extends Pessoa {
    private String emailOrg;
    static ArrayList<Organizador> organizadores = new ArrayList<>();

    public Organizador(int idPessoa, String nomePessoa, String emailOrg) {
        super(idPessoa, nomePessoa);
        this.emailOrg = emailOrg;
        organizadores.add(this);
    }

    public String getEmailOrg() {
        return emailOrg;
    }

    public void setEmailOrg(String emailOrg) {
        this.emailOrg = emailOrg;
    }
}
