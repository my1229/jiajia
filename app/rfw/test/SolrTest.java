package rfw.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import rfw.test.SolrTest.TradeInfo.Item;

public class SolrTest {
    public static void main3(String[] args) {
    }

    public static void main2(String[] args) {
        String urlString = "http://localhost:8983/solr/sinno";
        // solr 客户端
        SolrClient solr = new HttpSolrClient(urlString);
        TradeInfo ti = new TradeInfo();
        ti.setTid(1111l);
        ti.setDescr("aaaa");

        List<Item> items = new ArrayList<SolrTest.TradeInfo.Item>();

        Item i1 = new Item();
        i1.setNumIid(1l);
        i1.setTitle("11aa");
        items.add(i1);
        i1 = new Item();
        i1.setNumIid(2l);
        i1.setTitle("b");
        items.add(i1);
        ti.setItems(items);
        try {
            UpdateResponse response1 = solr.addBean(ti);

            System.out.println("r1:" + response1.getQTime());

            UpdateResponse response2 = solr.commit();

            System.out.println("r2:" + response2.getQTime());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String urlString = "http://localhost:8983/solr/sinno";
        // solr 客户端
        SolrClient solr = new HttpSolrClient(urlString);
        TradeInfo ti = new TradeInfo();
        ti.setTid(111l);
        ti.setDescr("aaaa");
        SolrInputDocument document = new SolrInputDocument();
        // document.add
        document.addField("id", "978-0641723445");
        document.addField("name", "Gouda cheese wheel");
        document.addField("price", "46");
        document.addField("tidinfo", ti);
        try {
            UpdateResponse response1 = solr.add(document);

            // solr.deleteByQuery("_version_:[1497773656859738000 TO *]");

            System.out.println("r1:" + response1.getQTime());

            UpdateResponse response2 = solr.commit();

            System.out.println("r2:" + response2.getQTime());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class TradeInfo implements Serializable {
        @Field("tid_l")
        private Long tid;
        @Field("descr_s")
        private String descr;
        @Field("items")
        private List<Item> items;

        public Long getTid() {
            return tid;
        }

        public void setTid(Long tid) {
            this.tid = tid;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item {
            @Field("num_iid_l")
            private Long numIid;
            @Field("title")
            private String title;

            public Long getNumIid() {
                return numIid;
            }

            public void setNumIid(Long numIid) {
                this.numIid = numIid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "{\"\"" + "}";
            }

        }
    }
}
