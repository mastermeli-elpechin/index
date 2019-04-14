import java.util.Collection;

public interface IUsuarioService {

    public void addUsuario (Usuario usuario);
    public Collection<Usuario> getUsuarios();
    public Usuario getUsuario (int id) throws UsuarioException;
    public Usuario editUsuario (Usuario usuario) throws UsuarioException;
    //Eliminar un usuario que no sea responsable, propietario, reportador
    public void deleteUsuario (int id) throws UsuarioException;
    public boolean validateUsuario(Usuario usuario);
    public boolean validateUserIdExists(int id);

}
