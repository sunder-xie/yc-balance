package com.ai.slp.balance.api.sendcoupon.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.balance.api.couponuserule.param.FunCouponUseRuleQueryResponse;
import com.ai.slp.balance.api.sendcoupon.interfaces.ISendCouponSV;
import com.ai.slp.balance.api.sendcoupon.param.DeductionCouponRequest;
import com.ai.slp.balance.api.sendcoupon.param.DeductionCouponResponse;
import com.ai.slp.balance.api.sendcoupon.param.FreezeCouponRequest;
import com.ai.slp.balance.api.sendcoupon.param.FunDiscountCouponResponse;
import com.ai.slp.balance.api.sendcoupon.param.QueryCouCountRequest;
import com.ai.slp.balance.api.sendcoupon.param.QueryCouponRequest;
import com.ai.slp.balance.api.sendcoupon.param.QueryCouponRsponse;
import com.ai.slp.balance.api.sendcoupon.param.SendCouponRequest;
import com.ai.slp.balance.constants.ExceptCodeConstants;
import com.ai.slp.balance.service.business.interfaces.ICouponUseRuleBusiSV;
import com.ai.slp.balance.service.business.interfaces.ISendCouponBusiSV;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class SendCouponSVImpl implements ISendCouponSV {
	/*private static final IOrderQuerySV iOrderQuerySV = DubboUtil.getIOrderQuerySV();*/
    
    @Autowired
    private ISendCouponBusiSV sendCouponBusiSV;
    
    @Autowired
    private ICouponUseRuleBusiSV couponUseRuleBusiSV;
    /**
     * 注册领取优惠券.<br>
     */
	@Override
	public BaseResponse registerSendCoupon(SendCouponRequest param) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try {
			sendCouponBusiSV.registerSendCoupon(param);
			//
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
			responseHeader.setResultMessage("注册领取优惠券成功!");
			response.setResponseHeader(responseHeader);
		}catch (Exception e) {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_ERROR);
			responseHeader.setResultMessage("注册领取优惠券失败!");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}

	/**
	 * 查询可使用的优惠券
	 */
	@Override
	public BaseListResponse<DeductionCouponResponse> queryDisCountCoupon(DeductionCouponRequest param)
			throws BusinessException, SystemException {
		BaseListResponse<DeductionCouponResponse> queryDeducionCoupons = new BaseListResponse<>();
		ResponseHeader responseHeader = new ResponseHeader();
		List<DeductionCouponResponse> deductionCouponResponse = new ArrayList<DeductionCouponResponse>();
		List<DeductionCouponResponse> queryDeducionCoupon = sendCouponBusiSV.queryDisCountCoupon(param);
		for (int i = 0; i < queryDeducionCoupon.size(); i++) {
			String couponUserId = queryDeducionCoupon.get(i).getCouponUserId();
			List<FunCouponUseRuleQueryResponse> queryCouponUseRule = couponUseRuleBusiSV
					.queryCouponUseRule(couponUserId);
			for (int j = 0; j < queryCouponUseRule.size(); j++) {
				Integer requiredMoneyAmount = queryCouponUseRule.get(j).getRequiredMoneyAmount();
				if (param.getTotalFee() >= requiredMoneyAmount) {
					DeductionCouponResponse deductionCouponResponse2 = new DeductionCouponResponse();
					BeanUtils.copyProperties(queryDeducionCoupon.get(i), deductionCouponResponse2);
					deductionCouponResponse.add(deductionCouponResponse2);
				}
			}
		}
		responseHeader.setIsSuccess(true);
		responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
		responseHeader.setResultMessage("查询可用优惠券成功!");
		queryDeducionCoupons.setResponseHeader(responseHeader);
		queryDeducionCoupons.setResult(deductionCouponResponse);
		return queryDeducionCoupons;
	}

	/**
	 * 查询优惠券状态变为解冻
	 */
	@Override
	public BaseResponse updateStateThaw(FreezeCouponRequest param) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		
		try {
			sendCouponBusiSV.updateStateThaw(param);
			//
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
			responseHeader.setResultMessage("状态更改成功!");
			response.setResponseHeader(responseHeader);
		}catch (Exception e) {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_ERROR);
			responseHeader.setResultMessage("状态更改失败!");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}
	/**
	 * 查询优惠券状态变为冻结
	 */
	@Override
	public BaseResponse updateStateFreeze(FreezeCouponRequest param)throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		
		try {
			sendCouponBusiSV.updateStateFreeze(param);
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
			responseHeader.setResultMessage("状态更改成功!");
			response.setResponseHeader(responseHeader);
		}catch (Exception e) {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_ERROR);
			responseHeader.setResultMessage("状态更改失败!");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}
	
	/**
	 * 根据用户ID查询优惠券
	 */
	@Override
	public BaseListResponse<FunDiscountCouponResponse> queryCouponByUserId(SendCouponRequest param) throws BusinessException, SystemException {
		BaseListResponse<FunDiscountCouponResponse> queryCouponByUserIds = new BaseListResponse<>();
		ResponseHeader responseHeader = new ResponseHeader();
		try{
			List<FunDiscountCouponResponse> queryCouponByUserId = sendCouponBusiSV.queryCouponByUserId(param);
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
			responseHeader.setResultMessage("查询优惠券成功!");
			queryCouponByUserIds.setResponseHeader(responseHeader);
			queryCouponByUserIds.setResult(queryCouponByUserId);
		}catch(Exception e){
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_ERROR);
			responseHeader.setResultMessage("查询优惠券失败!");
			queryCouponByUserIds.setResponseHeader(responseHeader);
		}
		return queryCouponByUserIds;
	}

	/**
	 * 抵扣优惠券
	 */
	@Override
	public BaseResponse deducionCoupon(DeductionCouponRequest param) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try {
			sendCouponBusiSV.deducionCoupon(param);
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
			responseHeader.setResultMessage("抵扣成功!");
			response.setResponseHeader(responseHeader);
		} catch (Exception e) {
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_ERROR);
			responseHeader.setResultMessage("抵扣失败!");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}
		/*String couponId = param.getCouponId();
		List<DeductionCouponResponse> deducionCoupon = sendCouponBusiSV.deducionCouponCheck(couponId);
		if (deducionCoupon == null) {
			throw new BusinessException(ExceptCodeConstants.Special.NO_FIND_DISCOUNTCOUPON, "优惠券抵扣失败，优惠券不存在!");
		} else {
			String couponUserId = deducionCoupon.get(0).getCouponUserId();
			List<FunCouponUseRuleQueryResponse> queryCouponUseRule = couponUseRuleBusiSV
					.queryCouponUseRule(couponUserId);
			Integer requiredMoneyAmount = queryCouponUseRule.get(0).getRequiredMoneyAmount();
			if (!param.getUsedScene().equals(deducionCoupon.get(0).getUsedScene())) {
				throw new BusinessException(ExceptCodeConstants.Special.NO_DISCOUNTCOUPON_USEDSCENE,
						"优惠券抵扣失败，此优惠券不符合使用场景限制!");
			} else if (param.getTotalFee() <= requiredMoneyAmount) {
				throw new BusinessException(ExceptCodeConstants.Special.NO_REQYUIREDMONEYAMOUNT,
						"优惠券抵扣失败，此优惠券不符合所消费面额限制!");
			} else if (!param.getOrderType().equals(deducionCoupon.get(0).getUseLimits())
					&& !deducionCoupon.get(0).getUseLimits().equals("0")) {
				throw new BusinessException(ExceptCodeConstants.Special.NO_DISCOUNTCOUPON_USELIMITS,
						"优惠券抵扣失败，此优惠券不符合订单类型的使用规则限制!");
			} else if (deducionCoupon.get(0).getStatus().equals("2")) {
				throw new BusinessException(ExceptCodeConstants.Special.DISCOUNTCOUPON_EFFECT, "优惠券抵扣失败，优惠券已失效!");
			}*/
	/*	}*/
	/**
	 * 优惠券数量查询
	 */
	@Override
	public Integer queryCouponCount(QueryCouCountRequest request)throws BusinessException, SystemException {
		Integer findCouponCount = sendCouponBusiSV.findCouponCount(request);
		ResponseHeader responseHeader = new ResponseHeader();
		if(findCouponCount != null){
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
			responseHeader.setResultMessage("数量查询成功!");
		}
		return findCouponCount;
	}

	/**
	 * 根据用户ID、状态查询优惠券
	 */
	@Override
	public QueryCouponRsponse queryCouponPage(QueryCouponRequest queryCouponRequest)throws BusinessException, SystemException {
		QueryCouponRsponse queryCouponRsponse = new QueryCouponRsponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try {
            PageInfo<DeductionCouponResponse> pageInfo = sendCouponBusiSV.queryCouponPage(queryCouponRequest);
            queryCouponRsponse.setPageInfo(pageInfo);
            responseHeader.setIsSuccess(true);
            responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
            responseHeader.setResultMessage("优惠券模板查询成功");
            queryCouponRsponse.setResponseHeader(responseHeader);
        }catch (BusinessException businessException){
            responseHeader.setResultCode(businessException.getErrorCode());
            responseHeader.setResultMessage(businessException.getErrorMessage());
            queryCouponRsponse.setResponseHeader(responseHeader);
        }catch (Exception e){
            responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_ERROR);
            responseHeader.setResultMessage("优惠券模板查询失败");
            queryCouponRsponse.setResponseHeader(responseHeader);
        }
		return queryCouponRsponse;
	}
	/**
	 * 查询过期的优惠券数量
	 */
	@Override
	public Integer queryCouponOveCount(QueryCouCountRequest request) throws BusinessException, SystemException {
		Integer findCouponCount = sendCouponBusiSV.queryCouponOveCount(request);
		ResponseHeader responseHeader = new ResponseHeader();
		if(findCouponCount != null){
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_SUCCESS);
			responseHeader.setResultMessage("数量查询成功!");
		}
		return findCouponCount;
	}
	
	/*@Override
	public void offLineSendCoupon(int maxCount, String couponName, String userId)
			throws BusinessException, SystemException {
		sendCouponBusiSV.offLineSendCoupon(maxCount, couponName, userId);
	}*/


}
