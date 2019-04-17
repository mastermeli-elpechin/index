import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;

public class IAgencyServiceImp implements IAgencyService{

    private static HashMap<String, Agency> hashMap = new HashMap<>();

    public Collection<Agency> getAgencies(String site_id, String payment_method_id, String near_to, String limit, String offset, String criterio ) throws AgencyException {

        try {
            if(limit == null ) {
                limit = "50";
            }
            if (offset == null){
                offset = "0";
            }
            String urlAgency = readUrl("https://api.mercadolibre.com/sites/"+ site_id +
                            "/payment_methods/" + payment_method_id + "/agencies?near_to=" + near_to +
                            "&limit="+ limit +"&offset="+offset);


            JsonObject jsonObject = new JsonParser().parse(urlAgency).getAsJsonObject();
            Agency[] listaAgencias = new Gson().fromJson(jsonObject.get("results"), Agency[].class);
            System.out.println("\n");
            switch (criterio){
                    case "distance":
                        Agency.criterio = Criterio.DISTANCE;
                        break;
                    case "agency_code":
                        Agency.criterio = Criterio.AGENCY_CODE;
                        break;
                    case "address_line":
                        Agency.criterio = Criterio.ADDRESS_LINE;
                        break;
                //para el caso de que no cargue un criterio particular.
                default: Agency.criterio = Criterio.DISTANCE;
                }
            listaAgencias = Operador.burbuja(listaAgencias);
            for(Agency agency : listaAgencias){
                hashMap.put(agency.getId(), agency);
            }
            return hashMap.values();

        } catch (IOException e) {
            throw new AgencyException(e.getMessage());
        }

    }
    public String readUrl (String urlString) throws IOException {

        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            //se pasa una key, y
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            //Creo un input stream reader, con lo que me trae urlConnection, pero necesito la codificacion
            // por cuestiones de escritura de la api que recibe, ahroa en Reader poseo un BufferReader.
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            int read=0;
            //Creo un objeto java, streamBuffer, en vez de un bufferreader. para facilitacion de trabajo por ser tipo string
            StringBuffer buffer = new StringBuffer();
            //creo un array de char, de 1024 porq es el estandar de ese arreglo que estoy leyendo
            char[] chars = new char[1024];
            //Leer cuantos caracteres quiero leer de reader
            while( (read = reader.read(chars)) != -1){
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
            //la responsabilidad de corregir la url es del usuario, el cual debe de verificar
            // que envia, entonces, que lo resuelva el usuario
        } /*catch (MalformedURLException e) {
            e.printStackTrace();
        } */
        //le aviso al cliente que no puedo dar de alta la conexion, lo aviso en un throw, IOException
            /*catch (IOException e) {
            e.printStackTrace();
        }*/ finally {
            //cerrar la coneccion abierta de urlConnection
            if(reader != null){
                reader.close();
            }
        }
    }
}
