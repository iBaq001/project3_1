package com.amr.project.mapper;

import com.amr.project.model.entity.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CouponMapper {

    Long couponToLong(Coupon coupon) {
        return coupon.getId();
    }
    Coupon couponIdToCoupon (Long id){
        Coupon coupon =  new Coupon();
        coupon.setId(id);
        return coupon;
    }

}
