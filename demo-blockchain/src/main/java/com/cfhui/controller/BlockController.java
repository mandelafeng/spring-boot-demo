package com.cfhui.controller;

import com.alibaba.fastjson2.JSON;
import com.cfhui.service.BlockService;
import com.cfhui.service.PowService;
import com.cfhui.util.BlockCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class BlockController {

  @Resource
  BlockService blockService;

  @Autowired
  BlockCache blockCache;

    @Autowired
    PowService powService;

  /**
   * 查看当前节点区块链数据
   * @return
   */
  @GetMapping("/scan")
  @ResponseBody
  public String scanBlock() {
    return JSON.toJSONString(blockCache.getBlockChain());
  }

  /**
   * 创建创世区块
   * @return
   */
  @GetMapping("/create")
  @ResponseBody
  public String createFirstBlock() {
    blockService.createGenesisBlock();
    return JSON.toJSONString(blockCache.getBlockChain());
  }
    /**
     * 工作量证明PoW
     * 挖矿生成新的区块
     */
    @GetMapping("/mine")
    @ResponseBody
    public String createNewBlock() {
        powService.mine();
        return JSON.toJSONString(blockCache.getBlockChain());
    }
 }
