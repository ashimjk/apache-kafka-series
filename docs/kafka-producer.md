# Kafka Producer

## Three Major Required Properties
- bootstrap.servers
  - required for producer to start up
  - used for discovering full membership of the cluster
  - used for determining the partition leaders or owners
- key.serializer
- value.serializer

Here is the full list of [producer configs](https://kafka.apache.org/documentation/#producerconfigs)

## ProducerRecord
- topic
  - Topic to which the ProducerRecord will be sent
- value
  - The message content (matching the serializer type for value)
- partition
  - specific partition within the topic to send ProducerRecord
- timestamp
  - Unix timestamp applied to the record
KafkaProducer instances can only send ProducerRecord that match the key and value serializers types it is configured with.
- key
  - a value to be used as the basis of determining the partitioning strategy to be employed by the Kafka Producer
  - Additional information in the message
  - Can determine what partitions the message will be written to

## RecordAccumulator
- Implements Micro-batching pattern
  - Small and fast batches of messages
  - Sending (Producer)
  - Writing (Broker)
  - Reading (Consumer)
- Uses Queue for storing records
- Maintains RecordBatch for each topic partition

## Message Buffering Properties
- batch.size : buffer size per RecordBatch in bytes
- buffer.memory : buffer size of all record batch in bytes
- max.block.ms : time for blocking the send record call
- linger.ms : time to wait before sending record

## Broker Acknowledgement - "acks"
- 0 : fire and forget
- 1 : leader acknowledged
- 2 : replication quorum acknowledged

## Configuration for Broker responds with error
- retries : how many times producer should try to send the record
- retry.backoff.ms : how many milli-seconds does producer needs to wait for retries

## Message Order
- No guarantee of order if multiple partitions are used
- If there is an error while sending record then there may be a chances of un-ordered record
  - Set `max.in.flight.request.per.connection` to 1 if order needs to be maintain

## Delivery Semantics
- at-most-once
- at-least-once
- only-once

## Kafka Producer Internals
- Properties ~> ProducerConfig
- Message ~> ProducerRecord
- Processing Pipeline ~> Serializers and Partitioners
- Micro-batching ~> RecordAccumulator and RecordBuffer
