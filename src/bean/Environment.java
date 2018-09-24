package bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 保存温度、湿度、光照强度、二氧化碳浓度
 * @author liangyumim
 * @created 2018年9月20日 下午2:27:20
 */
public class Environment implements Serializable {
	private static final long serialVersionUID = 1424661156233521782L;
	
	private String srcId;
	private String dstId;
	private String devId;
	private String sersorAddress;
	private int count;
	private String cmd;
	private String name;
	private float data;
	private int status;
	private Timestamp gather;
	
	/**
	 * 
	 * @param srcId 发送端id
	 * @param dstId 树莓派系统id
	 * @param devId 实验箱区域模块id(1-8)
	 * @param sersorAddress 模块上传感器地址
	 * @param count 传感器个数
	 * @param cmd 指令标号(3表示需要接受数据  16表示需要发送数据)
	 * @param name 数据项名
	 * @param data 数据
	 * @param status 状态标示(默认为1表示成功)
	 * @param gather 采集时间(单位时秒)
	 */
	public Environment(String srcId, String dstId, String devId, String sersorAddress, int count, String cmd,
			String name, float data, int status, Timestamp gather) {
		super();
		this.srcId = srcId;
		this.dstId = dstId;
		this.devId = devId;
		this.sersorAddress = sersorAddress;
		this.count = count;
		this.cmd = cmd;
		this.name = name;
		this.data = data;
		this.status = status;
		this.gather = gather;
	}

	public Environment() {
		super();
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getDstId() {
		return dstId;
	}

	public void setDstId(String dstId) {
		this.dstId = dstId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getSersorAddress() {
		return sersorAddress;
	}

	public void setSersorAddress(String sersorAddress) {
		this.sersorAddress = sersorAddress;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getData() {
		return data;
	}

	public void setData(float data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getGather() {
		return gather;
	}

	public void setGather(Timestamp gather) {
		this.gather = gather;
	}

	@Override
	public String toString() {
		return "Environment[srcId:"+srcId+",dstId:"+dstId+",devId:"+devId+
				",sersorAddress:"+sersorAddress+",count:"+count+",cmd:"+cmd+
				",name:"+name+",data:"+data+",status:"+status+",gather:"+gather+"]";
	}


	
	
}
