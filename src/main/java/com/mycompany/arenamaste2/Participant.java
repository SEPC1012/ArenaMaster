package com.mycompany.arenamaste2;

import java.util.List;

public interface Participant {

    String getId();
    String getName();

    ParticipantType getType();

    //Statistics getStatistics();

    String showInfo();
    void requestEnrollment(ServiceContainer services);

    List<Match> getLastMatches();
    void addMatch(Match match);

    List<Tournament> getLastTournaments();
    void addTournament(Tournament tournament);

}


