/**
 * 
 */
package yunbei.common.base.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lizhong.chen
 * @date 2014年8月26日下午4:10:02
 * @description solr server
 * @version V1.0
 */

public class SolrServer implements ISolrServer {

    private static final Logger LOG = LoggerFactory.getLogger(SolrServer.class);

    // taoci02
    // private static String serverUrl =
    // "http://223.4.49.216:30002/solr/crmmember";

    // time01
    // private static String serverUrl =
    // "http://223.4.51.131:30002/solr/crmmember";

    // time01:http://223.4.51.131:30002/solr/crmmember

    private static String serverUrl = "http://121.199.171.143:30002/solr/crmmember";

    // private static String serverUrl = SolrConfigs.SOLR_SERVER_URL +
    // "/solr/crmmember";

    private static SolrServer instance = new SolrServer();

    private SolrServer() {
    }

    public static SolrServer getInstance() {
        return instance;
    }

    private static Map<Integer, HttpSolrServer> servers = new HashMap<Integer, HttpSolrServer>();

    @Override
    public List<HttpSolrServer> getSolrServers(Long userId) {
        List<HttpSolrServer> servers = new ArrayList<HttpSolrServer>();
        if (userId != null) {
            servers.add(getSolrServer((int) (userId % 16)));
        } else {
            for (int i = 0; i < 16; i++) {
                servers.add(getSolrServer(i));
            }
        }
        return servers;
    }

    @Override
    public synchronized HttpSolrServer getSolrServer(Integer hashkey) {
        HttpSolrServer server = servers.get(hashkey);
        if (server == null) {
            String surl = serverUrl + hashkey;
            server = new HttpSolrServer(surl);
            server.setSoTimeout(60000);
            server.setConnectionTimeout(600000);
            server.setDefaultMaxConnectionsPerHost(3000);
            server.setMaxTotalConnections(2000);
            server.setFollowRedirects(false);
            server.setAllowCompression(true);
            server.setMaxRetries(1);
            servers.put(hashkey, server);
        }

        // tomcat 身份验证
        // if (SolrConfigs.SOLR_AUTH_ENABLE) {
        // HttpClientUtil.setBasicAuth((DefaultHttpClient)
        // server.getHttpClient(), SolrConfigs.SOLR_AUTH_USERNAME,
        // SolrConfigs.SOLR_AUTH_PASSWORD);
        // }

        if (true) {
            HttpClientUtil.setBasicAuth((DefaultHttpClient) server.getHttpClient(), "solradmin", "yunbei123solr");
        }

        return server;
    }

    @Override
    public List<QueryResponse> query(Long userId, ModifiableSolrParams solrParams) {
        List<QueryResponse> qrs = new ArrayList<QueryResponse>();

        if (userId != null) {
            HttpSolrServer httpSolrServer = getSolrServer((int) (userId % 16));
            QueryResponse qr;
            try {
                qr = query(httpSolrServer, solrParams);
                if (qr != null) {
                    qrs.add(qr);
                }
            } catch (SolrServerException e) {
                LOG.error(e.getMessage(), e);
            }
        } else {
            for (int i = 0; i < 16; i++) {
                HttpSolrServer httpSolrServer = getSolrServer(i);
                QueryResponse qr;
                try {
                    qr = query(httpSolrServer, solrParams);
                    if (qr != null) {
                        qrs.add(qr);
                    }
                } catch (SolrServerException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        return qrs;
    }

    @Override
    public QueryResponse query(HttpSolrServer server, ModifiableSolrParams solrParams) throws SolrServerException {
        return server.query(solrParams);
    }

}
