package com.example.demo.utils;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//  In most cases V should just be Object to avoid problems on recursive operations
@Data
@Log4j2
public class CustomMapFlattener <V> {

  Map<java.lang.String, V> map;
  String mapName;

  Boolean excludeArrays;
  String separator;


  @Builder
  private CustomMapFlattener(Map<java.lang.String, V> map, String mapName, Boolean excludeArrays, String separator){
    this.map = map;
    this.mapName = mapName;
    this.excludeArrays = excludeArrays != null && excludeArrays;
    this.separator = separator == null || separator.isBlank() ? "." : separator;
  }


  public Map<java.lang.String, V> doFlatten(){
    if(map == null) return null;
    if(map.isEmpty()) return map;

    return this.flatten(
      this.map,
      this.mapName,
      this.excludeArrays,
      this.separator
    );
  }

  private Map<java.lang.String,V> flatten(Map<java.lang.String, V> map, String mapName, Boolean excludeArrays, String separator){
    if(map == null) return null;
    if(map.isEmpty()) return map;

    Map<java.lang.String,V> result = new HashMap<>();

    //  Starting Recursive operations
    map.forEach((k,v) -> {
      // Should be handled with a switch but switches of instance of are not possible on java 11

      if(v instanceof List && !excludeArrays)
        result.putAll(flatten(
          //  Converting the array to a map with index as key and value as value to perform recursion
          (Map<java.lang.String, V>)((List)v).parallelStream().collect(Collectors.toMap(
            (n) -> String.valueOf(((List)v).indexOf(n)), // Index of the value as key
            (n) -> n // Value as value
          )),
          mapName == null || mapName.isBlank() ? k : mapName + separator + k, // Name concatenation
          false,
          separator
        ));

      //  Recustive call to flatten the map inside the map
      else if(v instanceof Map)
        result.putAll(
          flatten(
            (Map<java.lang.String, V>)v,
            mapName == null || mapName.isBlank() ? k : mapName + separator + k, // Name concatenation
            excludeArrays,
            separator
          )
        );

      //  Best case scenario of just moving a record from a place to the other
      //  Would like to remove but that will make not possible to pass Immutable maps with something like Map.ofEntries...
      else
        result.put(
          mapName == null || mapName.isBlank() ? k : mapName + separator + k, // Name concatenation
          map.get(k)
        );

    });

    return result;
  }


}
