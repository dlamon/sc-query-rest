package cn.net.liaowei.sc.query.rest.controller;

import cn.net.liaowei.sc.query.rest.domain.ResultVO;
import cn.net.liaowei.sc.query.rest.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Getter
@Api(tags = "/query/env", description = "环境变量服务")
@RequestMapping("/query/env")
public class EnvQueryController {
    @Value("${env}")
    private String env;

    @GetMapping("/get")
    @ApiOperation("获取环境变量")
    public ResultVO<String> get() {
        return ResultUtil.success(this.getEnv());
    }
}
