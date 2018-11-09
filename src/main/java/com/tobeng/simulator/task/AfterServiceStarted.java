package com.tobeng.simulator.task;

import com.tobeng.simulator.message.Image;
import com.tobeng.simulator.message.ImageInfoObject;
import com.tobeng.simulator.message.Record;
import com.tobeng.simulator.producer.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @package com.tobeng.simulator.task
 * @date 2018/11/8
 */
@Component
@Slf4j
public class AfterServiceStarted implements ApplicationRunner {

    @Autowired
    private MessageProducer messageProducer;

    @Value("#{'${data.list.deviceId}'.split(',')}")
    private List<String> deviceId;
    @Value("#{'${data.list.plateNo}'.split(',')}")
    private List<String> plateNo;
    @Value("#{'${data.list.laneId}'.split(',')}")
    private List<Integer> laneId;
    @Value("${data.time.interval}")
    private Integer interval;

    private String MotorVehicleID = "100100010314010000250220181102101045920";
    private Integer rand = 100000000;
    private static int i =0;

    /**
     * 服务启动后立即执行
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        Random random = new Random();
        while (true){

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = format.format(new Date());
            Record record = new Record();
            record.setId(i);
            record.setDescOfFrontItem(date);
            record.setAppearTime(date);
            record.setPassTime(date);
            record.setEnqueueTime(date);
            rand++;
            record.setMotorVehicleID(MotorVehicleID + rand);
            int plateInt = random.nextInt(plateNo.size());
            record.setPlateNo(plateNo.get(plateInt));

            Image image = new Image();
            List list = new ArrayList();
            list.add(new ImageInfoObject());
            list.add(new ImageInfoObject());
            image.setSubImageInfoObject(list);

            int deviceInt = random.nextInt(deviceId.size());
            record.setTollgateID(deviceId.get(deviceInt));
            int laneInt = random.nextInt(laneId.size());
            record.setLaneNo(laneId.get(laneInt));

            messageProducer.send(record);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("数量：{}", i);
            i++;
        }
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    private void count(){
        i = 0;
    }
}
