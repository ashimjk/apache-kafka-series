# Kafka Single Instance

In order to follow the below sections, you need to setup Apache Kafka. And for that, you can follow the steps define in [Setup](README.md).

After the installation, go to the kafka directory and follow though the steps to create a topic, start producing and consuming the messages.


## Kafka Topic

### Create
`bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic my_topic`

### List
`bin/kafka-topics.sh --list --zookeeper localhost:2181`

### View
`bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic my_topic`


## Kafka Console Producer
`bin/kafka-console-producer.sh --broker-list localhost:9092 --topic my_topic`


## Kafka Console Consumer
`bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my_topic --from-beginning`


## Kafka Message Log
- Stored in `/tmp/kafka-logs`
- For above topic, `my_topic-0` directory will be created
- View log using `cat /tmp/kafka-logs/my_topic-0/00000000000000000000.log`

