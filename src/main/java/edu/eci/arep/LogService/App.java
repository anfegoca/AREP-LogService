package edu.eci.arep.LogService;

import static spark.Spark.*;

import java.util.List;

import edu.eci.arep.LogService.model.Log;
import spark.Request;
import spark.Response;




public class App {
    

    public static void main(String... args) {
        port(getPort());
        get("log", (req, res) -> resultsPage(req, res));
    }

    private static String resultsPage(Request req, Response res) {
        String respuesta = "{ ";
        String mensaje = req.queryParams("msg");
        Log.getInstance().agregarMensaje(mensaje);
        List<String> mensajes = Log.getInstance().obtenerRegistros();
        int cont=10;
        int cont2=mensajes.size()-1;
        while(cont2 > 0 && cont > 0){
            respuesta += mensajes.get(cont2)+ ",";
            cont--;
            cont2--;
        }
        respuesta += "}";
        return respuesta;

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
