package com.example.basehttp.Manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Create BY Luck-S ON 11:26
 * Email: fine9987@163.com
 * Package:com.example.basehttp.Manager
 * Description:
 */
public class UrlManager {
    private volatile static UrlManager instance;

    private Map<String, String> urlMap;

    public static String DEFAULT_URL_KEY = "rx_default_url_key";

    public static UrlManager getInstance() {
        if (instance == null) {
            synchronized (UrlManager.class) {
                if (instance == null) {
                    instance = new UrlManager();
                }
            }
        }
        return instance;
    }

    private UrlManager() {
        urlMap = new HashMap<>();
    }

    /**
     * 一次性传入urlMap
     *
     * @param urlMap map
     * @return UrlManager
     */
    public UrlManager setMultipleUrl(Map<String, String> urlMap) {
        this.urlMap = urlMap;
        return this;
    }

    /**
     * 向map中添加url
     *
     * @param urlKey   key
     * @param urlValue value
     * @return UrlManager
     */
    public UrlManager addUrl(String urlKey, String urlValue) {
        urlMap.put(urlKey, urlValue);
        return this;
    }

    /**
     * 从map中删除某个url
     *
     * @param urlKey 需要删除的urlKey
     * @return UrlManager
     */
    public UrlManager removeUrlByKey(String urlKey) {
        urlMap.remove(urlKey);
        return this;
    }

    /**
     * 针对单个baseUrl切换的时候清空老baseUrl的所有信息
     *
     * @param urlValue url
     * @return UrlManager
     */
    public UrlManager setUrl(String urlValue) {
        urlMap.put(DEFAULT_URL_KEY, urlValue);
        return this;
    }

    /**
     * 获取全局唯一的baseUrl
     *
     * @return url
     */
    public String getUrl() {
        return getUrlByKey(DEFAULT_URL_KEY);
    }

    /**
     * 根据key
     *
     * @param urlKey 获取对应的url
     * @return url
     */
    public String getUrlByKey(String urlKey) {
        return urlMap.get(urlKey);
    }

    /**
     * 清空设置的url相关的所以信息
     * 相当于重置url
     * 动态切换生产测试环境时候调用
     *
     * @return UrlManager
     */
    public UrlManager clear() {
        urlMap.clear();
//        ApiFactory.getInstance().clearAllApi();
//        HttpUtils.removeAllCookie();
        return this;
    }
}
