/**
 * 
 */
package rfw.common.base.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.Play;

/**
 * @author lizhong.chen
 * @date 2014年8月26日上午9:59:06
 * @description sorl 连接 管理器
 * @version V1.0
 */

public class SolrManagerImpl implements ISolrManager {
    private final static Logger LOG = LoggerFactory.getLogger(SolrManagerImpl.class);

    private static SolrManagerImpl instance = new SolrManagerImpl();

    private SolrManagerImpl() {
    }

    public static SolrManagerImpl getInstance() {
        return instance;
    }

    private ISolrServer solrServer = SolrServer.getInstance();

    /**
     * 
     * @author lizhong.chen
     * @date 2014年8月26日上午9:59:06
     * @description solr 服务器状态
     * @version V1.0
     */
    public static enum ServerStatus {
        /**
         * 空闲状态
         */
        IDLE(1, "idle", "idle"),
        /**
         * 忙碌状态
         */
        BUSY(2, "busy", "busy");
        private ServerStatus(int index, String valueCn, String valueEn) {
            this.index = index;
            this.valueCn = valueCn;
            this.valueEn = valueEn;
        }

        private int index;
        private String valueCn;
        private String valueEn;

        public int getIndex() {
            return index;
        }

        public String getValueCn() {
            return valueCn;
        }

        public String getValueEn() {
            return valueEn;
        }

    }

    @Override
    public UpdateResponse optimizeCore(int route) throws SolrServerException, IOException {
        HttpSolrServer hss = solrServer.getSolrServer(route);
        UpdateResponse response = hss.optimize();
        hss.commit();
        return response;
    }

    @Override
    public QueryResponse dataImportFromDB(int route, boolean clean, Long startIndex, Long endIndex)
            throws SolrServerException, IOException {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("qt", "/dataimport");
        params.set("command", "full-import");
        params.set("clean", clean);
        params.set("startIndex", startIndex != null ? startIndex + "" : 0 + "");
        params.set("endIndex", endIndex != null ? endIndex + "" : 100000 + "");
        HttpSolrServer hss = solrServer.getSolrServer(route);

        QueryResponse response = hss.query(params);
        hss.commit();
        return response;
    }

    @Override
    public String getDataImportStatus(int route) throws SolrServerException, IOException {
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("qt", "/dataimport");
        params.set("command", "status");
        params.set("indent", "true");
        params.set("wt", "json");
        HttpSolrServer hss = solrServer.getSolrServer(route);
        QueryResponse response = hss.query(params);
        NamedList nl = response.getResponse();
        Object obj = nl.get("status");
        if (obj != null) {
            return (String) obj;
        }
        return "busy";
    }

    @Override
    public QueryResponse deltaImportFromDBByUserId(int route, boolean clean, Long userId, long lastDeltaTime)
            throws SolrServerException, IOException {
        ModifiableSolrParams params = new ModifiableSolrParams();
        if ("weiddtest".equals(Play.id)) {
            params.set("qt", "/dataimportbyuseridwei");
        } else {
            params.set("qt", "/dataimportbyuserid");
        }
        params.set("command", "delta-import");
        params.set("clean", clean);
        params.set("lastDeltaTime", "" + lastDeltaTime);
        if (userId != null) {
            params.set("userId", userId + "");
        }
        HttpSolrServer hss = solrServer.getSolrServer(route);
        QueryResponse response = hss.query(params);
        hss.commit();
        return response;
    }

    @Override
    public String getDeltaImportStatus(int route) throws SolrServerException, IOException {
        ModifiableSolrParams params = new ModifiableSolrParams();
        if ("weiddtest".equals(Play.id)) {
            params.set("qt", "/dataimportbyuseridwei");
        } else {
            params.set("qt", "/dataimportbyuserid");
        }
        params.set("command", "status");
        params.set("indent", "true");
        params.set("wt", "json");
        HttpSolrServer hss = solrServer.getSolrServer(route);
        QueryResponse response = hss.query(params);
        NamedList nl = response.getResponse();
        Object obj = nl.get("status");
        if (obj != null) {
            return (String) obj;
        }
        return "busy";
    }

