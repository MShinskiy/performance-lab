//Максим Шинский

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class task3 {

    //JSON
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();


    //write updated tests to json
    public static void writeTest(Test test, Path path) {
        try(BufferedWriter bw = Files.newBufferedWriter(path)) {
            GSON.toJson(test, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read TESTS from json
    public static Test readTest(String file){
        Test t = null;
        Path path = Paths.get(file);
        try(BufferedReader br = Files.newBufferedReader(path)){
            Type type = new TypeToken<Test>(){}.getType();
            t = GSON.fromJson(br, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        return t;
    }
    //read VALUES from json
    public static Map<Integer, String> readValues(String file){
        Value v = null;
        Map<Integer, String> valMap = new HashMap<>();
        Path path = Paths.get(file);
        try(BufferedReader br = Files.newBufferedReader(path)){
            Type type = new TypeToken<Value>(){}.getType();
             v = GSON.fromJson(br, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        //Create a map for easier lookup
        for (Value val : v.getValues()) valMap.put(val.getId(), val.getValue());
        return valMap;
    }

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Usage: java task3 <file1.json> <file2.json>");
            System.exit(1);
        }

        //args[0] - tests, args[1] - values
        Test tests = readTest(args[0]);
        Map<Integer, String> valMap = readValues(args[1]);

        //update the values in "tests" according to value in "values"
        for (Test test: tests.getTests()){
            test.updateTest(valMap);
        }

        //write updated "tests" to report.json
        Path path = Paths.get("report.json");
        writeTest(tests, path);
    }
}

class Test {
    private Integer id;
    private String title, value;
    private Test[] values, tests;

    //initial deserialization
    public Test(Test[] tests){
        this.tests = tests;
    }

    //tests WITHOUT subtests
    public Test(int id, String title, String value){
        this.id = id;
        this.title = title;
        this. value = value;
    }

    //tests WITH subtests
    public Test(int id, String title, String value, Test[] values){
        this.values = values;
        this.id = id;
        this.title = title;
        this. value = value;
    }

    //setters
    public void setValue(String value){
        this.value = value;
    }

    //getters
    public Test[] getTests(){
        //null safety
        if(tests != null) return this.tests;
        else return new Test[0];
    }

    //updates (recursive to iterate through subtests)
    public void updateTest( Map<Integer, String> valMap){
        //recursively update subtests
        if(values != null) updateSubtest(valMap);
        //set new value with matching id
        this.setValue(valMap.get(id));
    }

    public void updateSubtest( Map<Integer, String> valMap){
        for ( Test t : values) t.updateTest(valMap);
    }
}

class Value {
    private Integer id;
    private String value;
    private Value[] values;

    //initial deserialization
    public Value(Value[] values){
        this.values = values;
    }

    //value constructor
    public Value(int id, String value){
        this.id = id;
        this.value = value;
    }

    //getters
    public int getId(){
        return this.id;
    }

    public String getValue(){
        return this.value;
    }

    public Value[] getValues(){
        //null safety
        if(values != null) return this.values;
        else return new Value[0];
    }
}
