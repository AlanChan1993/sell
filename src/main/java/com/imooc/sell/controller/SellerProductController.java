package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.form.ProductForm;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/*卖家端商品*/
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> productPage = productService.findAll(pageRequest);
        //log.info("【orderDTOPage】 元素,orderDTOPage={}", productPage.getTotalElements());
        //log.info("【orderDTOPage】 页数,orderDTOPage={}",productPage.getTotalPages());
        map.put("productPage", productPage);
        map.put("currentPage",page);
        map.put("currentSize",size);
        return new ModelAndView("product/list", map);//9-2
    }

    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.offSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);

        }
        //查询所有类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("categoryList",productCategoryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo =new ProductInfo();
        try {
            if(!StringUtils.isEmpty(productForm.getProductId())) {
                productService.findOne(productForm.getProductId());
            }else {
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("common/login");
    }

}


