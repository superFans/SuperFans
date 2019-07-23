package  com.springboot.maven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.maven.entity.TReportTemplate;
import com.springboot.maven.entity.TSigninRule;
import com.springboot.maven.mapper.TSigninRuleMapper;
import com.springboot.maven.service.ITSigninRuleService;
import com.springboot.maven.service.utlis.MapUtils;
import com.springboot.maven.service.utlis.UUIDS;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 签到规则 服务实现类
 * </p>
 *
 * @author dancer
 * @since 2019-07-19
 */
@Service
public class TSigninRuleServiceImpl extends ServiceImpl<TSigninRuleMapper, TSigninRule> implements ITSigninRuleService {

    @Autowired
    TSigninRuleMapper tsigninRuleMapper;

    /**
     *
     *
     * 新增签到规则
     * @param req
     * @return
     */
    @Override
    public Map<String,Object> insertSingRule (HttpServletRequest req){
        try {
            Map<String,Object> map = new HashMap<>();
            if(MapUtils.mapIsAnyBlank(map,"company_id","dept_id","center_lng","center_lat","center_name","distance","start_time","finish_time")){
             return  null;
            }
            TSigninRule tsr = new TSigninRule();
            tsr.setCenterLat(map.get("center_lat").toString());
            tsr.setCenterLng(map.get("center_lng").toString());
            tsr.setCompanyId(map.get("company_id").toString());
            tsr.setCreateTime(System.currentTimeMillis());
            tsr.setDeleted(0);
            tsr.setDeptId(Integer.valueOf(map.get("dept_id").toString()));
            tsr.setDistance(Integer.valueOf(map.get("distance").toString()));
            tsr.setFinishTime(Long.valueOf(map.get("finish_time").toString()));
            tsr.setId(UUIDS.getID());
            tsr.setStartTime(Long.valueOf(map.get("start_time").toString()));
            tsr.setCenterName(map.get("center_name").toString());
           int singRule = tsigninRuleMapper.insert(tsr);
           if (singRule>0){
               return  map;
           }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return null;

    }
    /**
     *
     *
     * s删除签到规则
     * @param id
     * @return
     */
    @Override
    public Map<String,Object> deleteSingRule (String id){
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            if(StringUtils.isAllBlank(id)){
                return  null;
            }
            TSigninRule tsr = new TSigninRule();
            tsr.setId(id);
            int singRule = tsigninRuleMapper.deleteById(id);
            if (singRule>0){
                map.put("code", 0);
                return map;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return null;

    }


    /**
     *
     *
     * 修改签到规则
     * @param req
     * @return
     */
    @Override
    public Map<String,Object> updateSingRule (HttpServletRequest req){
        try {
            Map<String,Object> map = new HashMap<>();
            if(MapUtils.mapIsAnyBlank(map,"id","center_lng","center_lat","center_name","distance","start_time","finish_time")){
                return  null;
            }
            TSigninRule tsr = new TSigninRule();
            tsr.setId(map.get("id").toString());
            TSigninRule singRule = tsigninRuleMapper.selectById(tsr);
            if(singRule==null){
                return  null;
            }
            tsr.setCenterLat(map.get("center_lat").toString());
            tsr.setCenterLng(map.get("center_lng").toString());
            tsr.setModifyTime(System.currentTimeMillis());
            tsr.setDeptId(Integer.valueOf(map.get("dept_id").toString()));
            tsr.setDistance(Integer.valueOf(map.get("distance").toString()));
            tsr.setFinishTime(Long.valueOf(map.get("finish_time").toString()));
            tsr.setStartTime(Long.valueOf(map.get("start_time").toString()));
            tsr.setCenterName(map.get("center_name").toString());
            int singRuleCode = tsigninRuleMapper.updateById(tsr);
            if (singRuleCode>0){
                return  map;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return null;
    }


    /**
     *
     *
     * 部门签到规则列表
     * @param dept_id
     * @return
     */
    @Override
    public  Map<String,Object> signinRuleListByDeptId (String dept_id){

        try {
            QueryWrapper<TSigninRule> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TSigninRule::getDeleted, dept_id);
            queryWrapper.lambda().orderByDesc(TSigninRule::getCreateTime);
            List<TSigninRule> singRule = tsigninRuleMapper.selectList(queryWrapper);
            if(singRule==null&&singRule.size()>0){
                return  null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return null;

    }







}
