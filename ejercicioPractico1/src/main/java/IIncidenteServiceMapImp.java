import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IIncidenteServiceMapImp implements IIncidenteService{

    public static List<Incidente> incidenteArray = new ArrayList<Incidente>();
    private final IUsuarioService usuarioService = new IUsuarioServiceMapImp();

    @Override
    public void addIncidente(Incidente incidente) {
        //Verifico si el usuario existe antes de agregar un incidente
            incidenteArray.add(incidente);
    }

    @Override
    public void addDescripcion(int id, String descripcion) {
            incidenteArray.get(id).setDescripcion(descripcion);
    }

    @Override
    public void changeEstado(int id, Estado estado) throws UsuarioException{
       try{

        //cambiar el estado, del incidente con indice id solo si su estado es ASIGNADO
        if(incidenteArray.get(id).getEstado() == Estado.ASIGNADO){
            incidenteArray.get(id).setEstado(Estado.RESUELTO);
        }
       } catch (Exception e) {
           throw new UsuarioException("No puede modificarse un incidente resuelto.");
       }
    }

    @Override
    public boolean getUsuarioReportador(Usuario usuario) {
        boolean reportadorExists = false;
        for ( int i = 0; i < incidenteArray.size(); i++) {
            if (incidenteArray.get(i).getResponsable() == usuario){
                reportadorExists = true;
            }
        }
        return reportadorExists;
    }

    @Override
    public boolean getUsuarioResponsable(Usuario usuario) {
        boolean responsableExists = false;
        for ( int i = 0; i < incidenteArray.size(); i++) {
            if (incidenteArray.get(i).getResponsable() == usuario){
                responsableExists = true;
            }
        }
        return responsableExists;
    }

    @Override
    public boolean searchUser(Usuario usuario) {
        boolean userExists = false;
        if (getUsuarioReportador(usuario) && getUsuarioResponsable(usuario)) {
            userExists = true;
        }
        return userExists;
    }

    @Override
    public boolean searchProyecto(Proyecto proyecto) {
        boolean incidentProyecto = false;
        for(int i = 0; i<incidenteArray.size(); i++) {
            if (incidenteArray.get(i).getProyecto() == proyecto){
                incidentProyecto = true;
            }
        }
        return incidentProyecto;
    }

    //Mostrar todos los incidentes.
    @Override
    public Collection<Incidente> getIncidente() {
        return incidenteArray;
    }

    //Mostrar los incidentes con abiertos o en estado pendiente.
    public Collection<Incidente> getIncidentesPendientes() {
        ArrayList<Incidente> incidentesPendientesArray = new ArrayList<Incidente>();
        for(int i=0; i < incidenteArray.size(); i++) {
            if (incidenteArray.get(i).getEstado() == Estado.ASIGNADO) {
                incidentesPendientesArray.add(incidenteArray.get(i));
            }
        }
        return incidentesPendientesArray;
    }


    //Metodo para mostrar los incidentes reportados por un usuario.
    public Collection<Incidente> getIncidentesReportador(Usuario usuario){
        ArrayList<Incidente> incidenteReportador = new ArrayList<Incidente>();
        for(int i=0; i < incidenteArray.size(); i++) {
            if (getUsuarioReportador(usuario) && (usuario == incidenteArray.get(i).getResponsable())) {
                incidenteReportador.add(incidenteArray.get(i));
            }
        }
        return incidenteReportador;
    }

    //Incidentes asignados a un determinado proyecto.
    public Collection<Incidente> getProyectoIncidentes (Proyecto proyecto){
        ArrayList<Incidente> incidenteProyectoArray = new ArrayList<Incidente>();
        for(int i=0; i < incidenteArray.size(); i++) {
            if ( incidenteArray.get(i).getProyecto() == proyecto ) {
                incidenteProyectoArray.add(incidenteArray.get(i));
            }
        }
        return incidenteProyectoArray;
    }

    //Mostrar los incidentes con Estado Resuelto
    public Collection<Incidente> getIncidentesResueltos() {
        ArrayList<Incidente> incidentesResueltosArray = new ArrayList<Incidente>();
        for(int i=0; i < incidenteArray.size(); i++) {
            if (incidenteArray.get(i).getEstado() == Estado.RESUELTO) {
                incidentesResueltosArray.add(incidenteArray.get(i));
            }
        }
        return incidentesResueltosArray;
    }

    //Incidentes asignados a un determinado usuario
    public Collection<Incidente> getIncidentesAsignadosUsuario (Usuario usuario){
        ArrayList<Incidente> incidentesAsignados = new ArrayList<Incidente>();
        for(int i=0; i < incidenteArray.size(); i++) {
            //Filtro por los incidentes que se encuentran con estado asignado
            if(incidenteArray.get(i).getEstado() == Estado.ASIGNADO){
                //Primero verifico si el id del user existe, y evito verificar TODOS los incidentes
                if ( usuarioService.validateUsuario(usuario) &&
                        //Verifico si el usuario, es responsable o reportador de ese incidente asignado.
                        ( (incidenteArray.get(i).getResponsable() == usuario) ||  (incidenteArray.get(i).getReportador() == usuario)) ){
                    incidentesAsignados.add(incidenteArray.get(i));
                }
            }
        }
        return incidentesAsignados;
    }
}
