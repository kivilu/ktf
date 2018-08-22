/**
 * 
 */
package com.kivi.framework.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kivi.framework.component.ApplicationKit;
import com.kivi.framework.component.KtfProperties;
import com.kivi.framework.constant.GlobalErrorConst;
import com.kivi.framework.db.dao.BaseDao;
import com.kivi.framework.enums.KtfServiceStatus;
import com.kivi.framework.exception.AppException;
import com.kivi.framework.persist.mapper.KtfApplicationMapperEx;
import com.kivi.framework.persist.model.KtfApplication;
import com.kivi.framework.service.KtfNameService;
import com.kivi.framework.util.CommonUtils;
import com.kivi.framework.util.kit.DateTimeKit;
import com.kivi.framework.util.kit.StrKit;

/**
 * @author Eric
 *
 */
@Service( "ktfNameService" )
@DependsOn( value = { "springContextHolder", "tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration" } )
public class KtfApplicationImpl extends BaseDao<KtfApplication> implements KtfNameService {

    private static final Logger           log                  = LoggerFactory.getLogger(KtfApplicationImpl.class);

    // 状态更新周期5分钟
    private static final int              STATUS_UPDATE_SECOND = 5 * 60;

    private static volatile AtomicInteger localSequence        = new AtomicInteger(10000000);

    private static String                 seqPrefix            = "";

    private KtfApplication                application          = null;

    private List<KtfApplication>          servicelist          = null;

    @Autowired
    private KtfProperties                 ktfProperties;

    @Autowired
    private KtfApplicationMapperEx        ktfApplicationMapperEx;

    public KtfApplicationImpl() {

    }

    @PostConstruct
    private void startup() throws Exception {
        log.info("应用开始启动");
        String path = ktfProperties.getCommon().getSidDir();
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();

        String myidPath = StrKit.join("/", path, "sid");
        myidPath = StrKit.join(".", myidPath, ApplicationKit.me().getServerPort());
        log.info("保存myid，路径:{}", myidPath);

        File fmyid = new File(myidPath);
        if (!fmyid.exists()) {
            // myid不存在，需要注册
            KtfApplication entity = null;
            entity = new KtfApplication();
            entity.setName(ApplicationKit.me().getAppcationName());
            entity.setHost(CommonUtils.geLocaltHostIp());
            entity.setPort(ApplicationKit.me().getServerPort());

            // 注册application
            application = this.registService(entity);

            // 写入myid文件
            JSON.writeJSONString(new FileOutputStream(fmyid), application);
        }
        else {
            application = JSON.parseObject(new FileInputStream(fmyid), KtfApplication.class);

            this.updateServiceStatus(KtfServiceStatus.online);
        }

        //
        // Timer time = new Timer();
        // time.schedule(new ServiceStatusTask(), STATUS_UPDATE_SECOND * 1000,
        // STATUS_UPDATE_SECOND * 1000);
    }

    @PreDestroy
    private void stoping() {
        log.info("应用开始停止");
        this.updateServiceStatus(KtfServiceStatus.offline);
    }

    @Override
    public int index() {
        String name = application.getName();
        List<Short> list = ktfApplicationMapperEx.listApplicationSlotId(name);
        int pos = list.indexOf(application.getSlotId());
        return pos;
    }

    public int count( String name ) {
        KtfApplication entity = new KtfApplication();
        entity.setName(name);

        return super.count(entity);
    }

    @Override
    public int countOnline() {
        KtfApplication entity = new KtfApplication();
        entity.setName(application.getName());
        entity.setStatus(KtfServiceStatus.online.getCode());

        return super.count(entity);
    }

    @Override
    public String nameAndId() {
        return StrKit.join("-", application.getName(), application.getSid());
    }

    @Override
    public String getUniqueqId() {
        return StrKit.join("", application.getSlotId(), ApplicationKit.me().nextId());
    }

    private List<KtfApplication> listOnlineService() {
        KtfApplication entity = new KtfApplication();
        entity.setName(this.application.getName());
        entity.setStatus(KtfServiceStatus.online.getCode());

        List<KtfApplication> list = super.selectByEntity(entity);

        return list;
    }

    private KtfApplication registService( KtfApplication app ) {
        if (StrKit.isBlank(app.getName())) {
            log.error("应用service的名称为空");
            throw new AppException(GlobalErrorConst.E_PARAM_IS_NULL);
        }

        app.setSid(UUID.randomUUID().toString());
        app.setSlotId((short) this.count(app.getName()));
        app.setStatus(KtfServiceStatus.online.getCode());
        app.setGmtCreate(DateTimeKit.date());
        app.setGmtUpdate(app.getGmtCreate());

        return super.saveNotNull(app);
    }

    private void updateServiceStatus( KtfServiceStatus status ) {

        KtfApplication updateEntity = new KtfApplication();
        updateEntity.setId(this.application.getId());
        updateEntity.setStatus(status.getCode());
        updateEntity.setGmtUpdate(DateTimeKit.date());

        super.updateNotNull(updateEntity);
    }

    private void updateServiceStatus( Long id, KtfServiceStatus status ) {

        KtfApplication updateEntity = new KtfApplication();
        updateEntity.setId(id);
        updateEntity.setStatus(status.getCode());
        updateEntity.setGmtUpdate(DateTimeKit.date());

        super.updateNotNull(updateEntity);
    }

    @SuppressWarnings( "unused" )
    @Deprecated
    private class ServiceStatusTask extends TimerTask {

        @Override
        public void run() {
            // 更新service状态
            updateServiceStatus(KtfServiceStatus.online);

            servicelist = listOnlineService();
            Iterator<KtfApplication> it = servicelist.iterator();
            while (it.hasNext()) {
                KtfApplication s = it.next();
                long minute = DateTimeKit.diff(s.getGmtUpdate(), DateTimeKit.date(), DateTimeKit.SECOND_MS);
                if (minute > STATUS_UPDATE_SECOND) {
                    updateServiceStatus(s.getId(), KtfServiceStatus.offline);
                }
            }
        }

    }

}
