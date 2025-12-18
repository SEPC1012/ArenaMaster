package com.mycompany.arenamaste2;
public class TournamentService {

    private DataBaseService dbs;

    public TournamentService(DataBaseService dbs) {
        this.dbs = dbs;
    }

    public void setDbs(DataBaseService dbs) {
        this.dbs = dbs;
    }

    public DataBaseService getDb() {
        return dbs;
    }

}
