package com.tian.sakura.cdd.srv.web.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tian.sakura.cdd.db.domain.express.Express;
import com.tian.sakura.video.service.auth.ExpressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "快递公司api" , tags = {"快递公司"})
@RestController
@RequestMapping("express")
public class ExpressController {
	
	@Autowired
	private ExpressService expressService;

	@ApiOperation("快递公司列表")
    @PostMapping("/expressList")
   public List<Express> getExpress() {
		return expressService.listExpress(null);
	}
}
