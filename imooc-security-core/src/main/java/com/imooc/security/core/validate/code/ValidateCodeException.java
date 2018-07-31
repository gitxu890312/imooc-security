package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;
/**
 * 验证码错误异常
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 中化能源科技有限公司</p>
 *
 * @author xuming
 * @since：2018年7月31日
 * @version v1.0
 *
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
