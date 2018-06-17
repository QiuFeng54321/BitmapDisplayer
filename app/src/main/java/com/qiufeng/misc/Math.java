package com.qiufeng.misc;
import java.math.*;
public class Math{
	public static BigDecimal PI(int n) {
        final int PRECISION = n+1;//计算精度
        final int THENUMBEROFCIRCLES = n-7;//循环次数
        BigDecimal PI = new BigDecimal(0);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < THENUMBEROFCIRCLES; i++) {
            //PI=PI+1/16^i(4/(8i+1)-2/(8i+4)-1/(8n+5)-1/(8n+6))
            PI = PI.add((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(16).pow(i))).multiply((BigDecimal.valueOf(4)
																							   .divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(1)), PRECISION, BigDecimal.ROUND_DOWN))//ROUND_DOWN接近零的舍入模式（截取）  
																							   .subtract(BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(4)), PRECISION,BigDecimal.ROUND_DOWN))
																							   .subtract((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(5)), PRECISION,BigDecimal.ROUND_DOWN)))
																							   .subtract((BigDecimal.valueOf(1).divide(BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(i)).add(BigDecimal.valueOf(6)), PRECISION,BigDecimal.ROUND_DOWN)))));
        }
		return PI.setScale(n, BigDecimal.ROUND_DOWN);
    }
}
