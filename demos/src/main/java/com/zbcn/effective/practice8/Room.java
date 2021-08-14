package com.zbcn.effective.practice8;

import java.util.stream.Stream;

/**
 * An autocloseable class using a cleaner as a safety  net
 *
 * @author likun
 * @since 2021/8/4 16:45
 */
public class Room implements AutoCloseable{

    // resourse that requires cleaning. must not refer to room
    private static class State implements Runnable{

        // number of junk piles in this room
        int numJunkPiles;

        public State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("cleaning room");
            numJunkPiles = 0;
        }
    }

    // the state of this room, shared with our cleanable
    private  State state;


    @Override
    public void close() throws Exception {

    }

    public static void main(String[] args) {

        StringBuilder reduce = Stream.of("aa", "bb", "eee").reduce(new StringBuilder(), (builder, str) -> {
            if (builder.length() > 0) {
                builder.append(",");
            }
            builder.append(str);
            return builder;
        }, (left, right) -> left.append(right));
        System.out.println(reduce.toString());
    }
}
