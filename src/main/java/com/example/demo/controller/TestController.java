package com.example.demo.controller;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.*;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.example.demo.models.FullChargePointSearchRequest;
import com.example.demo.utils.FunctionalExtensions;
import com.example.demo.utils.ServiceFeatureTableExtended;
import com.example.demo.utils.CustomMapFlattener;
import com.example.demo.utils.RESTClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.cognitoidentity.CognitoIdentityClient;
import software.amazon.awssdk.services.cognitoidentity.model.GetIdRequest;
import software.amazon.awssdk.services.cognitoidentity.model.GetIdResponse;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/esri")
@Log4j2
public class TestController {

//    @Autowired
    ServiceFeatureTable featureTable;

//    @Autowired
    ServiceFeatureTableExtended serviceFeatureTableExtended;

//    @Autowired
//    NoteRepositoryTemplate noteRepositoryTemplate;


    @GetMapping(value = "/caller")
    public String caller(){

      RESTClient<Object, String> client = new RESTClient<>(null, "http://127.0.0.1:8080/esri/called", null, null, new ParameterizedTypeReference<String>() {});

      try{

        ResponseEntity<String> resp = client.executePOST();
        System.out.println("I called the post");
        if(resp == null || resp.getBody() == null){
          return "This is also not good";
        }

        return resp.getBody();

      }catch(Exception e){
        return "Not Good";
      }

    }

    @PostMapping(value = {"/called"})
    public ResponseEntity<?> called(){

      System.out.println("I received the call");

      return new ResponseEntity<>("This is the body",HttpStatus.CONFLICT);

    }


    @GetMapping(value = "/tstingcredentials")
    public String testingCredentials(){
      CognitoIdentityClient client = CognitoIdentityClient.builder().build();
      GetIdResponse getId = client.getId(GetIdRequest.builder().identityPoolId("").build());
      String id = getId.identityId();

      return "some";
    }


    @PostMapping(value = "/testingBody")
    public String testingBody(
      @RequestBody FullChargePointSearchRequest search
      ){


      return "Something happened";
    }

    @GetMapping(value = "/save")
    public String save(){

//      try(
//        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\stefano.scarpitta\\chnet\\demo\\src\\main\\resources\\testing.txt"));
//      ){
//
//        reader.mark(1000000000);
//
//        reader.lines().forEach((l) ->
//          log.info(l)
//        );
//
//        log.info("Bofore async exec: ", reader.ready());
//
//        reader.reset();
//
//        noteRepositoryTemplate.bufferProcessor(reader);
//
//        log.info("After async exec: ", reader.ready());
//
//      }catch (Exception e){
//        log.error("Some some some: ", e);
//      }
//
//      log.info("Am outside of try-with-resource statement, reader is out of scope for me");


//      UserNote userNote = new UserNote(
//        "userId"
//      );
//      userNote.setBody("Body");
//
//      CompanyNote companyNote = new CompanyNote(
//        "companyId"
//      );
//      companyNote.setBody("Body");
//
//      noteRepositoryTemplate.addCompanyNote(companyNote);
//      noteRepositoryTemplate.addUserNote(userNote);

      return "Done";
    }


    @GetMapping(value = {"/add"})
    public String add(){

//      // Call geocodeAsync passing in an address
//      final LocatorTask onlineLocator = new LocatorTask("https://geocode-api.arcgis.com/arcgis/rest/services/World/GeocodeServer");
//      final ListenableFuture<List<GeocodeResult>> geocodeFuture = onlineLocator.geocodeAsync("380 New York Street, Redlands, CA");
//      geocodeFuture.addDoneListener( () -> {
//        try {
//          // Get the results of the async operation
//          List<GeocodeResult> geocodeResults = geocodeFuture.get();
//
//          if (geocodeResults.size() > 0) {
//            // Use the first result - for example display in an existing Graphics Overlay
//            GeocodeResult topResult = geocodeResults.get(0);
//
//          }
//        }
//        catch (InterruptedException | ExecutionException e) {
//          log.error("Exception encountered",e);
//        }
//      });




      checkFeatureTableReadinessAndRun(
        () -> addFeature(featureTable)
      );

      return "Something";
    }

//    @GetMapping(value = {"/addFields"})
//    public String addTable(){
//
//      Field fieldBigger = Field.fromJson(
//        "" +
//          "          \"properties\": {" +
//          "            \"chargepoint\": \"value0\"" +
//          "\"connector\": {" +
//          "\"connectorId\": 1" +
//          "\"supportedCharges\": [ \"1\", \"2\", \"3\" , {" +
//          "\"propAnother\"; \"propAnotherName\"" +
//          "}]" +
//          "}" +
//          "          }"
//        + ""
//      );
//
//      featureTable.getFields().add(fieldBigger);
//
//      applyEdit(featureTable);
//
//      return "Guess what happened ;)";
//    }

