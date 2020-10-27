package com.zbcn.socket.self;

import java.io.IOException;

/**
 *  选举信息测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/13 9:34
 */
public class Main {

    public static void main(String[] args) {
        //投票请求
        VoteMsg vote = new VoteMsg(false,false, 1, 0);

        //查询投票请求
        VoteMsg select = new VoteMsg(true,true, 1, 10);

        VoteMsgTextCoder voteMsgTextCoder = new VoteMsgTextCoder();

        try {
            byte[] bytes = voteMsgTextCoder.toWire(vote);
            VoteMsg voteMsg = voteMsgTextCoder.fromWire(bytes);
            System.out.println(voteMsg.toString());

            byte[] selectBytes = voteMsgTextCoder.toWire(select);

            VoteMsg selectMsg = voteMsgTextCoder.fromWire(selectBytes);
            System.out.println(selectMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
