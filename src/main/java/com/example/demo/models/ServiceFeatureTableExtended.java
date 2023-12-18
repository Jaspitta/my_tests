//package com.example.demo.models;
//
//import com.esri.arcgisruntime.data.ServiceFeatureTable;
//import com.esri.arcgisruntime.geometry.SpatialReference;
//import com.esri.arcgisruntime.io.RequestConfiguration;
//import com.esri.arcgisruntime.layers.FeatureLayer;
//import com.esri.arcgisruntime.layers.TableJoinSublayerSource;
//import com.esri.arcgisruntime.portal.Portal;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//@Component
//@Log4j2
//public class ServiceFeatureTableExtended {
//
//  private Map<Long, Runnable> loaderMap = new HashMap<>();
//
//  /*
//  *
//  * Treating featureTable as a bean allows to avoid reopening connections and/or instantiating the object every time,
//  * however having the object persist means that the listeners will pile up eventually.
//  * This class offer methods to more safely add the runnable listeners and remove them automatically once executed
//  *
//  */
//  @Bean
//  @Primary
//  public ServiceFeatureTable getServiceFeatureTable() {
//    ServiceFeatureTable featureTable = new ServiceFeatureTable("https://services.arcgis.com/zE5LmXYBaMadJkcP/arcgis/rest/services/testing_map/FeatureServer/0");
//
//    addManagedDoneLoadingListener(
//      featureTable,
//      () -> {
//        if(featureTable.getLoadError() != null)
//          log.info("Feature table loaded");
//        else
//          log.info("Error trying to load FeatureTable: ", featureTable.getLoadError().getCause());
//      }
//    );
//
//    featureTable.loadAsync();
//
//    return featureTable;
//  }
//
//  public void addManagedDoneLoadingListener(ServiceFeatureTable featureTable, Runnable runnable){
//
//    Long runnableId = System.currentTimeMillis();
//
//    // Runnable complete of original runnable and deletion runnable
//    Runnable runnableExtended = () -> {
//      runnable.run();
//      // Runnable to start deletion once execution is done.
//      // Async is required otherwise it will try to delete the task once it is in execution
//      CompletableFuture.runAsync( () -> {
//          featureTable.removeDoneLoadingListener(loaderMap.get(runnableId));
//          loaderMap.remove(runnableId);
//        }
//      );
//    };
//
//    loaderMap.put(runnableId, runnableExtended);
//
//    featureTable.addDoneLoadingListener(runnableExtended);
//  }
//
//}
