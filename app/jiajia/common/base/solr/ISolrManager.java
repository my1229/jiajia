/**
 * 
 */
package jiajia.common.base.solr;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;

/**
 * @author lizhong.chen
 * @date 2014年8月26日上午9:55:28
 * @description solr
 * @version V1.0
 */

public interface ISolrManager {

    /**
     * 优化core
     * 
     * @param route
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public UpdateResponse optimizeCore(int route) throws SolrServerException, IOException;

    /**
     * 根据区间段进行全量导入
     * 
     * @param route
     * @param clean
     * @param startIndex
     * @param endIndex
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public QueryResponse dataImportFromDB(int route, boolean clean, Long startIndex, Long endIndex)
            throws SolrServerException, IOException;

    /**
     * 获取数据导入状态
     * 
     * @param route
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public String getDataImportStatus(int route) throws SolrServerException, IOException;

    /**
     * 对route表下userId进行增量操作
     * 
     * @param route
     * @param clean
     * @param userId
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public QueryResponse deltaImportFromDBByUserId(int route, boolean clean, Long userId, long lastDeltaTime)
            throws SolrServerException, IOException;

    /**
     * 获取增量导入的状态
     * 
     * @param route
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public String getDeltaImportStatus(int route) throws SolrServerException, IOException;

}
