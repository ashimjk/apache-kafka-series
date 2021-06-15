## Startup
First, you need to download [Apache Kafka](https://downloads.apache.org/kafka/2.8.0/kafka_2.13-2.8.0.tgz).
And extract it to your desired folder.

If you go inside the Kafka directory, something like this `kafka_2.13-2.8.0`,
you will more likely see `config`, `bin`, `lib`, etc directories.
But for this series, we will only focus on the `config` and `bin` directory.

## Configuration
These are the two configuration files that reside inside the `config` directory,
that we will use to start the Zookeeper and the Kafka.
- zookeeper.properties
- server.properties

## Series
- [Part 1 - Kafka Key Terminology](kafka-key-terms.md)
- [Part 2 - Kafka Partitions](kafka-partitions.md)
- [Part 3 - Run a single instance of Apache Kafka](kafka-single-instance.md)
- [Part 4 - Run a multiple instances of Apache Kafka](kafka-multiple-instance.md)
- [Part 5 - Kafka Producer](kafka-producer.md)
- [Part 6 - Kafka Consumer](kafka-consumer.md)
- [Part 7 - Kafka Challenges](kafka-challenges.md)
- [Part 8 - Producer/Consumer using Kafka Client](producer-consumer-using-kafka-client.md)
