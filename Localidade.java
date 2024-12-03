import java.util.ArrayList;

public class Localidade {
    private int idLocal;
    private String descricao;
    private int vagas;
    static ArrayList<Localidade> locais = new ArrayList<>();

    public Localidade(int idLocal, String descricao, int vagas) {
        this.idLocal = idLocal;
        this.descricao = descricao;
        this.vagas = vagas;
        locais.add(this);
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
}
