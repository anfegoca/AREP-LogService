package edu.eci.arep.LogService.model;

import java.util.ArrayList;

import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import org.bson.Document;


public class Log {

    private MongoCollection<Document> collection;

    private static class helper {
        private static final Log INSTANCE = new Log();
    }

    public static Log getInstance() {
        return helper.INSTANCE;
    }

    private Log() {
        MongoClient mongoClient = new MongoClient( "172.24.0.3" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("test");
    }

  

    public void agregarMensaje(String msg) {
        Document doc = new Document("mensaje",msg);
        collection.insertOne(doc);
    }
    public List<String> obtenerRegistros(){
        List<String> res = new ArrayList<>();
        for(Document cur : collection.find()){
            res.add(cur.toJson());
        }
        return res;
        
    }


}
