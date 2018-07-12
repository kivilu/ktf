/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kivi.framework.echarts.samples.gauge;

import com.kivi.framework.echarts.code.Tool;
import com.kivi.framework.echarts.data.Data;
import com.kivi.framework.echarts.series.Gauge;
import com.kivi.framework.echarts.series.gauge.Detail;
import com.kivi.framework.echarts.util.EnhancedOption;
import org.junit.Test;

/**
 * 
 */
public class GaugeTest1 {

    @Test
    public void test() {
        // 地址： http://echarts.baidu.com/doc/example/gauge1.html
        EnhancedOption option = new EnhancedOption();
        option.tooltip().formatter("{a} <br/>{b} : {c}%");
        option.toolbox().show(true).feature(Tool.mark, Tool.restore, Tool.saveAsImage);
        option.series(new Gauge("业务指标").detail(new Detail().formatter("{value}%")).data(new Data("完成率", 75)));
        option.exportToHtml("guage1.html");
        option.view();
    }
}
