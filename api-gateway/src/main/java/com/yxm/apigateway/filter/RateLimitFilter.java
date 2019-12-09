package com.yxm.apigateway.filter;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.yxm.apigateway.exception.RateLimitException;
import org.springframework.stereotype.Component;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;
/**
 * @author yxm
 * @date 2019/7/13 10:27:27
 */
@Component
public class RateLimitFilter extends ZuulFilter {
    /**
     * 令牌桶的实现已经有人实现了:我们只需要调用就行
     * com.google.common.util.concurrent.RateLimiter
     * @return
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);
    @Override
    public String filterType() {
        /** 限流肯定用的是PRE_TYPE */
        return PRE_TYPE;
    }
    @Override
    public int filterOrder() {
        /**优先级为最高:组件自带的优先级最高位-3 我们这边比他还高*/
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }
    @Override
    public boolean shouldFilter() {
        return false;
    }
    @Override
    public Object run() throws ZuulException {
        /**run具体实现:如果没有拿到令牌返回结果(抛出一个自定义的异常)*/
        if(!RATE_LIMITER.tryAcquire()){
            throw new RateLimitException();
        }
        return null;
    }
}
