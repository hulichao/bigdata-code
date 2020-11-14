## 查看topic list
kafka-topics.sh --zookeeper localhost:2181/myKafka --list
## 创建topic,必须指定分区和副本，副本数要大于等于broker数
kafka-topics.sh --zookeeper localhost/myKafka --create --topic topic_1  --partitions 3 --replication-factor 1

## 查看topic具体信息
kafka-topics.sh --zookeeper localhost/myKafka --describe --topic topic_1

## 消费者消费消息
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic_1
--from-beginning # 指定重新开始消费

## 生产者发送消息
kafka-console-producer.sh --broker-list localhost:9092 --topic topic_1