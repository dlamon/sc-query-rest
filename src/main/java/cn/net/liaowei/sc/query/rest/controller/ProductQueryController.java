package cn.net.liaowei.sc.query.rest.controller;

import cn.net.liaowei.sc.product.rpc.client.ProductService;
import cn.net.liaowei.sc.product.rpc.common.ProductDTO;
import cn.net.liaowei.sc.query.rest.domain.ResultVO;
import cn.net.liaowei.sc.query.rest.util.ResultUtil;
import com.alipay.sofa.rpc.registry.consul.ConsulConstants;
import com.alipay.sofa.runtime.api.annotation.SofaParameter;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Api(tags = "/query/product", description = "订单服务")
@RequestMapping("/query/product")
public class ProductQueryController {
    @SofaReference(
            binding = @SofaReferenceBinding(
                    bindingType = "bolt",
                    parameters = @SofaParameter(key = ConsulConstants.CONSUL_SERVICE_NAME_KEY, value = "product-rpc")
            ),
            jvmFirst = false
    )
    private ProductService productService;

    @GetMapping("/on-sale")
    @ApiOperation("获取在售产品列表")
    public ResultVO<List<ProductDTO>> listOnSale() {
        List<ProductDTO> productDTOList = productService.listOnSale();
        return ResultUtil.success(productDTOList);

    }
}
