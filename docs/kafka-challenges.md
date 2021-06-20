# Part 7 - Kafka Challenges

Confluent is one of the biggest Apache Kafka ecosystem contributors.
And they have addressed many challenges that we might across during its uses.
One of them is the `Kafka Schema Registry`.

## Kafka Schema Registry
- provides serialization and deserialization schema support for
  - [Apache Avro Schema](https://avro.apache.org/docs/current/spec.html)
  - [JSON Schema](https://json-schema.org/)
  - [Protobuf Schema](https://developers.google.com/protocol-buffers/)
- puts Avro serializers and deserializers as a first-class citizen
- provides support for schema registry and version management
- provides RESTful service-based discovery
- provides version compatibility reconciliation

For more information, please visit the [site](https://docs.confluent.io/platform/current/schema-registry/index.html).

## Kafka Connect
Kafka Connect is an api for the developers, intended to make the job of connecting
data sources and targets easier and more consistent. The goal is to standardized on
a common approach for integrating diverse data sources with standard producer and
consumer applications.

Currently there are more than 50 platform connectors available, that are designed to
connect to many different products and services, and its growing day by day.
Confluent itself has created many of these connectors, for the full-list, you can
check it out [here](https://docs.confluent.io/home/connect/kafka_connectors.html).

For more information, please visit the [Apache Kafka Connect](https://kafka.apache.org/documentation/#connect)
and [Confluent Kafka Connect](https://docs.confluent.io/platform/current/connect/index.html).

## Kafka Streams
It is a new client library for real-time, stream-based processing. Any organization who are already
utilizing the kafka, can now have streaming capabilities without having to install, run and maintain
other different platforms. It is the same as producer and consumer client library, by extending it with
kafka streams library, we can utilize stream-based processing capabilities all within the same place.

For more information, please visit the [Apache Kafka Streams](https://kafka.apache.org/documentation/streams/)
and [Confluent Kafka Streams](https://docs.confluent.io/platform/current/streams/index.html).

## Series
- [Part 1 - Kafka Key Terminology](kafka-key-terms.md)
- [Part 2 - Kafka Partitions](kafka-partitions.md)
- [Part 3 - Run a single instance of Apache Kafka](kafka-single-instance.md)
- [Part 4 - Run a multiple instances of Apache Kafka](kafka-multiple-instance.md)
- [Part 5 - Kafka Producer](kafka-producer.md)
- [Part 6 - Kafka Consumer](kafka-consumer.md)
- [Part 8 - Producer/Consumer using Kafka Client](kafka-client.md)
- [Part 9 - Producer/Consumer using Spring Kafka](spring-kafka.md)