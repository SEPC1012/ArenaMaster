package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;

public class Registration {
    private Tournament tournament;
    private List<Participant> participants;

    public Registration(Tournament tournament) {
        this.tournament = tournament;
        this.participants = new ArrayList<>();
    }

    public void registrarParticipante(Participant p) {
        participants.add(p);
        tournament.addParticipants(p);
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
