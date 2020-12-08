package com.dl.shejimoshi.strategy;

import org.hamcrest.core.Is;

/**
 * 妙计2
 */
public class GivenGreenLight implements Istrategy {
    @Override
    public void oprate() {
        System.out.println("让吴国开绿灯");
    }
}
