KAFKA_PATH=kafka_2.13-2.8.0
SH_PATH=${KAFKA_PATH}/bin
CONFIG=${KAFKA_PATH}/config

zookeeper:
	${SH_PATH}/zookeeper-server-start.sh ${CONFIG}/zookeeper.properties

instance-0:
	${SH_PATH}/kafka-server-start.sh ${CONFIG}/server-0.properties

instance-1:
	${SH_PATH}/kafka-server-start.sh ${CONFIG}/server-1.properties

instance-2:
	${SH_PATH}/kafka-server-start.sh ${CONFIG}/server-2.properties

telnet-zookeeper:
	telnet localhost 2181

cleanup:
	rm -rf /tmp/zookeeper && rm -rf /tmp/kafka-logs-*

create-topic:
	${SH_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 3 --topic replica_topic

describe-topic:
	${SH_PATH}/kafka-topics.sh --describe --zookeeper localhost:2181 --topic replica_topic

describe-offsets:
	${SH_PATH}/kafka-topics.sh --describe --zookeeper localhost:2181 --topic __consumer_offsets

producer:
	${SH_PATH}/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic replica_topic

consumer:
	${SH_PATH}/kafka-console-consumer.sh --bootstrap-server localhost:9090 --topic replica_topic --from-beginning

perf-producer:
	${SH_PATH}/kafka-producer-perf-test.sh \
		--topic replica_topic \
		--num-records 50 \
		--record-size 1 \
		--throughput 10 \
		--producer-props \
			bootstrap.servers=localhost:9092 \
			key.serializer=org.apache.kafka.common.serialization.StringSerializer \
			value.serializer=org.apache.kafka.common.serialization.StringSerializer

alter-topic:
	${SH_PATH}/kafka-topics.sh --alter --zookeeper localhost:2181 --topic replica_topic --partitions 4
