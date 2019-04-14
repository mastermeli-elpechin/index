import java.util.Date;

public class Incidente {

    private int id;
    private Clasificacion clasificacion;
    private Estado estado;
    private String descripcion;
    private Usuario reportador;
    private Usuario responsable;
    private Date fecha_cracion;
    private Date fecha_solucion;
    private Proyecto proyecto;

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getReportador() {
        return reportador;
    }

    public void setReportador(Usuario reportador) {
        this.reportador = reportador;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public Date getFecha_cracion() {
        return fecha_cracion;
    }

    public void setFecha_cracion(Date fecha_cracion) {
        this.fecha_cracion = fecha_cracion;
    }

    public Date getFecha_solucion() {
        return fecha_solucion;
    }

    public void setFecha_solucion(Date fecha_solucion) {
        this.fecha_solucion = fecha_solucion;
    }

}
