import java.util.ArrayList;

public class Evento {
    private int idEvento;
    private String nomeEvento;
    private String descricaoEvento;
    private String dataEvento;
    private int idLocalEvento;
    private int idOrganizadorEvento;
    private ArrayList<EventoParticipante> participantes;
    static ArrayList<Evento> eventos = new ArrayList<>();

    public Evento(int idEvento, String nomeEvento, String descricaoEvento, String dataEvento, int idLocalEvento, int idOrganizadorEvento) throws Exception {
        for (Evento evento : eventos) {
            if (evento.idEvento == idEvento) {
                throw new Exception("Já existe um evento com este ID.");
            }
        }
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descricaoEvento;
        this.dataEvento = dataEvento;
        this.idLocalEvento = idLocalEvento;
        this.idOrganizadorEvento = idOrganizadorEvento;
        this.participantes = new ArrayList<EventoParticipante>();
        eventos.add(this);
    }

    public int getIdEvento() {
        return idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public String getdataEvento() {
        return dataEvento;
    }
    

    public int getLocalEvento() {
        return idLocalEvento;
    }

    public int getOrganizadorEvento() {
        return idOrganizadorEvento;
    }

    public void addParticipante(EventoParticipante participante) throws Exception {
        for (EventoParticipante ep : this.participantes) {
            if (ep.getIdParticipante() == participante.getIdParticipante()) {
                throw new Exception("Este participante já está vinculado a este evento.");
            }
        }
        this.participantes.add(participante);
    }
    

    public ArrayList<EventoParticipante> getParticipantes() {
        return this.participantes;
    }

    static Evento buscarEventoPorId(int id) {
        for (Evento evento : eventos) {
            if (evento.idEvento == id) {
                return evento;
            }
        }
        return null;
    }
}
