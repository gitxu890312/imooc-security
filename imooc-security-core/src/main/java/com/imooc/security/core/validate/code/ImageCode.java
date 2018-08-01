package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
/**
 * 图形验证码类
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 中化能源科技有限公司</p>
 *
 * @author xuming
 * @since：2018年7月31日
 * @version v1.0
 * 图形验证码
 */
public class ImageCode  extends ValidateCode {

    
    private BufferedImage image;
    
    public ImageCode(BufferedImage image, String code, int expireSeconds) {
        super(code,expireSeconds);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


  

    
}
