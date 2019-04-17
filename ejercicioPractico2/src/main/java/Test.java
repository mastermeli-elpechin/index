import com.google.gson.Gson;
import spark.Spark;

import static spark.route.HttpMethod.get;

public class Test {

    public static void main(String[] args) {

        final IAgencyService iAgencyService = new IAgencyServiceImp();


        //Mostrar todas las agencias
        Spark.get("/agencies", (request, response) -> {
            response.type("application/json");
            //Cargar los valores de la url pasada por el cliente en el log.
            MyLog.loggear(request.queryString());
                return new Gson().toJson(iAgencyService.getAgencies(request.queryParams("site_id"), request.queryParams("payment_method_id")
                                ,request.queryParams("near_to"),request.queryParams("limit"), request.queryParams("offset")
                                , request.queryParams("criterio")));

        });
    }
}
