# Part 1 - Kafka Key Terminology

This section focuses on the key terminology and concepts about Apache Kafka.
And also note that each has its contribution to Apache Kafka Architecture.

## Distributed Messaging System

## Producers

## Consumers

## Topics
- name

## Broker or Worker or Node

## Cluster
- size

## Controller Election
- Worker availability
- Health
- Task Redundancy

## Leader
- Three pair
- Quorum
- 3 Replicas

## Communication and Consensus
- Worker node membership and naming
- Configuration management - startup-config
- Leader election
- Health status

## Zookeeper
- Broker management
- Maintaining metadata
  - Configuration information
  - Health Status
  - Group membership
- Add `4lw.commands.whitelist=*` in `zookeeper.properties` to whitelist command like `stat`, `conf`
- It is the responsibility of the Zookeeper to assign a broker to be responsible for the topic
- Check the status of zookeeper using `telnet localhost 2181`

## Topic message order
- Ordered sequence (by time)
- Immutable events

## Event Souring

## Message Content
- Timestamp
- Reference-able identifier
- Payload (binary)

## Offset
- Last read message position
- Maintained by the Kafka consumer
- Corresponds to the message identifier

## Message Retention Policy
- Default is 168 hours or seven days
- Retention period is defined on a per-topic basis
- Physical storage can constrain message retention

## Distributed Commit Log (Transaction or Commit Logs)
- Source of truth
- Physically stored and maintained
- Higher-order data structures derived from the log
  - Tables, indexes, views, etc
- Point of recovery
- Basis for replication and distribution


## Kafka Partitions
- Partitions == Log
- Each topic has one or more log files called partitions
- Basis for
  - Scaling
  - Fault-tolerant
  - Higher levels of throughput
- At-least one partition is required for one or more broker
- It is the physical representation of the topic as a commit log stored on one or more broker
- Directory structure `/tmp/kafka-logs/{topic}-{partition}` is `/tmp/kafka-logs/my_topic-0`
  - Contains index, log files


## Replication Factor
- Reliable work distribution
  - Redundancy of messages
  - Cluster resiliency
  - Fault-tolerance
- Guarantees
  - N-1 broker failure tolerance
  - 2 or 3 minimum
- Configured on a per-topic basis
- Broadcast In-Sync Replicas or ISR to the cluster is equal to the replication factor for that topic and partition

## Notes
- The scalability of Apache Kafka is determined by the number of partitions being managed by multiple broker nodes

## Series
- [Part 2 - Kafka Partitions](kafka-partitions.md)
- [Part 3 - Run a single instance of Apache Kafka](kafka-single-instance.md)
- [Part 4 - Run a multiple instances of Apache Kafka](kafka-multiple-instance.md)
- [Part 5 - Kafka Producer](kafka-producer.md)
- [Part 6 - Kafka Consumer](kafka-consumer.md)
