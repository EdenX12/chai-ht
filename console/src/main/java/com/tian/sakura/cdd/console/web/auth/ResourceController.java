package com.tian.sakura.cdd.console.web.auth;


import com.github.pagehelper.PageInfo;
import com.tian.sakura.cdd.db.domain.auth.TConResource;
import com.tian.sakura.cdd.db.domain.auth.vo.ResourceQueryVo;
import com.tian.sakura.cdd.service.auth.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 维护资源基本信息
 *
 * @author lvzonggang
 * @Date 2019/4/4
 */
@RestController
@RequestMapping("api")
public class ResourceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("findByPage")
    public PageInfo<TConResource> findByPaged(@RequestBody ResourceQueryVo queryVo) {
        return resourceService.findPaged(queryVo);
    }

    @RequestMapping("add")
    public void add(@RequestBody TConResource resource) {
        resourceService.addResource(resource);
    }

    @RequestMapping("update")
    public void update(@RequestBody TConResource resource) {
        resourceService.updateResource(resource);
    }

    @RequestMapping("delete/{id}")
    public void delete(@PathVariable("id") String id) {
        resourceService.logicDeleteResource(id);
    }
}
