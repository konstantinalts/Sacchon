package gr.codehub.pfizer.team1.enums;

public enum ChiefDoctor {

    DOCTOR("No"),
    CHIEF_DOCTOR("Yes");

    private String chiefDoctor;

    ChiefDoctor(String chiefDoctor) { this.chiefDoctor = chiefDoctor; }

    public String getChiefDoctor() { return chiefDoctor; }
}
