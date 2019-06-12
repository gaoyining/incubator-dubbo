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

import org.apache.dubbo.common.URL;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 为{@link ExtensionLoader}提供有用信息以注入依赖项扩展实例。
 *
 * @see ExtensionLoader
 * @see URL
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Adaptive {
    /**
     * 确定要注入的目标扩展名。目标扩展名由传递的参数决定
     *在URL中，参数名称由此方法给出。
     * <p>
     * 如果未在{@link URL}中找到指定的参数，则将使用默认扩展名
     *依赖注入（在其接口的{@link SPI}中指定）。
     * <p>
     * For example, given <code>String[] {"key1", "key2"}</code>:
     * <ol>
     * <li>find parameter 'key1' in URL, use its value as the extension's name</li>
     * <li>try 'key2' for extension's name if 'key1' is not found (or its value is empty) in URL</li>
     * <li>use default extension if 'key2' doesn't exist either</li>
     * <li>otherwise, throw {@link IllegalStateException}</li>
     * </ol>
     * 如果参数名称为空，则从接口生成默认参数名称
     *带有规则的类名：将classname从capital char分成几个部分，并将部分分开
     * dot'。'，例如，对于{@code org.apache.dubbo.xxx.YyyInvokerWrapper}，生成的名称是
     * <code> String [] {“yyy.invoker.wrapper”} </ code>。
     *
     * @return parameter names in URL
     */
    String[] value() default {};

}