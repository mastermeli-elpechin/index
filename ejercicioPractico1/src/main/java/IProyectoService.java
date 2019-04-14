import java.util.Collection;

public interface IProyectoService {

    public void addProyecto(Proyecto proyecto);
    public Collection<Proyecto> getProyecto();
    public Proyecto getProyecto(int id);
    public Proyecto editProyecto (Proyecto proyecto) throws UsuarioException;
    public void deleteProyecto (int id) throws UsuarioException;
    public boolean searchUser (Usuario usuario);
}
