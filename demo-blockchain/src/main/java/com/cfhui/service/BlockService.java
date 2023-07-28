package com.cfhui.service;

import com.alibaba.fastjson2.JSON;
import com.cfhui.model.Block;
import com.cfhui.util.BlockCache;
import com.cfhui.model.Transaction;
import com.cfhui.util.CryptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * [区块链核心服务]
 *
 * @author cfhui
 * @version V1
 * @date 2023/7/28 下午 2:08
 */
@Slf4j
@Service
public class BlockService {

    @Autowired
    BlockCache blockCache;

    public String createGenesisBlock() {
        Block genesisBlock = new Block();
        genesisBlock.setIndex(1);
        genesisBlock.setTimestamp(System.currentTimeMillis());
        genesisBlock.setNonce(1);
        List<Transaction> tsaList = new ArrayList<>();
        Transaction tsa = new Transaction();
        tsa.setId("1");
        tsa.setBusinessInfo("这是创世区块");
        tsaList.add(tsa);
        Transaction tsa2 = new Transaction();
        tsa2.setId("2");
        tsa2.setBusinessInfo("区块链高度为：1");
        tsaList.add(tsa2);
        genesisBlock.setTransactions(tsaList);
        // 设置创世区块的hash值
        genesisBlock.setHash(calculateHash("", tsaList, 1));
        // 添加到已打包保存的业务数据集合中
        blockCache.getPackedTransactions().addAll(tsaList);
        // 添加到区块链中
        blockCache.getBlockChain().add(genesisBlock);
        return JSON.toJSONString(genesisBlock);
    }
    /**
     * [ 计算区块的hash ]
     * @param previousHash
     * @param currentTransactions
     * @param nonce
     * @return java.lang.String
     * @author cfhui
     * @since V1
     * @date 2023/7/28 下午 2:17
     */
    public String calculateHash(String previousHash, List<Transaction> currentTransactions, int nonce) {
        return CryptoUtil.SHA256(previousHash + JSON.toJSONString(currentTransactions) + nonce);
    }

    public Block createNewBlock(int nonce, String previousHash, String hash, List<Transaction> blockTxs) {
        Block block = new Block();
        block.setIndex(blockCache.getBlockChain().size() + 1);
        // 时间戳
        block.setTimestamp(System.currentTimeMillis());
        block.setTransactions(blockTxs);
        // 工作量证明， 计算正确hash值的次数
        block.setNonce(nonce);
        // 上一区块的hash
        block.setPreviousHash(previousHash);
        // 当前区块的hash
        block.setHash(hash);
        if (addBlock(block)) {
            return block;
        }
        return null;

    }

    public boolean addBlock(Block newBlock) {
        if (isValidNewBlock(newBlock, blockCache.getLatestBlock())) {
            blockCache.getBlockChain().add(newBlock);
            // 新区块的业务数据需要加入到已打包的业务数据集合去
            blockCache.getPackedTransactions().addAll(newBlock.getTransactions());
            return true;
        }
        return false;
    }

    public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        if (!previousBlock.getHash().equals(newBlock.getPreviousHash())) {
            System.out.println("新区块的前一个区块hash验证不通过");
            return false;
        } else {
            String hash = calculateHash(newBlock.getPreviousHash(), newBlock.getTransactions(), newBlock.getNonce());
            if (!hash.equals(newBlock.getHash())) {
                //log.error("新区块的hash无效：{},{}", hash, newBlock.getHash());
                return false;
            }
            if (!isValidHash(newBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证hash值是否满足系统条件
     *
     * @param hash
     * @return
     */
    public boolean isValidHash(String hash) {
        return hash.startsWith("0000");
    }

    /**
     * 验证整个区块链是否有效
     * @param chain
     * @return
     */
    public boolean isValidChain(List<Block> chain) {
        Block block = null;
        Block lastBlock = chain.get(0);
        int currentIndex = 1;
        while (currentIndex < chain.size()) {
            block = chain.get(currentIndex);

            if (!isValidNewBlock(block, lastBlock)) {
                return false;
            }

            lastBlock = block;
            currentIndex++;
        }
        return true;
    }

    /**
     * 替换本地区块链
     *
     * @param newBlocks
     */
    public void replaceChain(List<Block> newBlocks) {
        List<Block> localBlockChain = blockCache.getBlockChain();
        List<Transaction> localpackedTransactions = blockCache.getPackedTransactions();
        if (isValidChain(newBlocks) && newBlocks.size() > localBlockChain.size()) {
            localBlockChain = newBlocks;
            //替换已打包保存的业务数据集合
            localpackedTransactions.clear();
            localBlockChain.forEach(block -> {
                localpackedTransactions.addAll(block.getTransactions());
            });
            blockCache.setBlockChain(localBlockChain);
            blockCache.setPackedTransactions(localpackedTransactions);
            System.out.println("替换后的本节点区块链："+JSON.toJSONString(blockCache.getBlockChain()));
        } else {
            System.out.println("接收的区块链无效");
        }
    }
}