    public static void main(String[] args) throws SolrServerException, IOException {
        // SolrManagerImpl manager = new SolrManagerImpl();
        // manager.init();
        // IMPORT
        // QueryResponse qr = manager.dataImportFromDBByUserId(false, 0l, 420l,
        // 531639775l, 10l);
        // QueryResponse qr = manager.deltaImportFromDBByUserId(false,
        // 531639775l);
        // if (qr.getResponse() != null) {
        // Map info = (Map) qr.getResponse().get("statusMessages");
        // if (info != null) {
        // Object fetch = info.get("Total Rows Fetched");
        // Object num = info.get("Total Documents Processed");
        // Object timeTaken = info.get("Time taken");
        // log.warn(qr.toString());
        // log.warn("last update info: fetch:" + fetch +
        // " Total Documents Processed:" + num + " Time taken:"
        // + timeTaken);
        // }
        // }
        // List<CrmMemberPlay> memberList = manager.getByUserId(false,
        // 531639775l,
        // " (neutralRateCount_i:0) AND (badRateCount_i:0) ", 0, 10);
        // if (CollectionUtils.isNotEmpty(memberList)) {
        // for (CrmMemberPlay crmMemberPlay : memberList) {
        // System.out.println(crmMemberPlay);
        // }
        // }
        // SolrManagerImpl solrManager = SolrManagerImpl.instance;
        // QueryResponse qr = solrManager.dataImportFromDB(route, false,
        // 2130000l, 2140000l);
        // Map info = (Map) qr.getResponse().get("statusMessages");
        // if (info != null) {
        // Object fetch = info.get("Total Rows Fetched");
        // Object num = info.get("Total Documents Processed");
        // Object timeTaken = info.get("Time taken");
        // LOG.warn(qr.toString());
        // LOG.warn("last update crmmember" + route + " info fetch:" + fetch +
        // " Total Documents Processed:" + num
        // + " Time taken:" + timeTaken);
        // }

        ISolrServer solrServer = SolrServer.getInstance();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("userId_l:1694587614");
        solrServer.getSolrServer(14).deleteByQuery("userId_l:1694587614");

        // String yd =
        // "mobiles_s:*134* OR mobiles_s:*135* OR mobiles_s:*136* OR mobiles_s:*137* "
        // +
        // "OR mobiles_s:*138* OR mobiles_s:*139* OR mobiles_s:*150* OR mobiles_s:*151* "
        // + "OR mobiles_s:*152* OR mobiles_s:*158* OR mobiles_s:*159*";
        // String lt =
        // "mobiles_s:*130* OR mobiles_s:*131* OR mobiles_s:*132* OR mobiles_s:*155* "
        // +
        // "OR mobiles_s:*156* OR mobiles_s:*157* OR mobiles_s:*185* OR mobiles_s:*186*";
        // String dy =
        // "mobiles_s:*133* OR mobiles_s:*153* OR mobiles_s:*180* OR mobiles_s:*189*";
        // String[] dd = new String[] {
        // "mobiles_s:*134* OR mobiles_s:*135* OR mobiles_s:*136* OR mobiles_s:*137* "
        // +
        // "OR mobiles_s:*138* OR mobiles_s:*139* OR mobiles_s:*150* OR mobiles_s:*151* "
        // + "OR mobiles_s:*152* OR mobiles_s:*158* OR mobiles_s:*159*",
        // "mobiles_s:*130* OR mobiles_s:*131* OR mobiles_s:*132* OR mobiles_s:*155* "
        // +
        // "OR mobiles_s:*156* OR mobiles_s:*157* OR mobiles_s:*185* OR mobiles_s:*186*",
        // "mobiles_s:*133* OR mobiles_s:*153* OR mobiles_s:*180* OR mobiles_s:*189*"
        // };
        // for (int i = 0; i < 3; i++) {
        // long count = 0;
        // for (int route = 0; route < 16; route++) {
        // SolrResponse<CrmMemberPlay> response = null;
        // try {
        // SolrQuery solrQuery = new SolrQuery();
        // solrQuery.setQuery(dd[i]);
        //
        // QueryResponse qr = solrServer.getSolrServer(route).query(solrQuery);
        //
        // response = new SolrResponse<CrmMemberPlay>(qr, beanType);
        //
        // long numFound = response.getNumFound();
        // count += numFound;
        // LOG.info("route:{},count:{}", route, numFound);
        // } catch (SolrServerException e) {
        // LOG.error("getByUserIdAndFilterQueryString", e);
        // }
        // }
        // LOG.info("dd:{},total:{}", i, count);
        // }
    }

}
