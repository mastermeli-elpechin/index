public class Proyecto {

    private int id;
    private String titulo;
    private Usuario usuario;
    private Incidente incidente[];

    public Incidente[] getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente[] incidente) {
        this.incidente = incidente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}