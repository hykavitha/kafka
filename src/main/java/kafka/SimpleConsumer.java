package kafka;

import java.util.Properties;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

public class SimpleConsumer {
  public static void main(String[] args) throws Exception {
//      if(args.length == 0){
//         System.out.println("Enter topic name");
//         return;
//      }
      //Kafka consumer configuration settings
      String topicName =  "kakka-session"; //args[0].toString();
      Properties props = new Properties();
      
      props.put("bootstrap.servers", "localhost:9092");
      props.put("group.id", "test123");
//      props.put("enable.auto.commit", "true");
//      props.put("auto.commit.interval.ms", "1000");
//      props.put("session.timeout.ms", "30000");
      
  	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
	props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

      KafkaConsumer<String, String> consumer = new KafkaConsumer
         <String, String>(props);
      
      //Kafka Consumer subscribes list of topics here.
      consumer.subscribe(Arrays.asList(topicName));
      
      //print the topic name
      System.out.println("Subscribed to topic " + topicName);
      int i = 0;
      
      while (true) {
         ConsumerRecords<String, String> records = consumer.poll(100);
         for (ConsumerRecord<String, String> record : records)
         
         // print the offset,key and value for the consumer records.
         System.out.printf("offset = %d, key = %s, value = %s\n", 
            record.offset(), record.key(), record.value());
      }
   }
}