    void addFeature(ServiceFeatureTable featureTable){

//      Map<String, Object> flatMap = customMapFlattener(
//        "_",
//        Map.ofEntries(
//          Map.<String, Object>entry("ChargePointId", String.valueOf(System.currentTimeMillis())),
//          Map.<String, Object>entry("userId", String.valueOf(System.currentTimeMillis())),
//          Map.<String, Object>entry("Connector",
//            Map.ofEntries(
//              Map.<String, Object>entry("type", "SUPER_MEGA_FAST_CHARGING"),
//              Map.<String, Object>entry("Location",
//                Map.<String, Object>ofEntries(
//                  Map.<String, Object>entry("PostalCode", "1010101"),
//                  Map.<String, Object>entry("Possibilites", List.of(
//                      "1",
//                      "2",
//                      Map.ofEntries(
//                        Map.entry("ciao", "come"),
//                        Map.entry("stai", "oggi")
//                      )
//                    )
//                  )
//                )
//              )
//            )
//          )
//        )
//      );

      var flatMap = CustomMapFlattener.builder()
        .map(
          Map.ofEntries(
            Map.<String, Object>entry("ChargePointId", String.valueOf(System.currentTimeMillis())),
            Map.<String, Object>entry("userId", String.valueOf(System.currentTimeMillis())),
            Map.<String, Object>entry("Connector",
              Map.ofEntries(
                Map.<String, Object>entry("type", "SUPER_MEGA_FAST_CHARGING"),
                Map.<String, Object>entry("Location",
                  Map.<String, Object>ofEntries(
                    Map.<String, Object>entry("PostalCode", "1010101"),
                    Map.<String, Object>entry("Possibilites", List.of(
                        "1",
                        "2",
                        Map.ofEntries(
                          Map.entry("ciao", "come"),
                          Map.entry("stai", "oggi")
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
        .separator("_")
        .build().doFlatten();

      log.info(flatMap);

      Feature feature = featureTable.createFeature(
        flatMap,
        new Point(90, 90)
      );

        log.info("Adding feature: {}", feature.getAttributes());

        featureTable.addFeatureAsync(feature)
          .addDoneListener(() -> {
              log.info("Done adding the feature locally");
              applyEdit(featureTable);
          });

    }
    void applyEdit(ServiceFeatureTable featureTable){
        ListenableFuture<List<FeatureEditResult>> editResult = featureTable.applyEditsAsync();

        editResult.addDoneListener(() ->
            FunctionalExtensions.runSafeAnyway(
              result -> {
                  var res = result.get();
                  res.parallelStream()
                    .map(String::valueOf)
                    .forEach(log::info);
                  return res;
              },
              editResult,
              null,
              true,
              "Error on applying changes"
            )
        );
    }

    void checkFeatureTableReadinessAndRun(Runnable runnable) {

      if(featureTable != null && LoadStatus.LOADED.equals(featureTable.getLoadStatus()) && featureTable.isEditable()){
        runnable.run();
        return;
      }

      featureTable = serviceFeatureTableExtended.getServiceFeatureTable();

      serviceFeatureTableExtended.addManagedDoneLoadingListener(
        featureTable,
        () -> {
          log.info("Feature Table done loading");
          runnable.run();
        }
      );

      featureTable.loadAsync();

    }


    /**
     *
     * This method convert a map with nested fields and more into the corresponding flat version using dot notation.
     * Arrays also are flattened using their index position
     *
     * @param mapName Optional value that correspond to the name of the map you are passing,
     *                unless the map you are passing is a nested map of something else this should always be null.
     *                If passed it adds this name as the prefix of all the internal fields
     *
     * @param excludeArrays Is an optional boolean that should be omitted unless you want to avoid flattening arrays also
     * @param separator Is simply used as the character for the separation notation. the default is a dot
     *
     * @apiNote
     * Not sure if I should return a different map or change the old one.
     * Returning the old one allow for cleaner and safer code using immutable maps.
     * Changing the passed map allows to use less memory because you do not have to create a new
     *
     * For now I choose the safe option of creating a new map, after al it will live only for the scope of this method
     *
     * */
    <V> Map<java.lang.String,V> customMapFlattener(Map<java.lang.String,V> map, String mapName, Boolean excludeArrays, String separator){
      if(map == null) return null;
      if(map.isEmpty()) return map;

      Map<java.lang.String,V> result = new HashMap<>();

      //  Starting Recursive operations
      //  The function should just flatten a map regardless of where it is coming from
      map.forEach((k,v) -> {

        if(v instanceof List && !excludeArrays)
          result.putAll(customMapFlattener(
            //  Converting the array to a map with index as key and value as value to perform flattening
            (Map<java.lang.String, V>)((List)v).parallelStream().collect(Collectors.toMap(
              (n) -> String.valueOf(((List)v).indexOf(n)),
              (n) -> n
            )),
            mapName == null || "".equals(mapName) ? k : mapName + separator + k, // Name concatenation
            false,
            separator
          ));

        //  Recustive call to flatten the map inside the map
        else if(v instanceof Map)
          result.putAll(
            customMapFlattener(
              (Map<java.lang.String, V>)v,
              mapName == null || "".equals(mapName) ? k : mapName + separator + k, // Name concatenation
              excludeArrays,
              separator
            )
          );

        //  Best case scenario of just moving a record from a place to the other
        //  Would like to remove but that will make not possible to pass Immutable maps with something like Map.ofEntries...
        else result.put(
          mapName == null || "".equals(mapName) ? k : mapName + separator + k, // Name concatenation
          map.get(k)
        );

      });

      return result;
    }

    <V> Map<java.lang.String,V> customMapFlattener(Map<java.lang.String,V> map, Boolean excludeArrays){
      return customMapFlattener(map, null, excludeArrays, ".");
    }

    <V> Map<java.lang.String,V> customMapFlattener(Map<java.lang.String,V> map, String mapName){
      return customMapFlattener(map, mapName, false, ".");
    }

    <V> Map<java.lang.String,V> customMapFlattener(String separator, Map<java.lang.String,V> map){
      return customMapFlattener(map, null, false, separator);
    }

    <V> Map<java.lang.String,V> customMapFlattener(Map<java.lang.String,V> map){
      return customMapFlattener(map, null, false, ".");
    }


}
