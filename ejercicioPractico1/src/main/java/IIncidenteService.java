import java.util.Collection;

public interface IIncidenteService {

    public void addIncidente (Incidente incidente);
    public void addDescripcion(int id, String descripcion);
    public void changeEstado(int id, Estado estado) throws UsuarioException;
    //Analogo al metodo de buscar usuario en proyecto
    public boolean getUsuarioReportador(Usuario usuario);
    public boolean getUsuarioResponsable(Usuario usuario);
    //buscar un usuario que llame a los otros 2 metodos directamente. para no hacer 2 ifs en el main para eliminar un usuaroi.
    public boolean searchUser (Usuario usuario);
    public boolean searchProyecto(Proyecto proyecto);
    public Collection<Incidente> getIncidentesPendientes();
    //Todos los incidentes.
    public Collection<Incidente> getIncidente();
    //Incidentes con estado Resuelto
    public Collection<Incidente> getIncidentesResueltos();
    //Incidentes creados por un determinado usuario reportador
    public Collection<Incidente> getIncidentesReportador(Usuario usuario);
    //Incidentes de un determinado proyecto.
    public Collection<Incidente> getProyectoIncidentes (Proyecto proyecto);
    //Incidentes asignados a un determinado usuario.
    public Collection<Incidente> getIncidentesAsignadosUsuario (Usuario usuario);

}
