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

package com.kivi.framework.echarts.samples.treemap;

import com.kivi.framework.echarts.code.Tool;
import com.kivi.framework.echarts.code.Trigger;
import com.kivi.framework.echarts.data.TreeData;
import com.kivi.framework.echarts.series.Treemap;
import com.kivi.framework.echarts.util.EnhancedOption;
import org.junit.Test;

/**
 * 
 */
public class TreemapTest1 {

    @Test
    public void test() {
        //地址:http://echarts.baidu.com/doc/example/treemap.html
        EnhancedOption option = new EnhancedOption();
        option.title().text("手机占有率").subtext("虚构数据");
        option.tooltip().trigger(Trigger.item).formatter("{b}: {c}");
        option.toolbox().show(true).feature(
                Tool.mark,
                Tool.dataView,
                Tool.restore,
                Tool.saveAsImage);
        option.calculable(false);

        Treemap treemap = new Treemap("矩形图");
        treemap.itemStyle().normal().label().show(true).formatter("{b}");
        treemap.itemStyle().normal().borderWidth(1);

        treemap.itemStyle().emphasis().label().show(true);
        treemap.data(new TreeData("三星", 6),
                new TreeData("小米", 4),
                new TreeData("苹果", 4),
                new TreeData("华为", 2),
                new TreeData("联想", 2),
                new TreeData("魅族", 1),
                new TreeData("中兴", 1)
        );

        option.series(treemap);
        option.exportToHtml("treemap.html");
        option.view();
    }
}
