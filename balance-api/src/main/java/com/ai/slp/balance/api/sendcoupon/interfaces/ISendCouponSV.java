package com.ai.slp.balance.api.sendcoupon.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.balance.api.sendcoupon.param.DeductionCouponRequest;
import com.ai.slp.balance.api.sendcoupon.param.DeductionCouponResponse;
import com.ai.slp.balance.api.sendcoupon.param.FunDiscountCouponResponse;

/**
 * 优惠券发放接口类 <br>
 *
 */
@Path("/sendCouponService")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ISendCouponSV {

    /**
     * 注册领取优惠券.<br>
     * @param activityName,userId
	 * @return 优惠券生成是否成功
	 * @throws BusinessException,SystemException
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL sendCouponService/registerSendCoupon
     */
	@POST
	@Path("/registerSendCoupon")
    public void registerSendCoupon(String activityName, String userId) throws BusinessException,SystemException;
	
	/**
	 * 查询可使用的优惠券
	 * @param templateId
	 * @return 优惠券
	 * @throws BusinessException,SystemException
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL sendCouponService/queryDisCountCoupon
     */
	@POST
	@Path("/queryDisCountCoupon")
    public List<DeductionCouponResponse> queryDisCountCoupon(DeductionCouponRequest param) throws BusinessException,SystemException;
	
	/**
	 * 抵用优惠券
	 * @param templateId
	 * @return 优惠券
	 * @throws BusinessException,SystemException
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL sendCouponService/deducionCoupon
     */
	@POST
	@Path("/deducionCoupon")
    public void deducionCoupon(DeductionCouponRequest param) throws BusinessException,SystemException;
	
	/**
	 * 查询优惠券状态变为解冻
	 * @throws BusinessException,SystemException
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL sendCouponService/updateStateThaw
     */
	@POST
	@Path("/updateStateThaw")
    public List<FunDiscountCouponResponse> updateStateThaw(String couponId) throws BusinessException,SystemException;
	
	/**
	 * 查询优惠券状态变为冻结
	 * @throws BusinessException,SystemException
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL sendCouponService/updateStateFreeze
     */
	@POST
	@Path("/updateStateFreeze")
    public List<FunDiscountCouponResponse> updateStateFreeze(String couponId) throws BusinessException,SystemException;
	
	/**
	 * 根据用户Id查询优惠券
	 * @param userId
	 * @return 优惠券
	 * @throws BusinessException,SystemException
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL sendCouponService/queryCouponByUserId
     */
	@POST
	@Path("/queryCouponByUserId")
    public List<FunDiscountCouponResponse> queryCouponByUserId(String userId) throws BusinessException,SystemException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * 线下发送/领取优惠券.<br>
     * @throws BusinessException,SystemException
     * @RestRelativeURL sendCouponService/offLineSendCoupon
     */
     /*
	@POST
	@Path("/offLineSendCoupon")
    public void offLineSendCoupon(int maxCount, String couponName, String userId) throws BusinessException,SystemException;*/

}
