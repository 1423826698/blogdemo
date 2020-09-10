package com.kyx.blog.service.impl;

import com.kyx.blog.entity.Guest;
import com.kyx.blog.entity.Repguest;
import com.kyx.blog.mapper.GuestMapper;
import com.kyx.blog.mapper.RepGuestMapper;
import com.kyx.blog.service.GuestService;
import com.kyx.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    GuestMapper guestMapper;
    @Autowired
    RepGuestMapper repGuestMapper;
    @Override
    public List<Guest> AllGuest() {
        List<Guest> list = guestMapper.allGuest();
        System.out.println(list);
        return list;
    }

    @Override
    public List<Guest> insertGuest(Guest guest) {
            TimeUtil timeUtil =new TimeUtil();
            guest.setId(timeUtil.getLongTime());
            guest.setCreateTime(timeUtil.currentPrecisionTime());
            guest.setIsRead(1);
            return guestMapper.insert(guest)>0? AllGuest():null;
    }

    @Override
    public List<Guest> insertRepGuest(Repguest guest) {
        TimeUtil timeUtil =new TimeUtil();
        guest.setRid(timeUtil.getLongTime());
        guest.setRcreateTime(timeUtil.currentPrecisionTime());
        guest.setRisRead(1);
        return repGuestMapper.insert(guest)>0? AllGuest():null;
    }
}
