public class AgencyException extends Exception {

    public AgencyException(){
        //llamo al constructor por defecto de la clase exception
        super();
    }

    public AgencyException(String mensaje){
        super(mensaje);
    }
}
