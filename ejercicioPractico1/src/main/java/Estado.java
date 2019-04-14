public enum Estado {

    ASIGNADO ("asignado"),
    RESUELTO ("resuelto");

    private String estado;

    Estado (String estado){
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
