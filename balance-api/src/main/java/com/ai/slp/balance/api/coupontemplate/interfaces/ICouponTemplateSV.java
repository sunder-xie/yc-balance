package com.ai.slp.balance.api.coupontemplate.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.balance.api.coupontemplate.param.CouponTemplateParam;
import com.ai.slp.balance.api.coupontemplate.param.DeleteFunCouponTemplate;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponDetailPageResponse;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponDetailQueryRequest;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponTemplateQueryRequest;
import com.ai.slp.balance.api.coupontemplate.param.FunCouponTemplateQueryResponse;
import com.ai.slp.balance.api.coupontemplate.param.SaveFunCouponTemplate;

/**
 * 优惠券模板接口
 */
@Path("/couponTemplateQueryService")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICouponTemplateSV {


	/**
     * 优惠券模板查询.<br>
     * @param param
     * @return 模板列表
     * @throws BusinessException,SystemException
     * @author shancc
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL couponTemplateQueryService/queryFunCouponTemplate
     */
	@POST
	@Path("/queryFunCouponTemplate")
    public FunCouponTemplateQueryResponse queryFunCouponTemplate(FunCouponTemplateQueryRequest param) throws BusinessException,SystemException;
	
	
	/**
	 * 根据优惠券模板ID查询优惠券明细
	 * @param templateId
	 * @return 模板明细列表
	 * @throws BusinessException,SystemException
	 * @author shancc
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL couponTemplateQueryService/queryCouponDetail
     */
	@POST
	@Path("/queryCouponDetail")
    public FunCouponDetailPageResponse queryCouponDetail(FunCouponDetailQueryRequest param) throws BusinessException,SystemException;
	
	
	/**
	 * 检测名称唯一
	 * @param couponName
	 * @throws BusinessException,SystemException
	 * @author shancc
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL couponTemplateQueryService/checkCouponTemplateName
     */
	@POST
	@Path("/checkCouponTemplateName")
    public Integer checkCouponTemplateName(CouponTemplateParam couponName) throws BusinessException,SystemException;
	
	/**
	 * 添加优惠券模板
	 * @param couponName
	 * @throws BusinessException,SystemException
	 * @author shancc
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL couponTemplateQueryService/savaCouponTemplate
     */
	@POST
	@Path("/savaCouponTemplate")
	public BaseResponse savaCouponTemplate(SaveFunCouponTemplate req)throws BusinessException,SystemException;
	
	/**
	 * @param couponName
	 * @throws BusinessException,SystemException
	 * @author shancc
	 * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL couponTemplateQueryService/deleteCouponTemplate
     */
	@POST
	@Path("/deleteCouponTemplate")
	public Integer deleteCouponTemplate(DeleteFunCouponTemplate param)throws BusinessException,SystemException;

}

