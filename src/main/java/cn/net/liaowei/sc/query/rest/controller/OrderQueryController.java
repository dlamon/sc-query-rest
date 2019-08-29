package cn.net.liaowei.sc.query.rest.controller;

import cn.net.liaowei.sc.order.rpc.client.OrderService;
import cn.net.liaowei.sc.order.rpc.common.OrderDTO;
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
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Api(tags = "/query/order", description = "订单服务")
@RequestMapping("/query/order")
public class OrderQueryController {
    @SofaReference(
            binding = @SofaReferenceBinding(
                    bindingType = "bolt",
                    parameters = @SofaParameter(key = ConsulConstants.CONSUL_SERVICE_NAME_KEY, value = "order-rpc")
            ),
            jvmFirst = false
    )
    private OrderService orderService;

    @GetMapping("/list")
    @ApiOperation("获取所有订单")
    public ResultVO<List<OrderDTO>> list(@RequestParam("ids") @NotNull(message="订单编号不能为空")  @ApiParam("订单编号") List<String> ids) {
        List<OrderDTO> orderDTOList = orderService.list(ids);
        return ResultUtil.success(orderDTOList);

    }
}
