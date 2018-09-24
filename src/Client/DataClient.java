package Client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

import bean.Environment;
import util.LogUtil;

public class DataClient {

	private Socket socket;

	// ��ʼ���ͻ��ˣ����ӷ�����
	public DataClient() throws Exception {
		try {
			LogUtil.LOGGER.info("�������ӷ�����...");
			socket = new Socket("localhost", 8088);
			LogUtil.LOGGER.info("�ѳɹ����ӷ�������");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// �����ͻ��ˣ������ݼ��϶������л��������У������͸�������
	public void start(Collection<Environment> list) {
		try {
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			LogUtil.LOGGER.info("���ڷ�������...");
			oos.writeObject(list);
			LogUtil.LOGGER.info("���ݷ�����ϣ�");
		} catch (IOException e) {
			LogUtil.LOGGER.error(e.getMessage()+" : IOException");
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		//�����ͻ��ˣ���������
        try {
			Collection<Environment> list = Gather.analytic(new File("radwtmp"));
			DataClient client = new DataClient();
			client.start(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
