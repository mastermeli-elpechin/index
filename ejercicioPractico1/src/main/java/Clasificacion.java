public enum Clasificacion {

    CRITICAL ("Critico"),
    NORMAL ("normal"),
    MINOR ("menor");

    private String clasificacion;

    Clasificacion (String clasificacion){
        this.clasificacion = clasificacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}
