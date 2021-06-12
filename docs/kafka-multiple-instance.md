# Kafka Multiple Instance

## Zookeeper
`bin/zookeeper-server-start.sh config/zookeeper.properties`

## Create Configuration for Kafka Instance

- Make a copy of existing `server.properties` files to
  - For instance 0 - `server-0.properties`
  - For instance 1 - `server-1.properties`
  - For instance 2 - `server-2.properties`

- Update `broker.id`
  - For instance 0 - broker.id=0
  - For instance 1 - broker.id=1
  - For instance 2 - broker.id=2

- Add server `listeners`
  - For instance 0 - `listeners=PLAINTEXT://localhost:9090`
  - For instance 1 - `listeners=PLAINTEXT://localhost:9091`
  - For instance 2 - `listeners=PLAINTEXT://localhost:9092`

- Update `log.dirs`
  - For instance 0 - `log.dirs=/tmp/kafka-logs-0`
  - For instance 1 - `log.dirs=/tmp/kafka-logs-1`
  - For instance 2 - `log.dirs=/tmp/kafka-logs-2`

You can also find these configuration in `samples` directory.

## Start Multiple Instance

### Start Instance 0
`bin/kafka-server-start.sh config/server-0.properties`


### Start Instance 1
`bin/kafka-server-start.sh config/server-1.properties`


### Start Instance 2
`bin/kafka-server-start.sh config/server-2.properties`


### Check Broker Registration using Zookeeper
- `telnet localhost 2181`
- type `stat`


## Create Topic
`bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 1 --topic replica_topic`

## Describe Topic
`bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic replica_topic`
Output:
```
Topic: replica_topic    TopicId: 6pL_IeaHRzW_OA4X_in1Og PartitionCount: 1       ReplicationFactor: 3    Configs:
    Topic: replica_topic    Partition: 0    Leader: 0       Replicas: 0,1,2 Isr: 0,1,2
```


## Kafka Console Producer
`bin/kafka-console-producer.sh --broker-list localhost:9092 --topic replica_topic`


## Kafka Console Consumer
`bin/kafka-console-consumer.sh --bootstrap-server localhost:9090 --topic replica_topic --from-beginning`

