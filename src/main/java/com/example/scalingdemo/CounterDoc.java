package com.example.scalingdemo;

import org.w3c.dom.css.Counter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class CounterDoc {

    private String docId;
    private Integer counter;

    public CounterDoc() {}

    public CounterDoc(String docId, Integer counter) {
        this.docId = docId;
        this.counter = counter;
    }

    @DynamoDbPartitionKey
    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
