package bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �����¶ȡ�ʪ�ȡ�����ǿ�ȡ�������̼Ũ��
 * @author liangyumim
 * @created 2018��9��20�� ����2:27:20
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
	 * @param srcId ���Ͷ�id
	 * @param dstId ��ݮ��ϵͳid
	 * @param devId ʵ��������ģ��id(1-8)
	 * @param sersorAddress ģ���ϴ�������ַ
	 * @param count ����������
	 * @param cmd ָ����(3��ʾ��Ҫ��������  16��ʾ��Ҫ��������)
	 * @param name ��������
	 * @param data ����
	 * @param status ״̬��ʾ(Ĭ��Ϊ1��ʾ�ɹ�)
	 * @param gather �ɼ�ʱ��(��λʱ��)
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
