package io.mycat.replica.heartbeat;

import io.mycat.MycatProxyBeanProviders;
import io.mycat.config.datasource.ReplicaConfig;
import io.mycat.replica.MySQLReplica;

/**
 * @author jamie12221
 *  date 2019-05-17 13:11
 **/
public class MySQLReplicaEx extends MySQLReplica {


  /**
   * 初始化mycat集群管理
   * @param replicaConfig
   * @param writeIndex
   * @param dataSourceFactory
   */
  public MySQLReplicaEx(ReplicaConfig replicaConfig, int writeIndex,
      MycatProxyBeanProviders dataSourceFactory) {
    super(replicaConfig, writeIndex, dataSourceFactory);
  }

  public boolean switchDataSourceIfNeed() {
      return super.switchDataSourceIfNeed();
  }
}
