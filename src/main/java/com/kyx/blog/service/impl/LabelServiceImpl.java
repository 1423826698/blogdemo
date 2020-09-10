package com.kyx.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kyx.blog.entity.Label;
import com.kyx.blog.entity.vo.BlogMessageVoEntity;
import com.kyx.blog.mapper.LabelMapper;
import com.kyx.blog.service.AsyncService;
import com.kyx.blog.service.LabelService;
import com.kyx.util.Constant;
import com.kyx.util.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    AsyncService asyncService;
    @Autowired
    LabelMapper labelMapper;
    @Autowired
    RedisOperate redisOperate;
    @Override
    public int insLabel(String[] label) {
        int flag = 0;
        for (int i =0;i< label.length;i++){
            Integer result = labelMapper.selectCount(new QueryWrapper<Label>().eq("label_name",label[i]));
            if (result == 0){
                Label tags =new Label();
                String id = UUID.randomUUID().toString();
                tags.setId(id);
                tags.setLabelName(label[i]);
                flag =labelMapper.insert(tags);
                redisOperate.lpush(Constant.LABEL_ALL,tags);
            }
        }

        return flag;
    }

    @Override
    public List<Label> queryList() {
        List<Label> list =null;
        if (redisOperate.hasKey(Constant.LABEL_ALL)){
            list =(List<Label>) redisOperate.range(Constant.LABEL_ALL,0,-1);
        }else {
            list =labelMapper.selectList(null);
            if (list !=null){
                asyncService.insLabelName(list);
            }
        }
        return list;
    }


}
