import java.util.*;

public class ProyectoServiceMapImp implements IProyectoService{

    private static List<Proyecto> proyectoArray = new ArrayList<Proyecto>();
    //Comunicacion con el servicio usuario e incidente
    private final IUsuarioService usuarioService = new IUsuarioServiceMapImp();
    private final IIncidenteService incidenteService = new IIncidenteServiceMapImp();

    public ProyectoServiceMapImp() {
        //proyectoArray = new Array[];
    }

    @Override
    public void addProyecto(Proyecto proyecto) {
        if(usuarioService.validateUsuario(proyecto.getUsuario())) {
            proyectoArray.add(proyecto);
        }
    }

    @Override
    public Collection<Proyecto> getProyecto() {
        return proyectoArray;
    }

    @Override
    public Proyecto getProyecto(int id) {
        return proyectoArray.get(id);
    }

    @Override
    public Proyecto editProyecto(Proyecto proyecto) throws UsuarioException{
        Proyecto proyectoEditar = proyectoArray.get(proyecto.getId());
        try{
            //Verifico si el id de proyecto es diferente al id de proyecto en memoria.
            if (proyecto.getId() != proyectoEditar.getId()) {
                //Response Error
                throw new UsuarioException("El id del proyecto No puede modificarse.");
            }
            if (proyecto.getTitulo() != null) {
                proyectoEditar.setTitulo(proyecto.getTitulo());
            }
            if (proyecto.getUsuario() != null) {
                proyectoEditar.setUsuario(proyecto.getUsuario());
            }
            return proyectoEditar;

        }catch (UsuarioException e) {
            throw  new UsuarioException("No es posible editar el proyecto.");
        }
    }

    @Override
    public void deleteProyecto(int id) throws UsuarioException{
        try{
            //Si tengo incidentes asociados, no puedo eliminar el proyecto
            if(!incidenteService.searchProyecto(proyectoArray.get(id))) {
                proyectoArray.remove(id);
            }
        } catch (Exception e) {
            throw new UsuarioException("No puede eliminarse proyecto, existen Incidentes asociados.");
        }

    }

    public boolean searchUser(Usuario usuario){
        boolean userExists = false;
        for(int i = 0; i < proyectoArray.size(); i++){
            if(usuarioService.validateUsuario(usuario)) {
                userExists = true;
                break;
            }
        }
        return userExists;
    }

    //Mostrar todos los proyectos de un usuario.
    public Collection<Proyecto> getProyectosUsuario(Usuario usuario){
        Collection<Proyecto> arrayProyectoUsuario = new ArrayList<Proyecto>();
        for(int i=0; i < proyectoArray.size(); i++){
            if( proyectoArray.get(i).getUsuario()==usuario ){
                arrayProyectoUsuario.add(getProyecto(i));
            }
        }
        return arrayProyectoUsuario;
    }
}
