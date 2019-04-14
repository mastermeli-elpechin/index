import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IUsuarioServiceMapImp implements IUsuarioService{

    private static List<Usuario> arrayUser = new ArrayList<Usuario>();
    private final IIncidenteService incidenteService = new IIncidenteServiceMapImp();
    private final IProyectoService proyectoService = new ProyectoServiceMapImp();

    public IUsuarioServiceMapImp(){
    }

    public void addUsuario(Usuario usuario) {

        arrayUser.add(usuario);
    }

    public Collection<Usuario> getUsuarios() {
        return arrayUser;
    }

    public Usuario getUsuario(int id) throws UsuarioException {
        return arrayUser.get(id);
    }

    public Usuario editUsuario(Usuario usuario) throws UsuarioException{

        Usuario usuarioEditar = arrayUser.get(usuario.getId());
        try {
            if (usuario.getId() <= 0) {
                //Response Error
                throw new UsuarioException("El id del integrante no puede ser menor que 1.");
            }
            if (usuario.getNombre() != null) {
                usuarioEditar.setNombre(usuario.getNombre());
            }
            if (usuario.getApellido() != null) {
                usuarioEditar.setApellido(usuario.getApellido());
            }
            return usuarioEditar;
        }
        catch (Exception e){
            throw new UsuarioException("No se puede editar usuario.");
        }
    }

    public void deleteUsuario(int id) throws UsuarioException {

        try{
            if (!(proyectoService.searchUser(arrayUser.get(id)) || (incidenteService.getUsuarioReportador(arrayUser.get(id)))
            || incidenteService.getUsuarioResponsable(arrayUser.get(id)))){
                arrayUser.remove(id);
            }

        } catch (Exception e){
            throw new UsuarioException("No se puede eliminar usuario. Posee proyectos asociados o incidentes asociados");
        }
    }

    public boolean validateUsuario(Usuario usuario){
        return arrayUser.contains(usuario);
    }

    @Override
    public boolean validateUserIdExists(int id) {
       return arrayUser.contains(id);
    }
}