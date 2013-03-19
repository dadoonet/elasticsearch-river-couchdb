package org.elasticsearch.river.couchdb;

import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class TestMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        XContentBuilder xb = jsonBuilder().prettyPrint()
                .startObject()
                .field("type", "couchdb")
                .startObject("couchdb")
                .field("use_revisions", true)
                .endObject()
                .endObject();

        Node node = NodeBuilder.nodeBuilder().settings(ImmutableSettings.settingsBuilder().put("gateway.type", "local")).node();
        Thread.sleep(1000);
        node.client().prepareIndex("_river", "db", "_meta").setSource(xb).execute().actionGet();
        Thread.sleep(1000000);
    }
}
