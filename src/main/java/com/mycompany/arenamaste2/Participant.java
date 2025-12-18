package com.mycompany.arenamaste2;

public interface Participant {

    String getId();
    String getName();

    ParticipantType getType();

    Statistics getStatistics();

    String getInfo();

    boolean canJoinTournament(Tournament tournament);
}


