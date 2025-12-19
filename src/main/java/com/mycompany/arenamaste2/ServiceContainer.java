package com.mycompany.arenamaste2;

public class ServiceContainer {
    private DataBaseService databaseService;
    private ViewerService viewerService;
    private AuthService authService;
    private TournamentService tournamentService;
    public ServiceContainer() {
        System.out.println("Inicializando servicios...\n");

        this.databaseService = new DataBaseService();
        System.out.println(" DatabaseService - Datos cargados");

        this.viewerService = new ViewerService(this);
        System.out.println(" ViewerService inicializado");

        this.authService = new AuthService(this);
        System.out.println(" AuthService inicializado");

        this.tournamentService = new TournamentService(this);
        System.out.println(" TournamentService inicializado");

        System.out.println("\nSistema listo!\n");
    }

    public DataBaseService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(DataBaseService databaseService) {
        this.databaseService = databaseService;
    }

    public ViewerService getViewerService() {
        return viewerService;
    }

    public void setViewerService(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public TournamentService getTournamentService() {
        return tournamentService;
    }

    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }
}
