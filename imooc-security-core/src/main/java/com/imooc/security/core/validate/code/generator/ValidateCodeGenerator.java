package com.imooc.security.core.validate.code.generator;

import javax.servlet.http.HttpServletRequest;

import com.imooc.security.core.validate.code.bean.ValidateCode;
/**
 * 
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 中化能源科技有限公司</p>
 *
 * @author xuming
 * @since：2018年7月31日
 * @version v1.0
 * 验证码生成器
 */
public interface ValidateCodeGenerator {

    ValidateCode createCode(HttpServletRequest request);
}
