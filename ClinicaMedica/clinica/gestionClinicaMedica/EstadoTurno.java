package clinica.gestionClinicaMedica;

public enum EstadoTurno {
    PENDIENTE(1),
    CONFIRMADO(2),
    CANCELADO(3);

    private final int id;

    EstadoTurno(int id) { this.id = id; }

    public int getId() { return id; }

    public static EstadoTurno fromId(int id) {
        for (EstadoTurno e : values()) {
            if (e.getId() == id) return e;
        }
        return PENDIENTE;
    }
}
