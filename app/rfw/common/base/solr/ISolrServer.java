/**
 * 
 */
package yunbei.common.base.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * @author lizhong.chen
 * @date 2014年8月26日下午3:58:27
 * @description solr server
 * @version V1.0
 */

public interface ISolrServer {

    /**
     * 获取userId对应的httpSolrServer
     * 
     * 若userId为null，则取所有的solrServer
     * 
     * @param userId
     * @return
     */
    public List<HttpSolrServer> getSolrServers(Long userId);

    /**
     * 根据hashkey获取solrserver
     * 
     * @param hashkey
     * @return
     */
    public HttpSolrServer getSolrServer(Integer hashkey);

    /**
     * 根据userId 执行solrParams
     * 
     * @param userId
     * @return
     */
    public List<QueryResponse> query(Long userId, ModifiableSolrParams solrParams);

    /**
     * 
     * 查询
     * 
     * @param server
     * @param solrParams
     * @return
     */
    public QueryResponse query(HttpSolrServer server, ModifiableSolrParams solrParams) throws SolrServerException;
}
