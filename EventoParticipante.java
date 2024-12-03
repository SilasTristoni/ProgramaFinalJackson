import java.util.ArrayList;
import java.util.List;

public class EventoParticipante {
    private int idEvento; 
    private int idParticipante;  

    static List<EventoParticipante> eventosParticipantes = new ArrayList<>();


    public EventoParticipante(Evento evento, Participante participante) throws Exception {
        this.idEvento = evento.getIdEvento();
        this.idParticipante = participante.getIdPessoa();
        evento.addParticipante(this); // Pode lançar exceção
        participante.addEvento(this); // Pode lançar exceção
        eventosParticipantes.add(this);  
    }
    

    public int getIdEvento() {
        return idEvento;
    }

    public int getIdParticipante() {
        return idParticipante;
    }
    
    static void vincularParticipanteAoEvento(int idParticipante, int idEvento) throws Exception {
        Participante participante = Participante.buscaParticipante(idParticipante);
        Evento evento = Evento.buscarEventoPorId(idEvento);
    
        if (participante == null) {
            throw new Exception("Participante não encontrado.");
        }
    
        if (evento == null) {
            throw new Exception("Evento não encontrado.");
        }
    
        // Cria a relação entre Evento e Participante
        new EventoParticipante(evento, participante);
    }
}
