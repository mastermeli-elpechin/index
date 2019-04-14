public class UsuarioException extends Exception {

        public UsuarioException(){
            //llamo al constructor por defecto de la clase exception
            super();
        }

        public UsuarioException(String mensaje){
            super(mensaje);
        }
}


