package com.example.scalingdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.Map;

@RestController
public class DynamoController {

    private final DynamoDbTable<CounterDoc> counterTable;
    private final DynamoDbClient dynamoClient;

    DynamoController(DynamoDbTable<CounterDoc> counterTable, DynamoDbClient dynamoClient) {
        this.counterTable = counterTable;
        this.dynamoClient = dynamoClient;
    }

    @GetMapping("/counterDoc")
    public CounterDoc getCounter() {
        return counterTable.getItem(Key.builder().partitionValue("my-doc").build());
    }

    @GetMapping("/resetDoc")
    public String resetDoc() {
        counterTable.putItem(new CounterDoc("my-doc", 0));
        return "Reset";
    }

    @GetMapping("/incrementDoc")
    public String incrementDoc() {
        CounterDoc doc = counterTable.getItem(Key.builder().partitionValue("my-doc").build());
        doc.setCounter(doc.getCounter() + 1);
        counterTable.putItem(doc);
        return "Done";
    }

    @GetMapping("/incrementDocUpdate")
    public String incrementDocUpdate() {
        dynamoClient.updateItem(UpdateItemRequest.builder()
                .tableName("counter-table")
                .key(Map.of("docId", AttributeValue.builder().s("my-doc").build()))
                .attributeUpdates(Map.of("counter",
                    AttributeValueUpdate.builder().action(AttributeAction.ADD).value(AttributeValue.builder().n("1").build()).build()
                ))
                .build());
        return "Done";
    }


}
