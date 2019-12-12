package com.yxm.product.server.controller;
import com.yxm.product.common.DecreaseStockInput;
import com.yxm.product.server.entity.ProductCategory;
import com.yxm.product.server.entity.ProductInfo;
import com.yxm.product.server.service.ProductCategoryService;
import com.yxm.product.server.service.ProductService;
import com.yxm.product.server.utils.ResultVOUtil;
import com.yxm.product.server.vo.ProductInfoVO;
import com.yxm.product.server.vo.ProductVO;
import com.yxm.product.server.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @author yxm
 * @date 2019/4/14 13:00:00
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;
    /**
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构建数据
     */
     @GetMapping("/list")
     /**
      * 允许跨域访问
      */
     @CrossOrigin(allowCredentials = "true")
    public ResultVO<ProductVO> list(HttpServletRequest request){
        //1.获取所有商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        //3.查询类目
        List<ProductCategory> categoryList =  productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //4.构建数据
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            //获取数据产品
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVoList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表(给订单服务使用)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList){
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        productService.decreaseStock(decreaseStockInputList);
    }

}

























































