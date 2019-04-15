import static spark.Spark.*;
import com.google.gson.Gson;

public class RestServerEjemplo {

    public static void main(String[] args) {

        final IUsuarioService usuarioService = new IUsuarioServiceMapImp();
        final IProyectoService proyectoService = new ProyectoServiceMapImp();
        // TODO: IMPLEMENTAR SERVICIO INCIDENCIAS. (done)
        final IIncidenteService incidenteService = new IIncidenteServiceMapImp();

        //Agregar un Usuario
        post("/usuario", (request,response) -> {
            response.type("application/json");
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            usuarioService.addUsuario(usuario);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //Mostrar Usuario
        get("/usuario/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(usuarioService.getUsuario(Integer.parseInt(request.params("id"))))));
        });

        //Mostrar Todos los usuarios
        get("/usuario", ((request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(usuarioService.getUsuarios())));
        }));

        //Editar usuario,
        put("/usuario", ((request, response) -> {
            Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
            Usuario integranteEditado = usuarioService.editUsuario(usuario);
            if (integranteEditado != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(integranteEditado)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Error al editar el integrante" +
                        request.params(":id")));
            }
        }));

        //Delete Usuario
        delete("/integrante/:id", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            String requestResponse = "";
            //TODO: FALTA LLAMAR METODO BUSCAR USUARIO DENTRO DE LAS INCIDENCIAS. (done)
            //Verifico si el userId existe, y si tiene un proyecto o se encuentra en el array de incidencias. De lo contrario no puedo eliminarlo.
            if(usuarioService.validateUserIdExists(userId) && proyectoService.searchUser(usuarioService.getUsuario(userId))
                || incidenteService.searchUser(usuarioService.getUsuario(userId))){
                usuarioService.deleteUsuario(userId);
                requestResponse = new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Usuario eliminado." +
                        request.params(":id")));
            } else {
                requestResponse = new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Error al eliminar el usuario" +
                        request.params(":id")));
            }
            return requestResponse;
        });


        //Agregar un Proyecto
        post("/proyecto", (request,response) -> {
            response.type("application/json");
            Proyecto proyecto = new Gson().fromJson(request.body(), Proyecto.class);
            proyectoService.addProyecto(proyecto);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //Buscar todos los Proyectos
        get("/proyecto", (request,response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(proyectoService.getProyecto())));
        });

        //Buscar un Proyecto
        get("/proyecto/:id", (request,response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(proyectoService.getProyecto(Integer.parseInt(request.params(":id"))))));
        });

        //Delete Proyecto
        delete("/proyecto/:id", (request, response) -> {
            int proyectoId = Integer.parseInt(request.params("id"));
            String requestResponse = "";
            //Verifico si el proyectoId existe, y si tiene un proyecto o se encuentra en el array de incidencias. De lo contrario no puedo eliminarlo.
            if(incidenteService.searchProyecto(proyectoService.getProyecto(proyectoId))){
                proyectoService.deleteProyecto(Integer.parseInt(request.params(":id")));
                requestResponse = new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Proyecto eliminado con id " +
                        request.params(":id")));
            } else {
                requestResponse = new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Error al eliminar el proyecto con id " +
                        request.params(":id")));
            }
            return requestResponse;
        });

        //Agregar un Incidente
        post("/incidente", (request,response) -> {
            response.type("application/json");
            Incidente incidente = new Gson().fromJson(request.body(), Incidente.class);
            incidenteService.addIncidente(incidente);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        

    }
}
