/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.common.extension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记扩展接口
 * <p />
 *扩展配置文件的更改<br/>
 *使用<code> Protocol </ code>作为示例，其配置文件'META-INF / dubbo / com.xxx.Protocol'更改为：<br/>
 * <pre>
 *     com.foo.XxxProtocol
 *     com.foo.YyyProtocol
 * </pre>
 * <p>
 * to key-value pair <br/>
 * <pre>
 *     xxx=com.foo.XxxProtocol
 *     yyy=com.foo.YyyProtocol
 * </pre>
 * <br/>
 * 这种变化的原因是：
 * <P>
 *如果静态字段引用的第三方库或扩展实现中的方法，其类将
 *如果第三方库不存在，则无法初始化。在这种情况下，dubbo无法找出扩展名的id
 *因此，如果使用以前的格式，则无法使用扩展名映射异常信息。
 * <p />
 * For example:
 * <p>
 * 无法加载扩展（“mina”）。当用户配置使用mina时，dubbo会抱怨扩展无法加载，
 *而不是报告哪些提取扩展实现失败和提取原因。
 * </p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * default extension name
     */
    String value() default "";

}