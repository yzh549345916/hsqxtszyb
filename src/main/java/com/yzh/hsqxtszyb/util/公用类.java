package com.yzh.hsqxtszyb.util;

import org.meteoinfo.data.GridData;

import java.util.stream.IntStream;

public class 公用类 {
    /**
     * @Author YZH
     * @Description //TODO meteoinfo.data.GridData 的初始化方法从3.5版本注释掉了x、y如果不是升序则进行重新排序的代码，导致站点插值报错，临时处理。如果后续修复，则不需要进行此步
     * @Date 15:26 2023/1/13
     * @Param [myGridData]
     * @return void
     **/
    public static void 格点数据排序(GridData myGridData){
        if(myGridData.getXDelta()< 0){
            double[] xArray=myGridData.getXArray();
            double[] xArray2=myGridData.getXArray();
            xArray = IntStream.range(0, xArray.length)
                    .mapToDouble(k-> xArray2[xArray2.length - 1 - k])
                    .toArray();
            myGridData.setXArray(xArray);
            myGridData.xReverse();
        }
        if(myGridData.getYDelta()< 0){
            double[] yArray=myGridData.getYArray();
            double[] yArray2=myGridData.getYArray();
            yArray = IntStream.range(0, yArray.length)
                    .mapToDouble(k-> yArray2[yArray2.length - 1 - k])
                    .toArray();
            myGridData.setYArray(yArray);
            myGridData.yReverse();
        }
    }
}
