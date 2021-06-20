# Part 3 - Run a single instance of Apache Kafka

To follow the below sections, you need to set up the Apache Kafka.
And for that, you can follow the steps define in [startup section](index.md).

After the installation, go to the Kafka directory, something like this `kafka_2.13-2.8.0`
and follow through the steps to create a topic, start producing and consuming the messages.

## Kafka Topic

### Create
`bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic my_topic`

### List
`bin/kafka-topics.sh --list --zookeeper localhost:2181`

### View
`bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic my_topic`


## Kafka Console Producer
`bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic my_topic`


## Kafka Console Consumer
`bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic --from-beginning`


## Kafka Message Log
- Stored in `/tmp/kafka-logs`
- For the above topic, `my_topic-0` directory will be created
- View log using `cat /tmp/kafka-logs/my_topic-0/00000000000000000000.log`

## Series
- [Part 1 - Kafka Key Terminology](kafka-key-terms.md)
- [Part 2 - Kafka Partitions](kafka-partitions.md)
- [Part 4 - Run a multiple instances of Apache Kafka](kafka-multiple-instance.md)
- [Part 5 - Kafka Producer](kafka-producer.md)
- [Part 6 - Kafka Consumer](kafka-consumer.md)
- [Part 7 - Kafka Challenges](kafka-challenges.md)
- [Part 8 - Producer/Consumer using Kafka Client](kafka-client.md)
- [Part 9 - Producer/Consumer using Spring Kafka](spring-kafka.md)
