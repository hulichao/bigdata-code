package com.hoult.mr.wordcount.speak;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author hulichao
 * @date 20-9-14
 **/
public class SpeakBean implements Writable {

    //定义属性
    private Long selfDuration;//自由内容时长
    private Long thirdPartDuration;//第三方使用时chang
    private String deviceId; //设备id
    private Long sumDuration;//总时长

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.selfDuration);
        dataOutput.writeLong(this.thirdPartDuration);
        dataOutput.writeUTF(this.deviceId);
        dataOutput.writeLong(this.sumDuration);
    }

    //构造
    public SpeakBean() {
    }

    //有参构造
    public SpeakBean(Long selfDuration, Long thirdPartDuration, String deviceId) {
        this.selfDuration = selfDuration;
        this.thirdPartDuration = thirdPartDuration;
        this.deviceId = deviceId;
        this.sumDuration = this.selfDuration + this.thirdPartDuration;
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.selfDuration = dataInput.readLong();
        this.thirdPartDuration = dataInput.readLong();
        this.deviceId = dataInput.readUTF();
        this.sumDuration = dataInput.readLong();
    }

    public Long getSelfDuration() {
        return selfDuration;
    }

    public void setSelfDuration(Long selfDuration) {
        this.selfDuration = selfDuration;
    }

    public Long getThirdPartDuration() {
        return thirdPartDuration;
    }

    public void setThirdPartDuration(Long thirdPartDuration) {
        this.thirdPartDuration = thirdPartDuration;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getSumDuration() {
        return sumDuration;
    }

    public void setSumDuration(Long sumDuration) {
        this.sumDuration = sumDuration;
    }

    @Override
    public String toString() {
        return "SpeakBean{" +
                "selfDuration=" + selfDuration +
                ", thirdPartDuration=" + thirdPartDuration +
                ", deviceId='" + deviceId + '\'' +
                ", sumDuration=" + sumDuration +
                '}';
    }
}
