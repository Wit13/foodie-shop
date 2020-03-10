package top.wycfight.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.wycfight.pojo.*;
import top.wycfight.pojo.vo.CommentLevelCountsVO;
import top.wycfight.pojo.vo.ItemsInfoVO;
import top.wycfight.service.ItemService;
import top.wycfight.utils.JSONResult;

import java.util.List;

/**
 * @author: wycfight@163.com
 * @description:
 * @create: 2020-03-08 15:49
 * @modify By:
 **/
@RestController
@Api(tags = "用于商品相关的接口", value = "商品")
@RequestMapping("items")
public class ItemsController {


    @Autowired
    private ItemService itemService;


    /**
     * 获取商品信息详情
     *
     * @return
     */
    @GetMapping("info/{itemId}")
    @ApiOperation(value = "获取商品信息详情", notes = "获取商品信息详情", httpMethod = "GET")
    public JSONResult info(@ApiParam(name = "itemId", value = "商品ID")
                               @PathVariable(value = "itemId") String itemId) {
        if (itemId == null) {
            return JSONResult.errorMsg("商品ID为null");
        }
        ItemsInfoVO itemsInfoVO = new ItemsInfoVO();
        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImgList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);

        itemsInfoVO.setItem(items);
        itemsInfoVO.setItemImgList(itemsImgs);
        itemsInfoVO.setItemParams(itemsParam);
        itemsInfoVO.setItemSpecList(itemsSpecs);
        return JSONResult.ok(itemsInfoVO);
    }


    /**
     * 获取评价总数
     *
     * @return
     */
    @GetMapping("commentLevel")
    @ApiOperation(value = "获取评价总数", notes = "获取评价总数", httpMethod = "GET")
    public JSONResult commentLevel(@ApiParam(name = "itemId", value = "商品ID")
                           @RequestParam(value = "itemId") String itemId) {
        if (itemId == null) {
            return JSONResult.errorMsg("商品ID为null");
        }

        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommnetCounts(itemId);

        return JSONResult.ok(commentLevelCountsVO);
    }


}