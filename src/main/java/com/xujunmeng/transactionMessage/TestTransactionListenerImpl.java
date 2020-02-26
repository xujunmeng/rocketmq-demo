package com.xujunmeng.transactionMessage;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author james
 * @date 2020/2/25
 */
public class TestTransactionListenerImpl implements TransactionListener {

    private ConcurrentHashMap<String, Integer> countHashMap = new ConcurrentHashMap<>();

    private final static int MAX_COUNT = 5;

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {

        //从消息中获取业务唯一ID
        String bizUniNo = message.getUserProperty("bizUniNo");

        //将bizUniNo入库，表名：t_message_transaction，表结构：bizUniNo(主键)，业务类型

        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

        Integer status = 0;

        //从数据库查询 t_message_transaction表，如果该表中存在记录，则提交
        //从消息中获取业务唯一ID
        String bizUniNo = messageExt.getUserProperty("bizUniNo");

        //然后查询t_message_transaction表，是否存在bizUniNO，如果存在，则返回COMMIT_MESSAGE
        //不存在，则记录查询次数，未超过次数，返回UNKNOW；超过次数，返回ROLLBACK_MESSAGE
        if (query(bizUniNo) > 0) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        return rollBackOrUnknow(bizUniNo);
    }

    private int query(String bizUniNo) {
        //select count(1) from t_message_transaction where biz_uni_no = #{bizUniNo}
        return 1;
    }

    private LocalTransactionState rollBackOrUnknow(String bizUniNo) {
        Integer num = countHashMap.get(bizUniNo);
        if (num != null && ++num > MAX_COUNT) {
            countHashMap.remove(bizUniNo);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        if (num == null) {
            num = 1;
        }
        countHashMap.put(bizUniNo, num);

        return LocalTransactionState.UNKNOW;
    }
}
